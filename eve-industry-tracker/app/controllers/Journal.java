package controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;

import model.WalletJournal;

import com.beimin.eveapi.account.characters.CharactersParser;
import com.beimin.eveapi.account.characters.CharactersResponse;
import com.beimin.eveapi.account.characters.EveCharacter;
import com.beimin.eveapi.character.wallet.journal.WalletJournalParser;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.wallet.journal.ApiJournalEntry;
import com.beimin.eveapi.shared.wallet.journal.WalletJournalResponse;

import play.mvc.*;

/**
 * NOTE: THIS CODE IS COMPLETELY EXPERIMENTAL AND IS IN NO WAY BEST PRACTICE
 * @author cl05tomp
 *
 */
public class Journal extends Controller {
	
	public static final int MAX_ENTRIES_PER_REQUEST = 2560;
	
	/**
	 * Get new journal entries via API
	 * @throws ApiException
	 * @throws SQLException
	 */
	public void getJournal() throws ApiException {
		int keyCode = 647747;
		String vCode = "ZUytaw1wLfOjUtVkN1THzAMCtthnJPJhn4XDdzpSlFu1V4ZyGdUQsNTZxtYvFe2O";
		ApiAuthorization auth = new ApiAuthorization(keyCode,vCode);
		CharactersParser parser = CharactersParser.getInstance();
		CharactersResponse response = parser.getResponse(auth);
		
		Set<EveCharacter> characters = response.getAll();
		long charID = 0;
		for (EveCharacter character : characters) {
			charID = character.getCharacterID();
		}
		ApiAccess api = new ApiAccess(keyCode, charID, vCode);
		int numEntries = MAX_ENTRIES_PER_REQUEST;
		Long lowestRefId = null;
		
		// Allows for journal walking -- see https://github.com/cl05tomp/eve-industry-tracker/wiki/Wallet-Journal-%22Walking%22
		while (numEntries == MAX_ENTRIES_PER_REQUEST) {
			WalletJournalParser jParser = WalletJournalParser.getInstance();
			WalletJournalResponse jResponse = null;
			if (lowestRefId == null) {
				jResponse = (WalletJournalResponse) api.getResponse(jParser, Long.MAX_VALUE, MAX_ENTRIES_PER_REQUEST);
			} else {
				jResponse = (WalletJournalResponse) api.getResponse(jParser, lowestRefId, MAX_ENTRIES_PER_REQUEST);
			}
			if (jResponse != null) {
				numEntries = jResponse.getAll().size();
				for (ApiJournalEntry j : jResponse.getAll()) {
					Long charId = api.getCharId();
					Long refId = new Long(j.getRefID());
					if (WalletJournal.getWalletJournal(charId, refId) == null) {
						Integer refTypeId = j.getRefType().getId();
						Timestamp date = new Timestamp(j.getDate().getTime());
						Double amount = new Double(j.getAmount());
						Double balance = new Double(j.getBalance());
						WalletJournal entry = new WalletJournal(charId,refId,refTypeId,date,amount,balance,j.getReason());
						entry.save();
						lowestRefId = refId;
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		System.out.println(api.toString());
	}
	
	public static Result viewJournal() throws ApiException, SQLException {
		Journal t = new Journal();
		t.getJournal();
        return ok("Hello!");
    }
}

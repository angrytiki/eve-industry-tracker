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
	
	public void getJournal() throws ApiException, SQLException {
		int keyCode = 2382842;
		String vCode = "BUJPu4zQcZs49AIWpcs5CTD4hwOUUCra9ZuGFsfC2OlaCIktec3jyOCgjro841GG";
		ApiAuthorization auth = new ApiAuthorization(keyCode,vCode);
		CharactersParser parser = CharactersParser.getInstance();
		CharactersResponse response = parser.getResponse(auth);
		
		Set<EveCharacter> characters = response.getAll();
		long charID = 0;
		for (EveCharacter character : characters) {
			charID = character.getCharacterID();
		}
		ApiAccess api = new ApiAccess(keyCode, charID, vCode);
		WalletJournalParser jParser = WalletJournalParser.getInstance();
		WalletJournalResponse jResponse = (WalletJournalResponse) api.getResponse(jParser);
		if (jResponse != null) {
			for (ApiJournalEntry j : jResponse.getAll()) {
				Long charId = api.getCharId();
				Long refId = new Long(j.getRefID());
				Integer refTypeId = j.getRefType().getId();
				Timestamp date = new Timestamp(j.getDate().getTime());
				Double amount = new Double(j.getAmount());
				Double balance = new Double(j.getBalance());
				WalletJournal entry = new WalletJournal(charId,refId,refTypeId,date,amount,balance,j.getReason());
				entry.save();
			}
		}
		System.out.println(api.toString());
		System.out.println();
		DatabaseResult rs = Database.runQuery("SELECT * FROM cache_timer");
		System.out.println(rs);
	}
	
	public static Result viewJournal() throws ApiException, SQLException {
		Journal t = new Journal();
		t.getJournal();
        return ok("Hello!");
    }
}

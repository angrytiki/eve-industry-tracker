package controllers;

import java.util.Set;

import com.beimin.eveapi.account.characters.CharactersParser;
import com.beimin.eveapi.account.characters.CharactersResponse;
import com.beimin.eveapi.account.characters.EveCharacter;
import com.beimin.eveapi.character.wallet.transactions.WalletTransactionsParser;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.wallet.transactions.ApiWalletTransaction;
import com.beimin.eveapi.shared.wallet.transactions.WalletTransactionsResponse;

import play.*;
import play.mvc.*;

import views.html.transactions.*;

public class Transactions extends Controller {
	
	public void getTransactions() throws ApiException {
		int keyCode = 2382842;
		String vCode = "BUJPu4zQcZs49AIWpcs5CTD4hwOUUCra9ZuGFsfC2OlaCIktec3jyOCgjro841GG";
		ApiAuthorization auth = new ApiAuthorization(keyCode,vCode);
		CharactersParser parser = CharactersParser.getInstance();
		CharactersResponse response = parser.getResponse(auth);
		Set<EveCharacter> characters = response.getAll();
		for (EveCharacter character : characters) {
			System.out.println(character.getCharacterID());
		}
		WalletTransactionsParser tParser = WalletTransactionsParser.getInstance();
			WalletTransactionsResponse tResponse = tParser.getResponse(auth, keyCode);
			System.out.println(tResponse.getCachedUntil());
			for (ApiWalletTransaction t : tResponse.getAll()) {
				System.out.println(t.getPrice());
			}
	}
	
	public static Result viewTransactions() throws ApiException {
		Transactions t = new Transactions();
		t.getTransactions();
        return ok(summary.render());
    }
}

package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
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
import play.db.DB;
import play.mvc.*;

import views.html.transactions.*;

import play.db.*;

/**
 * NOTE: THIS CODE IS COMPLETELY EXPERIMENTAL AND IS IN NO WAY BEST PRACTICE
 * @author cl05tomp
 *
 */
public class Transactions extends Controller {
	
	public void getTransactions() throws ApiException, SQLException {
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
		WalletTransactionsParser tParser = WalletTransactionsParser.getInstance();
		//WalletTransactionsResponse tResponse = tParser.getResponse(auth, keyCode);
		//System.out.println(tResponse.getCachedUntil());
		WalletTransactionsResponse tResponse = (WalletTransactionsResponse) api.getResponse(tParser);
		if (tResponse != null) {
			for (ApiWalletTransaction t : tResponse.getAll()) {
				System.out.println(t.getPrice());
			}
		}
		System.out.println(api.toString());
		Connection con = DB.getConnection();
		Statement stmt = null;
		System.out.println();
		//String query1 = "INSERT INTO cache_timers VALUES ("+charID+",'test','"+Database.getDateTime(tResponse.getCachedUntil())+"')";
		String query2 = "SELECT * FROM cache_timers";
		stmt = con.createStatement();
		//boolean q = stmt.execute(query1);
		//if (!q) {
		//	System.out.println("Something bad happened");
		//}
		ResultSet rs = stmt.executeQuery(query2);
		while (rs.next()) {
			System.out.println(rs.getString("charID"));
		}
		stmt.close();
	}
	
	public static Result viewTransactions() throws ApiException, SQLException {
		Transactions t = new Transactions();
		t.getTransactions();
        return ok(summary.render());
    }
}

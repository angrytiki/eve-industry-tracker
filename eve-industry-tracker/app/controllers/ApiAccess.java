package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.db.DB;

import com.beimin.eveapi.account.characters.CharactersParser;
import com.beimin.eveapi.account.characters.CharactersResponse;
import com.beimin.eveapi.character.wallet.transactions.WalletTransactionsParser;
import com.beimin.eveapi.core.AbstractListParser;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.core.ApiListResponse;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.wallet.transactions.WalletTransactionsResponse;

/**
 * Access API and is obeys cache timers. Use this class to access the API instead of directly
 * using ApiAuthorization since this class will check if the data is up to date.
 * @author cl05tomp
 * 
 */
public class ApiAccess {
	
	private static ApiAuthorization m_api;
	private int m_keyCode;
	private String m_vCode;
	
	public ApiAccess(int keyCode, Long charID, String vCode) {
		m_api = new ApiAuthorization(keyCode, charID, vCode);
	}
	
	public ApiListResponse<?> getResponse(AbstractListParser<?,?,?> parser) throws ApiException, SQLException {
		ApiListResponse<?> response = null;
		String requestType = null;
		if (parser instanceof CharactersParser && !isCached("characters")) {
			response = (CharactersResponse)((CharactersParser) parser).getResponse(m_api);
			requestType = "characters";
		} else if (parser instanceof WalletTransactionsParser && !isCached("wallet")) {
			response = (WalletTransactionsResponse)((WalletTransactionsParser) parser).getResponse(m_api, m_keyCode);
			requestType = "wallet";
		}
		if (requestType != null) {
			updateCacheTimer(response.getCachedUntil(),requestType);
		}
		return response;
	}
	
	private void updateCacheTimer(java.util.Date date, String requestType) {
		// Write cache time to DB
		// Either do an update or an insert
	}
	
	/**
	 * Checks if cache timer is up for an API call. Returning true indicates that DB still has most accurate data,
	 * while false indicates to get data from the API. 
	 * @param parserType API call to check
	 * @return false if cache timer is up, true if not
	 * @throws SQLException
	 */
	private boolean isCached(String parserType) throws SQLException {
		boolean cached = false;
		Connection con = DB.getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT cachedUntil FROM cache_timers WHERE charID = " + m_api.getCharacterID() + " AND requestType = '" + parserType+"'";
			ResultSet rs = stmt.executeQuery(query);
			Date cachedDate = null;
			while(rs.next()) {
				cachedDate = rs.getDate("cachedUntil");
			}
			if (cachedDate != null && cachedDate.after(new Date(System.currentTimeMillis()))) {
				cached = true;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return cached;
	}
	
	public ApiAuthorization getAuth() {
		return m_api;
	}
	
	public int getKeyCode() {
		return m_keyCode;
	}
	
	public String vCode() {
		return m_vCode;
	}
	
	@Override
	public String toString() {
		return m_api.toString();
	}
}

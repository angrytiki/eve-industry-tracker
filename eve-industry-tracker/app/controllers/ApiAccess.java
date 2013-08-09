package controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.CacheTimer;

import com.beimin.eveapi.account.characters.CharactersParser;
import com.beimin.eveapi.account.characters.CharactersResponse;
import com.beimin.eveapi.character.wallet.journal.WalletJournalParser;
import com.beimin.eveapi.core.AbstractListParser;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.core.ApiListResponse;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.wallet.journal.WalletJournalResponse;

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
	
	/**
	 * Retrieves API information from CCP's servers. Obeys cache timers.
	 * @param parser proper parser for intended API call
	 * @return API response information
	 * @throws ApiException
	 */
	public ApiListResponse<?> getResponse(AbstractListParser<?,?,?> parser) throws ApiException {
		ApiListResponse<?> response = null;
		boolean cached = true;
		String requestType = null;
		if (parser instanceof CharactersParser && !(cached = isCached("characters"))) {
			response = (CharactersResponse)((CharactersParser) parser).getResponse(m_api);
			requestType = "characters";
		} else if (parser instanceof WalletJournalParser && !(cached = isCached("wallet"))) {
			response = (WalletJournalResponse)((WalletJournalParser) parser).getResponse(m_api, m_keyCode);
			requestType = "wallet";
		}
		if (requestType != null && !cached) {
			updateCacheTimer(response.getCachedUntil(),requestType);
		}
		return response;
	}
	
	/**
	 * Updates the timer stored in the database, or creates a new row if a previous entry can not be found.
	 * @param date the date/time the API call is cached until
	 * @param requestType which API call is being stored
	 */
	private void updateCacheTimer(java.util.Date date, String requestType) {
		Timestamp cachedUntil = new Timestamp(date.getTime());
		CacheTimer timer = CacheTimer.getCacheTimer(m_api.getCharacterID(), requestType);
		if (timer != null) {
			timer.setCacheduntil(cachedUntil);
		} else {
			timer = new CacheTimer(m_api.getCharacterID(),requestType,cachedUntil);
		}
		timer.save();
	}
	
	/**
	 * Checks if cache timer is up for an API call. Returning true indicates that DB still has most accurate data,
	 * while false indicates to get data from the API. 
	 * @param requestType API call to check
	 * @return false if cache timer is up, true if not
	 * @throws SQLException
	 */
	private boolean isCached(String requestType) {
		CacheTimer timer = CacheTimer.getCacheTimer(m_api.getCharacterID(), requestType);
		if (timer != null) {
			Date cachedDate = new Date(timer.getCacheduntil().getTime());
			if (cachedDate.after(new Date(System.currentTimeMillis()))) {
				System.out.println("It's cached");
				return true;
			}
		}
		return false;
	}
	
	public ApiAuthorization getAuth() {
		return m_api;
	}
	
	public Long getCharId() {
		return m_api.getCharacterID();
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

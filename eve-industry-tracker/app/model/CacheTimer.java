package model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * Represents timer expirations on API calls. Keeps track of when cache timer is over.
 */
@Entity
@Table(name="cache_timer")
public class CacheTimer extends Model {

	private static final long serialVersionUID = 7961795752261901445L;

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@Required
	public Long charid;
	@Required
	@MaxLength(20)
	public String requesttype;
	@Required
	public Timestamp cacheduntil;
	
	/**
	 * @param charid character ID
	 * @param requesttype API call request type
	 * @param cacheduntil request call cache timestamp
	 */
	public CacheTimer(Long charid, String requesttype, Timestamp cacheduntil) {
		this.charid = charid;
		this.requesttype = requesttype;
		this.cacheduntil = cacheduntil;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Finder<Long,CacheTimer> find = new Finder(
			Long.class, CacheTimer.class
	);
	
	public static List<CacheTimer> all() {
		return find.all();
	}
	
	public static void create(CacheTimer timer) {
		timer.save();
	}
	
	/**
	 * Deletes a cache timer based on primary key
	 * @param id
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Deletes all cache timers in the table
	 */
	public static void deleteAll() {
		List<CacheTimer> all = find.all();
		for (CacheTimer timer : all) {
			find.ref(timer.getId()).delete();
		}
	}
	
	/**
	 * Gets a character's list of cache timers
	 * @param charID character ID
	 * @return list of cache timers belonging to that character
	 */
	public static List<CacheTimer> findByCharId(Long charID) {
		return find.where().eq("charid", charID).findList();
	}
	
	/**
	 * Gets a cache timer based on character ID and request type
	 * @param charID character ID
	 * @param requestType API call type
	 * @return CacheTimer representing row in DB or null if doesn't exist
	 */
	public static CacheTimer getCacheTimer(Long charID, String requestType) {
		return find.where().eq("charid", charID).conjunction().eq("requesttype", requestType).findUnique();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCharid() {
		return charid;
	}

	public void setCharid(Long charid) {
		this.charid = charid;
	}

	public String getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}

	public Timestamp getCacheduntil() {
		return cacheduntil;
	}

	public void setCacheduntil(Timestamp cacheduntil) {
		this.cacheduntil = cacheduntil;
	}
	
	public String toString() {
		return id + " " + charid;
	}
}

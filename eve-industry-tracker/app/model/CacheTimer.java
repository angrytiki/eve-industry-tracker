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
	
	public CacheTimer() {
		
	}
	
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
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void deleteAll() {
		List<CacheTimer> all = find.all();
		for (CacheTimer timer : all) {
			find.ref(timer.getId()).delete();
		}
	}
	
	public static List<CacheTimer> findByCharId(Long charID) {
		return find.where().eq("charid", charID).findList();
	}
	
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

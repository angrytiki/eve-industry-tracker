package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name="cache_timer")
public class CacheTimer extends Model {

	private static final long serialVersionUID = 7961795752261901445L;

	@Id public Long id;
	
	@Required
	public Integer charid;
	@Required
	@MaxLength(20)
	public String requesttype;
	@Required
	public Date cacheduntil;
	
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
	
	
}

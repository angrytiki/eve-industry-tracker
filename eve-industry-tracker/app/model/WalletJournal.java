package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;
import play.data.validation.Constraints.*;

@Entity
@Table(name="wallet_journal")
public class WalletJournal extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1510229945765102647L;
	
	@Id public long id;
	
	@Required
	public Integer charid;
	@Required
	public Integer refid;
	@Required
	public Integer reftypeid;
	@Required
	public Date date;
	@Required
	public Double amount;
	@Required
	public Double balance;
	
	@MaxLength(128)
	public String reason;
	
	public static Finder<Long,WalletJournal> find = new Finder(
			Long.class, WalletJournal.class
	);
	
	public static List<WalletJournal> all() {
		return find.all();
	}
	
	public static void create(WalletJournal journal) {
		journal.save();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}

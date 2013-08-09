package model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;
import play.data.validation.Constraints.*;

/**
 * Represents an entry in a user's wallet journal.
 */
@Entity
@Table(name="wallet_journal")
public class WalletJournal extends Model {
	
	private static final long serialVersionUID = -5229839649593687902L;

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@Required
	public Long charid;
	@Required
	public Long refid;
	@Required
	public Integer reftypeid;
	@Required
	@Column(name="timestamp")
	public Timestamp timestamp;
	@Required
	public Double amount;
	@Required
	public Double balance;
	@MaxLength(128)
	public String reason;
	
	
	
	/**
	 * @param charid character ID
	 * @param refid reference ID
	 * @param reftypeid reference type ID
	 * @param timestamp timestamp of journal entry
	 * @param amount amount of entry
	 * @param balance wallet balance
	 * @param reason why entry happened (possibly null)
	 */
	public WalletJournal(Long charid, Long refid,
			Integer reftypeid, Timestamp date, Double amount, Double balance,
			String reason) {
		this.charid = charid;
		this.refid = refid;
		this.reftypeid = reftypeid;
		this.timestamp = date;
		this.amount = amount;
		this.balance = balance;
		this.reason = reason;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	public Long getRefid() {
		return refid;
	}

	public void setRefid(Long refid) {
		this.refid = refid;
	}

	public Integer getReftypeid() {
		return reftypeid;
	}

	public void setReftypeid(Integer reftypeid) {
		this.reftypeid = reftypeid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setDate(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}

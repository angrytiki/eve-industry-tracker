package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;
import play.data.validation.Constraints.*;

@Entity
@Table(name="wallet_journal")
public class WalletJournal extends Model {
	
	private static final long serialVersionUID = -5229839649593687902L;

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
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

	public Integer getCharid() {
		return charid;
	}

	public void setCharid(Integer charid) {
		this.charid = charid;
	}

	public Integer getRefid() {
		return refid;
	}

	public void setRefid(Integer refid) {
		this.refid = refid;
	}

	public Integer getReftypeid() {
		return reftypeid;
	}

	public void setReftypeid(Integer reftypeid) {
		this.reftypeid = reftypeid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

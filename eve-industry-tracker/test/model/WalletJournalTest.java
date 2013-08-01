package model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.avaje.ebean.Ebean;

public class WalletJournalTest extends BaseModelTest {
	@Test
	public void can_save_to_database() {
		WalletJournal wj = new WalletJournal();

		wj.setId(1L);
		wj.setCharid(1);
		wj.setRefid(1);
		wj.setReftypeid(1);
		wj.setDate(java.sql.Date.valueOf("2013-07-31"));
		wj.setAmount(100.0);
		wj.setBalance(100.0);
		wj.setReason("Because");
		
		wj.save();
		
		assertTrue(WalletJournal.all().size() == 1);
	}
	
	@Test
	public void can_delete_from_database() {
		can_save_to_database();
		
		WalletJournal.delete((long) 1);
		
		assertTrue(WalletJournal.all().size() == 0);
	}
	
	@Test
	public void can_update() {
		WalletJournal wj = new WalletJournal();
		
		wj.setId(1L);
		wj.setCharid(1);
		wj.setRefid(1);
		wj.setReftypeid(1);
		wj.setDate(java.sql.Date.valueOf("2013-07-31"));
		wj.setAmount(101.0);
		wj.setBalance(100.0);
		wj.setReason("Because");
		wj.save();
		wj.setAmount(25.0);
		wj.update(wj.id);
		WalletJournal wj2 = WalletJournal.all().get(0);
		assertTrue(wj.amount.equals(wj2.amount));
	}
}

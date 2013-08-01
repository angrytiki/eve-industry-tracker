package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class WalletJournalTest extends BaseModelTest {
	@Test
	public void can_save_to_database() {
		WalletJournal wj = new WalletJournal();

		wj.id = 1L;
		wj.charid = 1;
		wj.refid = 1;
		wj.reftypeid = 1;
		wj.date = java.sql.Date.valueOf("2013-07-31");
		wj.amount = 100.0;
		wj.balance = 100.0;
		wj.reason = "Because";
		
		wj.save();
		
		assertTrue(WalletJournal.all().size() == 1);
	}
	
	@Test
	public void can_delete_from_database() {
		can_save_to_database();
		
		WalletJournal.delete((long) 1);
		
		assertTrue(WalletJournal.all().size() == 0);
	}
}

package model;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;


public class WalletJournalTest extends BaseModelTest {
	@Test
	public void can_save_to_database() {
		WalletJournal wj = new WalletJournal(1L,1L,1,Timestamp.valueOf("2013-07-31 00:00:00"),100.0,100.0,"Because");
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
		WalletJournal wj = new WalletJournal(1L,1L,1,Timestamp.valueOf("2013-07-31 00:00:00"),101.0,100.0,"Because");
		wj.save();
		
		wj.setAmount(25.0);
		wj.update(wj.id);
		
		WalletJournal wj2 = WalletJournal.all().get(0);
		assertTrue(101.0 != wj2.amount);
	}
}

package model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class CacheTimerTest extends BaseModelTest {
	@Test
	public void canSaveDatabase() {
		CacheTimer timer = new CacheTimer();
		timer.id = 1L;
		timer.charid = 1;
		timer.requesttype = "wallet";
		timer.cacheduntil = new Date(System.currentTimeMillis());
		
		timer.save();
		
		assertTrue(timer.all().size() == 1);
	}
	
	@Test
	public void testRequestConstraint() {
		CacheTimer timer = new CacheTimer();
		timer.id = 1L;
		timer.charid = 1;
		timer.requesttype = "test";
		timer.cacheduntil = new Date(System.currentTimeMillis());
		
		timer.save();
	}
}

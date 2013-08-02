package model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class CacheTimerTest extends BaseModelTest {
	
	public long m_id = 1;
	
	@Test
	public void canSaveDatabase() {
		CacheTimer timer = new CacheTimer();
		timer.setId(++m_id);
		timer.charid = 1;
		timer.requesttype = "wallet";
		timer.cacheduntil = new Date(System.currentTimeMillis());
		timer.save();
		
		System.out.println(CacheTimer.all().size());
		
		for (CacheTimer t : CacheTimer.all()) {
			System.out.println(t.toString());
		}
		
		assertTrue(CacheTimer.all().size() == 1);
	}
	
	@Test(expected=javax.persistence.PersistenceException.class)
	public void testRequestConstraint() {
		CacheTimer timer = createNewCacheTimer();
		timer.requesttype = "test";
		
		timer.save();
	}
	
	@Test
	public void canUpdate() {
		CacheTimer timer = createNewCacheTimer();
		timer.save();
		
		CacheTimer timer2 = CacheTimer.all().get(0);
		assertTrue(timer2.requesttype.equals("wallet"));
		
		timer.setRequesttype("character");
		timer.update(timer.id);
		
		assertTrue(CacheTimer.all().get(0).requesttype.equals("character"));
		
		CacheTimer.delete(timer.getId());
	}
	
	@Test
	public void canDelete() {
		CacheTimer timer = createNewCacheTimer();
		timer.save();
		
		CacheTimer.delete(timer.getId());
		assertTrue(CacheTimer.all().size() == 0);
	}
	
	public CacheTimer createNewCacheTimer() {
		CacheTimer timer = new CacheTimer();
		timer.setId(++m_id);
		timer.charid = 1;
		timer.requesttype = "wallet";
		timer.cacheduntil = new Date(System.currentTimeMillis());
		return timer;
	}
}

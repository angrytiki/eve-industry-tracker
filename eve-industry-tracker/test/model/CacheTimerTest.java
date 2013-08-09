package model;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

public class CacheTimerTest extends BaseModelTest {
	
	public long m_id = 1;
	
	@Test
	public void canSaveDatabase() {
		CacheTimer.deleteAll();
		CacheTimer timer = new CacheTimer(1L,"wallet",new Timestamp(System.currentTimeMillis()));
		timer.save();
		
		assertTrue(CacheTimer.all().size() == 1 && CacheTimer.all().get(0).getCharid() == timer.getCharid());
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
	
	@Test
	public void canFindByCharId() {
		CacheTimer timer = new CacheTimer(432L,"character",new Timestamp(System.currentTimeMillis()));
		timer.save();
		
		assertTrue(CacheTimer.findByCharId(timer.getCharid()).size() > 0);
	}
	
	public CacheTimer createNewCacheTimer() {
		CacheTimer timer = new CacheTimer(1L,"wallet",new Timestamp(System.currentTimeMillis()));
		return timer;
	}
}

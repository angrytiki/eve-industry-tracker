import org.junit.*;

import controllers.MarketData;

import java.util.*;

public class MarketDataTest {
	
	@Test
	public void testGetMarketData(){
		ArrayList<Integer> whateverYouWant = new ArrayList<Integer>();
		whateverYouWant.add(34);
		MarketData.getMarketData(whateverYouWant);
		whateverYouWant.add(481516);
		MarketData.getMarketData(whateverYouWant);
		ArrayList<Integer> regionList = new ArrayList<Integer>();
		regionList.add(1234);
		regionList.add(4321);
		MarketData.getMarketData(whateverYouWant, regionList);
		Integer useSys = new Integer(42);
		MarketData.getMarketData(whateverYouWant, useSys);
	}
	
	
}

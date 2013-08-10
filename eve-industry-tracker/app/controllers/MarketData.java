package controllers;

import java.util.List;

public class MarketData {
	
	public static final String EVE_CENTRAL_URI = "http://api.eve-central.com/api/";
	
	public static List<MarketStat> getMarketData(Integer h, List<Integer> t, Long m, List<Integer> r, Integer u){
		String url = EVE_CENTRAL_URI + "marketstat?";
		if(h != null){
			url += "hours=" + h + "&";
		}
		
		for(int i = 0; i < t.size(); i++){
			Integer typeId = t.get(i);
			url += "typeid=" + typeId;
			if (i + 1 < t.size()){
				url += "&";
			}
		}
		if(m != null){
			url += "&" + "minQ=" + m;
		}
		if(r != null){
			for(Integer regionLimit : r){
				url += "&" + "regionlimit=" + regionLimit;
			}
		}
		if(u != null){
			url += "&" + "usesystem=" + u;
		}
		System.out.println(url);
		return null;
	
	}
	
	public static List<MarketStat> getMarketData(List<Integer> t){
		
		List<Integer> typeid = t;
		return getMarketData(null, typeid, null, null, null);
		
	}
	
	public static List<MarketStat> getMarketData(List<Integer> t, List<Integer> r){
		
		List<Integer> typeid = t;
		List<Integer> regionlimit = r;
		return getMarketData(null, typeid, null, regionlimit, null);
		
	}
	
	public static List<MarketStat> getMarketData(List<Integer> t, Integer u){
		
		List<Integer> typeid = t;
		Integer usesystem = u;
		return getMarketData(null, typeid, null, null, usesystem);
		
	}
	

	
}

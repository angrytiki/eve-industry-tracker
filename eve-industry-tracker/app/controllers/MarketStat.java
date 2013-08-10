package controllers;

public class MarketStat {
	
	private int type;
	private MarketResult buy;
	private MarketResult sell;
	private MarketResult all;
	public MarketStat(int type, MarketResult buy, MarketResult sell, MarketResult all) {
		this.type = type;
		this.buy = buy;
		this.sell = sell;
		this.all = all;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public MarketResult getBuy() {
		return buy;
	}
	public void setBuy(MarketResult buy) {
		this.buy = buy;
	}
	public MarketResult getSell() {
		return sell;
	}
	public void setSell(MarketResult sell) {
		this.sell = sell;
	}
	public MarketResult getAll() {
		return all;
	}
	public void setAll(MarketResult all) {
		this.all = all;
	}
	
	
}

package controllers;

public class MarketResult {
	private String type;
	private long volume;
	private double avg;
	private double max;
	private double min;
	private double stddev;
	private double median;
	private double percentile;
	public MarketResult(String t, long v, double a, double ma, double mi, double sd, double me, double perc) {
		type = t;
		volume = v;
		avg = a;
		max = ma;
		min = mi;
		stddev = sd;
		median = me;
		percentile = perc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getStddev() {
		return stddev;
	}
	public void setStddev(double stddev) {
		this.stddev = stddev;
	}
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}
	public double getPercentile() {
		return percentile;
	}
	public void setPercentile(double percentile) {
		this.percentile = percentile;
	}
	
}

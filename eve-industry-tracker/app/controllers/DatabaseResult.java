package controllers;

import java.util.ArrayList;

public class DatabaseResult {
	private ArrayList<ArrayList<Object>> m_results;
	
	public void addResult(ArrayList<Object> row) {
		m_results.add(row);
	}
	
	public int numResults() {
		return m_results.size();
	}
	
}

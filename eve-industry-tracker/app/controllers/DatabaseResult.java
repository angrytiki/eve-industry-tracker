package controllers;

import java.util.ArrayList;

public class DatabaseResult {
	private ArrayList<ArrayList<Object>> m_results;
	private int m_numResults;
	
	public DatabaseResult() {
		m_results = new ArrayList<ArrayList<Object>>();
	}
	
	public void addRow(ArrayList<Object> row) {
		m_results.add(row);
	}
	
	public ArrayList<Object> getRow(int rowNum) {
		return m_results.get(rowNum);
	}
	
	public int numCols() {
		if (m_results != null && m_results.get(0) != null) {
			return m_results.get(0).size();
		} else {
			return 0;
		}
	}
	
	public int numRows() {
		if (m_results != null) {
			return m_results.size();
		} else {
			return m_numResults;
		}
	}
	
	public void setNumResults(int numResults) {
		m_numResults = numResults;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < numRows(); i++) {
			sb.append("[");
			ArrayList<Object> row = getRow(i);
			for (int j = 0; j < row.size(); j++) {
				sb.append(row.get(j));
				if (j+1 < row.size()) {
					sb.append(",");
				}
			}
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}

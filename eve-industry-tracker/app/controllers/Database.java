package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import play.db.DB;

public class Database {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static Connection m_con;
	
	public static DatabaseResult runQuery(String query) throws SQLException {
		return runQuery(query, null);
	}
	
	public static DatabaseResult runQuery(String query, ArrayList<Object> arr) throws SQLException {
		m_con = DB.getConnection();
		PreparedStatement stmt = setParams(query,arr);
		ResultSet results = null;
		
		results = stmt.executeQuery();
		DatabaseResult r = new DatabaseResult();
		while (results.next()) {
			ArrayList<Object> objs = new ArrayList<Object>();
			ResultSetMetaData rsmd = results.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				Object obj = results.getObject(i); 
				objs.add(obj);
				System.out.println(obj);
			}
			r.addRow(objs);
		}
		results.close();
		stmt.close();
		m_con.close();
		return r;
	}
	
	public static DatabaseResult runUpdateQuery(String query) throws SQLException {
		return runUpdateQuery(query, null);
	}
	
	public static DatabaseResult runUpdateQuery(String query, ArrayList<Object> params) throws SQLException {
		m_con = DB.getConnection();
		PreparedStatement stmt = setParams(query, params);
		
		int numResults = stmt.executeUpdate();
		DatabaseResult dr = new DatabaseResult();
		dr.setNumResults(numResults);
		return dr;
	}
	
	public static String getDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}
	
	private static PreparedStatement setParams(String query, ArrayList<Object> params) throws SQLException {
		PreparedStatement stmt = m_con.prepareStatement(query);
		int index = 1;
		if (params != null) {
			for (Object obj : params) {
				stmt.setObject(index, obj);
				index++;
			}
		}
		return stmt;
	}
}

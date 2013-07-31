package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import play.db.DB;

public class Database {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public Connection m_con;
	
	public Database() {
		m_con = DB.getConnection();
	}
	
	public ArrayList<ArrayList<Object>> runQuery(String query) throws SQLException {
		return runQuery(query, null);
	}
	
	public ArrayList<ArrayList<Object>> runQuery(String query, ArrayList<Object> arr) throws SQLException {
		PreparedStatement stmt = m_con.prepareStatement(query);
		ResultSet results = null;
		int index = 0;
		if (arr != null) {
			for (Object obj : arr) {
				stmt.setObject(index, obj);
				index++;
			}
		}
		results = stmt.executeQuery();
		ArrayList<ArrayList<Object>> r = new ArrayList<ArrayList<Object>>();
		while (results.next()) {
			ArrayList<Object> objs = new ArrayList<Object>();
			for (int i = 0; i < results.getFetchSize(); i++) {
				Object obj = results.getObject(i); 
				objs.add(obj);
				System.out.println(obj);
			}
			r.add(objs);
		}
		results.close();
		stmt.close();
		return r;
	}
	
	public static String getDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}
}

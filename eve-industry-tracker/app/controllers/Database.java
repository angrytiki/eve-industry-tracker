package controllers;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.db.DB;

public class Database {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public Connection m_con;
	
	public Database() {
		m_con = DB.getConnection();
	}
	
	public void runQuery(String query) {
		
	}
	
	public static String getDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}
}

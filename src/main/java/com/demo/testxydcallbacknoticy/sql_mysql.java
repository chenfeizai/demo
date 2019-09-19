package com.demo.testxydcallbacknoticy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sql_mysql {
//	private final static String driver="com.mysql.cj.jdbc.Driver"; 
	private final static String driver="com.mysql.jdbc.Driver"; 
	private final static String connection="jdbc:mysql://10.148.181.129:3306/dfpay?useUnicode=true&characterEncoding=utf8&autoReconnect=true"; 
    
	private String SlmSqlselectrow ;
	private int SlmSqluptrow = 0;
	public String MySqlsel(String MySqlsel, String MySqlselfiled) {
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connection, "hbank_w", "hbank_w");
			st = conn.createStatement();
			rs = st.executeQuery(MySqlsel);
			rs.next();
			SlmSqlselectrow = rs.getString(MySqlselfiled);	
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return SlmSqlselectrow;

	}
	
	
	public String MySqlsel() {

		return SlmSqlselectrow;

	}

	

	public int MySqlupt(String MySqlupt) {
		Statement st = null;
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connection, "hbank_w", "hbank_w");
			st = conn.createStatement();
			SlmSqluptrow = st.executeUpdate(MySqlupt);
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();;
		} return SlmSqluptrow;
		

	}

	// ���·���
	public int MySqlupt() {

		return SlmSqluptrow;

	}

}
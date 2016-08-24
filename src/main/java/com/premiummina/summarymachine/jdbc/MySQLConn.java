package com.premiummina.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQL 커넥션 연결
 * 
 * @author premiumMina
 * created on 2016. 7. 29.
 */
public class MySQLConn {
	private Connection conn;
	private static boolean isDBOpend;

	public Connection getDBConnection() {
		try {
			/* 드라이버 로딩 : DriverManager에 등록한다. */
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/userinfo?useSSL=false&autoReconnect=true";
			String username = "root";
			String password = "root";

			conn = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("데이터베이스를 사용하지 않습니다. :" + e);
			isDBOpend = false;
		}
		return conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static boolean isDBOpend() {
		return isDBOpend;
	}

	public static void setDBOpend(boolean isDBOpend) {
		MySQLConn.isDBOpend = isDBOpend;
	}
}
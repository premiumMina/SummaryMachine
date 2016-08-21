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

	public Connection getDBConnection() {
		try {
			/* 드라이버 로딩 : DriverManager에 등록한다. */
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/userinfo?useSSL=false&autoReconnect=true";
			String username = "root";
			String password = "root";

			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("MySQLConn에서 Database를 사용할 수 없습니다. :" + e);
		}
		return conn;
	}
}
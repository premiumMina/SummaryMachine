package com.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConn {
	private UserInsertDAO userInsertDAO;
	private Connection con;

	public MySQLConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// 드라이버로딩 : DriverManager에 등록
			String url = "jdbc:mysql://localhost/premium";
			String username = "root";
			String password = "rlaxod";

			con = DriverManager.getConnection(url, username, password);

			userInsertDAO = new UserInsertDAO();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("ClassNotFoundExcpetion:");
		}
	}

}

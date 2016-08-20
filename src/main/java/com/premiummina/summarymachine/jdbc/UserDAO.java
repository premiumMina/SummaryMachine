package com.premiummina.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 데이터 엑세스 오브젝트(DAO)
 * 
 * @author premiumMina
 * created on 2016. 8. 1.
 */
public class UserDAO {
	private Statement stmt = null;
	private Connection conn = null;

	public UserDAO() {
		conn = new MySQLConn().getDBConnection();
	}
	
	/**
	 * 사용자가 검색한 히스토리를 데이터베이스에 저장한다.
	 * 
	 * @author premiumMina
	 * created on 2016. 7. 22.
	 */
	public void insertHistory(String userid, String filepath, String content, String keyword, double accuracy) {
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String searchdate = sdf.format(d);
			stmt = (Statement) conn.createStatement();

			String sql = "insert into userinfo (userid, searchdate, filepath, content, keyword, accuracy) values(" + "'"
					+ userid + "','" + searchdate + "','" + filepath + "','" + content + "','" + keyword + "','"
					+ accuracy + "');";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
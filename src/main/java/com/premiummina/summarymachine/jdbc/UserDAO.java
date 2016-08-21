package com.premiummina.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private String[][] data;
	
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

			pstmt = conn.prepareStatement("insert into userinfo "
					+ "(userid, searchdate, filepath, content, keyword, accuracy)" + "values(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, userid);
			pstmt.setString(2, searchdate);
			pstmt.setString(3, filepath);
			pstmt.setString(4, content);
			pstmt.setString(5, keyword);
			pstmt.setDouble(6, accuracy);

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.err.println(e);
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	}

	public String[][] historyGet() {
		try {
			conn = new MySQLConn().getDBConnection();
			stmt = (Statement) conn.createStatement();

			String rowcountsql = "SELECT COUNT(*) AS rowcount FROM userinfo;";
			ResultSet rs = null;
			rs = stmt.executeQuery(rowcountsql);
			rs.next();
			int count = rs.getInt("rowcount") - 1;

			String sql = "SELECT * FROM userinfo.userinfo ORDER BY searchdate ASC;";
			rs = null;
			rs = stmt.executeQuery(sql);

			data = new String[count + 1][6];

			for (int index = 0; index <= count; index++) {
				data[index][0] = new String();
				data[index][1] = new String();
				data[index][2] = new String();
				data[index][3] = new String();
				data[index][4] = new String();
				data[index][5] = new String();
			}

			while (rs.next()) {
				data[count][0] = rs.getString("userid");
				data[count][1] = rs.getString("searchdate");
				data[count][2] = rs.getString("filepath");
				data[count][3] = rs.getString("content");
				data[count][4] = rs.getString("keyword");
				data[count][5] = rs.getString("accuracy");
				count--;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
		return data;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

}
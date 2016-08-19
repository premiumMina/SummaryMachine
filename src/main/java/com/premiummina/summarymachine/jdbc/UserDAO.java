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
	private String id;
	private String searchdate;
	private String content;
	private String keyword;
	private String accuracy;
	private String filepath;
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
	public void insertHistory(String id, String filepath, String content, String keyword, double accuracy) {
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			searchdate = sdf.format(d);
			stmt = conn.createStatement();
			
			System.out.println(content);
			content.replaceAll("\"", "");
			System.out.println(content);
			content.replaceAll("\'", "");
			System.out.println(content);
			
			String sql = "INSERT INTO userinfo.userinfo (userId, dateTime, filepath, content, keyword, accuracy) VALUES("
					+ "'" + id + "','" + searchdate + "','" + filepath + "','" + content + "','" + keyword + "','"
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

	public String getFilePath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return searchdate;
	}

	public void setDate(String date) {
		this.searchdate = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
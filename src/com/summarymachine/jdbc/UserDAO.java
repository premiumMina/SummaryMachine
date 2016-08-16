package com.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 연결하는 부분만 따로 만들어? 왜? 매번연결하니까 creat와 결과를 만드는것을 만든다. db는 시작할때 마다
* 연결해야대. 근데 사실 연결하는것 딱 한번만 호출..? 생성자 이 클래스 -> db연결하는 것 한클래스로 생성자로
* 연결한다. 근데 db가 모든 곳에... 없으면 text파일로 쓸수있지. connection
*/
public class UserDAO {
	private String id;
	private String searchDate;
	private String content;
	private String keyword;
	private String accuracy;
	private String fileFath;
	private Statement stmt = null;
	private Connection conn = null;

	public UserDAO() {
		conn = new MySQLConn().getDBConnection();
	}

	public void insertHistory(String id, String fileFath, String content, String keyword, double accuracy) {
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			searchDate = sdf.format(d);
			stmt = conn.createStatement();
			
			System.out.println(content);
			content.replaceAll("\"", "");
			System.out.println(content);
			content.replaceAll("\'", "");
			System.out.println(content);
			
			String sql = "INSERT INTO userinfo.userinfo (userId, dateTime, fileFath, content, keyword, accuracy) VALUES("
					+ "'" + id + "','" + searchDate + "','" + fileFath + "','" + content + "','" + keyword + "','"
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

	public String getFilFath() {
		return fileFath;
	}

	public void setFilFath(String fileFath) {
		this.fileFath = fileFath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return searchDate;
	}

	public void setDate(String date) {
		this.searchDate = date;
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
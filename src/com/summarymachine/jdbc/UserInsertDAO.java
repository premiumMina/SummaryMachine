package com.summarymachine.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.UserFrequencyPanel;

/*
* 연결하는 부분만 따로 만들어? 왜? 매번연결하니까 creat와 결과를 만드는것을 만든다. db는 시작할때 마다
* 연결해야대. 근데 사실 연결하는것 딱 한번만 호출..? 생성자 이 클래스 -> db연결하는 것 한클래스로 생성자로
* 연결한다. 근데 db가 모든 곳에... 없으면 text파일로 쓸수있지. connection
*/
public class UserInsertDAO {
	private String id;
	private String date;
	private String content;
	private String keyword;
	private String accuracy;
	private String fileFath;
	static int dbLine;

	public static int getDbLine() {
		return dbLine;
	}

	public static void setDbLine(int dbLine) {
		UserInsertDAO.dbLine = dbLine;
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
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	private Statement stmt = null;
	private Connection con = null;

	public UserInsertDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/premium", "root", "rlaxod");
			stmt = (Statement) con.createStatement();

			String sql = "select * from sysinfo;";
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dbLine++;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public UserInsertDAO(String id, String fileFath, String content, String keyword, String accuracy) {
		try {
	
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String date = dayTime.format(new Date(time));
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/premium", "root", "rlaxod");
			
			stmt = (Statement) con.createStatement();
			String sql = "INSERT INTO sysinfo (id, DATE, file_path, content, keyword, accuracy) VALUES(" + "'" + id
					+ "','" + date + "','" + fileFath + "','" +content + "','" + keyword + "','" + accuracy
					+ "');";

			stmt.executeUpdate(sql);

			// 테이블 페이지 만들기

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}

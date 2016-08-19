package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.premiummina.summarymachine.jdbc.MySQLConn;
import com.premiummina.summarymachine.jdbc.UserDAO;

/**
 * 사용자 히스토리 출력 패널
 * 
 * @author premiumMina
 * created on 2016. 8. 2.
 */
public class UserHistoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Statement stmt = null;
	private Connection conn;
	private UserDAO userDAO;
	Object rowData[][];
	public UserHistoryPanel(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 150));
		historyChart();
	}
	public void historyChart() {
		try {
			conn = new MySQLConn().getDBConnection();
			stmt = (Statement) conn.createStatement();

			String sql = "select * from userinfo.userinfo;";
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			// getUserInsertDAO().getDbLine() -> 디비 총 갯수
			int count = 0;
			/* 행에 데이터베이스에 있는 줄 만큼의 값을 넣어야 하는데... */
			String[][] input = new String[10][6];
			while (rs.next()) {
				input[count][0] = rs.getString("userid");
				input[count][1] = rs.getString("searchdate");
				input[count][2] = rs.getString("filepath");
				input[count][3] = rs.getString("content");
				input[count][4] = rs.getString("keyword");
				input[count][5] = rs.getString("accuracy");
				count++;

				final String columnNames[] = { "UserID", "Date", "File Path", "Content", "Keyword", "Accuracy" };
				final JTable table = new JTable(input, columnNames);
				JScrollPane scrollPane = new JScrollPane(table);
				add(scrollPane, BorderLayout.CENTER);
				
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setUserDAO(UserDAO userInsertDAO) {
		this.userDAO = userInsertDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
}
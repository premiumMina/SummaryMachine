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

	public UserHistoryPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 150));
		historyChart();
	}

	public void historyChart() {
		try {
			conn = new MySQLConn().getDBConnection();
			stmt = (Statement) conn.createStatement();

			String rowcountsql = "select count(*) as rowcount from userinfo;";
			ResultSet rs = null;
			rs = stmt.executeQuery(rowcountsql);
			rs.next();
			int count = rs.getInt("rowcount") - 1;

			String sql = "select * from userinfo.userinfo;";
			rs = null;
			rs = stmt.executeQuery(sql);

			String[][] data = new String[count + 1][6];

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

			final String columnNames[] = { "UserID", "Date", "File Path", "Content", "Keyword", "Accuracy" };
			final JTable table = new JTable(data, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
			rs.close();
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
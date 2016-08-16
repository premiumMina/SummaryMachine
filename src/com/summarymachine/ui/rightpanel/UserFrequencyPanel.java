package com.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.summarymachine.jdbc.MySQLConn;
import com.summarymachine.jdbc.UserInsertDAO;

public class UserFrequencyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Statement stmt = null;
	private Connection conn;
	private UserInsertDAO userInsertDAO;
	Object rowData[][];

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setUserInsertDAO(UserInsertDAO userInsertDAO) {
		this.userInsertDAO = userInsertDAO;
	}

	public UserFrequencyPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 150));
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
				input[count][0] = rs.getString("userId");
				input[count][1] = rs.getString("dateTime");
				input[count][2] = rs.getString("fileFath");
				input[count][3] = rs.getString("content");
				input[count][4] = rs.getString("keyword");
				input[count][5] = rs.getString("accuracy");
				count++;

				final String columnNames[] = { "UserID", "Date", "File Fath", "Content", "Keyword", "Accuracy" };

				final JTable table = new JTable(input, columnNames);
				JScrollPane scrollPane = new JScrollPane(table);
				add(scrollPane, BorderLayout.CENTER);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void showAllWord(){
		
	}

}

package com.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.summarymachine.jdbc.UserInsertDAO;

public class UserFrequencyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private UserInsertDAO userInsertDAO;
	private Statement stmt = null;
	private Connection con = null;

	public UserInsertDAO getUserInsertDAO() {
		return userInsertDAO;
	}

	public void setUserInsertDAO(UserInsertDAO userInsertDAO) {
		this.userInsertDAO = userInsertDAO;
	}

	public UserFrequencyPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 150));
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/premium", "root", "rlaxod");

			stmt = (Statement) con.createStatement();

			String sql2 = "select * from sysinfo;";
			ResultSet rs = null;
			rs = stmt.executeQuery(sql2);

			int line = getUserInsertDAO().getDbLine();
			// getUserInsertDAO().getDbLine() -> 디비 총 갯수
			int count = 0;
			String[][] input = new String[line][6];
			while (rs.next()) {
				input[count][0] = rs.getString("id");// 결과반
				input[count][1] = rs.getString("date");
				input[count][2] = rs.getString("file_path");
				input[count][3] = rs.getString("content");
				input[count][4] = rs.getString("keyword");
				input[count][5] = rs.getString("accuracy");
				count++;
			}

			Object rowData[][] = new Object[line][6];
			for (int i = 0; i < line; i++) {
				for (int j = 0; j < 6; j++) {
					rowData[i][j] = input[i][j]; // db 값들을 저장해야함.
				}
			}
			final String columnNames[] = { "UserID", "Date", "File Fath", "Content", "Keyword", "Accuracy" };

			final JTable table = new JTable(rowData, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

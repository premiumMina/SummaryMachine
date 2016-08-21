package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.premiummina.summarymachine.jdbc.UserDAO;

/**
 * 사용자 히스토리 출력 패널
 * 
 * @author premiumMina
 * created on 2016. 8. 2.
 */
public class UserHistoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private int count;
	private String[][] data;

	public UserHistoryPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 150));
		this.userDAO = new UserDAO();
		data = userDAO.historyGet();
		historyChart();

	}

	public void historyChart() {
		final String columnNames[] = { "UserID", "Date", "File Path", "Content", "Keyword", "Accuracy" };
		final JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setUserDAO(UserDAO userInsertDAO) {
		this.userDAO = userInsertDAO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
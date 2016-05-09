package com.summarymachine.ui.leftpanel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	public ResultPanel() {
		this.setLayout(null);
		JButton searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		this.add(searchBtn);
	}
}

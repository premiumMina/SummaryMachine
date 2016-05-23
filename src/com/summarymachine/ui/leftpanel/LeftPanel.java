package com.summarymachine.ui.leftpanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.summarymachine.ui.rightpanel.RightPanel;

public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private RightPanel rightPanel;

	public LeftPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth + 20;
		this.panelHeight = panelHeight;
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void leftComponents(Container contentPane) {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(6, 0));

		UserIdCheckPanel userIdCheckPanel = new UserIdCheckPanel();
		this.add(userIdCheckPanel);

		DocumentUrlPanel docUrlPanel = new DocumentUrlPanel();
		this.add(docUrlPanel);

		KeywordPanel keywordPanel = new KeywordPanel();// 1
		this.add(keywordPanel);

		ResultPanel resultPanel = new ResultPanel();
		resultPanel.setRightPanel(rightPanel);
		resultPanel.setKeywordPanel(keywordPanel);
		this.add(resultPanel);
	}

}
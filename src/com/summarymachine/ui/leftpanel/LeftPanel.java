package com.summarymachine.ui.leftpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

/**
 * 왼쪽 컴포넌트 패널
 * @author PremiumMina
 */
public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private RightPanel rightPanel;
	private CrawlerInWeb crawlerInWeb;

	public LeftPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth + 20;
		this.panelHeight = panelHeight;
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void initLeftComponents() {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(6, 0));

		UserIdCheckPanel userIdCheckPanel = new UserIdCheckPanel();
		this.add(userIdCheckPanel);

		DocumentUrlPanel docUrlPanel = new DocumentUrlPanel();
		this.add(docUrlPanel);

		KeywordPanel keywordPanel = new KeywordPanel();
		this.add(keywordPanel);

		SearchPanel resultPanel = new SearchPanel();
		resultPanel.setRightPanel(rightPanel);
		resultPanel.setKeywordPanel(keywordPanel);
		resultPanel.setDocumentUrlPanel(docUrlPanel);
		resultPanel.setSummaryTextPanel(rightPanel.getSummaryTextPanel());

		resultPanel.setCrawlerInWeb(crawlerInWeb);
		this.add(resultPanel);

	}

}
package com.summarymachine.ui.leftpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

/**
 * 왼쪽 컴포넌트 패널
 * 
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

		DocumentUrlPanel documentUrlPanel = new DocumentUrlPanel();
		this.add(documentUrlPanel);

		KeywordPanel keywordPanel = new KeywordPanel();
		this.add(keywordPanel);

		SearchPanel searchPanel = new SearchPanel();
		searchPanel.setRightPanel(rightPanel);
		searchPanel.setKeywordPanel(keywordPanel);
		searchPanel.setDocumentUrlPanel(documentUrlPanel);
		searchPanel.setUserIdCheckPanel(userIdCheckPanel);
		searchPanel.setSummaryTextPanel(rightPanel.getSummaryTextPanel());
		searchPanel.setWordGraphPanel(rightPanel.getWordGraphPanel());
		searchPanel.setCrawlerInWeb(crawlerInWeb);
		this.add(searchPanel);

	}

}
package com.premiummina.summarymachine.ui.leftpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.premiummina.summarymachine.analyzer.ContentAnalyzer;
import com.premiummina.summarymachine.ui.rightpanel.RightPanel;

/**
 * 좌측 컴포넌트 패널
 * 
 * @author premiumMina
 * created on 2016. 7. 10.
 */
public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private RightPanel rightPanel;
	private ContentAnalyzer contentAnalyzer;

	public LeftPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth + 20;
		this.panelHeight = panelHeight;
	}

	public void initLeftComponents() {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(6, 0));

		UserIdCheckPanel userIdCheckPanel = new UserIdCheckPanel();
		this.add(userIdCheckPanel);

		FilePathPanel filePathPanel = new FilePathPanel();
		this.add(filePathPanel);

		KeywordPanel keywordPanel = new KeywordPanel();
		this.add(keywordPanel);

		SearchPanel searchPanel = new SearchPanel();
		searchPanel.setRightPanel(rightPanel);
		searchPanel.setKeywordPanel(keywordPanel);
		searchPanel.setFilePathPanel(filePathPanel);
		searchPanel.setUserIdCheckPanel(userIdCheckPanel);
		searchPanel.setSummaryTextPanel(rightPanel.getSummaryTextPanel());
		searchPanel.setWordGraphPanel(rightPanel.getWordGraphPanel());
		searchPanel.setCrawlerInWeb(contentAnalyzer);
		this.add(searchPanel);
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
}
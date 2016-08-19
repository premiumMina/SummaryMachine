package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.premiummina.summarymachine.analyzer.ContentAnalyzer;
import com.premiummina.summarymachine.jdbc.UserDAO;
import com.premiummina.summarymachine.ui.leftpanel.KeywordPanel;

/**
 * 우측 컴포넌트 패널
 * 
 * @author premiumMina
 * created on 2016. 8. 2.
 */
public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private WordAccuracyPanel wordAccuracyPanel;
	private WordGraphPanel wordGraphPanel;
	private KeywordPanel keywordPanel;
	private SummaryTextPanel summaryTextPanel;
	private ContentAnalyzer crawlerInWeb;
	private UserHistoryPanel userHistoryPanel;
	private InfoPanel helpPanel;
	private UserDAO userDAO;

	public RightPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
	}

	public void initRightComponents() {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(1, 1));

		JPanel rightFirstPage = new JPanel();
		rightFirstPage.setLayout(null);
		JPanel rightSecondPage = new JPanel();
		rightSecondPage.setLayout(null);
		JPanel rightThirdPage = new JPanel();
		rightThirdPage.setLayout(null);

		JTabbedPane jtb = new JTabbedPane();
		jtb.add("Analyzer", rightFirstPage);
		jtb.add("User Frequency", rightSecondPage);
		jtb.add("Help", rightThirdPage);
		jtb.setBounds(0, 0, panelWidth, 20);
		this.add(jtb);

		/* 첫 번째 탭 */
		summaryTextPanel = new SummaryTextPanel();
		summaryTextPanel.setBounds(20, 30, 550, 200);
		rightFirstPage.add(summaryTextPanel);

		wordGraphPanel = new WordGraphPanel();
		wordGraphPanel.setBounds(20, 270, 550, 400);
		rightFirstPage.add(wordGraphPanel);

		wordAccuracyPanel = new WordAccuracyPanel();
		keywordPanel = new KeywordPanel();
		wordAccuracyPanel.setKeywordText(keywordPanel.getKeyword());
		wordAccuracyPanel.setBounds(20, 700, 550, 100);
		rightFirstPage.add(wordAccuracyPanel);

		/* 두 번째 탭 */
		userHistoryPanel = new UserHistoryPanel();
		userHistoryPanel.setBounds(0, 0, panelWidth, panelHeight);
		userHistoryPanel.setUserDAO(userDAO);
		rightSecondPage.add(userHistoryPanel);
		
		/* 세 번째 탭 */
		helpPanel = new InfoPanel();
		helpPanel.setBounds(0, 0, panelWidth, panelHeight);
		rightThirdPage.add(helpPanel);
	}
	
	public WordAccuracyPanel getWordAccuracyPanel() {
		return wordAccuracyPanel;
	}

	public WordGraphPanel getWordGraphPanel() {
		return wordGraphPanel;
	}

	public SummaryTextPanel getSummaryTextPanel() {
		return summaryTextPanel;
	}

	public ContentAnalyzer getCrawlerInWeb() {
		return crawlerInWeb;
	}
}
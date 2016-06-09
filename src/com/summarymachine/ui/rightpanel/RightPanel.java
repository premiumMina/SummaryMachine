package com.summarymachine.ui.rightpanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.summarymachine.ui.leftpanel.KeywordPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private WordAccuracyPanel wordAccuracyPanel;
	private KeywordPanel keywordPanel;
	private SummaryTextPanel summaryTextPanel;
	private CrawlerInWeb crawlerInWeb;

	public RightPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
	}

	public WordAccuracyPanel getWordAccuracyPanel() {
		return wordAccuracyPanel;
	}

	public SummaryTextPanel getSummaryTextPanel() {
		return summaryTextPanel;
	}
	
	public CrawlerInWeb getCrawlerInWeb(){
		return crawlerInWeb;
	}
	
	public void rightComponents(Container contentPane) {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(1, 1));

		JPanel rightFirstPage = new JPanel();
		rightFirstPage.setLayout(null);
		JPanel rightSecondPage = new JPanel();
		JPanel rightThirdPage = new JPanel();
		JPanel rightForthPage = new JPanel();

		JTabbedPane jtb = new JTabbedPane();
		jtb.add("Analyzer", rightFirstPage);
		jtb.add("Second page", rightSecondPage);
		jtb.add("Third page", rightThirdPage);
		jtb.add("Forth page", rightForthPage);
		jtb.setBounds(0, 0, panelWidth, 20);
		this.add(jtb);

		summaryTextPanel = new SummaryTextPanel();
		summaryTextPanel.setBounds(20, 30, 500, 130);
		rightFirstPage.add(summaryTextPanel);

		WordGraphPanel wordFrequency = new WordGraphPanel();
		wordFrequency.setBounds(20, 180, 500, 200);
		rightFirstPage.add(wordFrequency);

		wordAccuracyPanel = new WordAccuracyPanel();
		keywordPanel = new KeywordPanel();
		wordAccuracyPanel.setKeywordPanel(keywordPanel);
		wordAccuracyPanel.setBounds(20, 400, 300, 100);
		rightFirstPage.add(wordAccuracyPanel);

	}
}

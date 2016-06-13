package com.summarymachine.ui.rightpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.summarymachine.ui.leftpanel.KeywordPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

/**
 * 오른쪽 컴포넌트 패널
 * @author PremiumMina
 */
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
	
	public void initRightComponents() {
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
		
		JPanel infoPanel = new JPanel();
		TitledBorder infoTextborder = new TitledBorder("Program");
		infoPanel.setBorder(infoTextborder);
		String input = "Program Infomation";
		JTextArea infoText = new JTextArea(12, 45);
		infoText.setText(input);
		infoText.setEditable(false);
		infoPanel.add(infoText);
		rightForthPage.add(infoPanel);

		JPanel developerPanel = new JPanel();
		TitledBorder developerBorder = new TitledBorder("Developer Introduction");
		developerPanel.setBorder(developerBorder);
		developerPanel.setLayout(new GridLayout(1, 2));

		JTextArea minaText = new JTextArea(12, 23);
		minaText.setLayout(new GridLayout(3, 1));
		minaText.setEditable(false);
		minaText.setText("Kim Min-A");
		developerPanel.add(minaText);
		

		JTextArea daeun = new JTextArea(12, 23);
		daeun.setLayout(new GridLayout(3, 1));
		daeun.setEditable(false);
		daeun.setText("Kim Da-Eun");
		developerPanel.add(daeun);
		
		rightForthPage.add(developerPanel);
	}
}

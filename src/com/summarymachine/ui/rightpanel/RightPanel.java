package com.summarymachine.ui.rightpanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.summarymachine.jdbc.UserInsertDAO;
import com.summarymachine.ui.leftpanel.KeywordPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

/**
 * 오른쪽 컴포넌트 패널
 * 
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
	private UserFrequencyPanel userFrequencyPanel;
	private InfoPanel infoPanel;
	private UserInsertDAO userInsertDAO;

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

	public CrawlerInWeb getCrawlerInWeb() {
		return crawlerInWeb;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(InfoPanel infoPanel) {
		this.infoPanel = infoPanel;
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
		// rightThirdPage

		JTabbedPane jtb = new JTabbedPane();
		jtb.add("Analyzer", rightFirstPage);
		jtb.add("User Frequency", rightSecondPage);
		jtb.add("Help", rightThirdPage);
		jtb.setBounds(0, 0, panelWidth, 20);
		this.add(jtb);

		/* 1 */
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

		/* 2 */
		userFrequencyPanel = new UserFrequencyPanel();
		userFrequencyPanel.setBounds(0, 0, panelWidth, panelHeight);
		userFrequencyPanel.setUserInsertDAO(userInsertDAO);
		rightSecondPage.add(userFrequencyPanel);

		/* 3 */
		infoPanel = new InfoPanel();
		infoPanel.setBounds(0, 0, panelWidth, panelHeight);
		rightThirdPage.add(infoPanel);
	}
}

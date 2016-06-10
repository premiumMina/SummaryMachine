package com.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.SummaryTextPanel;
import com.summarymachine.ui.rightpanel.WordAccuracyPanel;
import com.summarymachine.ui.test.CrawlerInWeb;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private KeywordPanel keywordPanel;
	private WordAccuracyPanel wordAccuracyPanel;
	private SummaryTextPanel summaryTextPanel;
	private RightPanel rightPanel;
	private DocumentUrlPanel documentUrlPanel;
	private CrawlerInWeb crawlerInWeb;

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	
	public void setKeywordPanel(KeywordPanel keywordPanel) {
		this.keywordPanel = keywordPanel;
	}

	public void setCrawlerInWeb(CrawlerInWeb crawlerInWeb) {
		this.crawlerInWeb = crawlerInWeb;
	}

	public void setWordAccuracyPanel(WordAccuracyPanel wordAccuracyPanel) {
		this.wordAccuracyPanel = wordAccuracyPanel;
	}

	public void setDocumentUrlPanel(DocumentUrlPanel documentUrlPanel) {
		this.documentUrlPanel = documentUrlPanel;
	}

	public void setSummaryTextPanel(SummaryTextPanel summaryTextPanel) {
		this.summaryTextPanel = summaryTextPanel;
	}

	public SearchPanel() {
		this.setLayout(null);
		JButton searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (documentUrlPanel.getUrlField().length() > 0) {
					CrawlerInWeb crawlerInWeb = new CrawlerInWeb((String)documentUrlPanel.getUrlField());
					summaryTextPanel.setSummaryTextField(crawlerInWeb.getResult());
				}
				if (keywordPanel.getCheckBox().isSelected())
					rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));
			}
		});
		this.add(searchBtn);
	}

}

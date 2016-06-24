package com.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.summarymachine.jdbc.UserInsertDAO;
import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.SummaryTextPanel;
import com.summarymachine.ui.rightpanel.UserFrequencyPanel;
import com.summarymachine.ui.test.CrawlerInWeb;
import com.summarymachine.ui.test.WebCrawler;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private KeywordPanel keywordPanel;
	private SummaryTextPanel summaryTextPanel;
	private RightPanel rightPanel;
	private DocumentUrlPanel documentUrlPanel;
	private CrawlerInWeb crawlerInWeb;
	private WebCrawler webCrawler;
	private UserInsertDAO userInsertDAO;
	private UserIdCheckPanel userIdCheckPanel;

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setKeywordPanel(KeywordPanel keywordPanel) {
		this.keywordPanel = keywordPanel;
	}

	public void setCrawlerInWeb(CrawlerInWeb crawlerInWeb) {
		this.crawlerInWeb = crawlerInWeb;
	}

	public void setDocumentUrlPanel(DocumentUrlPanel documentUrlPanel) {
		this.documentUrlPanel = documentUrlPanel;
	}

	public void setSummaryTextPanel(SummaryTextPanel summaryTextPanel) {
		this.summaryTextPanel = summaryTextPanel;
	}

	public UserInsertDAO getUserInsertDAO() {
		return userInsertDAO;
	}

	public void setUserInsertDao(UserInsertDAO userInsertDAO) {
		this.userInsertDAO = userInsertDAO;
	}

	public UserIdCheckPanel getUserIdCheckPanel() {
		return userIdCheckPanel;
	}

	public void setUserIdCheckPanel(UserIdCheckPanel userIdCheckPanel) {
		this.userIdCheckPanel = userIdCheckPanel;
	}

	public SearchPanel() {
		this.setLayout(null);
		JButton searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* first page */
				if (documentUrlPanel.getUrlField().length() > 0) {
					if (documentUrlPanel.getUrlField().startsWith("http")) {
						webCrawler = new WebCrawler(documentUrlPanel.getUrlField());
						crawlerInWeb = new CrawlerInWeb("test.txt", webCrawler.getContentType(), 0);
						summaryTextPanel.setSummaryTextField(crawlerInWeb.getSortedResultSentence());
					} else { /* not web url */
						webCrawler = new WebCrawler(documentUrlPanel.getUrlField());
						crawlerInWeb = new CrawlerInWeb(documentUrlPanel.getUrlField(), webCrawler.getContentType(), 1);
						summaryTextPanel.setSummaryTextField(crawlerInWeb.getSortedResultSentence());
					}
				}

				if (keywordPanel.getCheckBox().isSelected())
					rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));

				userInsertDAO = new UserInsertDAO(userIdCheckPanel.getIdField(), documentUrlPanel.getUrlField(),
						crawlerInWeb.getContent(), keywordPanel.getKeyword(), "100");

			}
		});
		this.add(searchBtn);
	}

}

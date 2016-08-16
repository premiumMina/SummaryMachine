package com.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.summarymachine.jdbc.UserDAO;
import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.SummaryTextPanel;
import com.summarymachine.ui.rightpanel.WordAccuracyPanel;
import com.summarymachine.ui.rightpanel.WordGraphPanel;
import com.summarymachine.ui.test.ContentAnalyzer;
import com.summarymachine.ui.test.WebCrawler;
import com.summarymachine.utils.Utils;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private KeywordPanel keywordPanel;
	private SummaryTextPanel summaryTextPanel;
	private RightPanel rightPanel;
	private DocumentUrlPanel documentUrlPanel;
	private ContentAnalyzer contentAnalyzer;
	private WebCrawler webCrawler;
	private UserDAO userDAO;
	private UserIdCheckPanel userIdCheckPanel;
	private WordGraphPanel wordGraphPanel;
	private WordAccuracyPanel wordAccuracyPanel;
	private JButton searchBtn;

//	public WordAccuracyPanel getWordAccuracyPanel() {
//		return wordAccuracyPanel;
//	}

	public void setWordAccuracyPanel(WordAccuracyPanel wordAccuracyPanel) {
		this.wordAccuracyPanel = wordAccuracyPanel;
	}

	public void setWordGraphPanel(WordGraphPanel wordGraphPanel) {
		this.wordGraphPanel = wordGraphPanel;
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setKeywordPanel(KeywordPanel keywordPanel) {
		this.keywordPanel = keywordPanel;
	}

	public void setCrawlerInWeb(ContentAnalyzer crawlerInWeb) {
		this.contentAnalyzer = crawlerInWeb;
	}

	public void setDocumentUrlPanel(DocumentUrlPanel documentUrlPanel) {
		this.documentUrlPanel = documentUrlPanel;
	}

	public void setSummaryTextPanel(SummaryTextPanel summaryTextPanel) {
		this.summaryTextPanel = summaryTextPanel;
	}
//
//	public UserInsertDAO getUserInsertDAO() {
//		return userInsertDAO;
//	}

	public void setUserInsertDao(UserDAO userInsertDAO) {
		this.userDAO = userInsertDAO;
	}

	public UserIdCheckPanel getUserIdCheckPanel() {
		return userIdCheckPanel;
	}

	public void setUserIdCheckPanel(UserIdCheckPanel userIdCheckPanel) {
		this.userIdCheckPanel = userIdCheckPanel;
	}

	public SearchPanel() {
		this.setLayout(null);
		contentAnalyzer = new ContentAnalyzer();
		webCrawler = new WebCrawler();
		searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		searchBtn.addActionListener(new SearchActionListener(contentAnalyzer));
		this.add(searchBtn);
	}
	
	class SearchActionListener implements ActionListener {
		private ContentAnalyzer contentAnalyzer;
		private UserDAO userDAO;
		
		public SearchActionListener() {}
		
		public SearchActionListener(ContentAnalyzer contentAnalyzer) {
			this.contentAnalyzer = contentAnalyzer;
			this.userDAO = new UserDAO();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pushButton();
		}
		
		public void pushButton() {
			if (documentUrlPanel.getUrlField().length() > 0) {
				contentAnalyzer.setKeywordFromUserInput(keywordPanel.getKeyword());
				
				if (documentUrlPanel.getUrlField().startsWith("http")) {
					webCrawler.crawliing(documentUrlPanel.getUrlField());
					contentAnalyzer.analyze(webCrawler.getCrawlingResult().toString());
					summaryTextPanel.setSummaryTextField(contentAnalyzer.getSortedResultSentence());
					/* 단어와 가중치를 넘긴다 */
					wordGraphPanel.setWordWeight(contentAnalyzer.getWordWeight());
					wordGraphPanel.showGraph();
				}
			}

			/* 문서 내용의 분석과 3줄 추출작업이 끝난 후에 해야 하는 작업 */
			
			/* 키워드와 문서의 정확도를 알기위해 추가된 옵션 패널 */
			if (keywordPanel.getCheckBox().isSelected()) {
				rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));
				/* 1. 키워드 정확도 전달 */
				rightPanel.getWordAccuracyPanel().setKeywordAccuracy(contentAnalyzer.getAccuracyValue());
			}

			/* 2. DB에 저장 */
			userDAO.insertHistory(userIdCheckPanel.getIdField(), documentUrlPanel.getUrlField(),
					contentAnalyzer.getSortedResultSentence(), keywordPanel.getKeyword(), contentAnalyzer.getAccuracyValue());
		}
		
		public void insertSearchHistory() {
			
		}
		
		
		public ContentAnalyzer getContentAnalyzer() {
			return contentAnalyzer;
		}

		public void setContentAnalyzer(ContentAnalyzer contentAnalyzer) {
			this.contentAnalyzer = contentAnalyzer;
		}
		
	}
}
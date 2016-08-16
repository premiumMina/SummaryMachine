package com.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.summarymachine.jdbc.UserInsertDAO;
import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.SummaryTextPanel;
import com.summarymachine.ui.rightpanel.WordAccuracyPanel;
import com.summarymachine.ui.rightpanel.WordGraphPanel;
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

	public void setCrawlerInWeb(CrawlerInWeb crawlerInWeb) {
		this.crawlerInWeb = crawlerInWeb;
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
		searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pushButton();
			}
		});
		this.add(searchBtn);
		
		crawlerInWeb = new CrawlerInWeb();
	}
	
	public void pushButton() {
		if (documentUrlPanel.getUrlField().length() > 0) {
			crawlerInWeb.setKeyword(keywordPanel.getKeyword());
			webCrawler = new WebCrawler();
			if (documentUrlPanel.getUrlField().startsWith("http")) {
				webCrawler.crawlering(documentUrlPanel.getUrlField());
				crawlerInWeb.crawling("test.txt", webCrawler.getContentType(), 0);
				summaryTextPanel.setSummaryTextField(crawlerInWeb.getSortedResultSentence());
				/* 단어와 가중치를 넘긴다 */
				wordGraphPanel.setWordWeight(crawlerInWeb.getWordWeight());
				wordGraphPanel.showGraph();
			} else { /* not web url */
				webCrawler.crawlering(documentUrlPanel.getUrlField());
				crawlerInWeb.crawling(documentUrlPanel.getUrlField(), webCrawler.getContentType(), 1);
				summaryTextPanel.setSummaryTextField(crawlerInWeb.getSortedResultSentence());
			}
		}

		/* 문서 내용의 분석과 3줄 추출작업이 끝난 후에 해야 하는 작업 */
		
		/* 키워드와 문서의 정확도를 알기위해 추가된 옵션 패널 */
		if (keywordPanel.getCheckBox().isSelected()) {
			rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));
			/* 1. 키워드 정확도 전달 */
			rightPanel.getWordAccuracyPanel().setKeywordAccuracy(crawlerInWeb.getAccuracyValue());
		}

		/* 2. DB에 저장 */
		userInsertDAO = new UserInsertDAO(userIdCheckPanel.getIdField(), documentUrlPanel.getUrlField(),
				crawlerInWeb.getSortedResultSentence(), keywordPanel.getKeyword(), crawlerInWeb.getAccuracyValue());

	}

}
package com.premiummina.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.premiummina.summarymachine.analyzer.ContentAnalyzer;
import com.premiummina.summarymachine.crawler.WebCrawler;
import com.premiummina.summarymachine.jdbc.MySQLConn;
import com.premiummina.summarymachine.jdbc.UserDAO;
import com.premiummina.summarymachine.ui.rightpanel.RightPanel;
import com.premiummina.summarymachine.ui.rightpanel.SummaryTextPanel;
import com.premiummina.summarymachine.ui.rightpanel.WordAccuracyPanel;
import com.premiummina.summarymachine.ui.rightpanel.WordGraphPanel;
import com.premiummina.summarymachine.utils.Utils;

/**
 * 검색 및 분석 시작 패널
 * 
 * @author premiumMina 
 * created on 2016. 7. 18.
 */
public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private KeywordPanel keywordPanel;
	private SummaryTextPanel summaryTextPanel;
	private RightPanel rightPanel;
	private FilePathPanel filePathPanel;
	private ContentAnalyzer contentAnalyzer;
	private WebCrawler webCrawler;
	private UserIdCheckPanel userIdCheckPanel;
	private WordGraphPanel wordGraphPanel;
	private WordAccuracyPanel wordAccuracyPanel;
	private JButton searchBtn;
	private UserDAO userDAO;

	public SearchPanel(UserDAO userDAO) {
		this.setLayout(null);
		contentAnalyzer = new ContentAnalyzer();
		webCrawler = new WebCrawler();
		searchBtn = new JButton("search");
		searchBtn.setBounds(200, 10, 80, 25);
		setUserDAO(userDAO);
		searchBtn.addActionListener(new SearchActionListener(contentAnalyzer, this.userDAO));
		this.add(searchBtn);
	}

	class SearchActionListener implements ActionListener {
		private ContentAnalyzer contentAnalyzer;
		private UserDAO userDAO;

		public SearchActionListener() {
		}

		public SearchActionListener(ContentAnalyzer contentAnalyzer, UserDAO userDAO) {
			this.contentAnalyzer = contentAnalyzer;
			this.userDAO = userDAO;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			pushButton();
		}

		public void pushButton() {
			if (filePathPanel.getUrlField().length() > 0) {
				contentAnalyzer.setKeywordFromUserInput(keywordPanel.getKeyword());

				if (filePathPanel.getUrlField().startsWith("http")) {
					webCrawler.crawling(filePathPanel.getUrlField(),Utils.WEB_DOCUMENT);
					contentAnalyzer.analyze(webCrawler.getCrawlingResult().toString(),0);
					summaryTextPanel.setSummaryTextField(contentAnalyzer.getSortedResultSentence());
					/* 단어와 가중치를 넘긴다 */
					wordGraphPanel.setWordWeightMap(contentAnalyzer.getWordWeightMap());
					wordGraphPanel.showGraph();
				}else if(filePathPanel.getUrlField().endsWith(".rtf")){
					webCrawler.crawling(filePathPanel.getUrlField(),1);
					contentAnalyzer.analyze(webCrawler.getCrawlingResult().toString(), Utils.TEXT_DOCUMENT);
					summaryTextPanel.setSummaryTextField(contentAnalyzer.getSortedResultSentence());
					wordGraphPanel.setWordWeightMap(contentAnalyzer.getWordWeightMap());
					wordGraphPanel.showGraph();
				}

				/* 문서 내용의 분석과 3줄 추출작업이 끝난 후에 해야 하는 작업 */

				/* 키워드와 문서의 정확도를 알기위해 추가된 옵션 패널 */
				if (keywordPanel.getCheckBox().isSelected()) {
					rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));
					/* 1. 키워드 정확도 전달 */
					rightPanel.getWordAccuracyPanel().setKeywordAccuracy(contentAnalyzer.getAccuracyValue() + "%");
				}
				
				/* 데이터베이스를 사용하는 경우에만 히스토리 저장 */
				if (MySQLConn.isDBOpend()) {
					insertSearchHistory();
				}

			} else {
				System.out.println("다시 확인하세요");
			}

		}

		public void insertSearchHistory() {
			userDAO.insertHistory(userIdCheckPanel.getIdField(), filePathPanel.getUrlField(),
					contentAnalyzer.getSortedResultSentence(), keywordPanel.getKeyword(),
					contentAnalyzer.getAccuracyValue());
		}

		public ContentAnalyzer getContentAnalyzer() {
			return contentAnalyzer;
		}

		public void setContentAnalyzer(ContentAnalyzer contentAnalyzer) {
			this.contentAnalyzer = contentAnalyzer;
		}
	}

	public WordAccuracyPanel getWordAccuracyPanel() {
		return wordAccuracyPanel;
	}

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

	public void setFilePathPanel(FilePathPanel filePathPanel) {
		this.filePathPanel = filePathPanel;
	}

	public void setSummaryTextPanel(SummaryTextPanel summaryTextPanel) {
		this.summaryTextPanel = summaryTextPanel;
	}

	public UserIdCheckPanel getUserIdCheckPanel() {
		return userIdCheckPanel;
	}

	public void setUserIdCheckPanel(UserIdCheckPanel userIdCheckPanel) {
		this.userIdCheckPanel = userIdCheckPanel;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
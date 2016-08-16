package com.summarymachine.ui.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class CrawlerInWeb {
	/* 내용 3줄이 들어가는 String*/
	private String sortedResultSentence;
	private Map<String, Integer> wordWeight;
	private String keyword;
	private double accuracyValue;
	private KeywordExtractor ke;

	public double getAccuracyValue() {
		return accuracyValue;
	}

	public void setAccuracyValue(double accuracyValue) {
		this.accuracyValue = accuracyValue;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map<String, Integer> getWordWeight() {
		return wordWeight;
	}

	public void setWordWeight(Map<String, Integer> wordWeight) {
		this.wordWeight = wordWeight;
	}

	public String getSortedResultSentence() {
		return sortedResultSentence;
	}

	public CrawlerInWeb() {
		ke = new KeywordExtractor();
	}

	public void crawling(String docUrl, String contentType, int kind) {

		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = null;
			String readText;
			if (contentType.contains("UTF-8"))
				br = new BufferedReader(new InputStreamReader(new FileInputStream(docUrl), "UTF-8"));
			if (contentType.contains("EUC-KR"))
				br = new BufferedReader(new InputStreamReader(new FileInputStream(docUrl), "EUC-KR"));

			boolean start = false;
			/*
			 * kind == 0 , http~
			 * kind == 1 , document
			 */
			if (kind == 0) {
				while ((readText = br.readLine()) != null) {
					if (readText.contains("<!-- article_content -->"))
						start = true;

					if (readText.contains("<!-- 본문 내용 -->"))
						start = true;

					if (start) {
						sb.append(readText);
					}

					if (readText.contains("<!-- /article_content -->"))
						break;

					if (readText.contains("<!-- // 본문 내용 -->"))
						break;
				}
			} else {
				/* document */
			}
			
			/* 본문 내용을 한 문장씩 lineArt 리스트에 넣는 작업 */
			List<String> lineArt = new ArrayList<String>();
			String addArticle;
			BufferedReader reader = new BufferedReader(new StringReader(sb.toString()));
			int line=0;
			while ((addArticle = reader.readLine()) != null) {
				lineArt.add(addArticle);
				line++;
			}
			
			/* string to extract keywords */
			String strToExtrtKwrd = sb.toString();

			/* init KeywordExtractor */

			/* extract keywords */
			KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

			/* 단어가중치 map, 문장가중치 맵 생성 */
			wordWeight = new HashMap<String, Integer>();
			Map<Integer, String> sentenceWeight = new HashMap<Integer, String>();
			List<String> word = new ArrayList<String>();

			/* save the word - wordWeight. */
			for (int i = 0; i < kl.size(); i++) {
				Keyword kwrd = kl.get(i);
				if (kwrd.getCnt() >= 2 && kwrd.getString().length() >= 2) {
//					System.out.println(kwrd.getString() + ": " +kwrd.getCnt());
					wordWeight.put(kwrd.getString(), kwrd.getCnt());
					word.add(kwrd.getString());
				}
			}

			/*
			 * 좌측 패널에서 입력한 키워드와 문서내 단어들과 정확도 확인하는 알고리즘
			 */
			int wordSize = word.size();

			if (wordWeight.containsKey(keyword)) {
				// true 조건
				accuracyValue = Math.round(((double) wordWeight.get(keyword) / (double) wordSize)) * 100;

			}

			/* 단어가중치값을 적용해서 한 문장의 가중치 값을 구한다. */
			for (int index = 0; index < line; index++) {
				int sum = 0;
				for (int total = 0; total < word.size(); total++) {
					if (lineArt.get(index).contains(word.get(total))) {
						sum = sum + wordWeight.get(word.get(total));
					}
				}
				/* 문장가중치 map에 가중치 값과 문장을 넣는다. */
				sentenceWeight.put(sum, lineArt.get(index));
			}
			
			
			/* 문장 가중치를 값이 높은 순서대로 정렬한다. map의 내림차순 정렬. */
			Map<Integer, String> sortedMap = new TreeMap<Integer, String>(Collections.reverseOrder());
			sortedMap.putAll(sentenceWeight);
			String sortedSentence = sortedMap.toString();
			sortedSentence = removeHtmlTag(sortedSentence.trim());
			int j = 0;
			sortedResultSentence = sortedSentence;
//			StringBuffer sortedSb = new StringBuffer();
//			BufferedReader reader2 = new BufferedReader(new StringReader(sortedSentence));
//			while ((sortedSentence = reader2.readLine()) != null) {
//				j++;
//				sortedSb.append(j + " : " + sortedSentence+"\n");
//				sortedResultSentence = sortedSb.toString();
//				/* 3줄만 출력되야 하므로.. */
//				if (j == 3) {
//					break;
//				}
//			}
			br.close();
		} catch (IOException ie) {
			System.out.println(ie);

		} catch (Exception ee) {
			System.out.println(ee);
		}

	}

	private static String removeHtmlTag(String content) {
		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");
		Pattern BR = Pattern.compile("\\.");

		Matcher m;

		m = SCRIPTS.matcher(content);
		content = m.replaceAll("");
		m = STYLE.matcher(content);
		content = m.replaceAll("");
		m = TAGS.matcher(content);
		content = m.replaceAll("");
		m = ENTITY_REFS.matcher(content);
		content = m.replaceAll("");
		m = WHITESPACE.matcher(content);
		content = m.replaceAll(" ");

		m = BR.matcher(content);
		content = m.replaceAll("\\." + "\n");

		return content;
	}
}
package com.summarymachine.ui.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.summarymachine.utils.Utils;

public class CrawlerInWeb {
	/* 내용 3줄이 들어가는 String*/
	private String sortedResultSentence;
	private Map<String, Integer> wordWeightMap;
	private String keywordFromUserInput;
	private double accuracyValue;
	private KeywordExtractor ke;

	public CrawlerInWeb() {
		/* init KeywordExtractor */
		ke = new KeywordExtractor();
	}

	public void crawling(String docUrl, String contentType, int kind) {

		StringBuffer sb = new StringBuffer();
		try {
			
			String encoding = null;
			if (contentType.contains("UTF-8")) encoding = "UTF-8";
			else  encoding = "EUC-KR";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(docUrl), encoding));
			

			List<String> contentBodyListByLine = new ArrayList<String>();
			String readText = null;
			
			if (kind == Utils.WEB_DOCUMENT) {
				boolean start = false;
				while ((readText = br.readLine()) != null) {
					if (readText.contains("<!-- article_content -->") || readText.contains("<!-- 본문 내용 -->"))
						start = true;
					
					else if (readText.contains("<!-- /article_content -->") || readText.contains("<!-- // 본문 내용 -->"))
						break;
					
					if (start) {
						sb.append(readText);
						contentBodyListByLine.add(readText);
					}
				}
			}
			
			/* 키워드 추출 */
			KeywordList kl = ke.extractKeyword(sb.toString(), true);

			/*
			 * 단어 가중치 맵 생성
			 * Key : 단어 / Value : 가중치
			 */
			wordWeightMap = new HashMap<String, Integer>();
			List<String> wordListInContent = new ArrayList<String>();

			/* save the word - wordWeight. */
			for (int i = 0; i < kl.size(); i++) {
				Keyword kwrd = kl.get(i);
				if (kwrd.getCnt() >= 2 && kwrd.getString().length() >= 2) {
					wordWeightMap.put(kwrd.getString(), kwrd.getCnt());
					wordListInContent.add(kwrd.getString());
				}
			}

			/*
			 * 좌측 패널에서 입력한 키워드와 문서내 단어들과 정확도 확인하는 알고리즘
			 */
			int numOfWords = wordListInContent.size();

			if (wordWeightMap.containsKey(this.keywordFromUserInput)) {
				accuracyValue = Math.round(((double) wordWeightMap.get(this.keywordFromUserInput) / (double) numOfWords)) * 100;
			}

			Map<Integer, String> sentenceWeightMap = new HashMap<Integer, String>();
			/* 단어가중치값을 적용해서 한 문장의 가중치 값을 구한다. */
			
			for(String content : contentBodyListByLine) {
				int sum = 0;
				for(String word : wordListInContent) {
					if(content.contains(word)) {
						sum = sum + wordWeightMap.get(word);
					}
				}
				/* 문장가중치 map에 가중치 값과 문장을 넣는다. */
				sentenceWeightMap.put(sum, content);
			}
			
			/* 문장 가중치를 값이 높은 순서대로 정렬한다. map의 내림차순 정렬. */
			Map<Integer, String> sortedMap = new TreeMap<Integer, String>(Collections.reverseOrder());
			sortedMap.putAll(sentenceWeightMap);
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
	
	public double getAccuracyValue() {
		return accuracyValue;
	}

	public void setAccuracyValue(double accuracyValue) {
		this.accuracyValue = accuracyValue;
	}

	public String getKeywordFromUserInput() {
		return keywordFromUserInput;
	}

	public void setKeywordFromUserInput(String keywordFromUserInput) {
		this.keywordFromUserInput = keywordFromUserInput;
	}

	public Map<String, Integer> getWordWeight() {
		return wordWeightMap;
	}

	public void setWordWeight(Map<String, Integer> wordWeight) {
		this.wordWeightMap = wordWeight;
	}

	public String getSortedResultSentence() {
		return sortedResultSentence;
	}
}
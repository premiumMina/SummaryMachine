package com.premiummina.summarymachine.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

import com.premiummina.summarymachine.utils.Utils;

/**
 * 내용을 분석한다.
 * 
 * @author premiumMina 
 * created on 2016. 7. 22.
 */
public class ContentAnalyzer {
	private String sortedResultSentence;
	private Map<String, Integer> wordWeightMap;
	private String keywordFromUserInput;
	private int accuracyValue;
	private KeywordExtractor ke;
	private KeywordList kl;
	private String extractedContentBody;
	private List<String> contentBodyListByLine;
	public ContentAnalyzer() {
		ke = new KeywordExtractor();
		sortedResultSentence = "";
	}

	public void analyze(String rawCrawlingResult, int kind) {	
		try {
			if (kind == Utils.WEB_DOCUMENT) {
				contentBodyListByLine = new ArrayList<String>();

				int startIndex = 0;
				startIndex = rawCrawlingResult.indexOf(Utils.NAVER_NEWS_BODY_START_FILTER);

				int endIndex = 0;
				endIndex = rawCrawlingResult.indexOf(Utils.NAVER_NEWS_BODY_END_FILTER);

				if (startIndex < 0 || endIndex < 0) {
					System.out.println("본문에 해당하는 내용을 찾을 수 없습니다.");
					System.exit(0);
				}

				String contentBody = rawCrawlingResult.substring(startIndex, endIndex);
				contentBody.replaceAll(Utils.NAVER_NEWS_BODY_START_FILTER, "");
				extractedContentBody = removeHtmlTag(contentBody);

				String[] splitedContentBody = extractedContentBody.split("\n");
				for (String contentSegment : splitedContentBody) {
					contentBodyListByLine.add(contentSegment);
				}
			} else if(kind == Utils.TEXT_DOCUMENT){
				contentBodyListByLine = new ArrayList<String>();
				extractedContentBody = removeHtmlTag(rawCrawlingResult);
				
				String[] splitedContentBody = extractedContentBody.split("\n");
				for (String contentSegment : splitedContentBody) {
					contentBodyListByLine.add(contentSegment);
				}
			}
		
			/* 키워드 추출 */
			kl = ke.extractKeyword(extractedContentBody, true);

			/*
			 * 단어 가중치 맵 생성 Key : 단어 / Value : 가중치
			 */
			wordWeightMap = new HashMap<String, Integer>();
			List<String> wordListInContent = new ArrayList<String>();

			/* 단어와 가중치를 부여한다. */
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
			double calValue;
			accuracyValue = 0;
			if (wordWeightMap.containsKey(this.keywordFromUserInput)) {
				int tmp = wordWeightMap.get(this.keywordFromUserInput);
				calValue = ((double) tmp / (double) numOfWords) * 100;
				accuracyValue = (int) Math.round(calValue);
			}

			/*
			 * 문장 가중치 맵 생성 Key : 문장 / Value : 가중치
			 */
			Map<String, Integer> sentenceWeightMap = new HashMap<String, Integer>();

			/* 단어의 가중치 값을 적용해서 한 문장의 가중치 값을 구한다. */
			for (String content : contentBodyListByLine) {
				int sum = 0;
				for (String word : wordListInContent) {
					if (content.contains(word)) {
						sum = sum + wordWeightMap.get(word);
					}
				}
				sentenceWeightMap.put(content, sum);
			}
			sortedResultSentence = "";
			/* 문장 가중치 맵을 가중치가 높은 순서대로 정렬한다. */
			Iterator it = ContentAnalyzer.sortMap(sentenceWeightMap).iterator();
			for (int index = 1; index < 4; index++) {
				String sentence = index + ". " + (String) it.next() + "\n";
				sortedResultSentence = sortedResultSentence + sentence;
			}

		} catch (Exception ee) {
			System.out.println(ee);
		}

	}

	@SuppressWarnings("unchecked")
	private static List sortMap(final Map map) {
		List<String> sortedSentenceList = new ArrayList<String>();
		sortedSentenceList.addAll(map.keySet());

		Collections.sort(sortedSentenceList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v1).compareTo(v2);
			}
		});
		Collections.reverse(sortedSentenceList);
		return sortedSentenceList;
	}

	private static String removeHtmlTag(String content) {
		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");
		Pattern BR = Pattern.compile("다\\.");

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
		content = m.replaceAll("다\\." + "\n");

		return content;
	}

	public int getAccuracyValue() {
		return accuracyValue;
	}

	public void setAccuracyValue(int accuracyValue) {
		this.accuracyValue = accuracyValue;
	}

	public String getKeywordFromUserInput() {
		return keywordFromUserInput;
	}

	public void setKeywordFromUserInput(String keywordFromUserInput) {
		this.keywordFromUserInput = keywordFromUserInput;
	}

	public Map<String, Integer> getWordWeightMap() {
		return wordWeightMap;
	}

	public void setWordWeight(Map<String, Integer> wordWeightMap) {
		this.wordWeightMap = wordWeightMap;
	}

	public String getSortedResultSentence() {
		return sortedResultSentence;
	}
}
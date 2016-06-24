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
	private String sortedResultSentence;
	private String docUrl;
	private static int line;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSortedResultSentence() {
		return sortedResultSentence;
	}

	/* ?? */
	public void setSortedResultSentence(String sortedResultSentence) {
		this.sortedResultSentence = sortedResultSentence;
	}

	public String getDocUrl() {
		return this.docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public CrawlerInWeb(String docUrl, String contentType, int kind) {

		StringBuffer sb = new StringBuffer();
		setDocUrl(docUrl);
		try {
			BufferedReader br = null;
			String readText;
			if (contentType.contains("UTF-8"))
				br = new BufferedReader(new InputStreamReader(new FileInputStream(docUrl), "UTF-8"));
			if (contentType.contains("EUC-KR"))
				br = new BufferedReader(new InputStreamReader(new FileInputStream(docUrl), "EUC-KR"));

			boolean start = false;
			String result;
			// {
			if (kind == 0) {
				while ((readText = br.readLine()) != null) {
					// <!-- article_content -->
					if (readText.contains("<!-- article_content -->"))
						start = true;

					if (readText.contains("<!-- 본문 내용 -->"))
						start = true;

					if (start) {
						sb.append(readText);
					}

					// <!-- /article_content -->
					if (readText.contains("<!-- /article_content -->"))
						break;

					if (readText.contains("<!-- // 본문 내용 -->"))
						break;
				}
			} else {
				while ((readText = br.readLine()) != null) {
					sb.append(readText);
				}
			}
			result = sb.toString();

			List<String> article = new ArrayList<String>();
			String addArticle;
			BufferedReader reader = new BufferedReader(new StringReader(result));

			while ((addArticle = reader.readLine()) != null) {
				article.add(addArticle);
				line++;
			}
			// string to extract keywords
			String strToExtrtKwrd = result;

			// init KeywordExtractor
			KeywordExtractor ke = new KeywordExtractor();

			// extract keywords
			KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

			/* word-wordWeight, sentence-sentenceWeight */
			/* save the word */
			Map<String, Integer> wordAnalyze = new HashMap<String, Integer>();
			Map<Integer, String> sentenceAnalyze = new HashMap<Integer, String>();
			List<String> word = new ArrayList<String>();

			/* save the word - wordWeight. */
			for (int i = 0; i < kl.size(); i++) {
				Keyword kwrd = kl.get(i);
				wordAnalyze.put(kwrd.getString(), kwrd.getCnt());
				word.add(kwrd.getString());
			}

			/* calculate a one line weight. */
			for (int index = 0; index < line; index++) {
				int sum = 0;
				for (int total = 0; total < word.size(); total++) {
					if (article.get(index).contains(word.get(total))) {
						sum = sum + wordAnalyze.get(word.get(total));
					}
				}
				sentenceAnalyze.put(sum, article.get(index));
			}

			Map<Integer, String> sortedMap = new TreeMap<Integer, String>(Collections.reverseOrder());
			sortedMap.putAll(sentenceAnalyze);

			String sortedSentence = sortedMap.toString();
			sortedSentence = removeHtmlTag(sortedSentence);
			int j = 0;

			StringBuffer sortedSb = new StringBuffer();
			BufferedReader reader2 = new BufferedReader(new StringReader(sortedSentence));
			while ((sortedSentence = reader2.readLine()) != null) {
				j++;
				sortedSb.append(j + " : " + sortedSentence);
				sortedResultSentence = sortedSb.toString();	
				content = sortedSentence;
				sortedResultSentence = removeHtmlTag(sortedResultSentence);
				sortedResultSentence = sortedResultSentence.trim();
			
				if (j == 3) {
					break;
				}
			}
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
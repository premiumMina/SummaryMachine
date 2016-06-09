package com.summarymachine.ui.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

import com.summarymachine.ui.leftpanel.DocumentUrlPanel;

/* 꼬꼬마 한글 형태소 분석 색인어 추출하기.*/
public class CrawlerInWeb {
	private String result;
	private String docUrl;

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDocUrl() {
		return this.docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public CrawlerInWeb(String docUrl) {

		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br;
			String readText;

			br = new BufferedReader(new FileReader(docUrl));
			boolean start = false;
			boolean end = false;
			ArrayList<String> articleList = new ArrayList<String>();

			while ((readText = br.readLine()) != null) {
				if (readText.contains("<!-- 본문 내용 -->"))
					start = true;
				if (readText.contains("▶ "))
					end = true;
				if (start) {
					readText = removeHtmlTag(readText);
					readText = readText.trim();
					if (!readText.isEmpty()) {
						articleList.add(readText);
						sb.append(articleList);
					}
				}

				if (end) {
					result = sb.toString();
					break;
				}

			}
			Iterator iterator = articleList.iterator();
			String element = "";
			while (iterator.hasNext()) {
				element = (String) iterator.next();
			}

			/*
			 * for (int index = 0; index < element.length(); index++) {
			 * System.out.print(element.charAt(index)); if
			 * (element.charAt(index) == '.') { System.out.println(); } }
			 */

			// string to extract keywords
			String strToExtrtKwrd = element;

			// init KeywordExtractor
			KeywordExtractor ke = new KeywordExtractor();

			// extract keywords
			KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

			// print result
			for (int i = 0; i < kl.size(); i++) {
				Keyword kwrd = kl.get(i);
				/*
				 * if (kwrd.getCnt() >= 2) System.out.println(kwrd.getString() +
				 * "\t" + kwrd.getCnt() + " ");
				 */
				br.close();
			}
		} catch (IOException ie) {
			System.out.println(ie);

		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

	/* 자바 정규식 메소드 */
	private static String removeHtmlTag(String content) {
		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");

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

		return content;
	}
}
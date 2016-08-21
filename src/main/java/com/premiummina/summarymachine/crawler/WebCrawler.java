package com.premiummina.summarymachine.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 웹 페이지에서 문서를 크롤링한다.
 * 
 * @author premiumMina
 * created on 2016. 7. 20.
 */
public class WebCrawler {
	private String contentType;
	private String webUrl;
	private StringBuilder crawlingResult;

	public WebCrawler() {
		crawlingResult = new StringBuilder();
	}

	public void crawliing(String webUrl) {
		URL url = null;
		crawlingResult.setLength(0);
		try {
			setWebUrl(webUrl);
			url = new URL(webUrl);

			URLConnection urlConn = url.openConnection();
			setContentType(urlConn.getContentType().toUpperCase());

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "EUC-KR"));

			String readLine = null;

			while ((readLine = br.readLine()) != null) {
				crawlingResult.append(readLine);
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public StringBuilder getCrawlingResult() {
		return crawlingResult;
	}

	public void setCrawlingResult(StringBuilder crawlingResult) {
		this.crawlingResult = crawlingResult;
	}
}

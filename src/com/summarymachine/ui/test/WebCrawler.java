package com.summarymachine.ui.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebCrawler {
	private String contentType;
	private String webUrl;
	private StringBuilder crawlingResult;

	public WebCrawler(){
		crawlingResult = new StringBuilder();
	}
	
	public void crawliing(String webUrl) {
		URL url = null;
		try {
			setWebUrl(webUrl);
			url = new URL(webUrl);
			
			URLConnection urlConn = url.openConnection();
			setContentType(urlConn.getContentType().toUpperCase());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "EUC-KR"));
			
			String readLine = null;
			
			while((readLine = br.readLine())!=null) {
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

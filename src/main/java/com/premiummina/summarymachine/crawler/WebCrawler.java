package com.premiummina.summarymachine.crawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import com.premiummina.summarymachine.utils.Utils;

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

	public void crawling(String webUrl,int kindOfFile) {
		if (kindOfFile == Utils.WEB_DOCUMENT) {
			URL url = null;
			BufferedReader br = null;
			crawlingResult.setLength(0);
			try {
				setWebUrl(webUrl);
				url = new URL(webUrl);

				URLConnection urlConn = url.openConnection();
				setContentType(urlConn.getContentType().toUpperCase());

				br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "EUC-KR"));

				String readLine = null;

				while ((readLine = br.readLine()) != null) {
					crawlingResult.append(readLine);
				}
				
				br.close();

			} catch (Exception e) {
				try { if(br != null) br.close(); } catch (IOException ex) { ex.printStackTrace();}
				System.err.println(e);
			}
		} else if (kindOfFile == Utils.TEXT_DOCUMENT) {
			crawlingResult.setLength(0);
			try {
				setWebUrl(webUrl);
				
				FileInputStream stream = new FileInputStream(webUrl);
				RTFEditorKit kit = new RTFEditorKit();
				Document doc = kit.createDefaultDocument();
				kit.read(stream, doc, 0);
				 
				String plainText = doc.getText(0, doc.getLength());
				
				crawlingResult.append(new String(plainText.getBytes("8859_1"),"KSC5601"));
				

			} catch (Exception e) {
				System.err.println(e);
			}
			
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

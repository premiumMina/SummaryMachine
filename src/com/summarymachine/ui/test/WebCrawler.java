package com.summarymachine.ui.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebCrawler {
	private String contentType;
	private String webUrl;

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

	public WebCrawler(String webUrl) {

		URL url = null;
		try {
			setWebUrl(webUrl);
			url = new URL(webUrl);
		} catch (MalformedURLException el) {
			System.out.println("This is a wrong type.");
			System.out.println(el);
			System.exit(1);
		}

		FileOutputStream fos = null;
		try {
			URLConnection urlcon = url.openConnection();

			contentType = urlcon.getContentType().toUpperCase();

			long dl = urlcon.getDate();
			java.util.Date d = new java.util.Date(dl);

			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String sdate = format.format(d);

			System.out.println("Content Type : " + contentType);
			setContentType(contentType);
			System.out.println("Time : " + sdate);

			InputStream in = urlcon.getInputStream();

			fos = new FileOutputStream("test.txt");

			byte[] buffer = new byte[512];
			int readcount = 0;

			System.out.println("Reading.....");

			while ((readcount = in.read(buffer)) != -1) {
				fos.write(buffer, 0, readcount);
			}

			System.out.println("text.txt" + " SAVE Finish");

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
			}
		}
	}

}

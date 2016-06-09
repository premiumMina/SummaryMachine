package com.summarymachine.ui.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.sound.midi.Synthesizer;
import javax.swing.JTextField;

import com.summarymachine.ui.leftpanel.DocumentUrlPanel;

public class WebCrawler {

	public static void main(String arg[]) {

		URL url = null;
		try {
			/* 입력된 URL주소가 넘어와야 함. */
			url = new URL(
					"http://news.naver.com/main/read.nhn?oid=437&sid1=102&aid=0000118700&mid=shm&mode=LSD&nh=20160516225309");
		} catch (MalformedURLException el) {
			System.out.println("잘못된 URL 형식입니다.");
			System.out.println(el);
			System.exit(1);
		}

		FileOutputStream fos = null;
		try {
			/* URL 클래스 openConnection()으로 객체 얻는다. */
			URLConnection urlcon = url.openConnection();

			/* 웹 문서의 헤드에서 contentType 읽어 온다. */
			String contentType = urlcon.getContentType();

			long dl = urlcon.getDate();
			java.util.Date d = new java.util.Date(dl);

			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String sdate = format.format(d);

			System.out.println("Content Type : " + contentType);
			System.out.println("읽어온 시간 : " + sdate);

			InputStream in = urlcon.getInputStream();
			// URL 문서의 input stream을 불러온다.

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

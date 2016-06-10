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
			url = new URL(
					"http://news.naver.com/main/read.nhn?oid=437&sid1=102&aid=0000118700&mid=shm&mode=LSD&nh=20160516225309");
		} catch (MalformedURLException el) {
			System.out.println("This is a wrong type.");
			System.out.println(el);
			System.exit(1);
		}

		FileOutputStream fos = null;
		try {
			URLConnection urlcon = url.openConnection();

			String contentType = urlcon.getContentType();

			long dl = urlcon.getDate();
			java.util.Date d = new java.util.Date(dl);

			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String sdate = format.format(d);

			System.out.println("Content Type : " + contentType);
			System.out.println("�о�� �ð� : " + sdate);

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

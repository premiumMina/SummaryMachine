package com.summarymachine.ui.test;

import java.awt.RadialGradientPaint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.sound.midi.Synthesizer;

public class CrawlerInWeb {
	public static void main(String[] args) {
		try {
			BufferedReader br;
			String readText;

			br = new BufferedReader(new FileReader("C:\\Users\\MadPlay\\Desktop\\Mina\\SummaryMachine\\test.txt"));
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

					}
				}
				if (end) {
					break;
				}
			}
			Iterator iterator = articleList.iterator();
			String element = "";
			while (iterator.hasNext()) {
				element = (String) iterator.next();
			}

			for (int index = 0; index < element.length(); index++) {
				System.out.print(element.charAt(index));
				if (element.charAt(index) == '.') {
					System.out.println();
				}
			}

			br.close();

		} catch (IOException ie) {
			System.out.println("파일이 존재하지 않습니다.");

		} catch (Exception e) {
			System.out.println(e);
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
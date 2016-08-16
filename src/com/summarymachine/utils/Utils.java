package com.summarymachine.utils;

public class Utils {
	public static final int WEB_DOCUMENT = 0;
	
	/* Web URL에서는 사용하지 않음 */
	public static final int TEXT_DOCUMENT = 1;
	public static final String INPUT_FILE_PATH = "test.txt";
	
	/* 네이버 뉴스의 본문 필터 */
	public static final String NAVER_NEWS_BODY_START_FILTER = "<!-- 본문 내용 -->";
	public static final String NAVER_NEWS_BODY_END_FILTER = "<!-- // 본문 내용 -->";
}

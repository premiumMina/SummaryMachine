package com.premiummina.summarymachine.utils;

/**
 * 프로그램 유틸리티 클래스
 * 
 * @author premiumMina
 * created on 2016. 8. 10.
 */
public class Utils {
	public static final int WEB_DOCUMENT = 0;
	
	/* Web URL에서는 사용하지 않음 */
	public static final int TEXT_DOCUMENT = 1;
	public static final String INPUT_FILE_PATH = "test.txt";
	
	/* 네이버 뉴스의 본문 필터 */
	public static final String NAVER_NEWS_BODY_START_FILTER = "<!-- 본문 내용 -->";
	public static final String NAVER_NEWS_BODY_END_FILTER = "<!-- // 본문 내용 -->";
}

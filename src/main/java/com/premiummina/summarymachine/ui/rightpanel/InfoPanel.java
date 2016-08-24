package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 세 번째 패널(개발자 소개 탭)
 * 
 * @author premiumMina
 * created on 2016. 8. 13.
 */
public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public InfoPanel() {
		String info = " news.naver.com 에서 제공하는 모든 기사를 요약하는 프로그램입니다." + "\n "
				+ "웹 크롤링, 형태소 분석을 통해서 문장과 단어에 가중치를 부여하여 가중치가 높은 문장을 추출했습니다." + "\n "
				+ "사용자 검색 질의를 통해 HTML태그 패턴 분석, HTML태그 제거를 통해서 정확도와 신뢰도 높은 내용을 추출합니다.";
		this.setLayout(new GridLayout(3, 1));
		TitledBorder infoTextBorder = new TitledBorder("Program");
		JTextArea infoProgram = new JTextArea();
		infoProgram.setText(info);
		infoProgram.setBorder(infoTextBorder);
		add(infoProgram);

//		JLabel image= new JLabel(new ImageIcon("infoimage.png"));
//		image.setBounds(100, 100, 100, 100);
//		add(image);
		String developer = "성신여자대학교 IT학부" + "\n " + "20121172 김민아" + "\n " 
				+ "premiummina@naver.com" + "\n "+ "gitHub : github.com/premiumMina";
		TitledBorder developerBorder = new TitledBorder("Developer Introduction");
		JTextArea developerText = new JTextArea();
		developerText.setText(developer); 
		developerText.setBorder(developerBorder);
		add(developerText);
	}
}
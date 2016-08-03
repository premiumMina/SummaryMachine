package com.summarymachine.ui.rightpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class WordGraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> wordAnalyze;
	private int count = 0;
	private String[] resultKey = new String[10];
	private Integer[] resultValue = new Integer[10];

	public Map<String, Integer> getWordAnalyze() {
		return wordAnalyze;
	}

	public void setWordAnalyze(Map<String, Integer> wordAnalyze) {
		this.wordAnalyze = wordAnalyze;
	}

	public WordGraphPanel() {
		setSize(new Dimension(450, 200));
		setBackground(Color.WHITE);
		TitledBorder border = new TitledBorder("Word Frequency");
		setBorder(border);
		setVisible(true);
	}

	/* 단어와 가중치 저장하는 배열 만들기 */
	public void showGraph() {
		Iterator iterator = wordAnalyze.entrySet().iterator();
		while (iterator.hasNext()) {
			// if (wordAnalyze.get((String) iterator.next()) > 2)
			Entry entry = (Entry) iterator.next();
			resultKey[count] = (String) entry.getKey();
			resultValue[count] = (Integer) entry.getValue();
			count++;
			if (count == 10) {
				break;
			}
		}

		this.repaint();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);// 부모 패인트호출

		g.clearRect(0, 0, getWidth(), getHeight());

		/* g.drawLine(5, 100, 500, 250); */
		for (int cnt = 16; cnt >= 0; cnt--) {
			g.drawString(cnt * 2 + " ", 25, 35 + 20 * cnt);
			g.drawLine(50, 190 - 10 * cnt, 450, 190 - 10 * cnt);
		}
		// http://egloos.zum.com/bluewins/v/1022912

		/* 오른쪽 세로선 */
		g.drawLine(50, 30, 50, 190);
		for (int i = 0; i < count; i++) {
			/* 가로축 */
			g.drawString(resultKey[i], 60 + 40 * i, resultValue[i] * 10 + 45);
			g.setColor(Color.BLACK);
			/* 막대 그래프 */
			g.fillRect(70 + 40 * i, 30, 10, resultValue[i] * 10);

		}
	}

	// http://devblog.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-Graphics-%EC%9B%90-%EA%B7%B8%EB%9E%98%ED%94%84-%EA%B7%B8%EB%A6%AC%EA%B8%B0
}

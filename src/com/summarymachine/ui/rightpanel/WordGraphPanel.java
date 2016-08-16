package com.summarymachine.ui.rightpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class WordGraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> wordWeight;
	private int count = 0;
	private String[] resultKey = new String[10];
	private Integer[] resultValue = new Integer[10];

	public Map<String, Integer> getWordWeight() {
		return wordWeight;
	}

	public void setWordWeight(Map<String, Integer> wordWeight) {
		this.wordWeight = wordWeight;
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
		/* 단어 가중치 큰 순서대로 정렬해야 함.*/
		Iterator iterator = wordWeight.entrySet().iterator();
		while (iterator.hasNext()) {
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

		for (int cnt = 16; cnt >= 0; cnt--) {
			g.drawString(cnt * 2 + " ", 25, 35 + 20 * cnt);
			g.drawLine(50, 190 - 10 * cnt, 450, 190 - 10 * cnt);
		}

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

}

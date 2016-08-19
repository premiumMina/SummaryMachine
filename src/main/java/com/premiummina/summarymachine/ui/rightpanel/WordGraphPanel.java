package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * 단어 빈도수 그래프 출력 패널
 * 
 * @author premiumMina
 * created on 2016. 8. 5.
 */
public class WordGraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> wordWeightMap;
	private int count = 0;
	private String[] resultKey = new String[10];
	private Integer[] resultValue = new Integer[10];

	public WordGraphPanel() {
		setSize(new Dimension(450, 200));
		setBackground(Color.WHITE);
		TitledBorder border = new TitledBorder("Word Frequency");
		setBorder(border);
		setVisible(true);
		
		for (int index = 0; index < 10; index++) {
			resultKey[index] = new String();
		}
	}

	/* 단어와 가중치 저장하는 배열 만들기 */
	public void showGraph() {
		/* 단어 가중치 큰 순서대로 정렬해야 함.*/
		Iterator<String> iterator = WordGraphPanel.sortMap(wordWeightMap).iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			resultKey[count] = key;
			resultValue[count] = wordWeightMap.get(key);
			count++;
			if (count == 10) {
				break;
			}
		}
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.clearRect(0, 0, getWidth(), getHeight());
		/* 가로축 세로 */
		g.drawLine(50, 30, 450, 30);
		g.drawLine(50, 30, 50, 350);
		for (int i = 0; i < count; i++) {
			g.setColor(Color.BLACK);
			g.fillRect(70 + 40 * i, 30, 10, 300 - i * 20);
			g.drawString(resultKey[i] + " " + resultValue[i], 70 + 40 * i, 350 - i * 20);
		}
	}

	private static List sortMap(final Map map) {
		List<String> sortedWordList = new ArrayList();
		sortedWordList.addAll(map.keySet());

		Collections.sort(sortedWordList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v1).compareTo(v2);
			}
		});
		Collections.reverse(sortedWordList);
		return sortedWordList;
	}

	public Map<String, Integer> getWordWeightMap() {
		return wordWeightMap;
	}

	public void setWordWeightMap(Map<String, Integer> wordWeightMap) {
		this.wordWeightMap = wordWeightMap;
	}
}
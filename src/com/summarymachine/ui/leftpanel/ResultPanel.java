package com.summarymachine.ui.leftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JViewport;

import com.summarymachine.ui.rightpanel.RightPanel;
import com.summarymachine.ui.rightpanel.WordAccuracyPanel;

public class ResultPanel extends JPanel {
	private KeywordPanel keywordPanel;
	private WordAccuracyPanel wordAccuracyPanel;
	private RightPanel rightPanel;

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setKeywordPanel(KeywordPanel keywordPanel) {
		this.keywordPanel = keywordPanel;
	}

	public void setWordAccuracyPanel(WordAccuracyPanel wordAccuracyPanel) {
		this.wordAccuracyPanel = wordAccuracyPanel;
	}

	public ResultPanel() {
		this.setLayout(null);
		JButton searchBtn = new JButton("search");
		searchBtn.setBounds(240, 10, 80, 25);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (keywordPanel.getCheckBox().isSelected())
					rightPanel.getWordAccuracyPanel().setKeywordText((keywordPanel.getKeyword()));
				else
					System.out.println("no keyword");

			}
		});
		this.add(searchBtn);
	}
}

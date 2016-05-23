package com.summarymachine.ui.rightpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.summarymachine.ui.leftpanel.KeywordPanel;

public class WordAccuracyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea keywordText;
	private JTextArea keywordAccuracy;
	private KeywordPanel keywordPanel;

	public void setKeywordPanel(KeywordPanel keywordPanel) {
		this.keywordPanel = keywordPanel;
	}

	public void setKeywordText(String keyword) {
		keywordText.setText(keyword);
	}

	public WordAccuracyPanel() {
		this.setLayout(null);
		TitledBorder border = new TitledBorder("Word Accuracy");
		this.setBorder(border);

		JLabel wordLabel = new JLabel("Word");
		wordLabel.setBounds(20, 30, 60, 20);
		this.add(wordLabel);

		keywordText = new JTextArea(1, 5);
		keywordText.setBounds(100, 30, 100, 20);
		keywordText.setEditable(false);
		this.add(keywordText);

		JLabel accuracyLabel = new JLabel("Accuracy");
		accuracyLabel.setBounds(20, 60, 60, 20);
		this.add(accuracyLabel);

		keywordAccuracy = new JTextArea(1, 5);
		keywordAccuracy.setBounds(100, 60, 100, 20);
		keywordAccuracy.setEditable(false);
		this.add(keywordAccuracy);

	}
}

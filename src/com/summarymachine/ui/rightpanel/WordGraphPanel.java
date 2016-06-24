package com.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class WordGraphPanel extends JPanel {
	public WordGraphPanel() {
		this.setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Word Frequency");
		this.setBorder(border);

		JTextArea summaryGraphField = new JTextArea(5, 10);
		summaryGraphField.setEditable(false);
		this.add(summaryGraphField);
		
		JScrollPane scrollPane = new JScrollPane(summaryGraphField);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
	}
}

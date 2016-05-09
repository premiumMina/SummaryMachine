package com.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class WordGraphPanel extends JPanel {
	public WordGraphPanel() {
		this.setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Word Frequency");
		this.setBorder(border);

		JTextArea summaryTextField = new JTextArea(5, 10);
		summaryTextField.setEditable(false);
		this.add(summaryTextField);
	}
}

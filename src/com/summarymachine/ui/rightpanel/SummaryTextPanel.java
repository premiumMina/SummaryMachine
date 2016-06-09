package com.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class SummaryTextPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea summaryTextField;

	public void setSummaryTextField(String content) {
		summaryTextField.setText(content);
	}

	public SummaryTextPanel() {
		this.setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Text");
		this.setBorder(border);

		summaryTextField = new JTextArea(5, 10);
		summaryTextField.setEditable(false);
		this.add(summaryTextField);

	}
}

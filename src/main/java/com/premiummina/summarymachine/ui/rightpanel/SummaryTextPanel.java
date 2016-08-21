package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 내용 3줄 추출
 * 
 * @author premiumMina
 * created on 2016. 7. 28.
 */
public class SummaryTextPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea summaryTextField;

	public void setSummaryTextField(String summaryText) {
		summaryTextField.setText(summaryText);
	}

	public SummaryTextPanel() {
		this.setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Text");
		this.setBorder(border);

		summaryTextField = new JTextArea(5, 10);
		summaryTextField.setLineWrap(true);
		summaryTextField.setEditable(false);
		this.add(summaryTextField);

		JScrollPane scrollPane = new JScrollPane(summaryTextField);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
	}
}
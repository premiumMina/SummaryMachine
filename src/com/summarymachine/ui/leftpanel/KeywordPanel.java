package com.summarymachine.ui.leftpanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class KeywordPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JCheckBox keywordBtn;
	private JTextField keywordField;

	public String getKeyword() {
		return keywordField.getText();
	}

	public JCheckBox getCheckBox() {
		return keywordBtn;
	}

	public KeywordPanel() {
		this.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("Option");
		this.setBorder(border);

		keywordBtn = new JCheckBox("Keyword");
		keywordBtn.setSize(60, 25);
		this.add(keywordBtn);

		keywordField = new JTextField(20);
		keywordField.setForeground(Color.BLACK);
		keywordField.setSize(150, 25);
		this.add(keywordField);
	}
}

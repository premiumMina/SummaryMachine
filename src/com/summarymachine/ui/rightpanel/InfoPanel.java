package com.summarymachine.ui.rightpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public InfoPanel() {
		this.setLayout(new GridLayout(2, 1));

		TitledBorder infoTextBorder = new TitledBorder("Program");
		JTextArea infoProgram = new JTextArea();
		infoProgram.setBorder(infoTextBorder);
		add(infoProgram);

		TitledBorder developerBorder = new TitledBorder("Developer Introduction");
		JTextArea developerText = new JTextArea();
		developerText.setBorder(developerBorder);
		add(developerText);

	}
}
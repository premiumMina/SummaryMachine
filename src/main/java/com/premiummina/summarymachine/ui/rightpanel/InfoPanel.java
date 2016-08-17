package com.premiummina.summarymachine.ui.rightpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 세 번째 패널(개발자 소개 탭)
 * 
 * @author premiumMina
 * created on 2016. 8. 13.
 */
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
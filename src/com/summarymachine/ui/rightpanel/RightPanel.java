package com.summarymachine.ui.rightpanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RightPanel extends JPanel {
	private int panelWidth;
	private int panelHeight;

	public RightPanel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
	}

	public void rightComponents(Container contentPane) {
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setLayout(new GridLayout(1, 1));

		JPanel rightFirstPage = new JPanel();
		rightFirstPage.setLayout(null);
		JPanel rightSecondPage = new JPanel();
		JPanel rightThirdPage = new JPanel();

		rightFirstPage.setLayout(null);

		JTabbedPane jtb = new JTabbedPane();
		jtb.add("First page", rightFirstPage);
		jtb.add("Second page", rightSecondPage);
		jtb.add("Third page", rightThirdPage);
		jtb.setBounds(0, 0, panelWidth, 20);
		this.add(jtb);

	}
}

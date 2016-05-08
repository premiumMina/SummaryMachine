package com.summarymachine.ui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.summarymachine.ui.leftpanel.LeftPanel;
import com.summarymachine.ui.rightpanel.RightPanel;

public class SummaryMachine extends JFrame {
	private static final long serialVersionUID = 1L;
	boolean continuousLayout = true;
	private final int screenWidth = 900;
	private final int screenHeight = 600;

	public SummaryMachine() {
		setTitle("Text Summary");
		setSize(screenWidth, screenHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents(getContentPane());
		setVisible(true);
	}

	private void initComponents(Container contentPane) {

		this.setLayout(new FlowLayout());
		JPanel mainPanel = new JPanel();

		RightPanel rightAllPanel = new RightPanel((screenWidth / 2 + 100), screenHeight - 50);
		LeftPanel leftAllPanel = new LeftPanel((screenWidth / 3), screenHeight - 50);

		rightAllPanel.rightComponents(getContentPane());
		leftAllPanel.leftComponents(getContentPane());

		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		jsp.add(rightAllPanel, JSplitPane.RIGHT);
		jsp.add(leftAllPanel, JSplitPane.LEFT);

		mainPanel.add(jsp);
		contentPane.add(mainPanel);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		new SummaryMachine();
	}

}

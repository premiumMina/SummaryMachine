package com.summarymachine.ui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.summarymachine.ui.leftpanel.LeftPanel;
import com.summarymachine.ui.rightpanel.RightPanel;

/**
 * 메인 프레임
 * @author PremiumMina
 */
public class SummaryMachine extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int screenWidth = 900;
	private final int screenHeight = 600;

	public SummaryMachine() {
		setTitle("Summary Machine");
		setSize(screenWidth, screenHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents(getContentPane());
		setVisible(true);
	}

	/**
	 * 모든 컴포넌트를 초기화한다.
	 * @author PremiumMina
	 */
	private void initComponents(Container contentPane) {

		this.setLayout(new FlowLayout());
		JPanel mainPanel = new JPanel();

		RightPanel rightAllPanel = new RightPanel((screenWidth / 2 + 100), screenHeight - 50);
		rightAllPanel.initRightComponents();
		
		LeftPanel leftAllPanel = new LeftPanel((screenWidth / 3), screenHeight - 50);
		leftAllPanel.setRightPanel(rightAllPanel); 
		leftAllPanel.initLeftComponents();

		/* 좌우 영역으로 분할한다. */
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.add(rightAllPanel, JSplitPane.RIGHT);
		jSplitPane.add(leftAllPanel, JSplitPane.LEFT);

		mainPanel.add(jSplitPane);
		contentPane.add(mainPanel);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new SummaryMachine();
	}
}
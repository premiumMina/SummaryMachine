package com.premiummina.summarymachine.ui.main;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.premiummina.summarymachine.jdbc.MySQLConn;
import com.premiummina.summarymachine.ui.leftpanel.LeftPanel;
import com.premiummina.summarymachine.ui.rightpanel.RightPanel;

/**
 * 메인 프레임 및 프로그램 시작
 * 
 * @author premiumMina
 * created on 2016. 6. 30.
 */
public class SummaryMachine extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int screenWidth = 1000;
	private final int screenHeight = 1000;

	public SummaryMachine() {
		setTitle("Summary Machine");
		setSize(screenWidth, screenHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents(getContentPane());
		setVisible(true);
	}

	/**
	 * 모든 컴포넌트를 초기화한다.
	 * 
	 * @author PremiumMina
	 */
	private void initComponents(Container contentPane) {

		this.setLayout(new FlowLayout());
		JPanel mainPanel = new JPanel();

		RightPanel rightAllPanel = new RightPanel((screenWidth / 2 + 150), screenHeight - 50);
		rightAllPanel.initRightComponents();

		LeftPanel leftAllPanel = new LeftPanel((screenWidth / 3 - 50), screenHeight - 50);
		leftAllPanel.setRightPanel(rightAllPanel);
		leftAllPanel.initLeftComponents();

		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.add(rightAllPanel, JSplitPane.RIGHT);
		jSplitPane.add(leftAllPanel, JSplitPane.LEFT);

		mainPanel.add(jSplitPane);
		contentPane.add(mainPanel);
		setResizable(false);
	}

	public static void main(String[] args) {
		new MySQLConn(); /* 사용자의 히스토리를 출력하기 위해 미리 호출한다. */
		new SummaryMachine();
	}
}
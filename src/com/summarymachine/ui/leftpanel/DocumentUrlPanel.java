package com.summarymachine.ui.leftpanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class DocumentUrlPanel extends JPanel {
	public DocumentUrlPanel() {
		this.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("File Path");
		this.setBorder(border);

		JLabel fileLabel = new JLabel("File Path");
		fileLabel.setVerticalAlignment(SwingConstants.CENTER);
		fileLabel.setSize(60, 20);
		this.add(fileLabel);

		JTextField urlField = new JTextField(15);
		urlField.setForeground(Color.BLACK);
		urlField.setSize(150, 25);
		this.add(urlField);

		JButton upLoadBtn = new JButton("upload");
		upLoadBtn.setSize(100, 25);
		this.add(upLoadBtn);
	}
}

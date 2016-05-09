package com.summarymachine.ui.leftpanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class UserIdCheckPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public UserIdCheckPanel() {
		this.setLayout(new FlowLayout());

		TitledBorder border = new TitledBorder("User");
		this.setBorder(border);

		JLabel idLabel = new JLabel("User ID");
		idLabel.setVerticalAlignment(SwingConstants.CENTER);
		idLabel.setSize(60, 20);
		this.add(idLabel);

		JTextField idField = new JTextField(15);
		idField.setForeground(Color.BLACK);
		idField.setSize(150, 25);
		this.add(idField);

		JButton idOkBtn = new JButton("Id Check");
		idOkBtn.setSize(100, 25);
		this.add(idOkBtn);
	}
}

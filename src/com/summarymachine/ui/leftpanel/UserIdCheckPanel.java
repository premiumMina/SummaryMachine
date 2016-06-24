package com.summarymachine.ui.leftpanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class UserIdCheckPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField idField;

	public String getIdField() {
		return idField.getText();
	}

	public UserIdCheckPanel() {
		this.setLayout(new FlowLayout());

		TitledBorder border = new TitledBorder("User");
		this.setBorder(border);

		JLabel idLabel = new JLabel("User ID");
		idLabel.setVerticalAlignment(SwingConstants.CENTER);
		idLabel.setSize(60, 20);
		this.add(idLabel);

		idField = new JTextField(15);
		idField.setForeground(Color.BLACK);
		idField.setSize(150, 25);
		this.add(idField);

		JButton idOkBtn = new JButton("Id Check");
		idOkBtn.setSize(100, 25);
		idOkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// id duplication check
			}
		});
		this.add(idOkBtn);
	}
}

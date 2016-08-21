package com.premiummina.summarymachine.ui.leftpanel;

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

/**
 * 웹 페이지의 주소를 입력하는 패널
 * 
 * @author premiumMina
 * created on 2016. 7. 13.
 */
public class FilePathPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField urlField;
	private JButton upLoadBtn;

	public FilePathPanel() {
		this.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("File Path");
		this.setBorder(border);

		JLabel fileLabel = new JLabel("File Path");
		fileLabel.setVerticalAlignment(SwingConstants.CENTER);
		fileLabel.setSize(60, 20);
		this.add(fileLabel);

		urlField = new JTextField(15);
		urlField.setForeground(Color.BLACK);
		urlField.setSize(150, 25);
		this.add(urlField);

		upLoadBtn = new JButton("upload");
		upLoadBtn.setSize(100, 25);
		upLoadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();
				fileSelector.showOpenDialog(FilePathPanel.this);

				if (fileSelector.getSelectedFile().exists()) {
					urlField.setText(fileSelector.getSelectedFile().getName());
					urlField.setEditable(false);
				}
			}
		});
		this.add(upLoadBtn);
	}

	public void setUrlField(String urlField) {
		this.urlField.setText(urlField);
	}

	public String getUrlField() {
		return urlField.getText();
	}
}
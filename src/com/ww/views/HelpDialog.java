package com.ww.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
/*
import javax.swing.JScrollBar;
import java.awt.Point;
import java.awt.ScrollPane;
*/

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class HelpDialog extends JDialog {

	// get rid of a warning about serialization.
	private static final long serialVersionUID = 19837505L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		try {
			HelpDialog dialog = new HelpDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	**/

	/**
	 * Create the dialog.
	 */
	public HelpDialog(  JFrame mf, String title, boolean modal) {
		// call super and force modal
		super(mf, title, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		
		setTitle("About ImpressShowRunner");
		setBounds(100, 100, 450, 507);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ImpressShowRunner");
			lblNewLabel.setBounds(101, 10, 183, 25);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Version 1.0");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(142, 49, 109, 13);
			contentPanel.add(lblNewLabel_1);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 79, 405, 328);
		contentPanel.add(scrollPane);
		
		JTextPane txtHelp = new JTextPane();
		txtHelp.setAutoscrolls(false);
		scrollPane.setViewportView(txtHelp);

		txtHelp.setEditable(false);
		txtHelp.setText("ImpressShowRunner is a program to chain together multiple LibreOffice impress slide shows.\r\n\r\nEach show in the sequence is set under Slide Settings so that mouse clicks do not advance the slides. This is important. ImpressShowRunner launches each show in the sequence and then clicks the mouse every few seconds and when the last black screen is shown, the mouse click terminates the show and Impress exits. ImpressShowRunner notices the process exit and then starts the next show. When the last show ends, ImpressShowRunner just starts the list again.\r\n\r\nThis complicated process gets around the fact that LibreOffice Impress apparently has no options or scripting features available to accomplish chaining and looping of multiple slide shows together.\r\n\r\nThe reason for wanting to chain multiple shows is that after 100 or so slides with images the save times and editing for shows becomes cumbersome. This is quite reasonable for at this point the show file size may become 100MB or more.\r\n\r\nFeatures\r\n\r\n    Launches Impress Slide Shows\r\n    Plays a sequence of slide shows\r\n    Loops the list\r\n    Requires no interaction between shows\r\n\r\nReleased under GPL 3.0\r\nDarrell Duffy\r\nApril 2020");

		txtHelp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHelp.setCaretPosition(0);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close(); // close the modal dialog
					}
				});
				btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnClose.setActionCommand("Close");
				buttonPane.add(btnClose);
			}
		}
	}

	// see if this closes the window
	// note it is set to DISPOSE_ON_CLOSE
	protected void close() {
		HelpDialog.this.setVisible(false);
		HelpDialog.this.dispatchEvent(new WindowEvent(
				HelpDialog.this, WindowEvent.WINDOW_CLOSING));
		
	}
}

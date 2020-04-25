package com.ww.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.List;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

public class ImpressShowRunnerView {

	private JFrame frmGuiGroupLayout;
	private JTextField tfImpressPath;
	private JTextField tfOptions;
	private JTextField tfShowPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImpressShowRunnerView window = new ImpressShowRunnerView();
					window.frmGuiGroupLayout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImpressShowRunnerView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuiGroupLayout = new JFrame();
		frmGuiGroupLayout.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmGuiGroupLayout.setMinimumSize(new Dimension(580, 580));
		frmGuiGroupLayout.setTitle("ImpressShowRunner");
		frmGuiGroupLayout.setBounds(100, 100, 620, 589);
		frmGuiGroupLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfImpressPath = new JTextField();
		tfImpressPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfImpressPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Path to Impress");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBrowseForImpress = new JButton("...");
		btnBrowseForImpress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAddShow = new JButton("Add Show");
		btnAddShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOptions.setHorizontalAlignment(SwingConstants.LEFT);
		
		tfOptions = new JTextField();
		tfOptions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfOptions.setColumns(10);
		
		JLabel lblShowToAdd = new JLabel("Show to Add");
		lblShowToAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfShowPath = new JTextField();
		tfShowPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfShowPath.setColumns(10);
		
		JButton btnBrowseForShow = new JButton("...");
		btnBrowseForShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblListOfShows = new JLabel("List of Shows");
		lblListOfShows.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnTop = new JButton("Top");
		btnTop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnUp = new JButton("Up");
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDown = new JButton("Down");
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRemoveSelected = new JButton("Remove Selected");
		btnRemoveSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPaneShows = new JScrollPane();
		scrollPaneShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnStopShows = new JButton("Stop Shows");
		btnStopShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnStartShows = new JButton("Start Shows");
		btnStartShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JCheckBox ckbxBeepAtEnd = new JCheckBox("Beep on End");
		ckbxBeepAtEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Delay in Secs");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JSpinner spnSecsBetweenShows = new JSpinner();
		spnSecsBetweenShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnSecsBetweenShows.setModel(new SpinnerNumberModel(5, 5, 99, 5));
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frmGuiGroupLayout.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ckbxBeepAtEnd)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(spnSecsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnStartShows)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStopShows)
							.addGap(9)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnClose)
					.addGap(25))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListOfShows, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(tfShowPath, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBrowseForShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddShow))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblOptions, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(tfOptions, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(tfImpressPath, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBrowseForImpress))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(32)
									.addComponent(btnTop, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveSelected, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
							.addGap(29))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShowToAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(499, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfImpressPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseForImpress))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptions)
						.addComponent(tfOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(lblShowToAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfShowPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseForShow)
						.addComponent(btnAddShow))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTop)
						.addComponent(btnUp)
						.addComponent(btnDown)
						.addComponent(btnRemoveSelected)
						.addComponent(btnRemoveAll))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblListOfShows)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartShows)
						.addComponent(ckbxBeepAtEnd)
						.addComponent(btnStopShows)
						.addComponent(lblNewLabel_1)
						.addComponent(spnSecsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(btnClose))
					.addGap(23))
		);
		
		List listShows = new List();
		scrollPaneShows.setViewportView(listShows);
		frmGuiGroupLayout.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frmGuiGroupLayout.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		mnFileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenShowList = new JMenuItem("Open Show List...");
		mntmOpenShowList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmOpenShowList);
		
		JMenuItem mntmSaveShowList = new JMenuItem("Save Show List...");
		mntmSaveShowList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmSaveShowList);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Save Defaults");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmRestoreDefaults = new JMenuItem("Restore Defaults");
		mntmRestoreDefaults.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmRestoreDefaults);
		
		JSeparator separator_1 = new JSeparator();
		mnFileMenu.add(separator_1);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmQuit);
		
		JMenu mnHelpMenu = new JMenu("Help");
		mnHelpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnHelpMenu);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHelpMenu.add(mntmAbout);
	}
}

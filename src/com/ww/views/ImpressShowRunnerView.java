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
		frmGuiGroupLayout.setMinimumSize(new Dimension(580, 580));
		frmGuiGroupLayout.setTitle("Gui Group Layout");
		frmGuiGroupLayout.setBounds(100, 100, 581, 582);
		frmGuiGroupLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnClose = new JButton("CLose");
		
		tfImpressPath = new JTextField();
		tfImpressPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Path to Impress");
		
		JButton btnBrowseForImpress = new JButton("...");
		
		JButton btnAddShow = new JButton("Add Show");
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.LEFT);
		
		tfOptions = new JTextField();
		tfOptions.setColumns(10);
		
		JLabel lblShowToAdd = new JLabel("Show to Add");
		
		tfShowPath = new JTextField();
		tfShowPath.setColumns(10);
		
		JButton btnBrowseForShow = new JButton("...");
		
		JLabel lblListOfShows = new JLabel("List of Shows");
		
		JButton btnTop = new JButton("Top");
		
		JButton btnUp = new JButton("Up");
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRemoveSelected = new JButton("Remove Selected");
		
		JButton btnRemoveAll = new JButton("Remove All");
		
		JScrollPane scrollPaneShows = new JScrollPane();
		
		JButton btnStopShows = new JButton("Stop Shows");
		
		JButton btnStartShows = new JButton("Start Shows");
		
		JCheckBox ckbxBeepAtEnd = new JCheckBox("Beep on End of Show");
		
		JLabel lblNewLabel_1 = new JLabel("Secs Between Shows");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JSpinner spnSecsBetweenShows = new JSpinner();
		spnSecsBetweenShows.setModel(new SpinnerNumberModel(5, 5, 99, 5));
		
		JLabel lblStatus = new JLabel("status");
		GroupLayout groupLayout = new GroupLayout(frmGuiGroupLayout.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListOfShows, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(tfShowPath, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBrowseForShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddShow))
								.addComponent(lblShowToAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblOptions, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfOptions, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(tfImpressPath, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBrowseForImpress))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(32)
									.addComponent(btnTop, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveSelected, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
							.addGap(29))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ckbxBeepAtEnd, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spnSecsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnStartShows, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStopShows, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(9)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnClose)
					.addGap(25))
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
					.addGap(6)
					.addComponent(lblShowToAdd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
					.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartShows)
						.addComponent(spnSecsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(ckbxBeepAtEnd)
						.addComponent(btnStopShows))
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
		frmGuiGroupLayout.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenShowList = new JMenuItem("Open Show List...");
		mnFileMenu.add(mntmOpenShowList);
		
		JMenuItem mntmSaveShowList = new JMenuItem("Save Show List...");
		mnFileMenu.add(mntmSaveShowList);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Save Defaults");
		mnFileMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmRestoreDefaults = new JMenuItem("Restore Defaults");
		mnFileMenu.add(mntmRestoreDefaults);
		
		JSeparator separator_1 = new JSeparator();
		mnFileMenu.add(separator_1);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFileMenu.add(mntmQuit);
		
		JMenu mnFontMenu = new JMenu("Font");
		menuBar.add(mnFontMenu);
		
		JMenuItem mntmFontSize10 = new JMenuItem("Font Size 10");
		mnFontMenu.add(mntmFontSize10);
		
		JMenuItem mntmFontSize14 = new JMenuItem("Font Size 14");
		mnFontMenu.add(mntmFontSize14);
		
		JMenuItem mntmFontSize16 = new JMenuItem("Font Size 16");
		mnFontMenu.add(mntmFontSize16);
		
		JMenu mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mnHelpMenu.add(mntmAbout);
	}
}

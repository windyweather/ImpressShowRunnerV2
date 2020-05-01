package com.ww.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.List;
import java.awt.Window;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JFrame;

public abstract class ImpressShowRunnerView implements ActionListener{

	// get rid of a warning about serialization.
	private static final long serialVersionUID = 19837502L;
	
	//private JPanel contentPane;
	
	protected JScrollPane scrollPaneShows;
	// Create the showList and listShows up here so we have some more control
	// and can access these from the child class more easily
	// very nice that this change does not mess up parsing by window builder
	protected DefaultListModel<String>showList = new DefaultListModel<String>();
	protected JList<String> listShows = new JList<String>(showList);
	
	static public JFrame frmGuiGroupLayout;
	protected JTextField tfImpressPath;
	protected JTextField tfOptions;
	protected JTextField tfShowPath;
	protected JLabel lblStatus;
	protected JCheckBox ckbxBeepOnEnd;
	protected JSpinner spSecondsBetweenShows;
	

	/**
	 * Launch the application.
	 */
	/*
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
	/*
*/
	
	
	/**
	 * Create the application.
	 */
	public ImpressShowRunnerView() {
		initialize();
		
		frmGuiGroupLayout.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "frmGuiGroupLayout windowClosing" );
				// Find ourselves in the great beyond
				//ImpressShowRunnerView win = (ImpressShowRunnerView)e.getWindow();
				//win.windowClosingEvent(e); // call us, which will call our child too
			}
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frmGuiGroupLayout = new JFrame();
		frmGuiGroupLayout.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmGuiGroupLayout.setMinimumSize(new Dimension(580, 580));
		frmGuiGroupLayout.setTitle("ImpressShowRunner");
		frmGuiGroupLayout.setBounds(100, 100, 620, 589);
		frmGuiGroupLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		JButton btnClose = new JButton("Close");
		btnClose.setActionCommand("btnClose");
		btnClose.addActionListener(this);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfImpressPath = new JTextField();
		tfImpressPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfImpressPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Path to Impress");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBrowseForImpress = new JButton("...");
		btnBrowseForImpress.setActionCommand("btnBrowseForImpress");
		btnBrowseForImpress.addActionListener(this);
		btnBrowseForImpress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAddShow = new JButton("Add Show");
		btnAddShow.setActionCommand("btnAddShow");
		btnAddShow.addActionListener(this);
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
		btnBrowseForShow.setActionCommand("btnBrowseForShow");
		btnBrowseForShow.addActionListener(this);
		btnBrowseForShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblListOfShows = new JLabel("List of Shows");
		lblListOfShows.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnTop = new JButton("Top");
		btnTop.setActionCommand("btnTop");
		btnTop.addActionListener(this);
		btnTop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnUp = new JButton("Up");
		btnUp.setActionCommand("btnUp");
		btnUp.addActionListener(this);
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDown = new JButton("Down");
		btnDown.setActionCommand("btnDown");
		btnDown.addActionListener(this);
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnRemoveSelected = new JButton("Remove Selected");
		btnRemoveSelected.setActionCommand("btnRemoveSelected");
		btnRemoveSelected.addActionListener(this);
		btnRemoveSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setActionCommand("btnRemoveAll");
		btnRemoveAll.addActionListener(this);
		btnRemoveAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPaneShows = new JScrollPane();
		scrollPaneShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnStopShows = new JButton("Stop Shows");
		btnStopShows.setActionCommand("btnStopShows");
		btnStopShows.addActionListener(this);
		btnStopShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnStartShows = new JButton("Start Shows");
		btnStartShows.setActionCommand("btnStartShows");
		btnStartShows.addActionListener(this);
		btnStartShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ckbxBeepOnEnd = new JCheckBox("Beep on End");
		ckbxBeepOnEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Delay in Secs");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spSecondsBetweenShows = new JSpinner();
		spSecondsBetweenShows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spSecondsBetweenShows.setModel(new SpinnerNumberModel(5, 5, 99, 5));
		
		lblStatus = new JLabel("status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frmGuiGroupLayout.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ckbxBeepOnEnd)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(spSecondsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnStartShows)
							.addGap(15)
							.addComponent(btnStopShows)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(btnClose)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListOfShows, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(tfShowPath, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBrowseForShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddShow))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblOptions, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
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
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
							.addGap(29))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShowToAdd, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(453, Short.MAX_VALUE))
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
					.addComponent(scrollPaneShows, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartShows)
						.addComponent(ckbxBeepOnEnd)
						.addComponent(lblNewLabel_1)
						.addComponent(spSecondsBetweenShows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStopShows))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(btnClose))
					.addGap(23))
		);
		
		//listShows = new JList<String>(showList);
		scrollPaneShows.setViewportView(listShows);
		frmGuiGroupLayout.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frmGuiGroupLayout.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		mnFileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenShowList = new JMenuItem("Open Show List...");
		mntmOpenShowList.setActionCommand("mntmOpenShowList");
		mntmOpenShowList.addActionListener(this);
		mntmOpenShowList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmOpenShowList);
		
		JMenuItem mntmSaveShowList = new JMenuItem("Save Show List...");
		mntmSaveShowList.setActionCommand("mntmSaveShowList");
		mntmSaveShowList.addActionListener(this);
		mntmSaveShowList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmSaveShowList);
		
		JMenuItem mntmCheckShowList = new JMenuItem("Check Show List");
		mntmCheckShowList.setActionCommand("mntmCheckShowList");
		mntmCheckShowList.addActionListener(this);
		mntmCheckShowList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmCheckShowList);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		JMenuItem mntmSaveDefaults = new JMenuItem("Save Defaults");
		mntmSaveDefaults.setActionCommand("mntmSaveDefaults");
		mntmSaveDefaults.addActionListener(this);
		mntmSaveDefaults.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmSaveDefaults);
		
		JMenuItem mntmRestoreDefaults = new JMenuItem("Restore Defaults");
		mntmRestoreDefaults.setActionCommand("mntmRestoreDefaults");
		mntmRestoreDefaults.addActionListener(this);
		mntmRestoreDefaults.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmRestoreDefaults);
		
		JSeparator separator_1 = new JSeparator();
		mnFileMenu.add(separator_1);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setActionCommand("mntmQuit");
		mntmQuit.addActionListener(this);
		mntmQuit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFileMenu.add(mntmQuit);
		
		JMenu mnHelpMenu = new JMenu("Help");
		mnHelpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnHelpMenu);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.setActionCommand("mntmAbout");
		mntmAbout.addActionListener(this);
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHelpMenu.add(mntmAbout);
	}
}

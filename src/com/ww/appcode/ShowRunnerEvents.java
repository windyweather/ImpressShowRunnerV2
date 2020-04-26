/**
 * 
 */
package com.ww.appcode;

import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ww.views.HelpDialog;
import com.ww.views.ImpressShowRunnerView;

/**
 * @author Darrell
 *
 */
public class ShowRunnerEvents extends ImpressShowRunnerView implements ActionListener{

	
	// get rid of a warning about serialization.
		private static final long serialVersionUID = 19837503L;

		// 5 seconds between mouse clicks while show is up
		private static final int MS_TIMER_TICK = 5000;
		
		
		/*
		 * Data for timer and show process control
		 */
		private boolean bTimerRunning;		// timer is running
		private boolean bShowRunning;		// shows are running
		private int		nShowIndex;			// show we are now running
		/*
		 * Control classes for timer and shows
		 */
		private Timer aTimer;
		private MyTimerTask timerTask;
		private Process pShowProcess;
		
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ShowRunnerEvents window = new ShowRunnerEvents();
						window.frmGuiGroupLayout.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		
		//
		// Put some text in the status line to say what's up
		//
		public void setStatus( String sts ) {
			
			lblStatus.setText( sts );
		}

		/*
		 * usually means that everything worked up to now
		*/
		public boolean isStatusEmpty() {
			return lblStatus.getText().isEmpty();
		}
		
		private boolean isOsWindows()
		{
			String osName = System.getProperty ("os.name");
			if ( osName.contains("Windows") ) {
				return true;
			}
			else {
				return false;
			}
		}
		
		private boolean isOsLinux()
		{
			String osName = System.getProperty ("os.name");
			if ( osName.contains("Linux") ) {
				return true;
			}
			else {
				return false;
			}
		}
		
		//
		// Do this in one place so we can easily turn it off later
		//
		private void printSysOut( String str ) {
			System.out.println(str);
		}

	/**
	 * Class Constructor
	 */
	public ShowRunnerEvents() {
		printSysOut("ShowRunnerEvents constructor reached");
		
		// build the main dialog
		super.initialize();
		
		bTimerRunning = false;
		bShowRunning = false;
		nShowIndex = 0;
		
		// init this once, not each time we start timer
		aTimer = new Timer();

		
	    String s = 
	    	      "name: " + System.getProperty ("os.name");
	    	    s += ", version: " + System.getProperty ("os.version");
	    	    s += ", arch: " + System.getProperty ("os.arch");
	    	    printSysOut ("OS=" + s);
		
		// Set up some default values depending on the OS we find

		if ( isOsWindows() ) {
			tfImpressPath.setText("C:\\Program Files\\LibreOffice\\program\\soffice.exe");
			tfOptions.setText("--impress --show");
		}
		else if ( isOsLinux() ) {
			tfImpressPath.setText("soimpress");
			tfOptions.setText("--show");
		}

		setStatus("ShowRunner Started");
		String userDir = System.getProperty("user.home");
		printSysOut("user.home "+ userDir);

		// Restore the defaults to the gui
		restoreDefaultsFile(); 
		
	}
	
	
	//
	// use the java System.Properties class for ini files
	// and write them in XML format.
	// Both the default file and the show files are stored in this way.
	//
	
	private String propertyFilePathPrefix()
	{
		return System.getProperty("user.home")+File.separator+".windyweather";
	}
	
	private String defaultsFilePath()
	{
		return propertyFilePathPrefix() + File.separator+"ImpressShowRunnerDefaults.xml";
	}
	
	
	//
	// Restore the Defaults File from XML
	//
	private void restoreDefaultsFile()
	{
		String defaultsFile = defaultsFilePath();
        Properties defProps = new Properties(); 
        //FileOutputStream out = new FileOutputStream(defaultsFile); 
		FileInputStream in;
		try {
			in = new FileInputStream(defaultsFile); 
	        // load the properties from specified xml 
	        defProps.loadFromXML(in); 
	        in.close();
        }
        catch(IOException ex){
            printSysOut("Defaults not found " + defaultsFile);
            setStatus("No Defaults found");
            return;
        }
        
        String sImpressPath = defProps.getProperty("ImpressPath");
        String sImpressOptions = defProps.getProperty("ImpressOptions");
        String sShowPath = defProps.getProperty("ShowPath");
        
        if ( 0 != sImpressPath.length() ) {
        	tfImpressPath.setText(sImpressPath);
        }
        if ( 0 != sImpressOptions.length() ) {
        	tfOptions.setText(sImpressOptions);
        }
        if ( 0 != sShowPath.length() ) {
        	tfShowPath.setText( sShowPath );
        }
        

        printSysOut("restoreDefaultsFile "+defaultsFile);
        defProps.list(System.out); 
        setStatus("Defaults restored");
	}
	
	//
	// store the gui to the defaults file
	//
	private void saveDefaultsFile()
	{
		String defaultsDir = propertyFilePathPrefix();
		
	    File directory = new File(defaultsDir);
	    if (! directory.exists()){
	    	printSysOut("saveDefaultsFile Creating defaults dir " + defaultsDir);
	        directory.mkdir();
	    	printSysOut("saveDefaultsFile Created defaults dir  " + defaultsDir);  
	        // If you require it to make the entire directory path including parents,
	        // use directory.mkdirs(); here instead.
	    }
		String defaultsFile = defaultsFilePath();
        Properties defProps = new Properties(); 
        FileOutputStream out;
        setStatus("Unable to write defaults");
		try {
			out = new FileOutputStream(defaultsFile);
        }
        catch(IOException ex){
            printSysOut("saveDefaultsFile open failure " + defaultsFile);
            return;
        }

        defProps.put("ImpressPath", tfImpressPath.getText() ); 
        defProps.put("ImpressOptions", tfOptions.getText() ); 
        defProps.put("ShowPath", tfShowPath.getText() ); 
        
		try {
	        // store the properties into specified xml 
	        defProps.storeToXML(out, null);
	        out.close();
	        printSysOut("saveDefaultsFile");
	        defProps.list(System.out); 
        }
        catch(IOException ex){
            printSysOut("saveDefaultsFile write fail " + defaultsFile);
            return;
        }
		setStatus("Defaults saved");
	}
	
	// catch the window closing event
	// passed here with a nightmare, but hey, that's Java and AWT/Swing for you.
	//@Override
	public void windowClosingEvent(WindowEvent e) {
		
		printSysOut( "windowClosing - save your stuff here" );
		// write the defaults file
		saveDefaultsFile();
		
		// Need to exit since our base class is different
		// from the previous version of this program.
	    System.exit(0);
	 
	}
	
	

	/**
	 * Event methods. Called by the dispatcher below.
	 */
		//
		// get a reasonable path from possibly an empty string
		// Assume we are passed a path to a file, or an empty string
		// something bogus
		//
		String getReasonablePath( String startPath )
		{
			File defDir;
			// protect ourselves against bad stuff if we can
			try {
				String path = tfShowPath.getText();
				if ( path.isEmpty() )
				{	// if the field is empty, then just use home path and
					// do not look for it's parent
					path = System.getProperty("user.home");
					defDir = new File(path);
				}
				else
				{
					// find parent folder assuming that the path points to a show.
					defDir = new File(path);
					defDir = new File (defDir.getParent() );
				}
				// if that does not exist, then the home folder is a guess
				if ( !defDir.exists() )
				{
					defDir = new File(System.getProperty("user.home"));
				}
				// return the path to the place we found
				return defDir.getAbsolutePath();
			}
			catch (Exception e) {
				//setStatus("Can't find a folder to start with");
				//printSysOut("browseForShowToAdd - Exception");
				// if this is not a valid directory, then we are in serious trouble here
				return System.getProperty("user.home");
			 }
		}
		//
		// If the default for the LibreOffice Impress program path is not correct, then
		// allow the user to browse for the executable file. Of course, she can just type
		// it in if that's easier. It will be saved/restored with the defaults when the program
		// exits
		//
		protected void browseForImpressProgram()
		{
			JFileChooser fileChooser = new JFileChooser();
			
			String path = getReasonablePath( tfImpressPath.getText() );
			File defDir = new File(path);
			// point fileChooser at a carefully chosen default
			fileChooser.setCurrentDirectory(defDir);
			// we don't need defaults for the file type on Linux or do we?
			if ( isOsWindows() ) {
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Executable", "exe"));
			}
			else {
				fileChooser.setFileFilter(null); // allow all files on Linux I think
			}
			fileChooser.setAcceptAllFileFilterUsed(!isOsWindows());
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle( "Find Impress Program");
					
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    if ( selectedFile.canExecute() ) {
			    	tfImpressPath.setText(selectedFile.getAbsolutePath());
				    printSysOut("Impress Program file: " + selectedFile.getAbsolutePath());
			    }
			    else {
			    	setStatus("Choose the Impress program");
			    	printSysOut("Impress Program file: " + selectedFile.getAbsolutePath());
			    }
			}
			else {
				setStatus("");
			}
		}
		
		//
		// Browse for a .xml file that contains the list of shows to play.
		//
		protected void browseForShowToAdd()
		{
			JFileChooser fileChooser = new JFileChooser();
			File defDir;
			
			// start with a possibly empty path to a show we looked at.
			String path = getReasonablePath(tfShowPath.getText());
			// point fileChooser at a carefully chosen default
			defDir = new File(path);
			fileChooser.setCurrentDirectory(defDir);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Impress Slides", "odp"));
			fileChooser.setAcceptAllFileFilterUsed(true);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle("Impress Slide Show File to Add");
					
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    tfShowPath.setText(selectedFile.getAbsolutePath());
			    printSysOut("Show File Path: " + selectedFile.getAbsolutePath());
			    setStatus("Path to show set");
			    }
			else {
				setStatus(""); // clear status line on cancel without change
			}
		}
		
		protected void addShowToList()
		{
			String showPath = tfShowPath.getText();
			showList.addElement(showPath);
			setStatus("Show added");
		}
		
		
		//
		// Get a path to open a show list
		//
		protected String showListOpenPath()
		{
			JFileChooser fileChooser = new JFileChooser();
			File defDir;
			
			// start with a possibly empty path to a show we looked at.
			String path = getReasonablePath(tfShowPath.getText());
			// point fileChooser at a carefully chosen default
			defDir = new File(path);
			fileChooser.setCurrentDirectory(defDir);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Show List", "xml"));
			fileChooser.setAcceptAllFileFilterUsed(true);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle("Show List File to Open");
					
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    printSysOut("Show List Path: " + selectedFile.getAbsolutePath());
		    return selectedFile.getAbsolutePath();
		    }
			else {
				//setStatus(""); // clear status line on cancel without change
			}
			return new String(""); // empty string for nothing chosen
		}
		
		//
		// Get a path to save a show list
		//
		protected String showListSavePath()
		{
			JFileChooser fileChooser = new JFileChooser();
			File defDir;
			
			// start with a possibly empty path to a show we looked at.
			String path = getReasonablePath(tfShowPath.getText());
			// point fileChooser at a carefully chosen default
			defDir = new File(path);
			fileChooser.setCurrentDirectory(defDir);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Show List", "xml"));
			fileChooser.setAcceptAllFileFilterUsed(true);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle("Save List of  Shows");
					
			int result = fileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    printSysOut("Show List Path: " + selectedFile.getAbsolutePath());
			    //setStatus("Path to show set");
			    String savePath = selectedFile.getAbsolutePath();
			    if (!savePath .endsWith(".xml"))
			    	savePath += ".xml";
			    return savePath;
			    }
			else {
				//setStatus(""); // clear status line on cancel without change
			}
			return new String(""); // empty string for nothing chosen
		}
		
		
		//
		// Save a show list as an XML file using the Properties class
		//
		protected void saveShowList()
		{
			int count = showList.size();
			if ( count <= 0 ) {
				setStatus("No shows to save");
				return;
			}
			String savePath = showListSavePath();
			if ( savePath.isEmpty() ) {
				return;
			}

		    File saveFile = new File(savePath);
		    if (saveFile.exists()){

		    	// do we need to do anything, or did dialog do it?
		    }
	        Properties saveProps = new Properties(); 
	        FileOutputStream out;
			try {
				out = new FileOutputStream(saveFile);
	        }
	        catch(IOException ex){
	            printSysOut("saveShowList open failure " + savePath);
	            return;
	        }

			// write the show list count to the props
			saveProps.put("ShowListCount", String.valueOf(count) ); 

			// save all the shows to the props with unique keys
			for ( int showIdx=0; showIdx<count; showIdx++ ) {
				String key = "ShowPath_"+String.valueOf(showIdx);
				String showPath = showList.elementAt(showIdx);
				saveProps.put(key, showPath );
			}
			
	        // write the XML and catch any errors
			try {
		        // store the properties into specified xml 
				saveProps.storeToXML(out, null);
		        out.close();
		        printSysOut("saveShowList closed");
		        saveProps.list(System.out); 
		        setStatus("Show List Saved");
	        }
	        catch(IOException ex){
	            printSysOut("saveShowList write fail " + savePath);
	            setStatus("Show List Save Failed");
	        }

		}
		
		

		
		
		//
		// Open a Show List from an XML file
		//
		protected void openShowList() {
			//
			// get a path to read some shows
			//
			setStatus(""); // clear the status
			String openPath = showListOpenPath();

			if (openPath.isEmpty()) {
				return;
			}
			
		    File openFile = new File(openPath);
		    if (!openFile.exists()){

		    	setStatus("No show list to open");
		    }
		    

	        Properties openProps = new Properties(); 
	        FileInputStream in;
			try {
				// open the input file and read the XML into props
				in = new FileInputStream(openFile);
				openProps.loadFromXML(in);
				in.close();
	        }
	        catch(IOException ex){
	            printSysOut("openShowList open failure " + openPath);
	            setStatus("Can't read that show list");
	            return;
	        }

			// read the show list count to the props
			int count = Integer.valueOf( openProps.getProperty("ShowListCount") );
			if ( count <= 0)
			{
				setStatus("No shows in that list");
				return;
			}
			
			// last minute clear of the list we have
			showList.clear();

			// read all the shows with unique keys and add to list
			for ( int showIdx=0; showIdx<count; showIdx++ ) {
				String key = "ShowPath_"+String.valueOf(showIdx);
				String showPath = openProps.getProperty(key );
				showList.addElement(showPath);
			}
			
			printSysOut("openShowList show opened");
	        openProps.list(System.out); 
	        setStatus("Show list opened");

		}
		
		
		//
		// Events to adjust order of shows in the list
		//
		protected void moveShowToTop() {
			int selIndex = listShows.getSelectedIndex();
			if ( selIndex == -1 ) {
				setStatus("Select a show first");
				return;
			}
			// rip it out and put it back at top
			String showPath = showList.remove(selIndex);
			showList.add( 0,  showPath );
			listShows.setSelectedIndex(0);
			setStatus("Show moved to top");
		}
		
		protected void moveShowUp()
		{
			int selIndex = listShows.getSelectedIndex();
			if ( selIndex == -1 ) {
				setStatus("Select a show first");
				return;
			}
			if ( selIndex == 0 ) {
				setStatus("Show is at the top");
				return;
			}
			// rip it out and put it back one at lower index
			String showPath = showList.remove(selIndex);
			showList.add( selIndex-1,  showPath );
			listShows.setSelectedIndex(selIndex-1);
			setStatus("Show moved up");

		}
		
		protected void moveShowDown()
		{
			int selIndex = listShows.getSelectedIndex();
			if ( selIndex == -1 ) {
				setStatus("Select a show first");
				return;
			}
			int count = showList.size();
			if ( selIndex == (count-1) ) {
				setStatus("Show is at the bottom");
				return;
			}
			// rip it out and put it back at higher index
			String showPath = showList.remove(selIndex);
			showList.add( selIndex+1,  showPath );
			listShows.setSelectedIndex(selIndex+1);
			setStatus("Show moved down");

		}
		
		protected void removeSelectedShow()
		{
			int selIndex = listShows.getSelectedIndex();
			if ( selIndex == -1 ) {
				setStatus("Select a show first");
				return;
			}

			// rip it out
			String showPath = showList.remove(selIndex);
			printSysOut("removeSelectedShow removed "+showPath);
			setStatus("Show removed");

		}
		
		protected void removeAllShows()
		{
			showList.clear();
			setStatus("All shows removed");
		}
		
		
		protected void startShows()
		{
			/**
			 * if there are shows, then set the index, mark shows running
			 * 
			 */
			setStatus(""); // we can check for errors now
			if ( bShowRunning )
			{
				setStatus("Shows are running");
				return;
			}
			if ( showList.isEmpty() )
			{
				setStatus("No shows in list");
				return;
			}
			
			/*
			 * Move the mouse South a little ways to prevent click on button again
			 */
			try {
				PointerInfo pointerInfo = MouseInfo.getPointerInfo();
				Point location = pointerInfo.getLocation();
				int x = (int) location.getX();
				int y = (int) location.getY();
		
				printSysOut("startShows - mouse (" +String.valueOf(x)+","+String.valueOf(y)+")");
				Robot robot = new Robot();
				robot.mouseMove(x, y + 100);
				/*
				 * Ok Mouse is out of the way of the buttons
				 */
			} catch (Exception e ) {
				// what can I say? We tried.
			}

			
			nShowIndex = 0; //start at first show
			bShowRunning = true;
			// start the timer and then start the next show
			startTimer( MS_TIMER_TICK );
			startNextShow();
			if ( isStatusEmpty() ) { // so far so good
				setStatus("Shows started");
			} // otherwise leave status already there
			
		}
		
		protected void stopShows()
		{
			// stop the mouse clicks
			stopTimer();
			if ( !bShowRunning )
			{
				setStatus("Shows are not running");
				//return;
			}
			bShowRunning = false;
			setStatus("Hit ESC to end current show");
			
			try {
				printSysOut("endShow waiting for show to end");
				// wait for current show to clean up, or toss exception
				pShowProcess.waitFor();
				printSysOut("stopShows show ended");
					
			} catch (Exception ex ) {
				printSysOut("stopShows exception");
			}
			setStatus("Shows stopped");
		}
		
		
		protected void showHelpDialog()
		{
			 printSysOut( "ShowRunnerEvents::showHelpDialog" );
			HelpDialog dlg = new HelpDialog( new JFrame(), "no title here", true);
			dlg.setVisible(true);
		}
		
		
		
		
		/**
		* Catch all the events here in the child class
		* use the same actionPerformed listener for every action
		* Qt has it's problems, but at least you don't have to write
		* a command dispatcher. Here in Java, it looks like the easiest way
		* to deal with events from a GUI is to use setActionCommand("string") and
		* then to dispatch those commands here.
		* 
		* In Qt, the dispatcher is built into the framework, so you don't need to build
		* a dispatcher based on text string. Oh Well. Deployment in Qt is a nightmare,
		* which is why I'm here, and this is much less of a problem than the Qt Deployment
		* issue.
		* 
		* Note we are using the meaningful Gui Object name, like btnAddShow or
		* mntmHelpDialog as the "command string", just to keep things consistent
		* and easy. And of course, we have moved all event processing to the child class.
		* Separation of Form and Function into different classes.
		**/
		@Override
		public void actionPerformed(ActionEvent e) {
			//printSysOut( "ShowRunnerEvents::actionPerformed" );
			// get the command string we set in the dialog
		    String actionCommand = e.getActionCommand();

		    //printSysOut( "ShowRunnerEvents::actionPerformed "+actionCommand );
		    // build a dispatcher on those command strings, which are the names
		    // of the gui items to keep it simple.
		    // don't do code here, but call another method to actually do the work
		    // to keep the dispatcher more readable.
		    printSysOut( "ShowRunnerEvents::actionPerformed "+actionCommand );
		    switch (actionCommand) {
		    case "btnBrowseForImpress":
		    	browseForImpressProgram();
	    	break;
	        case "btnBrowseForShow": 
	        	browseForShowToAdd();
	        break;
	        case "btnAddShow": 
	        	addShowToList();
	        break;
	        case "btnRemoveAll": {
	        	removeAllShows();
	        	break;
	        }
	        case "btnRemoveSelected": {
	        	removeSelectedShow();
	        	break;
	        }
	        case "btnStartShows": {
	        	startShows();
	        	break;
	        }
	        case "btnStopShows": {
	        	stopShows();
	        	break;
	        }
	        case "btnTop": {
	        	moveShowToTop();
	        	break;
	        }
	        case "btnUp": {
	        	moveShowUp();
	        	break;
	        }
	        case "btnDown": {
	        	moveShowDown();
	        	break;
	        }
	        case "mntmOpenShowList": {
	        	openShowList();
	        	break;
	        }
	        case "mntmSaveShowList": {
	        	saveShowList();
	        	break;
	        }
	        case "mntmSaveDefaults": {
	        	saveDefaultsFile();
	        	break;
	        }
	        case "mntmRestoreDefaults": {
	        	restoreDefaultsFile();
	        	break;
	        }
	        
	        case "btnClose":{
	        	// do as little as possible
	        	// allow the framework to do it all
	        	dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	        	break;
	        	
	        }
	        case "mntmQuit": {
	        	// do as little as possible
	        	// allow the framework to do it all
	        	dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	        	break;
	        	
	        }
	        case "mntmAbout": {
	        	showHelpDialog();
	        	break;
	        }
	        default:
	        {
	    	    //printSysOut( "ShowRunnerEvents::Woops "+actionCommand );
	        	break;
	        }
		    }
		}

	/**
	 * mntmOpenShowList
	 * mntmSaveShowList
	 * mntmSaveDefaults
	 * mntmRestoreDefaults
	 * mntmQuit
	 * mntmHelpDialog
	 * mntmAboutDialog
	 * btnBrowseForImpress
	 * btnBrowseForShow
	 * btnAddShow
	 * btnRemoveAll
	 * btnRemoveSelected
	 * btnStartShows
	 * btnStopShows
	 * btnTop
	 * btnUp
	 * btnDown
	 */
			
		/*
		 * a timer task to tick down the time
		 * up the tics and click the mouse
		*/
		private class MyTimerTask extends TimerTask {
			@Override
			public void run() {
				// if done with timers, stop us
				if ( !bTimerRunning ) {
					// absolutely stop us
					this.cancel();
					return;
				}
					
				// try to click the mouse here
				try {
					Robot bot = new Robot();
					int mask = InputEvent.BUTTON1_DOWN_MASK;
					// don't move the mouse in case the user wants to click on Stop Show or
					// something else. It will be fine, the show will stop on the click
					// if it's at the end.
					//bot.mouseMove(100, 100);           
					bot.mousePress(mask);     
				
					try {
						// hang for a bit before release
					    Thread.sleep(100);
					}
					catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					    printSysOut("MyTimerTask interrupted");
					}
					bot.mouseRelease(mask);
				}
				catch (Exception e ) {
					printSysOut("MyTimerTask Robot exception");
				}
				
				// if the show is running, watch for it to end in a strange way
				
				if ( bShowRunning ) {
					
					try {
						// so, rather than a wait, we check for exit value
						// and if that tosses an exception, the process is
						// still running. Ooooooookkkkkaaaaaayyyyyy No Problem
						int exitValue = pShowProcess.exitValue();
						// guess we don't do this to get rid of the not referened warning
						//(void)exitValue;
						// we don't care what the exit value was
						exitValue = 0;
						// but if we get here, then the show stopped, so
						// if we stop it now, it won't need to wait, it will be fine
						// we think.
						endShow();
					} catch (Exception ex) {
						// Process is still running. So just keep going until
						// mouse clicks or something else stops the show
					}
				}
			}
		}
		
		public void startTimer( long msecsPerTic ) {
			try {
				/*
				if ( bTimerRunning ) {
					timerTask.cancel();
					bTimerRunning = false;
					printSysOut("startTimer already running" );
				}
				//aTimer = new Timer();
				 */

				/*
				 * Before starting another task, make sure
				 * we have cancelled the last one. Lingering
				 * mouse clicks have been a problem.
				 */
				bTimerRunning = false;
				try {
					timerTask.cancel();
				} catch (Exception ee) {
					// just ignore this
				}
				timerTask = new MyTimerTask();
				aTimer.schedule(timerTask, MS_TIMER_TICK, MS_TIMER_TICK);
				bTimerRunning = true;
				printSysOut("startTimer started for "+String.valueOf(MS_TIMER_TICK) );
			} catch (Exception ex)
			{
				// just ignore any exceptions
				printSysOut("startTimer exception "+ex.getMessage() );
				//ex.printStackTrace(System.out);
			}
		
		}
		
		/*
		 * use a heavy hand here since we've had lingering timers
		 */
		public void stopTimer() {
			try {
				/*
				if ( !bTimerRunning ) {
					timerTask.cancel(); // hammer timer anyway
					printSysOut("stopTimer not running" );
					return;
				}
				*/
				bTimerRunning = false;
				timerTask.cancel();
				printSysOut("stopTimer cancelled" );
			} catch (Exception ex) {
				printSysOut("stopTimer exception" );
			}
		}


		/*
		 * Start the next show based on the nShowIndex
		 */
		public void startNextShow() {
			String showPath = showList.get(nShowIndex);
			printSysOut("startNextShow "+showPath);
			startShowPlaying( tfImpressPath.getText(),
					tfOptions.getText(),
					showPath);
			
		}
		
		/*
		 * Build the string array of the command, options and path and clean up options
		 * we are using an array to preserve and pass through spaces in Impress path and show path.
		 * Also any special characters should be passed along more robustly using String[] rather
		 * than just a string for the whole thing.
		 */
		public String [] buildCommandArray( String sImpress, String sOptions, String sShowPath ) {
			
			String [] cmdArray = {sImpress};
			String [] cmdArray2;
			String [] cmdArray3;
			// leave impress path alone. Note spaces "\Program Files\" are present on Windows
			// however, options are user-typed. So remove any extra blanks and bust up into
			// individual strings.
			String [] saOptions = {};
			if ( !sOptions.isBlank() && !sOptions.isEmpty() ) {
				
				try {
					saOptions = sOptions.split(" ");
					
				} catch ( Exception ex ) {
					saOptions[0] = sOptions; // let user deal with the issue
					printSysOut("buildCommandArray problem with splitting options string");
				}
			}
			// add options if we have some
			if ( saOptions.length != 0  ) {
				// left as an exercise for the reader
				cmdArray2 = Arrays.copyOf(cmdArray, cmdArray.length + saOptions.length);
				System.arraycopy(saOptions, 0, cmdArray2, cmdArray.length, saOptions.length);
			} else {
				cmdArray2 = cmdArray;
			}
			// finally add the show path and leave spaces in it
			int N = cmdArray2.length;
			cmdArray3 = Arrays.copyOf(cmdArray2, N + 1);
			cmdArray3[N] = sShowPath;
			return cmdArray3;
		}
		

		
		/*
		 * Build the command array to avoid problems with spaces, dashes, parens in path
		 * Execute the command in another process.
		 */
		public void startShowPlaying( String sImpress, String sOptions, String sShowPath ) {

			// for display purposes
			String cmdString = sImpress +" "+sOptions+" "+sShowPath;
			
			String[] cmdArray;
			cmdArray = buildCommandArray(sImpress, sOptions, sShowPath);
			if ( cmdArray.length == 0 ) {
				setStatus("No show to play");
				printSysOut("startShowPlaying no show "+cmdString );
				return;
			}
			printSysOut("startShowPlaying command "+cmdString);
			printSysOut("Command Array:");
			for (String cmd : cmdArray){
				printSysOut("|"+cmd+"|");
				}
			printSysOut("----");
			try {
				pShowProcess = Runtime.getRuntime().exec( cmdArray );
				printSysOut("startShowPlaying show started"+ cmdString);

			} catch (Exception ex ) {
				setStatus("Can't start the show");
				stopTimer();
				printSysOut("Exception "+ex.getMessage() );
				printSysOut("startShowPlaying exception");
				return;
			}
			try {
				// if for some reason the timer is still running
				// stop it so we can restart it
				if ( bTimerRunning ) {
					stopTimer();
				}
				startTimer( MS_TIMER_TICK );
			} catch (Exception ex ) {
				setStatus("Can't start the show timer");
				stopTimer();
				printSysOut("Exception "+ex.getMessage() );
				printSysOut("startShowPlaying exception");
			}
		
		}
		
		
		/*
		 * Only called from timer routine if the end check does
		 * not toss an exception. So, only if show has ended.
		 */
		public void endShow() {
			if ( !bShowRunning ) {
				printSysOut("endShow show not running");
				return;
			}
			// we cannot use destroy() since that would leave the Impress show
			// in a bad state. So all we can do is wait on the user to stop the
			// show and then clean up.
			
			try {
				// stop the mouse clicks
				stopTimer();
				printSysOut("endShow waiting for show to end");
				setStatus("Stop the show if it's running");
				// we only get here if timer sensed show had ended
				// so this should never wait, but just in case
				pShowProcess.waitFor();
				setStatus("Next show starting");
				printSysOut("endShow show ended");
				
				/**
				 * beep if we were told to between shows
				 */
				if ( ckbxBeepOnEnd.isSelected() ) {
					// Ring the bell using the Toolkit 
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
				printSysOut("endShow - after beep if we had one");
				/**
				 * just hang out until time for the next show.
				 * Maybe someday make this more elegant, but this works for now.
				 **/
				printSysOut("endShow - commitEdit on spinner");
				try {
					spSecondsBetweenShows.commitEdit();
				} catch ( java.text.ParseException e ) {
					// ignore the exception if any
				}
				
				int nSecsBetweenShows = (Integer) spSecondsBetweenShows.getValue();
				printSysOut("endShow sleeping for "+String.valueOf(nSecsBetweenShows));
				Thread.sleep(nSecsBetweenShows*1000);
				printSysOut("endShow - done sleeping");
				/**
				 * wrap the index around and start the next show
				 */
				nShowIndex++;
				if ( nShowIndex >= showList.getSize() ) {
					nShowIndex = 0;
				}
				printSysOut("endShow start show at index "+String.valueOf(nShowIndex));
				startNextShow(); // obviously, start the next show
				printSysOut("endShow show started");
			} catch (Exception ex ) {
				printSysOut("endShow exception "+ex.getMessage());
				setStatus("Failed to start next show");
				bShowRunning = false; // try to clean up
			}
		}
	} // end of ShowRunnerEvents class


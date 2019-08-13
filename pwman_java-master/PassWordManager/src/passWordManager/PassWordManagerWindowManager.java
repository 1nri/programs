/**
 * 
 */
package passWordManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author henrijuvonen
 * Created on 10th of July 2019.
 * This project aims to reconstruct and 
 * improve  an old utility program 
 * PasswordManager I made with Netbeans 
 * in 2015 or so. Part of the code was 
 * no longer working so I'll try to 
 * make this version a more generic 
 * one.
 * 
 * This class handles the graphical
 * user interface part of this 
 * application and calls other 
 * functions when needed.
 * 
 * edited 22.7.2019 : modified and 
 * added comments and input field for 
 * password seed
 */

class PassWordManagerWindowManager {

	/**
	 * @return This class doesn't return anything - except for program
	 * control once quit command is issued through UI
	 * 
	 */
	
	private static JFrame frameForApplication;
	private static JMenuBar menuBar;
	private static String textOnTheInput = "default";
	
	public static void launchGraphicalUserInterface(){
		
		// The frame of the application window
		createFrame();
		
		// Menubar on top of the window frame
		// Menu initiations
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        // Menus to menubar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        // Initialization of menuitems
        JMenuItem menuItemToOpenFile = new JMenuItem("Open");
        JMenuItem menuItemToSaveNewFile = new JMenuItem("Save as");
        JMenuItem menuItemToQuitProgram = new JMenuItem("Quit");
        JMenuItem menuItemToGetApplicationInfo = new JMenuItem("Application info");
        // Menuitems to menus on the menubar
        fileMenu.add(menuItemToOpenFile);
        fileMenu.add(menuItemToSaveNewFile);
        fileMenu.add(menuItemToQuitProgram);
        helpMenu.add(menuItemToGetApplicationInfo);
        
        // Some logic for each of the menu items
        // File menu logic
		fileMenu.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  fileMenuOpened();
  		  } 
  		} );
        
		// Logic of saving application state (user id and password perhaps?)
		menuItemToSaveNewFile.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	      		  menuItemToSaveNewFileItemClicked();
	  		  } 
	  		} );
		
		// Logic of opening an application state (user id and password perhaps?)
		menuItemToOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					openMenuOpened();
				}
			} );
		
		// Help menu logic
		helpMenu.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	  		    helpMenuOpened();
	  		  } 
	  		} );
		
		// Logic of acquiring information about the application
		menuItemToGetApplicationInfo.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	  		    applicationInfoMenuItemClicked();
	  		  } 
	  		} );
		
        //Creating the panel at bottom and adding components
        // Initialization of the panel at the bottom of the screen
		JPanel bottomPanel = new JPanel(); // the panel is not visible in output
        
		// Initialization of the label for user guidance
        JLabel adviceLabel = new JLabel("Enter text to the "
        		+ "input field on the right and click a button");

        // Input field initialization with default content
        // Input field shows up to 10 characters at a time
        JTextField tf = new JTextField(10);
        tf.setText(textOnTheInput);
        
        /**
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

         */
        
        // Initialization of two buttons as components of 
        // the bottom panel of frame
		JButton buttonToConfirm = new JButton("OK");
        JButton buttonToCancel = new JButton("Cancel");
        
        // Components Added using Flow Layout
        bottomPanel.add(adviceLabel);
        bottomPanel.add(tf);
        bottomPanel.add(buttonToConfirm);
        bottomPanel.add(buttonToCancel);
        
        /**
        frameForApplication.getContentPane().add(buttonToCancel);
        frameForApplication.getContentPane().add(buttonToConfirm);
        */ 
        // Some logic for the confirmation button
        buttonToConfirm.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		    confirmationButtonClicked();
        		  } 
        		} );
        
        buttonToCancel.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		    cancelButtonClicked();
      		  } 
      		} );
        
        // Components to the frame
        frameForApplication.getContentPane().add(BorderLayout.PAGE_START, menuBar);
        frameForApplication.getContentPane().add(BorderLayout.PAGE_END, bottomPanel);
		frameForApplication.setVisible(true);

	}

	// Logical function for opening program state (user id and password perhaps?)
	protected static void openMenuOpened() {
		
		PassWordManagerWindowItemTest.openFileTest();
		/**
		 * The test function contains the actual function
		 * to be called once testing has been concluded.
		 * Uncomment the commented code below in that
		 * scenario.
		 * 
		 * if(PassWordManagerPasswordUtilities.loadMenuItemClick() == 0) {
		 * 	System.out.println("seems to work nicely");
		 * }
		 */
	}

	// Logical function for information menuitem 
	protected static void applicationInfoMenuItemClicked() {
		
		PassWordManagerWindowItemTest.applicationInfoTest();
		
		/**
		 * The test function contains the actual function
		 * to be called once testing has been concluded.
		 * Uncomment the commented code below in that 
		 * scenario.
		 * 
		 * if(PassWordManagerPasswordUtilities.applicationInfoMenuItemClick() == 0) {
		 * 	System.out.println("seems to work nicely");
		 * }
		 */
	}

	// Logical function for saving application state (user id and password perhaps?)
	protected static void menuItemToSaveNewFileItemClicked() {
		
		PassWordManagerWindowItemTest.saveFileAsTest();
		/**
		 * The test function contains the actual function
		 * to be called once testing has been concluded.
		 * Uncomment the commented code below in that 
		 * scenario.
		 * 
		 * if(PassWordManagerPasswordUtilities.saveAsMenuItemClick() == 0) {
		 * 	System.out.println("seems to work nicely");
		 * }
		 */
	}

	// Logical function for opening help menu
	protected static void helpMenuOpened() {
		
		// This test call doesn't do anything.
		PassWordManagerWindowItemTest.helpMenuTest();
		
	}

	// Logical function for opening file menu
	protected static void fileMenuOpened() {
		
		//This doesn't do anything.
		PassWordManagerWindowItemTest.fileMenuTest();
		
	}

	
	// Logical function for cancel button on bottom panel
	protected static void cancelButtonClicked() {
		
		PassWordManagerWindowItemTest.cancelTest();
		/**
		 * The test function contains the actual function
		 * to be called once testing has been concluded.
		 * Uncomment the commented code below in that
		 * scenario.
		 * 
		 * if(PassWordManagerPasswordUtilities.cancelButtonClick() == 0) {
		 * 	System.out.println("seems to work nicely");
		 * }
		 */
	}

	// Logical function for ok button on bottom panel
	private static void confirmationButtonClicked() {
		
		PassWordManagerWindowItemTest.confirmTest();
		/**
		 * The test function contains the actual function
		 * to be called once testing has been concluded.
		 * Uncomment the commented code below in that
		 * scenario.
		 * 
		 * if(PassWordManagerPasswordUtilities.okButtonClick() == 0) {
		 *	System.out.println("seems to work nicely");
		 * }
		 */
	}
	
	// Function for creating the application window
	protected static void createFrame() {
		// The frame of the application window
		frameForApplication = new JFrame("PassWordManager");
		frameForApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameForApplication.setSize(450,450);
		createMenuBar();
	}
	
	// Function for creating the menubar
	protected static void createMenuBar() {
		menuBar = new JMenuBar();
	}
}

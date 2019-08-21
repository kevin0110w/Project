package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * This class is responsible for controlling the actions from the user entering the their details to the instruction panel and checking whether these are associated with a registered user
 *
 */
public class UserLoginInstructionController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	private UserLoginModel userLoginModel;
	
	public UserLoginInstructionController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainWindow = mw;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginModel = model;
		setListeners(); // set the listeners in all the separate view classes
	}
	
	/**
	 * Create a controller that'll handle the interactions on the login selection image panel
	 */
	public void createUserLoginSelectionController() {
		UserLoginSelectionController userLoginSelectionController = new UserLoginSelectionController(mainWindow, userLoginCardsPanel, userLoginModel);
	}
	
	/**
	 * Set the action listeners in all the separate views
	 */
	public void setListeners() {
		this.userLoginCardsPanel.setLoginInstructionPanelListeners(new UserLoginInstructionListener());
		this.userLoginCardsPanel.setLoginInstructionMouseListener(new UserLoginInstructionListener());
	}
	
	class UserLoginInstructionListener implements ActionListener, MouseListener {
		
		private String getUserID() {
			String userId = userLoginCardsPanel.getUseridInput().toUpperCase();
			return userId;
		}
		
		private boolean invalidInput(int pictureSetSelection, int loginSelection, String userID) {
			String text = "Please enter your unique UserID here";
			text = text.toUpperCase();
			return pictureSetSelection == 0 || loginSelection == 0 || userID.equals(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			/**
			 * If the user clicks on the back button, display the main page, clear everything from the model, clear any choices or inputs from the instruction view.
			 */
				case "BACK":
					mainWindow.showMainPage(); // show the main page
					userLoginModel.clear(); // clear any locally stored data
					userLoginCardsPanel.clearInstructionPanel(); // reset the instruction page view
					mainWindow.updateSmallFrameSize();
					break;
			/**
			 * If a user clicks on next, get the user id input, picture set choice,, login choice, and set these in the model. If the userid is empty, or zeroth index
			 * for either of the jcombo boxes is chosen, throw an exception.
			 * Set an updated login attempt number by getting the most recent one and incrementing it.
			 * 
			 */
				case "NEXT":
				boolean success = true;
				try {
					String userId = getUserID();
					int pictureSelection = userLoginCardsPanel.getPictureSelection();
					int loginSelection = userLoginCardsPanel.getLoginSelection();
					if (invalidInput(pictureSelection, loginSelection, userId)) {
						throw new Exception();
					}
					userLoginModel.setUserID(userId); // set the user id
					userLoginModel.setLoginMethod(loginSelection); // set the model's field variable for login method
					userLoginModel.setPictureSetSelection(pictureSelection); // set the model's field variable for picture set
					userLoginModel.setLoginAttemptNoFromDB(); // set the model's field variable for login attempt by pulling the details from the database
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(userLoginCardsPanel,
							"Warning - Invalid Login Details", "Log In Warning",
							JOptionPane.WARNING_MESSAGE);
					success = false;
					break;
				}
				/**
				 * Check that a valid data has been inputted and that these are associated with a valid user according to the database
				 * if so, check whether their most recent login attempt was successful, get the challenge set associated with the user and use these to create the image
				 * buttons in the selection panel
				 * Then display the login panel and start the timer
				 * otherwise display a dialog box
				 */
				if (success && userLoginModel.returnIsRegisteredUser()) {
					userLoginModel.returnMostRecentLoginSuccess();
					userLoginCardsPanel.getLoginSelectionPanel().getFilePaths(userLoginModel.getUsersImages());
					createUserLoginSelectionController();
					userLoginCardsPanel.showLoginPanel();
					userLoginModel.setInitialTime();
					mainWindow.setLabelSelectImages();
				} else {
					JOptionPane.showMessageDialog(userLoginCardsPanel, "Warning - Not a valid User ", "Log In Warning",
							JOptionPane.WARNING_MESSAGE);
					success = false;
				}
				break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			/**
			 * Clears the textfield if it's clicked on
			 */
			if (arg0.getSource() == userLoginCardsPanel.getLoginInstructionPanel().getInputArea()) {
				userLoginCardsPanel.getLoginInstructionPanel().setInputToTextfield(""); 
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {		
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			/**
			 * If the mouse is moved away from the Jtextfield and nothing's been entered, then reset the placeholder text
			 */
			String input = userLoginCardsPanel.getLoginInstructionPanel().getInput();
			if (arg0.getSource() == userLoginCardsPanel.getLoginInstructionPanel().getInputArea()) {
				if (!(input.length() > 0)) { 
				userLoginCardsPanel.getLoginInstructionPanel().setDefaultText(); 
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}

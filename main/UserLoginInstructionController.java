package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

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
	
	public void createUserLoginSelectionController() {
		UserLoginSelectionController userLoginSelectionController = new UserLoginSelectionController(mainWindow, userLoginCardsPanel, userLoginModel);
		userLoginSelectionController.setListeners();
	}
	
	/**
	 * Set the action listeners in all the separate views
	 */
	public void setListeners() {
		this.userLoginCardsPanel.setLoginInstructionPanel(new UserLoginInstructionListener());
		this.userLoginCardsPanel.setLoginInstructionMouseListener(new UserLoginInstructionListener());
	}
	
	class UserLoginInstructionListener implements ActionListener, MouseListener {
		boolean successfulPastLogin, success;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
				case "BACK":
					mainWindow.showMainPage(); // show the main page
					userLoginModel.clear(); // clear any locally stored data
					userLoginCardsPanel.clearInstructionPanel(); // reset the instruction page view
					mainWindow.updateSmallFrameSize();
					mainWindow.setLabelMainMenu();
					break;
			case "NEXT":
				try {
					success = true;
					String userId = userLoginCardsPanel.getInput().toUpperCase();
					String text = "Please enter your student ID here";
					text = text.toUpperCase();
//					if (userIdString.charAt(0) == '0') {
//						userIdString = userIdString.substring(1);
//					}
//					int userID = Integer.parseInt(userIdString);
					int pictureSelection = userLoginCardsPanel.getPictureSelection();
					int loginSelection = userLoginCardsPanel.getSelection();
					if (loginSelection == 0 || pictureSelection == 0 || userId.equals(text)) {
						success = false;
						throw new Exception(); // throw a dialog box if an invalid picture or login selection is chosen
												// from the jcombo boxes
					}
					userLoginModel.setUserID(userId); // set the user id
					userLoginModel.setLoginMethod(loginSelection); // set the model's field variable for login method
					userLoginModel.setPictureSelection(pictureSelection); // set the model's field variable for picture set
					userLoginModel.setLoginAttempt(); // set the model's field variable for login attempt by pulling the details from the database
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(userLoginCardsPanel,
							"Warning - Log-in Method and/or Picture Set have not been selected", "Log In Warning",
							JOptionPane.WARNING_MESSAGE);
					success = false;
					break;
				}
				// check that correct details have been inputted and that these are associated
				// with a registered user
				if (success && userLoginModel.returnIsValid()) {
					userLoginModel.returnMostRecentLoginSuccess(); // check whether this user logged in successfully recently
					userLoginCardsPanel.getLoginSelectionPanel().getFilePaths(userLoginModel.getDecoyImages()); // pull the file paths into the view and set the buttons
//					setLoginSelectionPanelListeners(); // set the action listeners in the selection panel now that buttons have been set
					createUserLoginSelectionController();
					userLoginCardsPanel.showLoginPanel(); // show the selection page
					userLoginModel.setInitialTime(); // start the clock
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
			if (arg0.getSource() == userLoginCardsPanel.getLoginInstructionPanel().getInputArea()) {
				userLoginCardsPanel.getLoginInstructionPanel().setTextToInput(""); // set text field to "" when it is selected
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
//			if (arg0.getSource() == userLoginCardsPanel.getLoginInstructionPanel().getInputArea()) {
//				userLoginCardsPanel.getLoginInstructionPanel().setTextToInput(""); // set text field to "" when it is selected
//			}
//			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			String input = userLoginCardsPanel.getLoginInstructionPanel().getInput();
			if (arg0.getSource() == userLoginCardsPanel.getLoginInstructionPanel().getInputArea()) {
				if (!(input.length() > 0)) { 
				userLoginCardsPanel.getLoginInstructionPanel().setDefaultText(); // set text field to "" when it is selected
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

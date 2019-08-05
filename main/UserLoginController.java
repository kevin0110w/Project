package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class is responsible for coordinating actions between the main login card panel with the model, passing data from the view to the model
 *
 */
public class UserLoginController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	private UserLoginModel userLoginModel;
	
	public UserLoginController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainWindow = mw;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginModel = model;
		setListeners(); // set the listeners in all the separate view classes
	}
	
	/**
	 * Set the action listeners in all the separate views
	 */
	public void setListeners() {
		this.userLoginCardsPanel.setLoginInstructionPanel(new UserLoginListener());
		this.userLoginCardsPanel.setLoginInstructionMouseListener(new UserLoginListener());

		this.userLoginCardsPanel.setLoginSuccessPanel(new UserLoginSuccessListener());
	}
	
	/**
	 * Set the action listeners in the selection panel once the users details have been pulled from the database
	 */
	public void setLoginSelectionPanelListeners() {
		this.userLoginCardsPanel.setLoginSelectionPanel(new UserSelectionListener());
		this.userLoginCardsPanel.setLoginSelectionMouseListener(new UserSelectionListener());
	}
	
	/**
	 * Set the text in the login complete page
	 */
	public void setText() {
//		if (this.model.correctPassword()) {
//			this.mainPanel.userLoginSuccessPanel.setTextArea("Welcome to the system: " + this.model.getUserID());
//		} else {
//			this.mainPanel.userLoginSuccessPanel.setTextArea("Your login was not correct");
//		}
		this.userLoginCardsPanel.getUserLoginSuccessPanel().setTextArea("The log in phase is now complete\n\nYou may now log off");
	}

	class UserLoginListener implements ActionListener, MouseListener {
		int loginSelection, pictureSelection = 0;
		String userIdString;
		boolean successfulPastLogin;
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
//				case "LOGINSELECTION":
////					try {
//						loginSelection = mainPanel.getSelection(); // set the login selection based on the selected index from the jcombo box
////						if (selection == 0) {
////							throw new Exception();
////						}
////					} catch (Exception exception) {
////						JOptionPane.showMessageDialog(mainPanel, "Log-in Method has not been selected",
////							    "Log In Warning",
////							    JOptionPane.WARNING_MESSAGE);
////					}
//					
//					break;
//				case "PICTURESELECTION":
//					pictureSelection = mainPanel.getPictureSelection(); // set the picture selection based on the selected index from the jcombo box
//					break;
				case "BACK":
					mainWindow.showMainPage();
//					userLoginCardsPanel.showMainPage(); // show the main page
					userLoginModel.clear(); // clear any locally stored data
					userLoginCardsPanel.clearInstructionPanel(); // reset the instruction page view
					break;
				case "NEXT":
					boolean success;
					try {
						success = true;
					userIdString = userLoginCardsPanel.getInput(); 
//					int userID = Integer.parseInt(userIdString); 
					userLoginModel.setUserID(userIdString);// set the user id
					pictureSelection = userLoginCardsPanel.getPictureSelection();
					loginSelection = userLoginCardsPanel.getSelection();
					if (loginSelection == 0 || pictureSelection == 0) {
						success = false;
						throw new Exception(); // throw a dialog box if an invalid picture or login selection is chosen from the jcombo boxes
					}
					userLoginModel.setLoginMethod(loginSelection); // set the model's field variable for login method
					userLoginModel.setPictureSelection(pictureSelection); // set the model's field variable for picture set
					userLoginModel.setLoginAttempt(); // set the model's field variable for login attempt by pulling the details from the database
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(userLoginCardsPanel, "Warning - UserID is not entered, Log-in Method and/or Picture Set have not been selected",
							    "Log In Warning",
							    JOptionPane.WARNING_MESSAGE);
						success=false;
					} 
					// check that correct details have been inputted and that these are associated with a registered user
					if (success && userLoginModel.returnIsValid()) {
					userLoginModel.returnMostRecentLoginSuccess(); // check whether this user logged in successfully recently
//					mainPanel.loginSelectionPanel.setSuccess(successfulPastLogin);
					userLoginCardsPanel.getLoginSelectionPanel().getFilePaths(userLoginModel.getDecoyImages()); // pull the file paths into the view and set the buttons
					setLoginSelectionPanelListeners(); // set the action listeners in the selection panel now that buttons have been set
					userLoginCardsPanel.showLoginPanel(); // show the selection page
					userLoginModel.setInitialTime(); // start the clock
					} else {
						JOptionPane.showMessageDialog(userLoginCardsPanel, "Warning - Not a valid User ",
							    "Log In Warning",
							    JOptionPane.WARNING_MESSAGE);
						success=false;
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
	
	class UserSelectionListener implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
				case "" + 0:
				case "" + 1:
				case "" + 2:
				case "" + 3:
				case "" + 4:
				case "" + 5:
				case "" + 6:
				case "" + 7:
				case "" + 8:
				case "" + 9:
				case "" + 10:
				case "" + 11:
				case "" + 12:
				case "" + 13:
				case "" + 14:
				case "" + 15:
				case "" + 16:
				case "" + 17:
				case "" + 18:
				case "" + 19:
				case "" + 20:
					userLoginCardsPanel.getLoginSelectionPanel().disableButton(arg0.getActionCommand());
					userLoginCardsPanel.setImage(arg0.getSource()); // set the image to the panel 
					userLoginModel.addImage(arg0.getSource()); // add the image to the model's field variable entered passwords
					userLoginModel.addTime(); // add the time taken to make this click in the model
					if (userLoginModel.getEnteredPasswords().size() == 3) {
						userLoginCardsPanel.getLoginSelectionPanel().disableButtons(); // if 3 clicks are performed, disable all buttons
					}
					break;
				case "LOGIN":
					if (userLoginModel.getEnteredPasswords().size() == 3) {
					userLoginCardsPanel.showLoginCompletePage(); // show the complete page
					setText(); // set the text
					userLoginModel.addLoginAttempt(); // add the user's login attempt to the database
					userLoginModel.clear();
					userLoginCardsPanel.getLoginSelectionPanel().clear();
					userLoginCardsPanel.clearInstructionPanel(); 
					} else {
						JOptionPane.showMessageDialog(userLoginCardsPanel.getLoginSelectionPanel(), "You have not selected 3 images",
						    "Image Selection Error",
						    JOptionPane.WARNING_MESSAGE);
					}
					break;
				case "BACK":
					userLoginCardsPanel.showInstructionPanel(); // show the instruction page
					userLoginCardsPanel.clearInstructionPanel(); // show the instruction page
					userLoginCardsPanel.getLoginSelectionPanel().clear(); // reset the selection panel view so that there is no crossover if user tries again with different details
					userLoginModel.clear(); // reset the model so that immediate log ins aren't holding onto previous data
					break;
				}
			}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}
	
	class UserLoginSuccessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "HOME":
//				userLoginCardsPanel.getMainWindow().showMainPage(); // show the home page
				mainWindow.showMainPage();
				userLoginCardsPanel.showInstructionPanel(); // reset the panel to the instruciton panel
				break;
			}
			
		}
		
	}
}
			
		


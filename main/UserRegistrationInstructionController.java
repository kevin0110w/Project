package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * This class is responsible for coordinating activities between the registration panel and the registration model
 *
 */
public class UserRegistrationInstructionController {
	private MainWindow mainWindow;
	private UserRegistrationInstructionPanel panel;
	private UserRegistrationModel model;

	public UserRegistrationInstructionController(UserRegistrationInstructionPanel panel, MainWindow view,
			UserRegistrationModel model) {
		this.panel = panel;
		this.mainWindow = view;
		this.model = model;
		this.panel.addUserIntructionListener(new UserRegistrationInstructionListener());
	}

	class UserRegistrationInstructionListener implements MouseListener, ActionListener {
		
		/**
		 * Check whether a user has made a selection in the drop down boxes
		 * @param input - index selected in the down
		 * @return
		 */
		public boolean invalidInput(int input) {
			return input == 0;
		}
		
		/**
		 * Throw up a dialog box if an invalid picture set selection is chosen
		 */
		public void invalidSelection() {
				JOptionPane.showMessageDialog(panel, "Please select a valid picture/login", "Invalid picture or login type selection",
						JOptionPane.WARNING_MESSAGE);
		}
		
//		public void invalidLoginSelection(boolean invalid) {
//			if (invalid) {
//				JOptionPane.showMessageDialog(panel, "Please select a valid registration method", "Invalid registration method selection",
//						JOptionPane.WARNING_MESSAGE);
//			}
//		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "BACK":
				mainWindow.showMainPage(); // show the main page of the program
				panel.resetPicsSelectionBox(); // reset the jcombo box to the 0th index element  
				panel.resetLoginSelectionBox();  // reset the jcombo box to the 0th index element
				try {
					panel.setInputText("Please enter your UserID"); // reset the input box
				} catch (NumberFormatException e) {

				}	
				break;
			case "NEXT":
				int userID;
				try {
					 userID = Integer.parseInt(panel.getUserEntry()); // try and capture the inputted text
					if (invalidInput(panel.getPicsSelection())) {
						invalidSelection();
						break;
					}
//						else if (invalidInput(login)) {
//							invalidPicSelection(invalidInput(login));
//							break;	
//						}
				} catch (NumberFormatException exception) { // throw up a dialog box if incorrect userid entered
					JOptionPane.showMessageDialog(panel, "Please enter a numeric UserID only", "UserID Input Error",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				model.setUserID(userID); // set the userid field in the model
				model.setLoginMethod(panel.getLoginMethod());
				model.setPictureSet(panel.getPicsSelection()); // set the picture set field in the model
				if (!model.isUserAlreadyRegistered()) { // check whether user is registered on the database
				model.createRegistrationSet(); // otherwise make model create the registration set of 60 images
				mainWindow.createRegistrationImagePage();
				mainWindow.showRegistrationPage(); // show the registration page
				model.setInitialTime(); // start the clock to time user clicks
				try {
					panel.setInputText("Please enter your UserID"); // otherwise reset the input 
					panel.resetPicsSelectionBox(); // reset the jcombo box incase user clicks back
					panel.resetLoginSelectionBox();// reset the jcombo box incase user clicks back
				} catch (NumberFormatException e) {

				}	
				} else { // throw up a warning if a user already exists
					JOptionPane.showMessageDialog(panel, "A User Already Exists With That UserID and Picture Set Choice", "User Registration Error",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
//			case "SELECTLOGIN":
//				login = panel.getLoginMethod();
//				invalidLoginSelection(invalidInput(login));
//				break;
//			case "SELECTPICS":
//				int pics = panel.getPicsSelection();
//				if (invalidInput(pics)) {
//					invalidPicSelection();
//				}
//				break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == panel.getInputArea()) {
				panel.setInputText(""); // clear the placeholder text if the input box is selected with the mouse.
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

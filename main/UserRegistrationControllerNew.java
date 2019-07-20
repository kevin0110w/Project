package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;;
/**
 * This class is responsible for coordinating the actions between the views that are attached to the userregistrationcardspanel with the model
 *
 */
public class UserRegistrationControllerNew {
	private MainWindow mainWindow;
	private UserRegistrationCardsPanel userRegistrationCardsPanel;
	private UserRegistrationModel userRegistrationModel;

	public UserRegistrationControllerNew(MainWindow mw, UserRegistrationCardsPanel panel, UserRegistrationModel model) {
		this.mainWindow = mw;
		this.userRegistrationCardsPanel = panel;
		this.userRegistrationModel = model;
		this.userRegistrationCardsPanel.getUserRegistrationInstructionPanel()
				.addUserIntructionListener(new UserRegistrationInstructionListener());
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().setModel(this.userRegistrationModel); // associate the userregistrationpanel with this model
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetOne(model.getFnt().getListOne()); // set the 1st image set in the UserRegistrationPanel class to create a unique panel
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetTwo(model.getFnt().getListTwo()); // set the 2nd image set in the UserRegistrationPanel class to create a unique panel
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetThree(model.getFnt().getListThree()); // set the 3rd image set in the UserRegistrationPanel class to create a unique panel
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().adduserRegistrationButtonListener(new UserRegistrationListener()); // associate each interactive button in the user registration panel with an action listener
	}

	/**
	 * Create the registration image page with the 60 images and the controller
	 */
	public void createRegistrationImagePage() {
		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(
				this.userRegistrationCardsPanel, this.userRegistrationModel);
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setUpImagePanels();
		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelOne(), this.userRegistrationModel);
		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelTwo(), this.userRegistrationModel);
		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelThree(), this.userRegistrationModel);
	}

	class UserRegistrationInstructionListener implements MouseListener, ActionListener {

		/**
		 * Check whether a user has made a selection in the drop down boxes
		 * 
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
			JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
					"Please select a valid picture/login", "Invalid picture or login type selection",
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
				userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetPicsSelectionBox(); // reset the
																											// jcombo
																											// box to
																											// the 0th
																											// index
																											// element
				userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetLoginSelectionBox(); // reset the
																											// jcombo
																											// box to
																											// the 0th
																											// index
																											// element
				try {
					userRegistrationCardsPanel.getUserRegistrationInstructionPanel()
							.setInputText("Please enter your UserID"); // reset the input box
				} catch (NumberFormatException e) {

				}
				break;
			case "NEXT":
				int userID;
				try {
					userID = Integer
							.parseInt(userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getUserEntry()); // try
																														// and
																														// capture
																														// the
																														// inputted
																														// text
					if (invalidInput(
							userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getPicsSelection())) {
						invalidSelection();
						break;
					}
//						else if (invalidInput(login)) {
//							invalidPicSelection(invalidInput(login));
//							break;	
//						}
				} catch (NumberFormatException exception) { // throw up a dialog box if incorrect userid entered
					JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
							"Please enter a numeric UserID only", "UserID Input Error", JOptionPane.WARNING_MESSAGE);
					break;
				}
				userRegistrationModel.setUserID(userID); // set the userid field in the model
				userRegistrationModel.setLoginMethod(
						userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getLoginMethod());
				userRegistrationModel.setPictureSet(
						userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getPicsSelection()); // set the
																												// picture
																												// set
																												// field
																												// in
																												// the
																												// model
				if (!userRegistrationModel.isUserAlreadyRegistered()) { // check whether user is registered on the
																		// database
					userRegistrationModel.createRegistrationSet(); // otherwise make model create the registration set
																	// of 60 images
					createRegistrationImagePage();
					userRegistrationCardsPanel.showRegistrationPanel(); // show the registration page
					userRegistrationModel.setInitialTime(); // start the clock to time user clicks
					try {
						userRegistrationCardsPanel.getUserRegistrationInstructionPanel()
								.setInputText("Please enter your UserID"); // otherwise reset the input
						userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetPicsSelectionBox(); // reset
																													// the
																													// jcombo
																													// box
																													// incase
																													// user
																													// clicks
																													// back
						userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetLoginSelectionBox();// reset
																													// the
																													// jcombo
																													// box
																													// incase
																													// user
																													// clicks
																													// back
					} catch (NumberFormatException e) {

					}
				} else { // throw up a warning if a user already exists
					JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
							"A User Already Exists With That UserID and Picture Set Choice", "User Registration Error",
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
			if (e.getSource() == userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputArea()) {
				userRegistrationCardsPanel.getUserRegistrationInstructionPanel().setInputText(""); // clear the
																									// placeholder text
																									// if the input box
																									// is selected with
																									// the mouse.
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

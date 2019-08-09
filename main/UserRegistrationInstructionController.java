package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;;

/**
 * This class is responsible for coordinating the actions between the views that
 * are attached to the userregistrationcardspanel with the model
 *
 */
public class UserRegistrationInstructionController {
	private MainWindow mainWindow;
	private UserRegistrationCardsPanel userRegistrationCardsPanel;
	private UserRegistrationModel userRegistrationModel;

	public UserRegistrationInstructionController(MainWindow mw, UserRegistrationCardsPanel panel, UserRegistrationModel model) {
		this.mainWindow = mw;
		this.userRegistrationCardsPanel = panel;
		this.userRegistrationModel = model;
		this.userRegistrationCardsPanel.getUserRegistrationInstructionPanel().addUserIntructionListener(new UserRegistrationInstructionListener());
	}

	/**
	 * Create the registration image page with the 60 images and the controller
	 */
	public void createRegistrationImagePage() {
		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(this.userRegistrationCardsPanel, this.userRegistrationModel);
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setUpImagePanels();
		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelOne(),
				this.userRegistrationModel);
		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelTwo(),
				this.userRegistrationModel);
		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelThree(),
				this.userRegistrationModel);
	}

	class UserRegistrationInstructionListener implements MouseListener, ActionListener {
		/**
		 * Check whether a user has made a selection in the drop down boxes
		 * 
		 * @param input - index selected in the down
		 * @return
		 */
		public boolean invalidInput(int pictureSetSelection, int loginSelection, String userID) {
			String text = "Please enter your student ID here";
			text = text.toUpperCase();
			return pictureSetSelection == 0 || loginSelection == 0 || userID.equals(text);
		}

		/**
		 * Throw up a dialog box if an invalid picture set selection is chosen
		 */
		public void invalidSelection() {
			JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
					"Please enter valid user registration details.", "Invalid User Registration Details",
					JOptionPane.WARNING_MESSAGE);
		}
		
		public void resetInputArea() {
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetPicsSelectionBox(); 
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetLoginSelectionBox(); // reset the jcombo box to the 0th element
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().setDefaultText(); // reset the input box
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "BACK":
				mainWindow.showMainPage(); // show the main page of the program
				resetInputArea();
				userRegistrationModel.clear();
				mainWindow.updateSmallFrameSize();
				mainWindow.setLabelMainMenu();
				break;
			case "NEXT":
				int pictureSetSelection, loginSelection;
				String id = null;
				id = getUserID().toUpperCase();
				pictureSetSelection = userRegistrationCardsPanel.getUserRegistrationInstructionPanel()
						.getPicsSelection();
				loginSelection = userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getLoginMethod();
				if (invalidInput(pictureSetSelection, loginSelection, id)) {
					invalidSelection();
					break;
				}
//					userID = Integer.parseInt(id); // capture inputted text

				userRegistrationModel.setUserID(id); // set the userid field in the model
				userRegistrationModel.setLoginMethod(loginSelection);
				userRegistrationModel.setPictureSet(pictureSetSelection); // set the picture set field in the model
				if (!userRegistrationModel.isUserAlreadyRegistered()) { // check whether user is registered against the
																		// database
					userRegistrationModel.createRegistrationSet(); // otherwise make model create the registration set
																	// of 60 images
					createRegistrationImagePage();
					userRegistrationCardsPanel.showRegistrationPanel(); // show the registration page
					userRegistrationModel.setInitialTime(); // start the clock to time user clicks
					resetInputArea();
					mainWindow.setLabelText("Password Selection");
				} else { // throw up a warning if a user already exists
					JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
							"A User Already Exists With Those Details. Please Try Again", "User Registration Error",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
			}
		}

		private String getUserID() {
			String userID = userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getUserEntry();
//			if (userID.charAt(0) == ('0')) {
//				userID = userID.substring(1);
//			}
			return userID;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputArea()) {
				userRegistrationCardsPanel.getUserRegistrationInstructionPanel().clearText(); 
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			String input = userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputText();
			if (e.getSource() == userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputArea()) {
				if (!(input.length() > 0)) {
					userRegistrationCardsPanel.getUserRegistrationInstructionPanel().setDefaultText(); 
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}
}

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import main.UserRegistrationController.UserRegistrationListener;;

/**
 * This class is responsible for coordinating the actions between the registration instruction panel with the model
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
	 * Create the three images panels and the controllers that'll handle the interactions between image panels.
	 */
	public void createRegistrationImagePanels() {
		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(mainWindow, userRegistrationCardsPanel, userRegistrationModel);
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetOne(userRegistrationModel.getImageFiles().getListOne()); // set the 1st image set in the UserRegistrationPanel class to create a unique panel
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetTwo(userRegistrationModel.getImageFiles().getListTwo()); // set the 2nd image set in the UserRegistrationPanel class to create a unique panel
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetThree(userRegistrationModel.getImageFiles().getListThree()); // set the 3rd image set in the UserRegistrationPanel class to create a unique panel
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
		 * A method to get the user id from the text box in the user registration instruction panel
		 * @return
		 */
		private String getUserID() {
			String userID = userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputText();
			return userID;
		}
		
		/**
		 * Check whether a user has made a valid selection in the drop down boxes or entered a valid userid
		 * @param input - index selected in the down
		 * @return
		 */
		public boolean invalidInput(int pictureSetSelection, int loginSelection, String userID) {
			String text = "Please enter your unique UserID here";
			text = text.toUpperCase();
			return pictureSetSelection == 0 || loginSelection == 0 || userID.equals(text);
		}

		/**
		 * Throw up a dialog box if an invalid choice is made.
		 */
		public void invalidSelection() {
			JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
					"Please enter valid user registration details.", "Invalid User Registration Details",
					JOptionPane.WARNING_MESSAGE);
		}
		
		/**
		 * Resets the user input areas. This method will be called if the user clicks on the back or next buttons.
		 */
		public void resetInputArea() {
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetPicsSelectionBox(); 
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().resetLoginSelectionBox(); // reset the jcombo box to the 0th element
			userRegistrationCardsPanel.getUserRegistrationInstructionPanel().setDefaultText(); // reset the input box
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			/**
			 * If the user clicks the back button, show the main page, reset the input areas in the user registration instruction panel,
			 * clear any items that are stored in the model, resize the window and reset the label in the main window
			 */
			case "BACK":
				mainWindow.showMainPage(); 
				resetInputArea(); 
				userRegistrationModel.clear();
				mainWindow.updateSmallFrameSize();
				break;
			/**
			 * If the user clicks the next button, process the data that has been input into the JTextfield and selections made from the jcombo boxes.
			 * Next, check whether a user with the same details is already stored in the database.
			 * If there is, throw up a warning, otherwise, create a new registration set of 60 images, create the image panels and controllers, show that panel and start the timer
			 */
			case "NEXT":
				int pictureSetSelection, loginSelection;
				String id = getUserID().toUpperCase();
				pictureSetSelection = userRegistrationCardsPanel.getUserRegistrationInstructionPanel()
						.getPicsSelection();
				loginSelection = userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getLoginMethod();
				if (invalidInput(pictureSetSelection, loginSelection, id)) {
					invalidSelection();
					break;
				}
				userRegistrationModel.setUserID(id);
				userRegistrationModel.setLoginMethod(loginSelection);
				userRegistrationModel.setPictureSet(pictureSetSelection); 
				if (!userRegistrationModel.isUserAlreadyRegistered()) {
					userRegistrationModel.createRegistrationSet();
					createRegistrationImagePanels();
					userRegistrationCardsPanel.showRegistrationPanel();
					userRegistrationModel.setInitialTime();
					resetInputArea();
					mainWindow.setLabelSelectImages();
				} else {
					JOptionPane.showMessageDialog(userRegistrationCardsPanel.getUserRegistrationInstructionPanel(),
							"A User Already Exists With Those Details. Please Try Again", "User Registration Error",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			/**
			 * If a user clicks on the JTextField on the user registration instruction panel, clear the placeholder text
			 */
			if (e.getSource() == userRegistrationCardsPanel.getUserRegistrationInstructionPanel().getInputArea()) {
				userRegistrationCardsPanel.getUserRegistrationInstructionPanel().clearText(); 
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			/**
			 * If a user exits the JTextField  on the user registration instruction panel without entering any text, reset the box to show the placeholder text
			 */
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

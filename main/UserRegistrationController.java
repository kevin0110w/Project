package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for coordinating events between the UserRegistrationPanel with the UserRegistrationModel
 */
public class UserRegistrationController {
	private UserRegistrationCardsPanel userRegistrationCardsPanel;
	private MainWindow mainWindow;
	private UserRegistrationModel model;

	public UserRegistrationController(MainWindow mw, UserRegistrationCardsPanel panel, UserRegistrationModel model) {
		this.mainWindow = mw;
		this.userRegistrationCardsPanel = panel;
		this.model = model;
		this.userRegistrationCardsPanel.getUserRegistrationPanel().adduserRegistrationActionListener(new UserRegistrationListener()); // associate each interactive button in the user registration panel with an action listener
	}

	class UserRegistrationListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			/**
			 * Show the previous image panel if the scroll left button is pressed
			 */
			case "SCROLLLEFT":
				userRegistrationCardsPanel.getUserRegistrationPanel().showPrevious(); // show earlier image panel
				break;
			/**
			 * Show the next image panel if the scroll left button is pressed
			 */	
			case "SCROLLRIGHT":
				userRegistrationCardsPanel.getUserRegistrationPanel().showNext(); // show later image panel
				break;
			/**
			 * Reset the main window label and show the user registration instruction panel if the back button is pressed.
			 * Clear any data that may have been stored in the model so that there is no crossovers between images should the user try a different picture set etc..
			 * and reset the selection view.
			 */
			case "BACK":
				mainWindow.setLabelRegistrationInstructions();
				userRegistrationCardsPanel.getUserRegistrationPanel().clear();
				model.clear();
				userRegistrationCardsPanel.showInstructionPanel(); 
				break;
			/**
			 * Call the add user method from the model and store the details about a user (i.e. user id, picture set, login method, seen images, challenge set images) to the database.
			 * Show the complete panel. Clear the model, reset the view.
			 */
			case "NEXT":
				try {
					model.addUser();
					userRegistrationCardsPanel.showCompletePanel();
					model.clear();
					userRegistrationCardsPanel.getUserRegistrationPanel().clear();
					mainWindow.setLabelRegistrationComplete();
					mainWindow.updateSmallFrameSize();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				break;
			}
		}
	}
}

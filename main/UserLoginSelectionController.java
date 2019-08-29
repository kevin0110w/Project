package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class is responsible for determining the images selected and relay this information to the model.
 *
 */
public class UserLoginSelectionController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	private UserLoginModel userLoginModel;
	
	public UserLoginSelectionController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainWindow = mw;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginModel = model;
		setListeners();
	}
	
	/**
	 * Set each image with an action listener
	 */
	public void setListeners() {
		this.userLoginCardsPanel.addUserSelectionListener(new UserSelectionListener());
	}
	
	class UserSelectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			/**
			 * Each image is associated with an action command.
			 * When an image is selected, i) that image will be disabled, ii) The selected image will be displayed, iii) the model will register the selected image,
			 * iv) the time taken to select that image is recorded.
			 * v) Once 3 images have been selected, all images will be disabled. 
			 */
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
					Icon icon = ((JButton) arg0.getSource()).getIcon();
					String filePath = icon.toString();
					userLoginCardsPanel.getLoginSelectionPanel().disableButton(arg0.getActionCommand());
					userLoginCardsPanel.setImage(filePath); // set the image to the panel 
					userLoginModel.addImage(filePath); // add the image to the model's field variable entered passwords
					userLoginModel.addTime(); // add the time taken to make this click in the model
					if (userLoginModel.getEnteredPasswords().size() == 3) {
						userLoginCardsPanel.getLoginSelectionPanel().disableButtons(); // if 3 clicks are performed, disable all buttons
					}
					break;
				/**
				 * If a user clicks next, the application will show the login complete page. The model will send a SQL update query via the DB Connect object to log an attempt on the database
				 * The panel and model will be reset for future login attempts. If a user hasn't selected 3 images, a warning dialog box will be displayed.
				 * A message will also be set advising whether the login has been successful or not.
				 */
				case "NEXT":
					if (userLoginModel.getEnteredPasswords().size() == 3) {
					userLoginCardsPanel.showLoginCompletePage();
					userLoginModel.addLoginAttempt();
					userLoginCardsPanel.getLoginSelectionPanel().clear();
					userLoginCardsPanel.clearInstructionPanel(); 
					} else {
						JOptionPane.showMessageDialog(userLoginCardsPanel.getLoginSelectionPanel(), "You have not selected 3 images",
						    "Image Selection Error",
						    JOptionPane.WARNING_MESSAGE);
						break;
					}
					setText();
					setMainWindowLabel();
					mainWindow.updateSmallFrameSize();
					userLoginModel.clear();
					break;
				/**
				 * If a user clicks on the back button, show the user login instruction panel
				 * Reset the views so that there isn't any crossovers if the user tries logging again and clear any data stored on the model.
				 */
				case "BACK":
					mainWindow.setLabelLoginInstructions();
					userLoginCardsPanel.showInstructionPanel(); 
					userLoginCardsPanel.clearInstructionPanel(); 
					userLoginCardsPanel.getLoginSelectionPanel().clear();
					userLoginModel.clear(); 
					break;
				}
			}
		
		/**
		 * Sets the appropriate message on the login complete panel depending on the outcome of the login
		 */
		private void setText() {
			String text = "";
			if (userLoginModel.correctPassword()) {
				text = "You have logged in successfully. \n\n";
				text += "Click on the Log Off button below to \nreturn to the main menu.";
			} else {
				text = "You have not been able to log in. \n\n";
				text += "Click on the Log Off button below to \nreturn to the main menu.";
			}
			userLoginCardsPanel.setLoginCompletePanelText(text);
		}
		
		/**
		 * Sets the appropriate message on the label on the login complete panel depending on the outcome of the login
		 */
		private void setMainWindowLabel() {
			if (userLoginModel.correctPassword()) {
				mainWindow.setLabelLoginSuccessful();
			} else {
				mainWindow.setLabelLoginUnsuccessful();
			}
		}
	}	
}

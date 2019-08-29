package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for controlling the actions between the complete panel with the mainWindow
 */
public class UserLoginCompleteController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	
	public UserLoginCompleteController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel) {
		this.mainWindow = mw;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginCardsPanel.getUserLoginCompletePanel().addListeners(new UserLoginSuccessListener());
	}
	

	class UserLoginSuccessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			/**
			 * If a user clicks on the home/log off button, show the main page and reset the user login cards panel to show the instruction panel.
			 */
			case "HOME":
				mainWindow.showMainPage(); // show the home page
				userLoginCardsPanel.showInstructionPanel(); // reset the panel to the instruciton panel
				break;
			}
			
		}
		
	}
}

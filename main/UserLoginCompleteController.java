package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for controlling the actions between the complete panel andn the model
 *
 */
public class UserLoginCompleteController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	private UserLoginModel model;
	
	public UserLoginCompleteController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainWindow = mw;
		this.model = model;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginCardsPanel.getUserLoginSuccessPanel().addListeners(new UserLoginSuccessListener());
	}
	

	class UserLoginSuccessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "HOME":
//				userLoginCardsPanel.getMainWindow().showMainPage(); // show the home page
				model.clear();
				mainWindow.showMainPage();
				userLoginCardsPanel.showInstructionPanel(); // reset the panel to the instruciton panel
				break;
			}
			
		}
		
	}
}

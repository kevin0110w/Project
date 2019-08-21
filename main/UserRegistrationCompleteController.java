package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for coordinating actions between the complete panel
 * with the main window
 *
 */
public class UserRegistrationCompleteController {
	private UserRegistrationCardsPanel userRegistrationCardsPanel;
	private MainWindow mw;

	public UserRegistrationCompleteController(UserRegistrationCardsPanel userRegistrationCardsPanel, MainWindow mw) {
		this.mw = mw;
		this.userRegistrationCardsPanel = userRegistrationCardsPanel;
		this.userRegistrationCardsPanel.getUserRegistrationCompletePanel().addActionListener(new UserRegistrationCompleteListener());
	}

	class UserRegistrationCompleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			/**
			 * If a user clicks the home button, the user registration cards panel will reset to the instruction panel for the next registration, the main window will display the main page.
			 */
			case "HOME":
				userRegistrationCardsPanel.showInstructionPanel();
				mw.showMainPage();
				mw.updateSmallFrameSize();
				break;
			}

		}

	}

}

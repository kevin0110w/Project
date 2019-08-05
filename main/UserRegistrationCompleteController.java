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
//	private UserRegistrationCompletePanel panel;
//	private MainWindow mw;

//	public UserRegistrationCompleteController(UserRegistrationCompletePanel panel, MainWindow mw) {
	public UserRegistrationCompleteController(UserRegistrationCardsPanel panel) {
//		this.panel = panel;
//		this.mw = mw;
		this.userRegistrationCardsPanel = panel;
		this.userRegistrationCardsPanel.getUserRegistrationCompletePanel()
				.addActionListener(new UserRegistrationCompleteListener());
	}

	class UserRegistrationCompleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "HOME":
				userRegistrationCardsPanel.showInstructionPanel();
				userRegistrationCardsPanel.getMw().showMainPage();
				userRegistrationCardsPanel.getMw().updateSmallFrameSize();
				break;
			}

		}

	}

}

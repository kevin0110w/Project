

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationInstructionController {
	private MainWindow view;
	private UserRegistrationInstructionPanel panel;
	public UserRegistrationInstructionController(UserRegistrationInstructionPanel x, MainWindow view) {
		this.panel = x;
		this.view = view;
		this.panel.addUserIntructionListener(new UserRegistrationInstructionListener());
	}
	
	class UserRegistrationInstructionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == panel.returnNext()) {
				view.showRegistrationPage();
			} else {
				view.showMainPage();
			}
		}
	}
}
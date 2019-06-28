

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInstructionController {
	private MainWindow view;
	private UserInstructionPanel panel;
	public UserInstructionController(UserInstructionPanel x, MainWindow view) {
		this.panel = x;
		this.view = view;
		this.panel.addUserIntructionListener(new UserInstructionListener());
	}
	
	class UserInstructionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == panel.returnNext()) {
				view.showRegistrationPage();
			} else {
				view.showMainPage();
			}
			
			
			// to-do
			// login validator -- user must correctly click
			// three pictures
		}
	}
}
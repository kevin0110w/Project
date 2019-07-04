import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationCompleteController {

	private UserRegistrationCompletePanel panel;
	private MainWindow mw;

	public UserRegistrationCompleteController(UserRegistrationCompletePanel panel, MainWindow mw) {
		this.panel = panel;
		this.mw = mw;
		this.panel.addActionListener(new UserRegistrationCompleteListener());
	}
	
	class UserRegistrationCompleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "HOME":
				mw.showMainPage();
				break;
			case "EXIT":
				System.exit(0);
				break;
			}
			
		}
		
	}

}

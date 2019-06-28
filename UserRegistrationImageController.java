import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationImageController {
	private UserRegistrationImagePanel panel;
	
	
	public UserRegistrationImageController(UserRegistrationImagePanel panel) {
		this.panel = panel;
		this.panel.addListener(new UserRegistrationImageListener());
	}
	
	class UserRegistrationImageListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "" + 1:
			case "" + 2:
			case "" + 3:
			case "" + 4:
			case "" + 5:
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
				panel.addString(e.getSource());
				panel.setImage(e.getSource());
				break;
			}
		}
	}
}
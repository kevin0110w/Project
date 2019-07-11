import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This class is responsible for controlling the actions between the complete panel andn the model
 *
 */
public class UserLoginSuccessController {
	private UserLoginSuccessPanel panel;
	private UserLoginModel model;
	
	public UserLoginSuccessController(UserLoginSuccessPanel panel, UserLoginModel model) {
		this.panel = panel;
		this.model = model;
	}
	
	/**
	 * Method to set the text area in the view
	 */
	public void setText() {
//		if (this.model.correctPassword()) {
//			this.panel.setTextArea("Welcome to the system: " + this.model.getUserID());
//		} else {
//			this.panel.setTextArea("Welcome to the system: Guest");
//		}
		this.panel.setTextArea("Login is now complete\n\nYou many now log off");
	}
}

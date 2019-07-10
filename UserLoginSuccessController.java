import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserLoginSuccessController {
	private UserLoginSuccessPanel panel;
	private UserLoginModel model;
	
	public UserLoginSuccessController(UserLoginSuccessPanel panel, UserLoginModel model) {
		this.panel = panel;
		this.model = model;
	}

	public void setText() {
//		if (this.model.correctPassword()) {
//			this.panel.setTextArea("Welcome to the system: " + this.model.getUserID());
//		} else {
//			this.panel.setTextArea("Welcome to the system: Guest");
//		}
		this.panel.setTextArea("Login is now complete\n\nYou many now log off");
	}
	public static void main(String[] args) {
		UserLoginSuccessPanel p = new UserLoginSuccessPanel();
		UserLoginModel m = new UserLoginModel();
		UserLoginSuccessController c = new UserLoginSuccessController(p, m);
		JFrame frame = new JFrame();
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		c.setText();
	}
}

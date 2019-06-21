import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserRegistrationController {
	private View_2 view;
	public UserRegistrationController(View_2 aView) {
		view = aView;
		this.view.addListener(new UserRegistrationListener());
	}
	
	class UserRegistrationListener implements ActionListener {

		
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("HELLO");
			view.loggedInScreen();
			
		}
		
	}

}

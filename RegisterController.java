import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
	private View_2 view;

	public RegisterController(View_2 aView) {
		view = aView;
		this.view.addListener(new RegisterListener());
	}

	class RegisterListener implements ActionListener {

	
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Hello");
			view.registerScreen();

		}
	}
}

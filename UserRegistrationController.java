import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is responsible for coordinating events between the UserRegistrationPanel with the UserRegistrationModel
 * @author 0808148w
 *
 */
public class UserRegistrationController {
	private MainWindow view;
	private UserRegistrationPanel panel;
	private UserRegistrationModel model;

	public UserRegistrationController(UserRegistrationPanel x, MainWindow view, UserRegistrationModel model) {
		this.panel = x;
		this.view = view;
		this.model = model;
		this.panel.setModel(this.model); // associate the userregistrationpanel with this model
		this.panel.setImageSetOne(model.getFnt().getListOne()); // set the 1st image set in the UserRegistrationPanel class to create a unique panel
		this.panel.setImageSetTwo(model.getFnt().getListTwo()); // set the 2nd image set in the UserRegistrationPanel class to create a unique panel
		this.panel.setImageSetThree(model.getFnt().getListThree()); // set the 3rd image set in the UserRegistrationPanel class to create a unique panel
		this.panel.adduserRegistrationButtonListener(new UserRegistrationListener()); // associate each interactive button in the user registration panel with an action listener
	}

	class UserRegistrationListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "SCROLLLEFT":
				panel.showPrevious(); // show earlier image panel
				break;
			case "SCROLLRIGHT":
				panel.showNext(); // show later image panel
				break;
			case "BACK":
				view.showRegistrationInstructions(); // go back to the instruction panel
				model.clear(); // clear the data in memory to stop any doubling of data (e.g. button/imagesets) if a user chooses a different picture set
				break;
			case "NEXT":
				try {
					model.addUser(); // call the add user in the model class to add a new user to the database
					view.showCompletePage(); // show the complete page.
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				break;
			}
		}
	}
}

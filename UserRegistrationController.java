import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserRegistrationController {
	private MainWindow view;
	private UserRegistrationPanel panel;
	private UserRegistrationModel model;

	public UserRegistrationController(UserRegistrationPanel x, MainWindow view, UserRegistrationModel model) {
		this.panel = x;
		this.view = view;
		this.model = model;
		this.panel.setModel(this.model);
		this.panel.setImageSetOne(model.getFnt().getListOne());
		this.panel.setImageSetTwo(model.getFnt().getListTwo());
		this.panel.setImageSetThree(model.getFnt().getListThree());
		this.panel.adduserRegistrationButtonListener(new UserRegistrationListener());
	}

	class UserRegistrationListener implements ActionListener {
		String userIDString;
		int userID, loginMethod;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "SCROLLLEFT":
				panel.showPrevious();
				break;
			case "SCROLLRIGHT":
				panel.showNext();
				break;
			case "BACK":
				view.showMainPage();
				model.clear();
				break;
			case "NEXT":
				try {
					model.addUser();
					view.showCompletePage();
				} catch (Exception exception) {

				}
				break;
			}
		}
	}
}

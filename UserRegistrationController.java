import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationController {
	private MainWindow view;
	private UserRegistrationPanel panel;
	
	public UserRegistrationController(UserRegistrationPanel x, MainWindow view) {
		this.panel = x;
		this.view = view;
		this.panel.adduserRegistrationButtonListener(new UserRegistrationListener());
	}
	
	class UserRegistrationListener implements ActionListener{
		DBConnect db;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == panel.returnL()) {
				panel.showPrevious();
				System.out.println("Hello");
			} else if (e.getSource() == panel.returnR()) {
				panel.showNext();
				System.out.println("Goodbye");
			} else if (e.getSource() == panel.returnFirst()){
				System.out.println(panel.returnFirst().getIcon().toString());
			} else if (e.getSource() == panel.getSelection()) {
				panel.setLogInAttempt();
			}
//			System.out.println(e.getActionCommand());
			
//			db = new DBConnect();
//			String password = panel.getUserEntry();
//			int nextUserID = db.returnLatestAddedUserID();
//			System.out.println(nextUserID);
//			nextUserID++;
//			System.out.println(nextUserID);
//			User user = new User(password, nextUserID);
//			db.addUserToDatabase(user);
//			panel.setUserEntry();
//			view.showMainPage();
			
			// to-do
			// login validator -- user must correctly click
			// three pictures
		}
	}
}

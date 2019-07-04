import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserRegistrationController {
	private MainWindow view;
	private UserRegistrationPanel panel;
	private UserRegistrationModel model;
	private FileNames fnt;

	public UserRegistrationController(UserRegistrationPanel x, MainWindow view, UserRegistrationModel model) {
		this.panel = x;
		this.view = view;
		this.model = model;
		this.panel.setImageSetOne(model.getFnt().getListOne());
		this.panel.setImageSetTwo(model.getFnt().getListTwo());
		this.panel.setImageSetThree(model.getFnt().getListThree());
		this.panel.setModel(this.model);
		this.panel.adduserRegistrationButtonListener(new UserRegistrationListener());
	}
	
	class UserRegistrationListener implements MouseListener, ActionListener{
		DBConnect db;
		String userIDString;
		int userID, loginMethod;
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == panel.returnL()) {
				panel.showPrevious();
			} else if (e.getSource() == panel.returnR()) {
				panel.showNext();
			} else if (e.getSource() == panel.returnFirst()){
//				System.out.println(panel.returnFirst().getIcon().toString());
			} else if (e.getSource() == panel.getSelection()) {
				model.setInitialTime();
				panel.setLogInAttempt();
			} else if (e.getActionCommand().equals("BACK")) {
				view.showMainPage();
			} else if (e.getActionCommand().equals("NEXT")) {
				try {
				userIDString = panel.getUserEntry();
				userID = Integer.parseInt(userIDString);
				model.setUserID(userID);
				loginMethod = panel.getLoginMethod();
				model.setLoginMethod(loginMethod);
				model.addUser();
				view.showCompletePage();
				} catch (Exception exception) {
					
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

		@Override
		public void mouseClicked(MouseEvent e) {
			panel.setInputText("");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

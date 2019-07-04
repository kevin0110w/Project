import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class UserLoginController {
//	private UserLoginInstructionPanel panel;
//	private MainWindow mainPanel;
	private UserLoginCardsPanel mainPanel;
	private UserLoginModel model;
//	private UserLoginSelectionPanel panel;
	
	public UserLoginController(UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainPanel = userLoginCardsPanel;
//		this.panel = panel;
		this.model = model;
		this.mainPanel.setLoginInstructionPanel(new UserLoginListener());
		this.mainPanel.setLoginSuccessPanel(new UserLoginSuccessListener());
	}
	
	public void setLoginSelectionPanelListeners() {
		this.mainPanel.setLoginSelectionPanel(new UserSelectionListener());
		this.model.setInitialTime();
	}
	
	public void setText() {
		if (this.model.correctPassword()) {
			this.mainPanel.userLoginSuccessPanel.setTextArea("Welcome to the system: " + this.model.getUserID());
		} else {
			this.mainPanel.userLoginSuccessPanel.setTextArea("Your login was not correct");
		}
	}

	class UserLoginListener implements ActionListener {
		int selection = 0;
		String userIdString;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
				case "SELECTION":
					selection = mainPanel.getSelection();
					model.setLoginMethod(selection);
					break;
				case "BACK":
					mainPanel.showMainPage();
					break;
				case "NEXT":
					userIdString = mainPanel.getInput();
					int userID = Integer.parseInt(userIdString);
					model.setUserID(userID);
					model.setLoginAttempt();
					mainPanel.loginPanel();
					mainPanel.loginSelectionPanel.getFilePaths(model.getFilePaths());
					setLoginSelectionPanelListeners();
					break;
			}
		}
	}
	
	class UserSelectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
				case "" + 0:
				case "" + 1:
				case "" + 2:
				case "" + 3:
				case "" + 4:
				case "" + 5:
				case "" + 6:
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
				case "" + 18:
				case "" + 19:
				case "" + 20:
//					panel.setImage(arg0.getSource());
					System.out.println("Pressing " + arg0.getActionCommand() + " button");
					mainPanel.setImage(arg0.getSource());
					model.addImage(arg0.getSource());
					model.addTime();
					break;
				case "LOGIN":
					mainPanel.loginUser();
					setText();
					model.addLoginAttempt();
					break;
				case "BACK":
					mainPanel.showInstructionPanel();
					break;
				}
			}
	}
	
	class UserLoginSuccessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "HOME":
				mainPanel.mw.showMainPage();
				break;
			}
			
		}
		
	}
}
			
		


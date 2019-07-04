import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginSelectionController {
	private UserLoginModel model;
	private UserLoginSelectionPanel panel;
	private UserLoginCardsPanel mainPanel;
	
	public UserLoginSelectionController(UserLoginModel model, UserLoginSelectionPanel panel, UserLoginCardsPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.model = model;
		this.panel = panel;
//		this.model.setUserID(22222);
//		this.model.setLoginMethod(1);
//		this.panel.getFilePaths(model.getFilePaths());
		this.panel.addListener(new UserLoginSelectionListener());	
	}
	
	class UserLoginSelectionListener implements ActionListener {

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
				panel.setImage(arg0.getSource());
				model.addImage(arg0.getSource());
				break;
			case "BACK":
				break;
			case "LOGIN":
				mainPanel.loginUser();
				break;
			}
			
		}
		
	}

}

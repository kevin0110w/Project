import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


//import UserLoginController.UserLoginListener;

public class UserLoginCardsPanel extends JPanel {
	private CardLayout cl;
//	UserLoginSelectionPanel u ;
//	UserLoginSuccessPanel uu ;
//	UserLoginSuccessController userLoginSuccessController;
	MainWindow mw;
	UserLoginInstructionPanel loginInstructionPanel;
	UserLoginSelectionPanel loginSelectionPanel;
	UserLoginSuccessPanel userLoginSuccessPanel;
//	UserLoginModel userLoginModel;
//	UserLoginSelectionController userLoginSelectionController;
	
	public UserLoginCardsPanel(MainWindow mw) {
		this.mw = mw;
		this.setLayout(new CardLayout());
		this.loginInstructionPanel = new UserLoginInstructionPanel();
		this.loginSelectionPanel = new UserLoginSelectionPanel();
		this.userLoginSuccessPanel = new UserLoginSuccessPanel();
//		this.userLoginModel = new UserLoginModel();
		this.add(loginInstructionPanel, "LOGININSTRUCTIONS");
		this.add(loginSelectionPanel, "LOGIN");
		this.add(userLoginSuccessPanel, "SUCCESS");
		this.cl = (CardLayout) this.getLayout();
		this.setVisible(true);
		this.cl.show(this, "LOGININSTRUCTIONS");	
	}
	
	public void loginPanel() {
		this.cl.show(this, "LOGIN");
	}

	public void loginUser() {	
//		this.userLoginSuccessController.setText();
		this.cl.show(this, "SUCCESS");
	}
	

	public void showMainPage() {
		mw.showMainPage();
	}

	public int getSelection() {
		return loginInstructionPanel.getSelection();
	}

	public String getInput() {
		return loginInstructionPanel.getInput();
	}

	public void setLoginInstructionPanel(ActionListener listener) {
		this.loginInstructionPanel.addUserLoginListener(listener);
	}
	
	
	public void setLoginSelectionPanel(ActionListener listener) {
		this.loginSelectionPanel.addListener(listener);	
	}
	
	/*
	public void setUserLoginSelectionController(UserLoginModel model) {
		this.userLoginSelectionController = new UserLoginSelectionController(model, loginSelectionPanel, this);
	}
	
	public void setUserLoginSuccessController(UserLoginModel model) {
		this.userLoginSuccessController = new UserLoginSuccessController(userLoginSuccessPanel, model);
	}
	*/

	public void setImage(Object source) {
		loginSelectionPanel.setImage(source);
	}

	public void showInstructionPanel() {
		this.cl.show(this, "LOGININSTRUCTIONS");
	}

	public void setLoginSuccessPanel(ActionListener userLoginSuccessListener) {
		this.userLoginSuccessPanel.addListeners(userLoginSuccessListener);
	}

}


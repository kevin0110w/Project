import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
		setListeners();
	}
	
	public void setListeners() {
		this.mainPanel.setLoginInstructionPanel(new UserLoginListener());
		this.mainPanel.setLoginInstructionMouseListener(new UserLoginListener());
		this.mainPanel.setLoginSuccessPanel(new UserLoginSuccessListener());
	}
	
	public void setLoginSelectionPanelListeners() {
		this.mainPanel.setLoginSelectionPanel(new UserSelectionListener());
		this.model.setInitialTime();
	}
	
	public void setText() {
//		if (this.model.correctPassword()) {
//			this.mainPanel.userLoginSuccessPanel.setTextArea("Welcome to the system: " + this.model.getUserID());
//		} else {
//			this.mainPanel.userLoginSuccessPanel.setTextArea("Your login was not correct");
//		}
		this.mainPanel.userLoginSuccessPanel.setTextArea("Login is now complete\n\nYou many now log off");
	}

	class UserLoginListener implements ActionListener, MouseListener {
		int loginSelection, pictureSelection = 0;
		String userIdString;
		boolean successfulPastLogin;
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
				case "LOGINSELECTION":
//					try {
						loginSelection = mainPanel.getSelection();
//						if (selection == 0) {
//							throw new Exception();
//						}
//					} catch (Exception exception) {
//						JOptionPane.showMessageDialog(mainPanel, "Log-in Method has not been selected",
//							    "Log In Warning",
//							    JOptionPane.WARNING_MESSAGE);
//					}
					
					break;
				case "PICTURESELECTION":
					pictureSelection = mainPanel.getPictureSelection();
					break;
				case "BACK":
					mainPanel.showMainPage();
					break;
				case "NEXT":
					boolean success;
					try {
						success = true;
					userIdString = mainPanel.getInput();
					int userID = Integer.parseInt(userIdString);
					model.setUserID(userID);
					if (loginSelection == 0 || pictureSelection == 0) {
						success = false;
						throw new Exception();
					}
					model.setLoginMethod(loginSelection);
					model.setPictureSelection(pictureSelection);
					model.setLoginAttempt();
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(mainPanel, "Warning - UserID is not entered, Log-in Method and/or Picture Set have not been selected",
							    "Log In Warning",
							    JOptionPane.WARNING_MESSAGE);
						success=false;
					} 
					if (success && model.returnIsValid()) {
					mainPanel.loginPanel();
					model.returnMostRecentLoginSuccess();
//					mainPanel.loginSelectionPanel.setSuccess(successfulPastLogin);
					mainPanel.loginSelectionPanel.getFilePaths(model.getDecoyImages());
					setLoginSelectionPanelListeners();
					} else {
						JOptionPane.showMessageDialog(mainPanel, "Warning - Not a valid User ",
							    "Log In Warning",
							    JOptionPane.WARNING_MESSAGE);
						success=false;
					}
					break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == mainPanel.loginInstructionPanel.getInputArea()) {
				mainPanel.loginInstructionPanel.setTextToInput("");
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
					mainPanel.setImage(arg0.getSource());
					model.addImage(arg0.getSource());
					model.addTime();
					if (model.getEnteredPasswords().size() == 3) {
						mainPanel.loginSelectionPanel.disableButtons();
					}
					break;
				case "LOGIN":
					mainPanel.loginUser();
					setText();
					model.addLoginAttempt();
					break;
				case "BACK":
					mainPanel.showInstructionPanel();
					mainPanel.loginSelectionPanel.clear();
					model.clear();
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
				mainPanel.mw.clearModel();
				mainPanel.showInstructionPanel();
				mainPanel.clearInstructionPanel();
				mainPanel.clearSelectionPanel();
//				model.clear();
//				mainPanel.reset();
				break;
			}
			
		}
		
	}
	
	
}
			
		


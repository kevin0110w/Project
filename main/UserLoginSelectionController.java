package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import main.UserLoginController.UserSelectionListener;

public class UserLoginSelectionController {
	private MainWindow mainWindow;
	private UserLoginCardsPanel userLoginCardsPanel;
	private UserLoginModel userLoginModel;
	
	public UserLoginSelectionController(MainWindow mw, UserLoginCardsPanel userLoginCardsPanel, UserLoginModel model) {
		this.mainWindow = mw;
		this.userLoginCardsPanel = userLoginCardsPanel;
		this.userLoginModel = model;
	}
	
	public void setListeners() {
		this.userLoginCardsPanel.setLoginSelectionPanel(new UserSelectionListener());
		this.userLoginCardsPanel.setLoginSelectionMouseListener(new UserSelectionListener());
	}

	public void setText() {
		String text = "";
		if (userLoginModel.correctPassword()) {
			text = "You have logged in successfully. \n\n";
			text += "Click on the Log Off button below to \nreturn to the main menu.";
		} else {
			text = "You have not been able to log in. \n\n";
			text += "Click on the Log Off button below to \nreturn to the main menu.";
		}
		userLoginCardsPanel.setLoginCompletePanelText(text);
	}
	
	class UserSelectionListener implements ActionListener, MouseListener {
		int counter = 0;
		int clickCounter = 1;
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
					userLoginCardsPanel.getLoginSelectionPanel().disableButton(arg0.getActionCommand());
					userLoginCardsPanel.setImage(arg0.getSource()); // set the image to the panel 
					userLoginModel.addImage(arg0.getSource()); // add the image to the model's field variable entered passwords
					userLoginModel.addTime(); // add the time taken to make this click in the model
					if (userLoginModel.getEnteredPasswords().size() == 3) {
						userLoginCardsPanel.getLoginSelectionPanel().disableButtons(); // if 3 clicks are performed, disable all buttons
					}
					break;
				case "NEXT":
					if (userLoginModel.getEnteredPasswords().size() == 3) {
					userLoginCardsPanel.showLoginCompletePage(); // show the complete page
					userLoginModel.addLoginAttempt(); // add the user's login attempt to the database
					userLoginCardsPanel.getLoginSelectionPanel().clear();
					userLoginCardsPanel.clearInstructionPanel(); 
					} else {
						JOptionPane.showMessageDialog(userLoginCardsPanel.getLoginSelectionPanel(), "You have not selected 3 images",
						    "Image Selection Error",
						    JOptionPane.WARNING_MESSAGE);
						break;
					}
					setText();
					setMainWindowLabel();
					mainWindow.updateSmallFrameSize();
					userLoginModel.clear();
					break;
				case "BACK":
					userLoginCardsPanel.showInstructionPanel(); // show the instruction page
					userLoginCardsPanel.clearInstructionPanel(); // show the instruction page
					userLoginCardsPanel.getLoginSelectionPanel().clear(); // reset the selection panel view so that there is no crossover if user tries again with different details
					userLoginModel.clear(); // reset the model so that immediate log ins aren't holding onto previous data
					break;
//				case "SHOW":
//					if (counter % 2 == 0) {
//					userLoginCardsPanel.getUserLoginSelectionPanel().setHideButton();
//					userLoginCardsPanel.getUserLoginSelectionPanel().showPasswords();
//					} else {
//						userLoginCardsPanel.getUserLoginSelectionPanel().setShowButton();
//						userLoginCardsPanel.getUserLoginSelectionPanel().hidePasswords();
//					}
//					counter++;
//					break;
				}
			}

		private void setMainWindowLabel() {
			if (userLoginModel.correctPassword()) {
				mainWindow.setLabelLoginSuccessful();
			} else {
				mainWindow.setLabelLoginUnsuccessful();
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
//			userLoginCardsPanel.getLoginSelectionPanel().showSelectedImages();
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
//			userLoginCardsPanel.getLoginSelectionPanel().showShowMeButton();
			
		}
	}

}

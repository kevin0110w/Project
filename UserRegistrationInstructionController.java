
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class UserRegistrationInstructionController {
	private MainWindow mainWindow;
	private UserRegistrationInstructionPanel panel;
	private UserRegistrationModel model;

	public UserRegistrationInstructionController(UserRegistrationInstructionPanel panel, MainWindow view,
			UserRegistrationModel model) {
		this.panel = panel;
		this.mainWindow = view;
		this.model = model;
		this.panel.addUserIntructionListener(new UserRegistrationInstructionListener());
	}

	class UserRegistrationInstructionListener implements MouseListener, ActionListener {
		int pics = 0, login = 0, userID = 0; 
		
		public boolean invalidInput(int input) {
			return input == 0;
		}
		
		public void invalidPicSelection(boolean invalid) {
			if (invalid) {
				JOptionPane.showMessageDialog(panel, "Please select a valid picture set", "Invalid picture type selection",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		
//		public void invalidLoginSelection(boolean invalid) {
//			if (invalid) {
//				JOptionPane.showMessageDialog(panel, "Please select a valid registration method", "Invalid registration method selection",
//						JOptionPane.WARNING_MESSAGE);
//			}
//		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
			case "BACK":
				mainWindow.showMainPage();
				break;
			case "NEXT":
					try {
						userID = Integer.parseInt(panel.getUserEntry());
						if (invalidInput(pics)) {
							invalidPicSelection(invalidInput(pics));
							break;
						} 
//						else if (invalidInput(login)) {
//							invalidPicSelection(invalidInput(login));
//							break;	
//						}
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(panel, "Please enter a numeric UserID only", "UserID Input Error",
								JOptionPane.WARNING_MESSAGE);
						break;
					}
				model.setUserID(userID);
//				model.setLoginMethod(login);
				model.setPictureSet(pics);
				model.createRegistrationSet();
				mainWindow.showRegistrationPage();
				model.setInitialTime();
				break;
//			case "SELECTLOGIN":
//				login = panel.getLoginMethod();
//				invalidLoginSelection(invalidInput(login));
//				break;
			case "SELECTPICS":
				pics = panel.getPicsSelection();
				invalidPicSelection(invalidInput(pics));
				break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == panel.getInputArea()) {
				panel.setInputText("");
			}
			
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

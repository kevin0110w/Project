package main;
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
	private UserRegistrationCardsPanel userRegistrationCardsPanel;
//	private UserRegistrationPanel panel;
	private UserRegistrationModel model;

//	public UserRegistrationController(UserRegistrationPanel x, MainWindow view, UserRegistrationModel model) {
		public UserRegistrationController(UserRegistrationCardsPanel x, UserRegistrationModel model) {
		this.userRegistrationCardsPanel = x;
		this.model = model;
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().setModel(this.model); // associate the userregistrationpanel with this model
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetOne(model.getImageFiles().getListOne()); // set the 1st image set in the UserRegistrationPanel class to create a unique panel
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetTwo(model.getImageFiles().getListTwo()); // set the 2nd image set in the UserRegistrationPanel class to create a unique panel
		this.userRegistrationCardsPanel.getUserRegistrationPanel().setImageSetThree(model.getImageFiles().getListThree()); // set the 3rd image set in the UserRegistrationPanel class to create a unique panel
		this.userRegistrationCardsPanel.getUserRegistrationPanel().adduserRegistrationActionListener(new UserRegistrationListener()); // associate each interactive button in the user registration panel with an action listener
//		this.userRegistrationCardsPanel.getUserRegistrationPanel().adduserRegistrationMouseListener(new UserRegistrationListener());
//		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelOne(), model);
//		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelTwo(), model);
//		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(this.userRegistrationCardsPanel.getUserRegistrationPanel().getImagePanelThree(), model);
	}
		
	public void createCompleteController() {
		UserRegistrationCompleteController userRegistrationCompleteController = new UserRegistrationCompleteController(
				this.userRegistrationCardsPanel);
	}

	class UserRegistrationListener implements ActionListener, MouseListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "SCROLLLEFT":
				userRegistrationCardsPanel.getUserRegistrationPanel().showPrevious(); // show earlier image panel
				break;
			case "SCROLLRIGHT":
				userRegistrationCardsPanel.getUserRegistrationPanel().showNext(); // show later image panel
				break;
			case "BACK":
				userRegistrationCardsPanel.getUserRegistrationPanel().clear();
				model.clear(); // clear the data in memory to stop any doubling of data (e.g. button/imagesets) if a user chooses a different picture set
				userRegistrationCardsPanel.showInstructionPanel(); // go back to the instruction panel
				break;
			case "NEXT":
				try {
					model.addUser(); // call the add user in the model class to add a new user to the database
					userRegistrationCardsPanel.showCompletePanel(); // show the complete page.
					createCompleteController();
					model.clear();
					userRegistrationCardsPanel.getUserRegistrationPanel().clear();
					userRegistrationCardsPanel.getMw().setLabelRegistrationComplete();
					userRegistrationCardsPanel.getMw().updateSmallFrameSize();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				break;
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
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == userRegistrationCardsPanel.getUserRegistrationPanel().getPasswordButton()) {
				userRegistrationCardsPanel.getUserRegistrationPanel().showSelectedImages();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			userRegistrationCardsPanel.getUserRegistrationPanel().showShowMeButton();

		}
	}
}

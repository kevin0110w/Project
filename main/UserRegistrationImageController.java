package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This class is responsible for coordinating the clicks on any of the image panels with the model
 */
public class UserRegistrationImageController {
	private UserRegistrationImagePanel panel;
	private UserRegistrationModel model;
	private static int clickCounter;
	
	public UserRegistrationImageController(UserRegistrationImagePanel panel, UserRegistrationModel model) {
		this.panel = panel;
		this.model = model;
		this.panel.addListener(new UserRegistrationImageListener());
		this.clickCounter = 1;
	}
	
	/**
	 * Return the clickCounter which keeps track how many clicks has been registered
	 * @return
	 */
	public int getCounter() {
		return this.clickCounter;
	}
	
	/**
	 * Set the clickCounter
	 * @param count a count of the number of clicks
	 */
	public void setCounter(int count) {
		this.clickCounter = count;
	}
	
	class UserRegistrationImageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			/**
			 * Each image button from the user registration image panel has an associated action command, to determine which one was selected.
			 * Once a button has been clicked, the filePath associated with a button will be registered by the model.
			 * The selected button will be disabled and can't be selected again. The selected image will be displayed in the beneath the image panel 
			 * and the time taken to choose the picture will be recorded. The clickcounter will be incremented.
			 */
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
				Icon icon = ((JButton) e.getSource()).getIcon();
				String filePath = icon.toString();
				if (getCounter() == 1) {
					model.setFirstSelectedImageFilePath(filePath); 
				} else if (getCounter() == 2) {
					model.setSecondSelectedImageFilePath(filePath);
				} else if (getCounter() == 3) {
					model.setThirdSelectedImageFilePath(filePath);
					panel.disableAllImageButtons();
				}
				panel.disableButton(e.getActionCommand()); 
				panel.setImage(icon, getCounter()); 
				model.addTimeTaken();  
				setCounter(getCounter()+1); 
				break;
			}
		}
	}
}
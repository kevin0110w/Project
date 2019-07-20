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
	private static int counter;
	
	public UserRegistrationImageController(UserRegistrationImagePanel panel, UserRegistrationModel model) {
		this.panel = panel;
		this.model = model;
		this.panel.addListener(new UserRegistrationImageListener()); // associate the image buttons in the user registration panel with action listeners
		this.counter = 1;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void setCounter(int count) {
		this.counter = count;
	}
	
	class UserRegistrationImageListener implements ActionListener{
//		int click = 1;
		String filePath;
		Icon icon;
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
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
				icon = ((JButton) e.getSource()).getIcon();
				filePath = icon.toString();
				// set the string of the file path in the model class depending on the click number
				// disable all buttons once three formal clicks are registered. 
				if (getCounter() == 1) {
					model.setFirstSelectedImageFilePath(filePath); 
				} else if (getCounter() == 2) {
					model.setSecondSelectedImageFilePath(filePath);
				} else if (getCounter() == 3) {
					model.setThirdSelectedImageFilePath(filePath);
					panel.disableAllImageButtons();
				}
				panel.disableButton(e.getActionCommand()); // disable the button that was clicked in the image panel
				panel.setImage(icon, getCounter()); // set the image in the selection panel at the bottom
				model.addTimeTaken(); // record the time taken to make a selection 
				setCounter(getCounter()+1); // increment the click counter
				break;
			}
		}
	}
}
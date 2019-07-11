import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserRegistrationImagePanel extends JPanel {
	private JPanel imagePanel;
	private List<JButton> buttons;
	private UserRegistrationPanel urp;
	private List<String> imageSet;
	
	/**
	 * Each UserRegistrationImagePanel will display a different imageSet containing the 20 filepath strings of the images
	 * A reference of the userregistrationpanel object is passed as a parameter so that it can set the images in the selection panel
	 * @param urp - the panel that is holding all three separate image panels.
	 * @param imageSet - the 20 strings of the images to be displayed on a panel.
	 */
	public UserRegistrationImagePanel(UserRegistrationPanel urp, List<String> imageSet) {
		this.imageSet = new ArrayList<String>();
		this.imageSet = imageSet;
		this.urp = urp;
		setUp();
	}
	
	/***
	 * Set up the buttons on the panel, create a new button, set an icon for it in an enabled & disabled state, set the margins to make it bigger and associate
	 * each button with an action command for easy linking with the controller class
	 */
	private void setUp() {
		this.imagePanel = new JPanel();
		this.imagePanel.setLayout(new GridLayout(4, 5));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < this.imageSet.size(); i++) {
			buttons.add(new JButton());
			buttons.get(i).setIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setDisabledIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttons.get(i).setActionCommand("" + i);
			this.imagePanel.add(buttons.get(i));
		}
		this.add(imagePanel);
	}
	
	public void addListener(ActionListener alistener) {
		for (int i = 0; i < this.buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
	}
	
	/**
	 * Call the set image method in the UserRegistrationPanel class, passing to it the filepath of the clicked icon and the number whether it was
	 * first, second or third click
	 * @param filePath - imageicon on a button
	 * @param n - the 1st, 2nd or 3rd click
	 */
	public void setImage(Icon filePath, int n) {
		this.urp.setImage(filePath, n);
	}
	
	/*
	 * Disable a button if it's been selected before.
	 * @Param - the action command associated with a clicked button
	 */
	public void disableButton(String actionCommand) {
		for (JButton button : this.buttons) {
			if (button.getActionCommand().equals(actionCommand)) {
				button.setEnabled(false);
			}
		}
	}

	/*
	 * Disable all buttons once 3 clicks are registered.
	 */
	public void disableAllImageButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
		}
	}
}


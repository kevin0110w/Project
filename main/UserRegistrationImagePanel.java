package main;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is responsible for setting up the panel that'll contain the 20 images that can be selected by the user
 * @author woohoo
 *
 */
public class UserRegistrationImagePanel extends JPanel {
	private JPanel imagePanel;
	private List<JButton> buttons;
	private UserRegistrationPanel urp;
	private List<String> imageSet;
	
	/**
	 * Each UserRegistrationImagePanel will display a different imageSet containing the 20 filepath strings of the images
	 * A reference of the userregistrationpanel object is passed as a parameter so that it can set the images in the registration panel
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
	 * Set up the clickable images on the panel, create a new button, set an icon for both it's enabled & disabled state, add minor cosmetic detail, set the margins to make it bigger and associate
	 * each button with an action command for easy linking with the controller class
	 */
	private void setUp() {
		this.imagePanel = new JPanel(new GridLayout(4,5));
		this.buttons = new ArrayList<JButton>();
		int counter = 0;
		Iterator<String> it = this.imageSet.iterator();
		while (it.hasNext()) {
			String imageFilePath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(counter).setSize(new Dimension(100, 100));
			this.buttons.get(counter).setIcon(new ImageIcon(imageFilePath));
			this.buttons.get(counter).setDisabledIcon(new ImageIcon(imageFilePath));
			this.buttons.get(counter).setOpaque(false);
			this.buttons.get(counter).setBorderPainted(true);
			this.buttons.get(counter).setContentAreaFilled(false);
			this.buttons.get(counter).setMargin(new Insets(0, 0, 0, 0));
			this.buttons.get(counter).setActionCommand("" + counter);
			this.imagePanel.add(this.buttons.get(counter));
			counter++;
		}
		this.add(imagePanel);
	}
	
	/**
	 * Add action listeners to each of the clickable images
	 * @param alistener - an instance of the user registration image controller class
	 */
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
	
	/**
	 * Disable an image if it's been selected before.
	 * @Param - the action command associated with a clicked button
	 */
	public void disableButton(String actionCommand) {
		for (JButton button : this.buttons) {
			if (button.getActionCommand().equals(actionCommand)) {
				button.setEnabled(false);
			}
		}
	}

	/**
	 * Disable all buttons once 3 clicks are registered.
	 */
	public void disableAllImageButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
		}
	}
}


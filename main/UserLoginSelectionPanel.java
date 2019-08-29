package main;
import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is the view, responsible for displaying the 20 images (17 decoys and 3 passwords) as part of the login process
 *
 */
public class UserLoginSelectionPanel extends JPanel {
	private List<JButton> buttons;
	private List<String> filePaths;
	private JPanel buttonPanel, bottomPanel, selectedImagesPanel ;
	private JButton back, login;
	private JLabel selectionOne, selectionTwo, selectionThree;
	private int clickCounter;
	
	/**
	 * This constructor will set the panel
	 */
	public UserLoginSelectionPanel() {
		this.setLayout(new BorderLayout()); // set this to border layout
		setTopPanel();
		this.filePaths = new ArrayList<String>(); // create a new arraylist object
		setBottomPanel();
		setButtonPanel(); // set the image buttons
		setSelectionPanel(); // set the selection panel along with interactive buttons
		setBackLoginButtons();
	}
	
	/**
	 * Set a top panel that'll house log in instructions
	 */
	private void setTopPanel() {
		JPanel topPanel = new JPanel(new GridLayout(0,3));
		topPanel.add(new JLabel());
		JPanel instructionPanel = new JPanel();
		JLabel t = new JLabel();
		t.setText("Please select the 3 images that make up your password, one by one and in order.");
		instructionPanel.add(t);
		this.add(instructionPanel, BorderLayout.NORTH);
	}

	/**
	 * This method will create a  panel that will house various interactive buttons and the icons of the selected images
	 */
	private void setBottomPanel() {
		this.bottomPanel = new JPanel(new BorderLayout());
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * This method will create the panel that will contain the selected images
	 */
	private void setSelectionPanel() {
		this.selectedImagesPanel = new JPanel();
		this.selectionOne = new JLabel();
		this.selectionTwo = new JLabel();
		this.selectionThree = new JLabel();
		this.selectedImagesPanel.add(selectionOne);
		this.selectedImagesPanel.add(selectionTwo);
		this.selectedImagesPanel.add(selectionThree);		
		this.bottomPanel.add(selectedImagesPanel, BorderLayout.CENTER);
	}
	
	/**
	 * This method will create the back and login buttons and fix these on the panel
	 */
	private void setBackLoginButtons() {
		JPanel backloginPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.login = new JButton("Next");
		this.login.setActionCommand("NEXT");
		backloginPanel.add(this.back);
		backloginPanel.add(this.login);
		this.bottomPanel.add(backloginPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * This method sets the layout of the image buttons
	 */
	private void setButtonPanel() {
		this.buttonPanel = new JPanel();
		this.add(this.buttonPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set a user's challenge set from the model and use these to set the image buttons
	 * @Param list - images from the model 
	 */
	public void getFilePaths(List<String> list) {
		this.filePaths.addAll(list);
		this.setButtons();
	}
	
	/**
	 * Set the image button by setting the icon, associating the button with an action command and finally attach it to the button JPanel
	 */
	private void setButtons() {
		int counter = 0;
		this.buttons = new ArrayList<JButton>();
		Iterator<String> it = this.filePaths.iterator();
		JPanel panel = new JPanel(new GridLayout(4,5));
		while (it.hasNext()) {
			String aPath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(counter).setSize(new Dimension(100,100));
			this.buttons.get(counter).setIcon(new ImageIcon(aPath));
			this.buttons.get(counter).setDisabledIcon(new ImageIcon(aPath));
			this.buttons.get(counter).setOpaque(false);
			this.buttons.get(counter).setBorderPainted(true);
			this.buttons.get(counter).setContentAreaFilled(false);
			this.buttons.get(counter).setActionCommand("" + counter);
			this.buttons.get(counter).setMargin(new Insets(0, 0, 0, 0));
			panel.add(this.buttons.get(counter));
			counter++;
		}
		this.buttonPanel.add(panel);
	}

	/**
	 * Set the selected image underneath the image buttons to let the user know which ones have been selected so far
	 * @Param source is the returned object from the action performed method
	 */
	public void setImage(String filePath) {
		Icon iconImage = new ImageIcon(filePath);
		if (clickCounter == 0) {
			this.selectionOne.setIcon(iconImage);
		} else if (clickCounter == 1) {
			this.selectionTwo.setIcon(iconImage);
		} else if (clickCounter == 2) {
			this.selectionThree.setIcon(iconImage);
		}
		clickCounter++;
	}
	
	/**
	 * Disable an image button if it's been selected before.
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
	 * Disable all buttons once 3 images have been selected
	 */
	public void disableButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
		}
		
	}
	
	/**
	 * Add listeners to each interactive JComponent that is on the JFrame for the user to click on (e.g. each image button, back and login buttons)
	 * @param alistener
	 */
	public void addUserSelectionListener(ActionListener userSelectionListener) {
		for (JButton button : this.buttons) {
			button.addActionListener(userSelectionListener);
		}
		this.back.addActionListener(userSelectionListener);
		this.login.addActionListener(userSelectionListener);
	}
	
	/**
	 * A method to reset and erase all locally stored data in the view, so that no historic data is shown for future logins.
	 */
	public void clear() {
		this.filePaths.clear(); // clear file paths from the list
		this.buttons.clear(); // clear buttons form the list
		this.remove(this.buttonPanel); // to be recreated on a future login
		this.remove(this.bottomPanel); // to be recreated on a future login
		this.clickCounter = 0; // reset the click counter
		setBottomPanel();
		setButtonPanel(); // set up button panel again 
		setSelectionPanel(); // set selection panel again
		setBackLoginButtons();
		revalidate();
		repaint();
	}
}

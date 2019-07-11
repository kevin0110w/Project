import java.awt.BorderLayout;
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
	private JPanel buttonPanel, selectionPanel;
	private JButton back, login;
	private JLabel selectionOne, selectionTwo, selectionThree;
	private int clickCounter;
	
	
	public UserLoginSelectionPanel() {
		this.setLayout(new BorderLayout()); // set this to border layout
		this.filePaths = new ArrayList<String>(); // create a new arraylist object
		setButtonPanel(); // set the image buttons
		setSelectionPanel(); // set the selection panel along with interactive buttons
	}

	/**
	 * This method will create the panel that will house various interactive buttons and the icons of the selected images
	 */
	private void setSelectionPanel() {
		this.selectionPanel = new JPanel();
		
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.selectionPanel.add(back);
		
		this.selectionOne = new JLabel();
		this.selectionTwo = new JLabel();
		this.selectionThree = new JLabel();
		this.selectionPanel.add(selectionOne);
		this.selectionPanel.add(selectionTwo);
		this.selectionPanel.add(selectionThree);
		
		this.login = new JButton("Log In");
		this.login.setActionCommand("LOGIN");
		this.selectionPanel.add(login);
		
		this.add(this.selectionPanel, BorderLayout.SOUTH);
	}

	/**
	 * This method sets the layout of the image buttons
	 */
	private void setButtonPanel() {
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(4,5));
		this.add(this.buttonPanel, BorderLayout.CENTER);
	}
	
	/*
	 * Getting a registered users 20 picture file paths, these will be shuffled only if the user most recently successfully logged in
	 */
	public void getFilePaths(List<String> list) {
		this.filePaths.addAll(list);
		this.setButtons();
	}
	
	/**
	 * Set the buttons - i.e. set the icon, action command and finally attach it to the button JPanel
	 */
	private void setButtons() {
		int counter = 0;
		this.buttons = new ArrayList<JButton>();
		Iterator<String> it = this.filePaths.iterator();
		while (it.hasNext()) {
			String aPath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(counter).setIcon(new ImageIcon(aPath));
			this.buttons.get(counter).setDisabledIcon(new ImageIcon(aPath));
			this.buttons.get(counter).setActionCommand("" + counter);
			this.buttons.get(counter).setMargin(new Insets(0, 0, 0, 0));
			this.buttonPanel.add(this.buttons.get(counter));
			counter++;
		}
	}
	
	
	/**
	 * Add listeners to each interactive JComponent that is on the JFrame for the user to click on (e.g. each image button, back and login buttons)
	 * @param alistener
	 */
	public void addListener(ActionListener alistener) {
		for (JButton button : this.buttons) {
			button.addActionListener(alistener);
		}
		this.back.addActionListener(alistener);
		this.login.addActionListener(alistener);
	}

	/*
	 * Set the image underneath the image buttons to let the user know which ones have been selected so far
	 * @Param source is the returned object from the action listener
	 */
	public void setImage(Object source) {
		Icon iconImage = ((JButton) source).getIcon();
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
	 * Disable buttons once 3 images have been clicked
	 */
	public void disableButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
		}
		
	}

	/**
	 * A method to reset and erase all locally stored data in the view, so that no historic data is shown for future logins.
	 */
	public void clear() {
		this.filePaths.clear(); // clear file paths from the list
		this.buttons.clear(); // clear buttons form the list
		this.remove(this.buttonPanel); // to be recreated on a future login
		this.remove(this.selectionPanel); // to be recreated on a future login
		this.clickCounter = 0; // reset the click counter
		setButtonPanel(); // set up button panel again 
		setSelectionPanel(); // set selection panel again
		revalidate();
		repaint();
	}
}

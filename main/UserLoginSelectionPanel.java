package main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
//	private JButton back, login, showPassword, showButton;
	private JButton back, login;
	private JLabel selectionOne, selectionTwo, selectionThree;
	private int clickCounter;
	
	public UserLoginSelectionPanel() {
		JPanel topPanel = new JPanel(new GridLayout(0,3));
		topPanel.add(new JLabel());
		JPanel instruction = new JPanel();
		JLabel t = new JLabel();
		t.setText("Please select the 3 images that make up your password, one by one and in order.");
		instruction.add(t);
		this.setLayout(new BorderLayout()); // set this to border layout
		this.add(instruction, BorderLayout.NORTH);
		this.filePaths = new ArrayList<String>(); // create a new arraylist object
		setBottomPanel();
		setButtonPanel(); // set the image buttons
		setSelectionPanel(); // set the selection panel along with interactive buttons
		setBackLoginButtons();
	}
	
	public void setBottomPanel() {
		this.bottomPanel = new JPanel(new BorderLayout());
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * This method will create the panel that will house various interactive buttons and the icons of the selected images
	 */
	private void setSelectionPanel() {
		this.selectedImagesPanel = new JPanel();
		this.selectionOne = new JLabel();
		this.selectionTwo = new JLabel();
		this.selectionThree = new JLabel();
//		this.selectionOne = new JButton();
//		this.selectionTwo = new JButton();
//		this.selectionThree = new JButton();
//		this.selectionOne.setActionCommand("ONE");
//		this.selectionTwo.setActionCommand("TWO");
//		this.selectionThree.setActionCommand("THREE");
		this.selectedImagesPanel.add(selectionOne);
		this.selectedImagesPanel.add(selectionTwo);
		this.selectedImagesPanel.add(selectionThree);
		showPasswords();
		this.bottomPanel.add(selectedImagesPanel, BorderLayout.CENTER);
	}
	
	private void setBackLoginButtons() {
		JPanel backloginPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
//		this.showButton = new JButton("Show Password");
//		this.showButton.setActionCommand("SHOW");
		this.login = new JButton("Next");
		this.login.setActionCommand("NEXT");
		backloginPanel.add(this.back);
//		backloginPanel.add(this.showButton);
		backloginPanel.add(this.login);
		this.bottomPanel.add(backloginPanel, BorderLayout.SOUTH);
	}
	
	
	public void showPasswords() {
		this.selectedImagesPanel.setVisible(true);
	}
	
//	public void hidePasswords() {
//		this.selectedImagesPanel.setVisible(false);
//	}
	
//	public void setShowButton() {
//		this.showButton.setText("Show Password");
//	}
//	
//	public void setHideButton() {
//		this.showButton.setText("Hide Password");
//	}

	/**
	 * This method sets the layout of the image buttons
	 */
	private void setButtonPanel() {
		this.buttonPanel = new JPanel();
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
		JPanel panel = new JPanel(new GridLayout(4,5));
		while (it.hasNext()) {
			String aPath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(counter).setSize(new Dimension(100,100));
			this.buttons.get(counter).setIcon(new ImageIcon(aPath));
			this.buttons.get(counter).setDisabledIcon(new ImageIcon(aPath));
//			this.buttons.get(counter).setOpaque(true);
			this.buttons.get(counter).setOpaque(false);
			this.buttons.get(counter).setContentAreaFilled(false);
//			this.buttons.get(counter).setBorderPainted(false);
			this.buttons.get(counter).setActionCommand("" + counter);
			this.buttons.get(counter).setMargin(new Insets(0, 0, 0, 0));
			panel.add(this.buttons.get(counter));
			counter++;
			/* 5 images each row */
//			if (counter % 5 == 0) {
//				this.buttonPanel.add(panel);
//				panel = new JPanel();
//			}
			
		}
		this.buttonPanel.add(panel);
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
		this.remove(this.bottomPanel); // to be recreated on a future login
		this.clickCounter = 0; // reset the click counter
		setBottomPanel();
		setButtonPanel(); // set up button panel again 
		setSelectionPanel(); // set selection panel again
		setBackLoginButtons();
		revalidate();
		repaint();
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
//		this.showButton.addActionListener(userSelectionListener);
	}
	
	public void addMouseListeners(MouseListener userSelectionListener) {
	}
}

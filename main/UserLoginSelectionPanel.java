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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is the view, responsible for displaying the 20 images (17 decoys and 3 passwords) as part of the login process
 *
 */
public class UserLoginSelectionPanel extends JPanel {
	private List<JButton> buttons;
	private List<String> filePaths;
	private JPanel buttonPanel, bottomPanel, bottomPanel2;
	private JButton back, login;
	private JLabel selectionOne, selectionTwo, selectionThree;
	private int clickCounter;
	private CardLayout cl;
	private JPanel showPanel;
	private JButton showButton;
	
	
	public UserLoginSelectionPanel() {
		this.setLayout(new BorderLayout()); // set this to border layout
		this.filePaths = new ArrayList<String>(); // create a new arraylist object
		setBottomPanel();
		setCardPanel();
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
		JPanel selectedImagesPanel = new JPanel();
		this.selectionOne = new JLabel();
		this.selectionTwo = new JLabel();
		this.selectionThree = new JLabel();
		selectedImagesPanel.add(selectionOne);
		selectedImagesPanel.add(selectionTwo);
		selectedImagesPanel.add(selectionThree);	
		this.bottomPanel2.add(selectedImagesPanel, "SELECTEDIMAGES");
//		this.bottomPanel.add(selectedImagesPanel, BorderLayout.CENTER);
	}
	
	public void setCardPanel() {
		this.bottomPanel2 = new JPanel(new CardLayout());
		this.showPanel = new JPanel();
		this.showButton = new JButton("Show Password");
		this.showButton.setActionCommand("SHOW");
		this.showPanel.add(this.showButton);
		this.bottomPanel2.add(this.showPanel, "SHOWPANEL");
		this.bottomPanel.add(this.bottomPanel2, BorderLayout.CENTER);
		this.bottomPanel2.setVisible(true);
		this.cl = (CardLayout) (this.bottomPanel2.getLayout());
		showShowMeButton();
	}

	private void setBackLoginButtons() {
		JPanel backloginPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.login = new JButton("Log In");
		this.login.setActionCommand("LOGIN");
		backloginPanel.add(this.back);
		backloginPanel.add(this.login);
		this.bottomPanel.add(backloginPanel, BorderLayout.SOUTH);
		
	}
	
	public void showShowMeButton() {
		this.cl.show(this.bottomPanel2, "SHOWPANEL");
	}
	
	public void showSelectedImages() {
		this.cl.show(this.bottomPanel2, "SELECTEDIMAGES");
	}
	
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
		JPanel panel = new JPanel();
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
			if (counter % 5 == 0) {
				this.buttonPanel.add(panel);
				panel = new JPanel();
			}
			
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
		setCardPanel();
		setButtonPanel(); // set up button panel again 
		setSelectionPanel(); // set selection panel again
		setBackLoginButtons();
		revalidate();
		repaint();
	}

	public void addMouseListeners(MouseListener userSelectionListener) {
		this.showButton.addMouseListener(userSelectionListener);
		
	}
}

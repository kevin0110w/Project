package main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.UserRegistrationController.UserRegistrationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for displaying the three separate image panels in a card layout, along with various interactive buttons during the registration
 * phase
 */
public class UserRegistrationPanel extends JPanel {
	private JButton back, next, scrollLeft, scrollRight;
	private JPanel imagesCardPanel, bottomPanel;
	private CardLayout imagePanelCL;
	private final static String ONE = "one";
	private final static String TWO = "two";
	private final static String THREE = "three";
	private JLabel selectedImageOne, selectedImageTwo, selectedImageThree;
	private List<String> imageListOne, imageListTwo, imageListThree;
	private UserRegistrationImagePanel imagePanelOne, imagePanelTwo, imagePanelThree;

	public UserRegistrationPanel() {
		this.imageListOne = new ArrayList<String>(); // to hold first set of 20 images
		this.imageListTwo = new ArrayList<String>(); // to hold second set of 20 images
		this.imageListThree = new ArrayList<String>(); // to hold third set of 20 images
		this.setLayout(new BorderLayout()); // set display layout
		this.setup();
	}

	/**
	 * Sets up the two panels for images and buttons. This is called to refresh the screen whenever a new display is to be shown
	 */
	public void setup() {
		this.setTopLabel();
		this.setImagesCardPanel();
		this.bottomPanel = new JPanel(new BorderLayout()); // a panel to hold the buttons and selected images
		this.setupButtons(); // set up various interactive buttons
		this.setupSelectedImagePanel(); // set up the selected images panel
	}
	
	/**
	 * Instructions pinned to the top of the screen as a JLabel
	 */
	private void setTopLabel() {
		JPanel panel = new JPanel();
		JPanel topPanel = new JPanel(new GridLayout(2,0));
		JLabel instructionsOne = new JLabel();
		JLabel instructionsTwo = new JLabel();
		String instructionsTextOne = "Create a password by selecting 3 images, one by one and in a memorable order.";
		String instructionsTextTwo = "Use the scroll left and right buttons to view all images and click on next when done.";
		instructionsOne.setText(instructionsTextOne);
		instructionsTwo.setText(instructionsTextTwo);
		topPanel.add(instructionsOne);
		topPanel.add(instructionsTwo);
		panel.add(topPanel);
		this.add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * Create a panel in the centre of the JPanel that will hold three different image panels
	 */
	private void setImagesCardPanel() {
		this.imagesCardPanel = new JPanel(new CardLayout());
		this.add(imagesCardPanel, BorderLayout.CENTER);  
		this.imagePanelCL = (CardLayout) (this.imagesCardPanel.getLayout());
	}
	
	/**
	 * Set up interactive buttons in this JPanel for scrolling through the image panels and going back and next
	 */
	private void setupButtons() {
		JPanel buttonPanel = new JPanel(); // create a new panel to house these buttons
		this.back = new JButton("Back"); // creating a back button
		this.back.setActionCommand("BACK"); // set action command to ensure correct button fired
		this.next = new JButton("Next"); // create a new next button
		this.next.setEnabled(false); // initially disable this and reenable once three images chosen
		this.next.setActionCommand("NEXT"); // set action command to  ensure correct button fired
		buttonPanel.add(this.back); // add the buttons to the button panel
		buttonPanel.add(this.next);
		this.bottomPanel.add(buttonPanel, BorderLayout.SOUTH); // add the button panel to this object
		setUpScrollButtons();
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Set up scroll left and scroll right buttons for a user to scroll through the different image panels
	 */
	private void setUpScrollButtons() {
		JPanel scrollPanelLeft = new JPanel(new BorderLayout());
		JPanel scrollPanelRight = new JPanel(new BorderLayout());
		this.scrollLeft = new JButton("Scroll Left"); 
		this.scrollLeft.setActionCommand("SCROLLLEFT"); 
		this.scrollRight = new JButton("Scroll Right"); 
		this.scrollRight.setActionCommand("SCROLLRIGHT");
		scrollPanelLeft.add(scrollLeft, BorderLayout.SOUTH);
		scrollPanelRight.add(scrollRight, BorderLayout.SOUTH);
		this.bottomPanel.add(scrollPanelLeft, BorderLayout.WEST);
		this.bottomPanel.add(scrollPanelRight, BorderLayout.EAST);
		
	}

	/**
	 * Selected images will be displayed as icons for a Jlabel on a panel underneath the image buttons
	 */
	private void setupSelectedImagePanel() {
		JPanel imagePanel = new JPanel(); 
		this.selectedImageOne = new JLabel(); 
		this.selectedImageTwo = new JLabel();
		this.selectedImageThree = new JLabel();
		imagePanel.add(selectedImageOne); 
		imagePanel.add(selectedImageTwo); 
		imagePanel.add(selectedImageThree);
		this.bottomPanel.add(imagePanel, BorderLayout.CENTER); // add the selected image panel to this object
 	}
	
	/**
	 * Adding action listeners to each interactive button
	 * @param aListener - an action listener from the user registration image controller class
	 */
	public void adduserRegistrationActionListener(ActionListener aListener) {
		this.scrollLeft.addActionListener(aListener);
		this.scrollRight.addActionListener(aListener);
		this.next.addActionListener(aListener);
		this.back.addActionListener(aListener);
	}
	
	/**
	 * Set the image to a particular JLabel depending on the click number
	 * @param filePath - reference of the icon object
	 * @param counter
	 */
	public void setImage(Icon filePath, int counter) {
		switch (counter) {
		case 1:
			selectedImageOne.setIcon(filePath);
			break;
		case 2:
			selectedImageTwo.setIcon(filePath);
			break;
		case 3:
			selectedImageThree.setIcon(filePath);
			next.setEnabled(true); // enable the next button once 3 images chosen
			break;
		}
	}

	/**
	 * Show the previous image panel
	 */
	public void showPrevious() {
		this.imagePanelCL.previous(this.imagesCardPanel);
	}

	/**
	 * Show the next image panel
	 */
	public void showNext() {
		this.imagePanelCL.next(this.imagesCardPanel);
	}

	/**
	 * Set the list variable responsible for holding the 20 images that'll be displayed in the first image panel
	 * @param listOne - the 20 images that were created in the model
	 */
	public void setImageSetOne(List<String> listOne) {
		this.imageListOne = listOne;
	}

	/**
	 * Set the field variable responsible for holding the 20 images that'll be displayed in the second image panel
	 * @Param listTwo - the 20 images that were created in the model
	 */
	public void setImageSetTwo(List<String> listTwo) {
		this.imageListTwo = listTwo;
	}

	/**
	 * Set the field variable responsible for holding the 20 images that'll be displayed in the first image panel
	 * @param listThree - the 20 images that were created in the model
	 */
	public void setImageSetThree(List<String> listThree) {
		this.imageListThree = listThree;
	}

	/**
	 * Set up the three image panels along with their controllers
	 * Add the image panels to this card layout panel which can be scrolled using scroll left and scroll right buttons
	 */
	public void setUpImagePanels() {
		this.imagePanelOne = new UserRegistrationImagePanel(this, this.imageListOne);
		this.imagePanelTwo = new UserRegistrationImagePanel(this, this.imageListTwo);
		this.imagePanelThree = new UserRegistrationImagePanel(this, this.imageListThree);
		this.imagesCardPanel.add(this.imagePanelOne, ONE);
		this.imagesCardPanel.add(this.imagePanelTwo, TWO);
		this.imagesCardPanel.add(this.imagePanelThree, THREE);
		this.imagesCardPanel.setVisible(true);
	}
	
	/**
	 * A method to @return the first image panel
	 */
	public UserRegistrationImagePanel getImagePanelOne() {
		return imagePanelOne;
	}

	/**
	 * Set an @param image panel to imagePanelOne
	 */
	public void setImagePanelOne(UserRegistrationImagePanel imagePanelOne) {
		this.imagePanelOne = imagePanelOne;
	}

	/**
	 * A method to @return the second image panel
	 */
	public UserRegistrationImagePanel getImagePanelTwo() {
		return imagePanelTwo;
	}

	/**
	 * Set an @param image panel to imagePanelTwo
	 */
	public void setImagePanelTwo(UserRegistrationImagePanel imagePanelTwo) {
		this.imagePanelTwo = imagePanelTwo;
	}

	/**
	 * A method to @return the third image panel
	 */
	public UserRegistrationImagePanel getImagePanelThree() {
		return imagePanelThree;
	}

	/**
	 * Set an @param image panel to imagePanelThree
	 */
	public void setImagePanelThree(UserRegistrationImagePanel imagePanelThree) {
		this.imagePanelThree = imagePanelThree;
	}
	
	/**
	 * Reset the view by clearing the variables set to data associated with a particular user
	 */
	public void clear() {
		this.imageListOne.clear(); 
		this.imageListTwo.clear();
		this.imageListThree.clear();
		this.imagePanelOne = null;
		this.imagePanelTwo = null;
		this.imagePanelThree = null;
		this.imagesCardPanel.removeAll();
		this.remove(this.imagesCardPanel);
		this.remove(this.bottomPanel);
		this.setup();
		revalidate();
		repaint();
	}
}

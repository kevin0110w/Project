package main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for displaying the three separate image panels in a card layout, along with various interactive buttons during the registration
 * phase
 *
 */
public class UserRegistrationPanel extends JPanel {
	private JButton back, next, scrollL, scrollR;
	private JPanel imagesCardPanel, bottomPanel;
	private CardLayout cl;
	private final static String ONE = "one";
	private final static String TWO = "two";
	private final static String THREE = "three";
	private JLabel selectedImageOne, selectedImageTwo, selectedImageThree;
//	private Printer p;
	private List<String> imageListOne, imageListTwo, imageListThree;
	private UserRegistrationImagePanel imagePanelOne, imagePanelTwo, imagePanelThree;

	public UserRegistrationPanel() {
		this.imageListOne = new ArrayList<String>(); // to hold first set of 20 images
		this.imageListTwo = new ArrayList<String>(); // to hold second set of 20 images
		this.imageListThree = new ArrayList<String>(); // to hold third set of 20 images
		this.setLayout(new BorderLayout()); // set display layout
		this.setup();
		this.cl = (CardLayout) (this.imagesCardPanel.getLayout());
	}

	/**
	 * Sets up the two panels for images and buttons. This is called to refresh the screen whenever a new display is to be shown
	 */
	public void setup() {
		this.setImagesCardPanel();
		this.bottomPanel = new JPanel(new BorderLayout()); // a panel to hold the buttons and selected images
		this.setupSelectedImagePanel(); // set up the selected images panel
		this.setupButtons(); // set up various interactive buttons
	}
	
	private void setImagesCardPanel() {
		this.imagesCardPanel = new JPanel(new CardLayout()); // create a new JPanel of cardlayout that'll hold the three separate image panels
		this.add(imagesCardPanel, BorderLayout.CENTER);  
		this.cl = (CardLayout) (this.imagesCardPanel.getLayout());
	}
	/**
	 * Set up the interactive buttons in this JPanel
	 */
	private void setupButtons() {
		JPanel buttonPanel = new JPanel(); // create a new panel to house these buttons
		this.back = new JButton("BACK"); // creating a back button
		this.back.setActionCommand("BACK"); // set action command to ensure correct button fired
		this.scrollL = new JButton("Scroll Left"); // create a scroll left button
		this.scrollL.setActionCommand("SCROLLLEFT"); // set action command to ensure correct button fired
		this.scrollR = new JButton("Scroll Right"); // create a scroll right button
		this.scrollR.setActionCommand("SCROLLRIGHT"); // set action command to ensu re correct button fired
		this.next = new JButton("Next"); // create a new next button
		this.next.setEnabled(false); // initially disable this and reenable once three images chosen
		this.next.setActionCommand("NEXT"); // set action command to  ensure correct button fired
		buttonPanel.add(this.back); // add the buttons to the button panel
		buttonPanel.add(this.scrollL);
		buttonPanel.add(this.scrollR);
		buttonPanel.add(this.next);
		this.bottomPanel.add(buttonPanel, BorderLayout.SOUTH); // add the button panel to this object
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * The selected Images will be house in a separate panel
	 */
	private void setupSelectedImagePanel() {
		JPanel imagePanel = new JPanel(); // create the images panel
		
		this.selectedImageOne = new JLabel(); // create new jlabels that'll display the new icons
		this.selectedImageOne.setMaximumSize(new Dimension(100, 100));// set sizes	
		this.selectedImageTwo = new JLabel();// create new jlabels that'll display the new icons
		this.selectedImageTwo.setMaximumSize(new Dimension(100, 100));// set sizes
		this.selectedImageThree = new JLabel();// create new jlabels that'll display the new icons
		this.selectedImageThree.setMaximumSize(new Dimension(100, 100));// set sizes
		
		imagePanel.add(selectedImageOne); // add the jlabels to this image panel
		imagePanel.add(selectedImageTwo); 
		imagePanel.add(selectedImageThree);
 
		this.bottomPanel.add(imagePanel, BorderLayout.CENTER); // add the selected image panel to this object
 	}
	
	/**
	 * Adding action listeners to each interactive button
	 * @param aListener - an action listener from the userregistrationimagecontroller class
	 */
	public void adduserRegistrationButtonListener(ActionListener aListener) {
		this.scrollL.addActionListener(aListener);
		this.scrollR.addActionListener(aListener);
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
	 * Show a former image panel
	 */
	public void showPrevious() {
		this.cl.previous(this.imagesCardPanel);
	}

	/**
	 * Show a latter image panel
	 */
	public void showNext() {
		this.cl.next(this.imagesCardPanel);
	}

	/**
	 * Set the field variable responsible for holding the 20 images that'll be displayed in the first image panel
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
	 * Add the image panels to this card layout panel which can be scrollable
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
	 * @return the imagePanelOne
	 */
	public UserRegistrationImagePanel getImagePanelOne() {
		return imagePanelOne;
	}

	/**
	 * @param imagePanelOne the imagePanelOne to set
	 */
	public void setImagePanelOne(UserRegistrationImagePanel imagePanelOne) {
		this.imagePanelOne = imagePanelOne;
	}

	/**
	 * @return the imagePanelTwo
	 */
	public UserRegistrationImagePanel getImagePanelTwo() {
		return imagePanelTwo;
	}

	/**
	 * @param imagePanelTwo the imagePanelTwo to set
	 */
	public void setImagePanelTwo(UserRegistrationImagePanel imagePanelTwo) {
		this.imagePanelTwo = imagePanelTwo;
	}

	/**
	 * @return the imagePanelThree
	 */
	public UserRegistrationImagePanel getImagePanelThree() {
		return imagePanelThree;
	}

	/**
	 * @param imagePanelThree the imagePanelThree to set
	 */
	public void setImagePanelThree(UserRegistrationImagePanel imagePanelThree) {
		this.imagePanelThree = imagePanelThree;
	}

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

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
	private UserRegistrationModel model;

	public UserRegistrationPanel() {
		this.imageListOne = new ArrayList<String>(); // to hold first set of 20 images
		this.imageListTwo = new ArrayList<String>(); // to hold second set of 20 images
		this.imageListThree = new ArrayList<String>(); // to hold third set of 20 images
		this.setLayout(new BorderLayout()); // set display layout
		this.imagesCardPanel = new JPanel(new CardLayout()); // create a new JPanel of cardlayout that'll hold the three separate image panels

		this.add(imagesCardPanel, BorderLayout.CENTER);  
		
		this.bottomPanel = new JPanel(new BorderLayout()); // a panel to hold the buttons and selected images
		
		this.setupSelectedImagePanel(); // set up the selected images panel
		this.setupButtons(); // set up various interactive buttons
		this.add(bottomPanel, BorderLayout.SOUTH);
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
	}

	/**
	 * The selected Images will be house in a separate panel
	 */
	private void setupSelectedImagePanel() {
		JPanel imagePanel = new JPanel(); // create the images panel
		imagePanel.setMaximumSize(new Dimension(100, 100));
		this.selectedImageOne = new JLabel(); // create new jlabels that'll display the new icons
//		this.selectedImageOne.setMinimumSize(new Dimension(100, 100)); // set sizes
//		this.selectedImageOne.setPreferredSize(new Dimension(100, 100));// set sizes
		this.selectedImageOne.setMaximumSize(new Dimension(100, 100));// set sizes
//		
		this.selectedImageTwo = new JLabel();// create new jlabels that'll display the new icons
//		this.selectedImageTwo.setMinimumSize(new Dimension(100, 100));// set sizes
//		this.selectedImageTwo.setPreferredSize(new Dimension(100, 100));// set sizes
		this.selectedImageTwo.setMaximumSize(new Dimension(100, 100));// set sizes
//		
		this.selectedImageThree = new JLabel();// create new jlabels that'll display the new icons
//		this.selectedImageThree.setMinimumSize(new Dimension(100, 100));// set sizes
//		this.selectedImageThree.setPreferredSize(new Dimension(100, 100));// set sizes
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
		if (counter == 1) {
			selectedImageOne.setIcon(filePath);
		} else if (counter == 2) {
			selectedImageTwo.setIcon(filePath);
		} else if (counter == 3) {
			selectedImageThree.setIcon(filePath);
			next.setEnabled(true); // enable the next button once 3 images chosen
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
	 * Get a reference of the model, so that it can be associated with the image controllers
	 * @param model
	 */
	public void setModel(UserRegistrationModel model) {
		this.model = model;
	}

	/**
	 * Set up the three image panels along with their controllers
	 * Add the image panels to this card layout panel which can be scrollable
	 */
	public void setUpImagePanels() {
		UserRegistrationImagePanel imagePanelOne = new UserRegistrationImagePanel(this, this.imageListOne);
		UserRegistrationImagePanel imagePanelTwo = new UserRegistrationImagePanel(this, this.imageListTwo);
		UserRegistrationImagePanel imagePanelThree = new UserRegistrationImagePanel(this, this.imageListThree);
		this.imagesCardPanel.add(imagePanelOne, ONE);
		this.imagesCardPanel.add(imagePanelTwo, TWO);
		this.imagesCardPanel.add(imagePanelThree, THREE);
		this.imagesCardPanel.setVisible(true);
		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(imagePanelOne, this.model);
		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(imagePanelTwo, this.model);
		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(imagePanelThree, this.model);
	}
}

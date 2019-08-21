package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is responsible for the view containing the instructions for registration and for the user to enter a combination of user id, picture set and login method.
 */
public class UserRegistrationInstructionPanel extends JPanel {
	private JTextArea textArea;
	private JButton back, next;
	private JTextField input;
	private JComboBox selectLogin, selectPics;
	private String[] loginChoices, picsChoices;
	private JPanel clickablePanel;
	private Font aFont;
	
	public UserRegistrationInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
		setUpInstructionArea();
		setUpClickableArea();
	}
	
	/**
	 * Set up the panel containing registration instructions
	 */
	private void setUpInstructionArea() {
		JPanel panel = new JPanel();
		this.textArea = new JTextArea();
		this.textArea.setColumns(50);
		this.textArea.setEditable(false);
		String text = "Thank you for taking part in this experiment.\n\n";
		text += "To get started, you will need to enter your user ID in the text box, select a login method and a \npicture set from the drop-down boxes below. You will do this 6 times with each of the different login \nmethods and picture sets.\n\n";
		text += "When you click next, you'll be presented with 60 images, displayed across 3 panels. You can scroll \nleft and right using the buttons provided.\n\n";
		text += "You must pick 3 unique images, one by one and remember the order in which these are selected. \nOnce an image is selected, it cannot be deleted so please take your time.\n\n";
		text += "Your choices will be timed and you'll be asked to complete a follow-up questionnaire regarding your choices and general thoughts on this form of authentication.\n\n";
		text += "To log back in the program, you will need to select the 3 images that you initially picked, in the correct order.\n\n";
		text += "Click next to continue or back to return to the previous screen.";	
		this.textArea.setMargin(new Insets(5, 5, 5, 5));
		this.textArea.setLineWrap(true);
		this.textArea.setText(text);
		panel.add(textArea);
		this.add(panel);
	}
	
	/**
	 * A method to add all the user interactive components to the panel
	 */
	private void setUpClickableArea() {
		this.aFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		this.clickablePanel = new JPanel(new GridLayout(4,0));
		setTextInput();
		setLoginSelectionPanel();
		setPictureSelectionPanel();
		setBackNextButtonPanel();
		this.add(clickablePanel);
	}
	
	/**
	 * A method to add the back and next buttons, associating each one with their respective action command to listen to action events
	 */
	private void setBackNextButtonPanel() {
		JPanel buttonPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.next = new JButton("Next");
		this.next.setActionCommand("NEXT");
		buttonPanel.add(back);
		buttonPanel.add(next);
		clickablePanel.add(buttonPanel);
	}

	/**
	 * A method to set up and populate the picture set selection jcombo box and add it to the panel
	 */
	private void setPictureSelectionPanel() {
		JPanel pictureSetSelectionPanel = new JPanel();
		JLabel pictureSetSelectionLabel = new JLabel();
		pictureSetSelectionLabel.setText("Picture Set: ");
		pictureSetSelectionLabel.setFont(aFont);
		this.picsChoices = new String[4];
		setPicsChoices();
		this.selectPics = new JComboBox(this.picsChoices);
		pictureSetSelectionPanel.add(pictureSetSelectionLabel);
		pictureSetSelectionPanel.add(this.selectPics);
		clickablePanel.add(pictureSetSelectionPanel);
	}
	/**
	 * A method to set up and populate the login method selection jcombo box and add it to the panel
	 */
	private void setLoginSelectionPanel() {
		JPanel loginSelectionPanel = new JPanel();
		JLabel loginSelectionLabel = new JLabel();
		loginSelectionLabel.setText(" Login Method: ");
		loginSelectionLabel.setFont(aFont);
		loginChoices = new String[3];
		setLoginChoices();
		this.selectLogin = new JComboBox(loginChoices);
		loginSelectionPanel.add(loginSelectionLabel);
		loginSelectionPanel.add(selectLogin);
		clickablePanel.add(loginSelectionPanel);
	}

	/**
	 * A method to set up the JTextField for a user to enter their useer id and add it to the panel
	 */
	private void setTextInput() {
		JPanel textFieldPanel = new JPanel();
		JLabel textInputLabel = new JLabel();
		textInputLabel.setText("                      User ID: ");
		textInputLabel.setFont(aFont);
		this.input = new JTextField(20);
		setDefaultText();
		textFieldPanel.add(textInputLabel);
		textFieldPanel.add(this.input);
		clickablePanel.add(textFieldPanel);
	}
	
	/**
	 * Set placeholder text in the jtextfield
	 */
	public void setDefaultText() {
		this.input.setBackground(new Color(0, 0, 0, 0));
		this.input.setText("Please enter your unique UserID here");	
		this.input.setOpaque(false);
	}
	
	/**
	 * Populate the login method selection jcombo box
	 */
	private void setLoginChoices() {
		loginChoices[0] = "Please choose a log in method";
		loginChoices[1] = "1";
		loginChoices[2] = "2";
	}
	
	/**
	 * Populate the picture set selection jcombo box
	 */
	private void setPicsChoices() {
		picsChoices[0] = "Please choose a picture set";
		picsChoices[1] = "Art";
		picsChoices[2] = "Mikon";
		picsChoices[3] = "Doodle";
	}
	
	/**
	 * Add action/mouse listeners to various interactive jcomponents
	 * @param listener from the user registration instruction controller class
	 */
	public void addUserIntructionListener(ActionListener listener) {
		this.back.addActionListener(listener);
		this.next.addActionListener(listener);
		this.input.addMouseListener((MouseListener) listener);
	}

	/**
	 * Return the chosen selection from the login method jcombo box
	 */
	public int getLoginMethod() {
		int loginMethod = selectLogin.getSelectedIndex();
		return loginMethod;
	}
	
	/**
	 * Return the chosen selection from the picture set jcombo box
	 * @return
	 */
	public int getPicsSelection() {
		int picsSelection = selectPics.getSelectedIndex();
		return picsSelection;
	}

	/**
	 * Return the JTextField to determine that the clicking/mouse entering occured in the jtextfield
	 * @return
	 */
	public JTextField getInputArea() {
		return this.input;
	}

	/**
	 * Reset the picture set jcombo box to display the 0th index. This method will be called if the user clicks he back or next button
	 */
	public void resetPicsSelectionBox() {
		this.selectPics.setSelectedIndex(0);
	}
	
	/**
	 * Reset the login method jcombo box to display the 0th index. This method will be called if the user clicks he back or next button
	 */
	public void resetLoginSelectionBox() {
		this.selectLogin.setSelectedIndex(0);
		
	}
	
	/**
	 * Return the entered userid in the JTextField
	 */
	public String getInputText() {
		return this.input.getText();
	}

	/**
	 * Clear the placeholder text from the JTextField. This method will be called if the jtextfield has been clicked.
	 */
	public void clearText() {
		this.input.setText("");
		this.input.setBackground(Color.WHITE);
		this.input.setOpaque(true);
	}
	
}

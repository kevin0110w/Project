package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is responsible for the user login instruction panel view
 */
public class UserLoginInstructionPanel extends JPanel {
	private JTextField input;
	private JButton back, next;
	private JTextArea textArea;
	private JComboBox loginSelection, pictureSelection;
	private static final String NEXT = "NEXT", BACK = "BACK";
	private Font aFont;
	private JPanel inputPanel;

	public UserLoginInstructionPanel() {
		this.setLayout(new GridLayout(2, 0));
		setupTextArea();
		setupInputArea();
	}

	/**
	 * Set up the instruction text area
	 */
	private void setupTextArea() {
		JPanel textPanel = new JPanel();
		this.textArea = new JTextArea();
		this.textArea.setColumns(50);
		String text = "You are trying to log in to the system.\n\n";
		text += "Please enter your UserID in the text field. Next, select a login method and picture set choice using the drop-down boxes.\n\n";
		text += "On the next screen, you'll be presented with the 20 image icons associated with your userID - 17 \ndistractor/decoy images and the 3 images that were chosen as the password \n\n";
		text += "You have to select your password images, one by one and in the correct order, to successfully \nlog in. Once an image has been selected, it can NOT be deleted.\n\n";
		text += "Your login success rate and login time will both be recorded.";
		text += "You will be attempt to log in 4 times per picture set - 24 logins in total";
		this.textArea.setText(text);
		this.textArea.setMargin(new Insets(5, 5, 5, 5));
		this.textArea.setLineWrap(true);
		this.textArea.setEditable(false);
		textPanel.add(this.textArea);
		this.add(textPanel);
	}

	/**
	 * Set up the login input area
	 */
	private void setupInputArea() {
		this.aFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		this.inputPanel = new JPanel(new GridLayout(4, 0));
		setupTextInputPanel();
		setupLoginSelectionPanel();
		setupPictureSelectionPanel();
		setupBackNext();
		this.add(inputPanel);
	}

	/**
	 * Set up and populate a jcombo box for the login method and add it to a panel
	 */
	private void setupLoginSelectionPanel() {
		JPanel loginSelectionPanel = new JPanel();
		JLabel loginSelectionLabel = new JLabel();
		loginSelectionLabel.setText(" Login Method: ");
		loginSelectionLabel.setFont(aFont);
		String[] loginChoice = new String[3];
		loginChoice = setLoginChoice(loginChoice);
		this.loginSelection = new JComboBox(loginChoice);
		loginSelectionPanel.add(loginSelectionLabel);
		loginSelectionPanel.add(loginSelection);
		inputPanel.add(loginSelectionPanel);

	}

	/**
	 * Set up and populate a jcombo box for the picture set selections and add it to a panel
	 */
	private void setupPictureSelectionPanel() {
		JPanel pictureSetSelection = new JPanel();
		JLabel pictureSetSelectionLabel = new JLabel();
		pictureSetSelectionLabel.setText(" Picture Set: ");
		pictureSetSelectionLabel.setFont(aFont);
		String[] pictureSetChoice = new String[4];
		pictureSetChoice = setPictureChoice(pictureSetChoice);
		this.pictureSelection = new JComboBox(pictureSetChoice);
		pictureSetSelection.add(pictureSetSelectionLabel);
		pictureSetSelection.add(pictureSelection);
		inputPanel.add(pictureSetSelection);
	}

	/**
	 * Set up a text input panel for the user to enter their userid
	 */
	private void setupTextInputPanel() {
		JPanel textInputPanel = new JPanel();
		JLabel textInputLabel = new JLabel();
		textInputLabel.setText("                      User ID: ");
		textInputLabel.setFont(aFont);
		this.input = new JTextField(20);
		this.setDefaultText();
		textInputPanel.add(textInputLabel);
		textInputPanel.add(this.input);
		inputPanel.add(textInputPanel);
	}
	
	/**
	 * Set up the panel that'll hold the back and next buttons
	 */
	private void setupBackNext() {
		JPanel buttonPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand(BACK);
		this.next = new JButton("Next");
		this.next.setActionCommand(NEXT);
		buttonPanel.add(back);
		buttonPanel.add(next);
		inputPanel.add(buttonPanel);
	}

	/**
	 * Set the default text in the JTextField
	 */
	public void setDefaultText() {
		this.input.setBackground(new Color(0, 0, 0, 0));
		this.input.setText("Please enter your unique UserID here");
		this.input.setOpaque(false);
	}

	/**
	 * populate the array that'll be displayed in the jcombox box for picture
	 * choices
	 */
	private String[] setPictureChoice(String[] pictureSetChoice) {
		pictureSetChoice[0] = "Please choose a picture set";
		pictureSetChoice[1] = "Art";
		pictureSetChoice[2] = "Mikon (Greyscale)";
		pictureSetChoice[3] = "Mikon (Colour)";
		return pictureSetChoice;
	}

	/**
	 * populate the array that'll be displayed in the jcombox box for login choices
	 */
	private String[] setLoginChoice(String[] choice) {
		choice[0] = "Please choose a login method";
		choice[1] = "1";
		choice[2] = "2";
		return choice;
	}

	/**
	 * Add action listeners to all interactive buttons
	 * @param userLoginListener
	 */
	public void addUserLoginListener(ActionListener userLoginListener) {
		this.input.addActionListener(userLoginListener);
		this.back.addActionListener(userLoginListener);
		this.next.addActionListener(userLoginListener);
	}

	/**
	 * Add a mouse listener to the JTextField
	 * @param mouselistener l
	 */
	public void addUserLoginMouseListener(MouseListener listener) {
		this.input.addMouseListener(listener);

	}

	/**
	 * Get the login selection from the jcombobox
	 * @return
	 */
	public int getLoginSelection() {
		return this.loginSelection.getSelectedIndex();
	}

	/**
	 * Get the picture selection from the Jcombobox
	 * @return
	 */
	public int getPictureSelection() {
		return this.pictureSelection.getSelectedIndex();
	}

	/**
	 * Get the text input into the JTextField
	 * @return - entered text in the jtextfield
	 */
	public String getInput() {
		String textInput = this.input.getText();
		return textInput;
	}

	/**
	 * Set text to the JTextField
	 * @param string
	 */
	public void setInputToTextfield(String string) {
		this.input.setText(string);
		this.input.setBackground(Color.WHITE);
		this.input.setOpaque(true);
	}

	/**
	 * Get JTextField
	 * @return
	 */
	public JTextField getInputArea() {
		return this.input;
	}

	/**
	 * A method to reset the view
	 */
	public void clear() {
		setDefaultText();
		this.loginSelection.setSelectedIndex(0);
		this.pictureSelection.setSelectedIndex(0);
	}
}

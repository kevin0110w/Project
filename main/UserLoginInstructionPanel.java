package main;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private static final String LOGINSELECTION = "LOGINSELECTION", NEXT = "NEXT", BACK = "BACK", PICTURESELECTION = "PICTURESELECTION";
	
	public UserLoginInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
		setupTextArea();
		setupInputArea();
	}

	/**
	 * Set up the instruction text area
	 */
	private void setupTextArea() {
		JPanel textPanel = new JPanel(new BorderLayout());
		this.textArea = new JTextArea();
		this.textArea.setColumns(50);
		String text = "You are trying to log in to the system.\n\n";
		text += "Please enter your UserID, followed by a login method and a picture set choice using the drop-down boxes.\n\n";
		text += "On the next screen, you'll be presented with the 20 image icons associated with your userID - 17 distractor/decoy images and the 3 images that were chosen as the password \n\n";
		text += "You will have to select your password in the correct order to successfully log in.\n\n";
		text += "Your login success rate and time will both be recorded.";
		this.textArea.setText(text);
		this.textArea.setLineWrap(true);
		this.textArea.setEditable(false);
		textPanel.add(this.textArea);
		this.add(textPanel);
	}

	/**
	 * Set up the login input area
	 */
	private void setupInputArea() {
		JPanel inputPanel = new JPanel(new GridLayout(3,0));
		// add the textfield where the user will type in their userid
		JPanel textInputPanel = new JPanel();
		this.input = new JTextField(25);
		this.setDefaultText();
		textInputPanel.add(this.input);
		inputPanel.add(textInputPanel);
		
		// add the jcombo box that houses the options to select a login method
		JPanel selectionPanel = new JPanel();
		String[] loginChoice = new String[3];
		loginChoice = setChoice(loginChoice);
		this.loginSelection = new JComboBox(loginChoice);
		this.loginSelection.setActionCommand(LOGINSELECTION);
		
		// add a jcombo box that houses the options to select a picture set
		String[] pictureSetChoice = new String[4];
		pictureSetChoice = setPictureChoice(pictureSetChoice);
		this.pictureSelection = new JComboBox(pictureSetChoice);
		this.pictureSelection.setActionCommand(PICTURESELECTION);
		
		selectionPanel.add(loginSelection);
		selectionPanel.add(pictureSelection);
		
		inputPanel.add(selectionPanel);
		inputPanel.add(setupBackNext());
		this.add(inputPanel);
	}

	private void setDefaultText() {
		this.input.setText("Please enter your unique UserID here");
	}
	
	// populate the array that'll be displayed in the jcombox box for picture choices
	private String[] setPictureChoice(String[] pictureSetChoice) {
		pictureSetChoice[0] = "Please choose a picture set";
		pictureSetChoice[1] = "1";
		pictureSetChoice[2] = "2";
		pictureSetChoice[3] = "3";
		return pictureSetChoice;
	}
	
	// populate the array that'll be displayed in the jcombox box for login choices
	private String[] setChoice(String[] choice) {
		choice[0] = "Please enter a log in method";
		choice[1] = "1";
		choice[2] = "2";
		return choice;
	}
	
	private JPanel setupBackNext() {
		JPanel buttonPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand(BACK);
		this.next = new JButton("Next");
		this.next.setActionCommand(NEXT);
		buttonPanel.add(back);
		buttonPanel.add(next);
		return buttonPanel;
	}
	
	/**
	 * Add action listeners to all interactive buttons
	 * @param userLoginListener
	 */
	public void addUserLoginListener(ActionListener userLoginListener) {
		this.input.addActionListener(userLoginListener);
		this.loginSelection.addActionListener(userLoginListener);
		this.back.addActionListener(userLoginListener);
		this.next.addActionListener(userLoginListener);
		this.pictureSelection.addActionListener(userLoginListener);
	}
	
	/**
	 * Add a mouse listener to the JTextField
	 * @param l
	 */
	public void addUserLoginMouseListener(MouseListener l) {
		this.input.addMouseListener(l);
		
	}

	/**
	 * Get the login selection from the jcombobox
	 * @return
	 */
	public int getSelection() {
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
	 * @return - inputted text
	 */
	public String getInput() {
		String textInput = this.input.getText();
		return textInput;
	}

	/**
	 * Set text to the JTextField
	 * @param string
	 */
	public void setTextToInput(String string) {
		this.input.setText(string);
		
	}
	
	/**
	 * Get JTextField
	 * @return
	 */
	public JTextField getInputArea() {
		// TODO Auto-generated method stub
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

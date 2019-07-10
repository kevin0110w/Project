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

public class UserLoginInstructionPanel extends JPanel {
	private JTextField input;
	private JButton back, next;
	private JTextArea textArea;
	private JComboBox loginSelection, pictureSelection;
	private static final String LOGINSELECTION = "LOGINSELECTION", NEXT = "NEXT", INPUT = "INPUT", BACK = "BACK", PICTURESELECTION = "PICTURESELECTION";
	
	public UserLoginInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
//		this.setLayout(new BorderLayout());
		setupTextArea();
		setupInputArea();
	}

	private void setupTextArea() {
		JPanel textPanel = new JPanel(new BorderLayout());
		this.textArea = new JTextArea();
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		String text = "You are trying to login to the system. Please enter your UserID, followed by login method in the drop-down.\n\n";
		text += "On the next screen, you'll be presented with image icons. You will have to select your password in the correct order to successfully log in.";
		this.textArea.setText(text);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(a, BorderLayout.WEST);
		textPanel.add(b, BorderLayout.EAST);
		this.add(textPanel);
//		this.add(textPanel, BorderLayout.NORTH);
	}

	private void setupInputArea() {
		JPanel inputPanel = new JPanel(new GridLayout(3,0));
		JPanel textInputPanel = new JPanel();
		this.input = new JTextField(25);
		setDefaultText();
		
//		this.input.setActionCommand(INPUT);
		textInputPanel.add(this.input);
		inputPanel.add(textInputPanel);
		
		JPanel selectionPanel = new JPanel();
		String[] loginChoice = new String[3];
		loginChoice = setChoice(loginChoice);
		this.loginSelection = new JComboBox(loginChoice);
		this.loginSelection.setActionCommand(LOGINSELECTION);
		
		String[] pictureSetChoice = new String[4];
		pictureSetChoice = setPictureChoice(pictureSetChoice);
		this.pictureSelection = new JComboBox(pictureSetChoice);
		this.pictureSelection.setActionCommand(PICTURESELECTION);
		
		selectionPanel.add(loginSelection);
		selectionPanel.add(pictureSelection);
		
		inputPanel.add(selectionPanel);
		inputPanel.add(setupBackNext());
		this.add(inputPanel);
//		this.add(inputPanel, BorderLayout.CENTER);
	}

	private void setDefaultText() {
		this.input.setText("Please enter your unique UserID here");
		
	}

	private String[] setPictureChoice(String[] pictureSetChoice) {
		pictureSetChoice[0] = "Please choose a picture set";
		pictureSetChoice[1] = "1";
		pictureSetChoice[2] = "2";
		pictureSetChoice[3] = "3";
		return pictureSetChoice;
	}

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
	
	public void addUserLoginListener(ActionListener userLoginListener) {
		this.input.addActionListener(userLoginListener);
		this.loginSelection.addActionListener(userLoginListener);
		this.back.addActionListener(userLoginListener);
		this.next.addActionListener(userLoginListener);
		this.pictureSelection.addActionListener(userLoginListener);
	}
	

	public void addUserLoginMouseListener(MouseListener l) {
		this.input.addMouseListener(l);
		
	}

	public int getSelection() {
		return loginSelection.getSelectedIndex();
	}
	
	public int getPictureSelection() {
		return pictureSelection.getSelectedIndex();
	}

	public String getInput() {
		String textInput = this.input.getText();
		return textInput;
	}

	public void setTextToInput(String string) {
		this.input.setText(string);
		
	}
	
	public JTextField getInputArea() {
		// TODO Auto-generated method stub
		return this.input;
	}

	public void clear() {
		setDefaultText();
		this.loginSelection.setSelectedIndex(0);
		this.pictureSelection.setSelectedIndex(0);
	}

	
/*
	public static void main(String[] args) {
		JFrame f = new JFrame();
		UserLoginInstructionPanel u = new UserLoginInstructionPanel();
		f.add(u);
		f.pack();
		f.setVisible(true);
		System.out.println(u.getSelection());
	}
*/
}

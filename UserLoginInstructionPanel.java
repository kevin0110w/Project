import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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
	private JComboBox selection;
	private static final String SELECTION = "SELECTION", NEXT = "NEXT", INPUT = "INPUT", BACK = "BACK";
	
	public UserLoginInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
//		this.setLayout(new BorderLayout());
		setupTextArea();
		setupInputArea();
	}

	private void setupTextArea() {
		JPanel textPanel = new JPanel();
		this.textArea = new JTextArea();
		String text = "You are trying to login to the system. Please enter your UserID, followed by login method in the drop-down.\n\n";
		text += "On the next screen, you'll be presented with image icons. You will have to select your password in the correct order to successfully log in.";
		this.textArea.setText(text);
		textPanel.add(textArea);
		this.add(textPanel);
//		this.add(textPanel, BorderLayout.NORTH);
	}

	private void setupInputArea() {
		JPanel inputPanel = new JPanel(new GridLayout(3,0));
		this.input = new JTextField(4);
//		this.input.setActionCommand(INPUT);
		String[] choice = new String[3];
		choice = setChoice(choice);
		this.selection = new JComboBox(choice);
		this.selection.setActionCommand(SELECTION);
		inputPanel.add(input);
		inputPanel.add(selection);
		inputPanel.add(setupBackNext());
		this.add(inputPanel);
//		this.add(inputPanel, BorderLayout.CENTER);
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
		this.selection.addActionListener(userLoginListener);
		this.back.addActionListener(userLoginListener);
		this.next.addActionListener(userLoginListener);
	}

	public int getSelection() {
		return selection.getSelectedIndex();
	}

	public String getInput() {
		String textInput = this.input.getText();
		return textInput;
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

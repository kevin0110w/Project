import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserRegistrationInstructionPanel extends JPanel {
	private JTextArea textArea;
	private JButton back, next;
	private JTextField input;
	private JComboBox selectLogin, selectPics;
	private String[] loginChoices, picsChoices;
	
	public UserRegistrationInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
		setUpInstructionArea();
		setUpClickableArea();
	}
	
	private void setUpInstructionArea() {
		this.textArea = new JTextArea();
		String text = "You will be presented with 60 images on the next Screen.\n\n To register a new account, you will be required to choose 3 images as your password to log in to the program on future occasions.\n\n Click next to continue or to go back, click on back";
		this.textArea.setText(text);
		this.add(textArea);
		
	}
	
	private void setUpClickableArea() {
		JPanel clickablePanel = new JPanel(new GridLayout(3,0));
		
		JPanel textFieldPanel = new JPanel();
		this.input = new JTextField(25);
		this.input.setText("Please enter your userID here");
		textFieldPanel.add(this.input);
		
		JPanel selectionPanel = new JPanel();
		loginChoices = new String[3];
		setLoginChoices();
		this.selectLogin = new JComboBox(loginChoices);
		this.selectLogin.setActionCommand("SELECTLOGIN");
		
		this.picsChoices = new String[4];
		setPicsChoices();
		this.selectPics = new JComboBox(this.picsChoices);
		this.selectPics.setActionCommand("SELECTPICS");
		
		selectionPanel.add(this.selectLogin);
		selectionPanel.add(this.selectPics);
		
		JPanel buttonPanel = new JPanel();
		
		this.back = new JButton("BACK");
		this.back.setActionCommand("BACK");
		this.next = new JButton("NEXT");
		this.next.setActionCommand("NEXT");
		buttonPanel.add(back);
		buttonPanel.add(next);
		
		
		
		clickablePanel.add(textFieldPanel);
		clickablePanel.add(selectionPanel);
		clickablePanel.add(buttonPanel);
		
		this.add(clickablePanel);
	}

	
	private void setLoginChoices() {
		loginChoices[0] = "Select Registration Method";
		loginChoices[1] = "1";
		loginChoices[2] = "2";
	}
	
	private void setPicsChoices() {
		picsChoices[0] = "Select Picture Set";
		picsChoices[1] = "1";
		picsChoices[2] = "2";
		picsChoices[3] = "3";
		
	}
	
	public void addUserIntructionListener(ActionListener listener) {
		this.back.addActionListener(listener);
		this.next.addActionListener(listener);
		this.input.addMouseListener((MouseListener) listener);
		this.selectLogin.addActionListener(listener);
		this.selectPics.addActionListener(listener);
	}
	
	public void setInputText(String string) {
		this.input.setText(string);	
	}
	
	public String getUserEntry() {
		return this.input.getText();
	}

	public int getLoginMethod() {
		int login = selectLogin.getSelectedIndex();
		System.out.println(login);
		int loginMethod = Integer.parseInt(loginChoices[login]);
		System.out.println(loginMethod);
		return loginMethod;
	}
	
	public int getPicsSelection() {
		int pics = selectPics.getSelectedIndex();
		int picsSelection = Integer.parseInt(picsChoices[pics]);
		return picsSelection;
	}

	public JTextField getInputArea() {
		return this.input;
	}
	
}

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		JPanel panel = new JPanel();
		this.textArea = new JTextArea();
		this.textArea.setColumns(50);
		this.textArea.setEditable(false);
		String text = "Thank you for taking part in this experiment.\n\n";
		text += "To register a new account, you will need to enter a UserID in the text box, select a picture set from the drop down and then select 3 images from a selection of 60.\n\n";
		
		text += "The 60 images will be displayed across 3 panels which you can scroll through using the buttons provided.\n\n";
		
		text += "Your choices will be timed and you'll be asked to complete a follow-up questionnaire regarding your choices and general thoughts on this form of authentication.\n\n";
		
		text += "To log back in the program, you will need to select the 3 images that you initially picked, in the correct order.\n\n";
		
		text += "Click next to continue or to go back, click on back\n\n";	
		String three = "3";
		String sixty = "60";
		
		this.textArea.setLineWrap(true);
		this.textArea.setText(text);
		panel.add(textArea);
		this.add(panel);
	}
	
	private void setUpClickableArea() {
		JPanel clickablePanel = new JPanel(new GridLayout(3,0));
		
		JPanel textFieldPanel = new JPanel();
		this.input = new JTextField(25);
		this.input.setText("Please enter your userID here");
		textFieldPanel.add(this.input);
		
		JPanel selectionPanel = new JPanel();
//		loginChoices = new String[3];
//		setLoginChoices();
//		this.selectLogin = new JComboBox(loginChoices);
//		this.selectLogin.setActionCommand("SELECTLOGIN");
		
		this.picsChoices = new String[4];
		setPicsChoices();
		this.selectPics = new JComboBox(this.picsChoices);
		this.selectPics.setActionCommand("SELECTPICS");
		
//		selectionPanel.add(this.selectLogin);
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
//		this.selectLogin.addActionListener(listener);
//		this.selectPics.addActionListener(listener);
	}
	
	public void setInputText(String string) {
		this.input.setText(string);	
	}
	
	public String getUserEntry() {
		return this.input.getText();
	}

	public int getLoginMethod() {
		int loginMethod = selectLogin.getSelectedIndex();
		return loginMethod;
	}
	
	public int getPicsSelection() {
		int picsSelection = selectPics.getSelectedIndex();
		return picsSelection;
	}

	public JTextField getInputArea() {
		return this.input;
	}

	public void resetPicsSelectionBox() {
		this.selectPics.setSelectedIndex(0);
	}
	
}

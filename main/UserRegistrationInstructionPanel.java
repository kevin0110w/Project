package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
		text += "To get started, you will need to enter your user ID in the text box, select a login method and a \npicture set from the drop-down boxes below.\n\n";
		text += "When you click next, you'll be presented with 60 images, displayed across 3 panels. You can scroll \nleft and right using the buttons provided.\n\n";
		text += "You must pick 3 unique images, one by one and remember the order in which these are selected. \n\n";
		text += "Your choices will be timed and you'll be asked to complete a follow-up questionnaire regarding your choices and general thoughts on this form of authentication.\n\n";
		text += "To log back in the program, you will need to select the 3 images that you initially picked, in the correct order.\n\n";
		text += "Click next to continue or back to return to the previous screen.";	
		String three = "3";
		String sixty = "60";
		this.textArea.setMargin(new Insets(5, 5, 5, 5));
		this.textArea.setLineWrap(true);
		this.textArea.setText(text);
		panel.add(textArea);
		this.add(panel);
	}
	
	private void setUpClickableArea() {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		JPanel clickablePanel = new JPanel(new GridLayout(4,0));
		
		JPanel textFieldPanel = new JPanel();
		JLabel textInputLabel = new JLabel();
		textInputLabel.setText("                      User ID: ");
		textInputLabel.setFont(font);
		this.input = new JTextField(20);
		setDefaultText();
		textFieldPanel.add(textInputLabel);
		textFieldPanel.add(this.input);
		
		JPanel loginSelectionPanel = new JPanel();
		JLabel loginSelectionLabel = new JLabel();
		loginSelectionLabel.setText(" Login Method: ");
		loginSelectionLabel.setFont(font);
		loginChoices = new String[3];
		setLoginChoices();
		this.selectLogin = new JComboBox(loginChoices);
		loginSelectionPanel.add(loginSelectionLabel);
		loginSelectionPanel.add(selectLogin);
		
		JPanel pictureSetSelectionPanel = new JPanel();
		JLabel pictureSetSelectionLabel = new JLabel();
		pictureSetSelectionLabel.setText("Picture Set: ");
		pictureSetSelectionLabel.setFont(font);
		this.picsChoices = new String[4];
		setPicsChoices();
		this.selectPics = new JComboBox(this.picsChoices);
		pictureSetSelectionPanel.add(pictureSetSelectionLabel);
		pictureSetSelectionPanel.add(this.selectPics);
		
		JPanel buttonPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.next = new JButton("Next");
		this.next.setActionCommand("NEXT");
		buttonPanel.add(back);
		buttonPanel.add(next);
		
		clickablePanel.add(textFieldPanel);
		clickablePanel.add(loginSelectionPanel);
		clickablePanel.add(pictureSetSelectionPanel);
		clickablePanel.add(buttonPanel);
		
		this.add(clickablePanel);
	}
	
	public void setDefaultText() {
		this.input.setBackground(new Color(0, 0, 0, 0));
		this.input.setText("Please enter your student ID here");	
		this.input.setOpaque(false);
	}
	
	private void setLoginChoices() {
		loginChoices[0] = "Please choose a log in method";
		loginChoices[1] = "1";
		loginChoices[2] = "2";
	}
	
	private void setPicsChoices() {
		picsChoices[0] = "Please choose a picture set";
		picsChoices[1] = "Art";
		picsChoices[2] = "Mikon";
		picsChoices[3] = "Doodle";
	}
	
	public void addUserIntructionListener(ActionListener listener) {
		this.back.addActionListener(listener);
		this.next.addActionListener(listener);
		this.input.addMouseListener((MouseListener) listener);
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

	public void resetLoginSelectionBox() {
		this.selectLogin.setSelectedIndex(0);
		
	}
	
	public String getInputText() {
		return this.input.getText();
	}

	public void clearText() {
		this.input.setText("");
		this.input.setBackground(Color.WHITE);
		this.input.setOpaque(true);
	}
	
}

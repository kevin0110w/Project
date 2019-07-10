import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserLoginSuccessPanel extends JPanel {
	private JTextArea textArea;
	private JButton logOffButton;
	private JButton home;
	public UserLoginSuccessPanel() {
		this.setLayout(new GridLayout(2,0));
		
		JPanel textPanel = new JPanel();
		this.textArea = new JTextArea();
		textPanel.add(this.textArea);
		
		
		JPanel buttonPanel = new JPanel();
//		this.logOffButton = new JButton("Log Off / Home");
//		buttonPanel.add(this.logOffButton);
		this.home = new JButton("Log Off / Home");
		this.home.setActionCommand("HOME");
		buttonPanel.add(this.home);
		
		this.add(textPanel);
		this.add(buttonPanel);
	}
	
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}
	
	public void addListeners(ActionListener listener) {
//		this.logOffButton.addActionListener(listener);
		this.home.addActionListener(listener);
	}
}

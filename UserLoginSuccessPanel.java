import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserLoginSuccessPanel extends JPanel {
	private JTextArea textArea;
	private JButton logOffButton;
	private JButton home;
	public UserLoginSuccessPanel() {
		this.setLayout(new BorderLayout());
		this.textArea = new JTextArea();
		
		JPanel buttonPanel = new JPanel();
		this.logOffButton = new JButton("Log Off");
		buttonPanel.add(textArea);
		buttonPanel.add(this.logOffButton);
		this.home = new JButton("Home");
		this.home.setActionCommand("HOME");
		buttonPanel.add(this.home);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}
	
	public void addListeners(ActionListener listener) {
		this.logOffButton.addActionListener(listener);
		this.home.addActionListener(listener);
	}
}

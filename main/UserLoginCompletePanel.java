package main;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This is the view to display the panel after a user has tried to choose their 3 password images
 * and will show whether the login has been successful or not.
 */
public class UserLoginCompletePanel extends JPanel {
	private JTextArea textArea;
	private JButton home;
	
	public UserLoginCompletePanel() {
		this.setLayout(new BorderLayout());
		setup();
	}
	
	/**
	 * Set up the panel
	 */
	public void setup() {
		setupTextArea();
		setupButtonPanel();
	}
	
	/**
	 * Set up the panel that'll show whether a user has logged in successfully or not
	 */
	private void setupTextArea() {
		JPanel textPanel = new JPanel();
		this.textArea = new JTextArea();
		this.textArea.setColumns(20);
		this.textArea.setEditable(false);
		this.textArea.setLineWrap(true);
		textPanel.add(this.textArea);
		this.add(textPanel, BorderLayout.CENTER);
	}

	/**
	 * Set up the log off button to return a user to the main page
	 */
	private void setupButtonPanel() {
		JPanel buttonPanel = new JPanel();
		this.home = new JButton("Log Off");
		this.home.setActionCommand("HOME");
		buttonPanel.add(this.home);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}

	/**
	 * Set the text to the text area
	 * @param text
	 */
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}
	
	/**
	 * Attach a listener to the JButton
	 * @param listener - an aciton listener
	 */
	public void addListeners(ActionListener listener) {
		this.home.addActionListener(listener);
	}
}

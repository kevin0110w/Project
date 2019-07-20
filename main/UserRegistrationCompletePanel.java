package main;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class is the view that'll be displayed once a registration has been completed
 *
 */
public class UserRegistrationCompletePanel extends JPanel {

	private JTextArea message;
	private JButton home, exit;
	private JPanel buttonPanel;

	public UserRegistrationCompletePanel() {
		setUp();
		
	}
	
	/**
	 * Sets up the view
	 */
	private void setUp() {
		this.message = new JTextArea(5, 50);
		this.message.setText("Registration Phase Complete");
		this.add(message);
		this.home = new JButton("Log Off / Home");
		this.home.setActionCommand("HOME");
		this.exit = new JButton("Exit");
		this.exit.setActionCommand("EXIT");
		this.buttonPanel = new JPanel();
		this.buttonPanel.add(home);
		this.add(buttonPanel);
	}
	
	/**
	 * Add action listeners to the interactive buttons
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		this.exit.addActionListener(listener);
		this.home.addActionListener(listener);
	}
}

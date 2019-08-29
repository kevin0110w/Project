package main;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class is the view that'll be displayed once a registration has been completed
 */
public class UserRegistrationCompletePanel extends JPanel {
	private JTextArea message;
	private JButton home;

	public UserRegistrationCompletePanel() {
		setUp();
	}
	
	/**
	 * Sets up the view
	 */
	private void setUp() {
		this.setLayout(new BorderLayout());
		setupMessagePanel();
		setupHomeButtonPanel();
	}
	
	/**
	 * Set up the home button
	 */
	private void setupHomeButtonPanel() {
		JPanel homeButtonPanel = new JPanel();
		this.home = new JButton("Log Off");
		this.home.setActionCommand("HOME");
		homeButtonPanel.add(home);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Set up the message panel advising the user that registration is complete
	 */
	private void setupMessagePanel() {
		JPanel messagePanel = new JPanel();
		this.message = new JTextArea();
		this.message.setText("Registration Phase Completed. \n\nClick on the Log Off button below to \nreturn to the main menu");
		this.message.setEditable(false);
		this.message.setColumns(20);
		this.message.setLineWrap(true);
		messagePanel.add(message);
		this.add(messagePanel, BorderLayout.CENTER);
	}

	/**
	 * Add action listeners to the interactive buttons
	 * @param listener - from the user registration complete class.
	 */
	public void addActionListener(ActionListener listener) {
		this.home.addActionListener(listener);
	}
}

package main;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This is the view to display the panel after a login
 *
 */
public class UserLoginCompletePanel extends JPanel {
	private JTextArea textArea;
	private JButton home;
	
	public UserLoginCompletePanel() {
		this.setLayout(new BorderLayout());
		setup();
	}
	
	public void setup() {
		JPanel textPanel = new JPanel();
		this.textArea = new JTextArea();
		this.textArea.setColumns(20);
		this.textArea.setEditable(false);
		this.textArea.setLineWrap(true);
		
		textPanel.add(this.textArea);
		
		JPanel buttonPanel = new JPanel();
		this.home = new JButton("Log Off");
		this.home.setActionCommand("HOME");
		buttonPanel.add(this.home);

		this.add(textPanel, BorderLayout.CENTER);
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

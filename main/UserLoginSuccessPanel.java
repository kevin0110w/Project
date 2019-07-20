package main;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


/**
 * This is the view to display the panel after a login
 *
 */
public class UserLoginSuccessPanel extends JPanel {
	private JTextPane textArea;
	private JButton home;
	
	public UserLoginSuccessPanel() {
		this.setLayout(new GridLayout(2,0));
		
		JPanel textPanel = new JPanel();
		this.textArea = new JTextPane();
		this.textArea.setEditable(false);
		textPanel.add(this.textArea);
		
		JPanel buttonPanel = new JPanel();
		this.home = new JButton("Log Off / Home");
		this.home.setActionCommand("HOME");
		buttonPanel.add(this.home);
		
		this.add(textPanel);
		this.add(buttonPanel);
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

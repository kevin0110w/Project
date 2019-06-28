import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserRegistrationCompletePanel extends JPanel {

	private JTextArea message;
	private JButton exit;
	private JButton home;
	private JPanel buttonPanel;

	public UserRegistrationCompletePanel() {
		this.setLayout(new GridLayout(2,0));
		setUp();
		
	}

	private void setUp() {
		message = new JTextArea();
		message.setText("Thank you for registering a new account. \n\n To go home, click on the button marked \"Home\" and to quit, click on \"Exit\"");
		this.add(message);
		home = new JButton("Home");
		home.setActionCommand("HOME");
		exit = new JButton("Exit");
		exit.setActionCommand("EXIT");
		buttonPanel = new JPanel();
		buttonPanel.add(home);
		buttonPanel.add(exit);
		this.add(buttonPanel);
	}
	
	void addActionListener(ActionListener listener) {
		this.exit.addActionListener(listener);
		this.home.addActionListener(listener);
	}
}

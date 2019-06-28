import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInstructionPanel extends JPanel {
	private JTextArea textArea;
	private JButton back, next;
	public UserInstructionPanel() {
		this.setLayout(new GridLayout(2,0));
		textArea = new JTextArea();
		String text = "You will be presented with 60 images on the next Screen.\n\n To register a new account, you will be required to choose 3 images as your password to log in to the program on future occasions.\n\n Click next to continue or to go back, click on back";
		textArea.setText(text);
		this.add(textArea);
		JPanel buttonPanel = new JPanel();
		back = new JButton("BACK");
		next = new JButton("NEXT");
		buttonPanel.add(back);
		buttonPanel.add(next);
		this.add(buttonPanel);
	}
	
	public void addUserIntructionListener(ActionListener listener) {
		this.back.addActionListener(listener);
		this.next.addActionListener(listener);
	}
	
	public JButton returnNext() {
		return this.next;
	}
	
	public JButton returnBack() {
		return this.back;
	}
}

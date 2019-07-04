import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserLoginImagePanel extends JPanel {
	
	private List<JButton> buttons;
	private JTextField userIdField;
	private JPanel loginPanel;
	private UserRegistrationPanel panel;
	private List<String> imageSet;
	private JComboBox selection;
	private String[] choices;
	private JTextField input;
	private JButton next;
	public UserLoginImagePanel(UserRegistrationPanel panel, List<String> images) {
		this.setLayout(new BorderLayout());
		setUpTopPanel();
		this.imageSet = new ArrayList<String>();
		this.imageSet = images;
		this.panel = panel;
		setup();
		next = new JButton("NEXXT");
		this.add(next, BorderLayout.SOUTH);
	}

	private void setup() {
		this.loginPanel = new JPanel();
		this.loginPanel.setLayout(new GridLayout(5,4));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 20; i++) {
			buttons.add(new JButton());
//			buttons.get(i).setIcon(new ImageIcon(filePaths.get(i)));
			buttons.get(i).setIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setActionCommand("" + i);
			this.loginPanel.add(buttons.get(i));
		}
		this.add(loginPanel);
	}
	private void setUpTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		input = new JTextField(10);
		choices = new String[3];
		setChoices();
		selection = new JComboBox(choices);
		topPanel.add(input);
		topPanel.add(selection);
		this.add(topPanel, BorderLayout.NORTH);
	}

	private void setChoices() {
		choices[0] = "Select Registration Type";
		choices[1] = "1";
		choices[2] = "2";
	}
	public void addListener(ActionListener alistener) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
		this.input.addActionListener(alistener);
		this.selection.addActionListener(alistener);
		this.next.addActionListener(alistener);
	}
	public void printString(Object object) {
		System.out.println(((JButton) object).getIcon());
		
	}
	
	public void setImage(Object object) {
		Icon s = ((JButton) object).getIcon();
		panel.setImage(s);
	}
	
	public void addImage(Object object) {
		Icon s = ((JButton) object).getIcon();
		System.out.println("Ha");
		System.out.println(s.toString());
		panel.addToUserImages(s.toString());
	}
}

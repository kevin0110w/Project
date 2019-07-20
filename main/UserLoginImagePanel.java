package main;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is responsible for displaying the login selection page, containing the 20 images (17 decoys and 3 passwords)
 *
 */
public class UserLoginImagePanel extends JPanel {
	private List<JButton> buttons; // list of buttons
	private List<String> imageSet; // list of the 20 image file paths
	private JButton next;
	
	public UserLoginImagePanel(List<String> images) {
		this.setLayout(new BorderLayout());
		this.imageSet = new ArrayList<String>();
		this.imageSet = images;
		this.setup();
	}
	
	/**
	 * Set up the buttons before adding them to the panel
	 */
	private void setup() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,4));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 20; i++) {
			buttons.add(new JButton());
			buttons.get(i).setIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setDisabledIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setMargin(new Insets(0,0,0,0));
			buttons.get(i).setActionCommand("" + i);
			buttonPanel.add(buttons.get(i));
		}
		this.add(buttonPanel);
	}
	
	/**
	 * Add action listeners to all interactive buttons on this panel
	 * @param alistener
	 */
	public void addListener(ActionListener alistener) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
	}
}

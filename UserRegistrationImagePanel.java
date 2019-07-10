import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserRegistrationImagePanel extends JPanel {
	private JPanel imagePanel;
	private List<JButton> buttons;
	UserRegistrationPanel urp;
	private List<String> imageSet;

//	public UserRegistrationImagePanel(UserRegistrationPanel urp) {
////		fnt = new FileNames();
//		setImageList(imageSet);
//		setUp();
//		this.urp = urp;
//	}
	
//	private void setImageList(List<String> images) {
//		this.imageSet = images;
//	}
	
	public UserRegistrationImagePanel(UserRegistrationPanel urp, List<String> imageSet) {
		this.imageSet = new ArrayList<String>();
		this.imageSet = imageSet;
		this.urp = urp;
		setUp();
	}
	
	private void setUp() {
		this.imagePanel = new JPanel();
		this.imagePanel.setLayout(new GridLayout(4, 5));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < this.imageSet.size(); i++) {
			buttons.add(new JButton());
			buttons.get(i).setIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setDisabledIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttons.get(i).setActionCommand("" + i);
			this.imagePanel.add(buttons.get(i));
		}
		this.add(imagePanel);
	}
	
	public void addListener(ActionListener alistener) {
		for (int i = 0; i < this.buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
	}
	
	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		UserRegistrationImagePanel user = new UserRegistrationImagePanel();
		frame.add(user);
		ArrayList<JButton> x = new ArrayList<JButton>();
		JButton firstButton = new JButton("FIRST");
		x.add(firstButton);
		frame.add(x.get(0));
		ImagePanelController i = new ImagePanelController(user);
		frame.pack();
		frame.setVisible(true);
	}
	*/

	public void setImage(Icon filePath, int n) {
		this.urp.setImage(filePath, n);
		
	}

	public void disableButton(String actionCommand) {
		for (JButton button : this.buttons) {
			if (button.getActionCommand().equals(actionCommand)) {
				button.setEnabled(false);
			}
			
		}
		
	}

	public void disableAllImageButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
			
		}
		
	}
}


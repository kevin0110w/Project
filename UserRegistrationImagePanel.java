import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserRegistrationImagePanel extends JPanel {
	private JPanel imagePanel;
//	private FileNames fnt;
	ArrayList<JButton> buttons;
	UserRegistrationPanel urp;
	List<String> imageSet;

//	public UserRegistrationImagePanel(UserRegistrationPanel urp) {
////		fnt = new FileNames();
//		setImageList(imageSet);
//		setUp();
//		this.urp = urp;
//	}
	
	private void setImageList(List<String> images) {
		this.imageSet = images;
	}
	
	public UserRegistrationImagePanel(UserRegistrationPanel urp, List<String> imageSet) {
//		fnt = new FileNames();
		this.imageSet = new ArrayList<String>();
		this.imageSet = imageSet;
		this.urp = urp;
		setUp();
	}
	
	private void setUp() {
		this.imagePanel = new JPanel();
		this.imagePanel.setLayout(new GridLayout(4,6));
		this.setPreferredSize(new Dimension(500,500));
//		Iterator<Map<String, String>> it = fnt.getImageSet().iterator();
//		List<String> filePaths = new ArrayList<String>();
//		while (it.hasNext()) {
//			for (String s: it.next().values()) {
//				filePaths.add(s);
//			}
//		}
//		System.out.println(filePaths.get(0));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 20; i++) {
			buttons.add(new JButton());
//			x.get(i).setIcon(new ImageIcon(filePaths.get(i)));
			buttons.get(i).setIcon(new ImageIcon(this.imageSet.get(i)));
			buttons.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttons.get(i).setActionCommand("" + i);
			this.imagePanel.add(buttons.get(i));
		}
		this.add(imagePanel);
//		firstButton.setIcon(new ImageIcon(filePaths.get(0)));
//		secondButton.setIcon(new ImageIcon(filePaths.get(1)));
//		thirdButton.setIcon(new ImageIcon(filePaths.get(2)));
//		fourthButton.setIcon(new ImageIcon(filePaths.get(3)));
//		imagePanel.add(firstButton);
//		imagePanel.add(secondButton);
//		imagePanel.add(thirdButton);
//		imagePanel.add(fourthButton);
	}
	
	public void addListener(ActionListener alistener) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
	}
	
	public void printString(Object object) {
		System.out.println(((JButton) object).getIcon());
		
	}
	
	public void setImage(Object object) {
		Icon s = ((JButton) object).getIcon();
		urp.setImage(s);
	}
	
	public void addImage(Object object) {
		Icon s = ((JButton) object).getIcon();
		System.out.println("Ha");
		System.out.println(s.toString());
		urp.addToUserImages(s.toString());
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
		urp.setImage(filePath, n);
		
	}

	public void disableButton(String actionCommand) {
		for (JButton button : this.buttons) {
			if (button.getActionCommand().equals(actionCommand)) {
				button.setEnabled(false);
			}
		}
		
	}
}


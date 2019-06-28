import java.awt.Frame;
import java.awt.GridLayout;
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
	private FileNames fnt;
	ArrayList<JButton> x;
	UserRegistrationPanel urp;

	public UserRegistrationImagePanel(UserRegistrationPanel urp) {
		fnt = new FileNames();
		setUp();
		this.urp = urp;
	}
	
	private void setUp() {
		this.imagePanel = new JPanel();
		this.imagePanel.setLayout(new GridLayout(4,5));
		Iterator<Map<String, String>> it = fnt.getImageSet().iterator();
		List<String> filePaths = new ArrayList<String>();
		while (it.hasNext()) {
			for (String s: it.next().values()) {
				filePaths.add(s);
			}
		}
//		System.out.println(filePaths.get(0));
		x = new ArrayList<JButton>();
		for (int i = 0; i < 20; i++) {
			x.add(new JButton());
			x.get(i).setIcon(new ImageIcon(filePaths.get(i)));
			x.get(i).setActionCommand("" + i);
			this.imagePanel.add(x.get(i));
			
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
		for (int i = 0; i < x.size(); i++) {
			x.get(i).addActionListener(alistener);
		}
	}
	
	public void addString(Object object) {
		System.out.println(((JButton) object).getIcon());
		
	}
	
	public void setImage(Object object) {
		Icon s = ((JButton) object).getIcon();
		urp.setImage(s);
	}

	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		UserRegistrationImagePanel user = new UserRegistrationImagePanel();
//		frame.add(user);
//		ArrayList<JButton> x = new ArrayList<JButton>();
//		JButton firstButton = new JButton("FIRST");
//		x.add(firstButton);
//		frame.add(x.get(0));
//		ImagePanelController i = new ImagePanelController(user);
//		frame.pack();
//		frame.setVisible(true);
	}	
}


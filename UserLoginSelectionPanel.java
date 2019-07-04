import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UserLoginSelectionPanel extends JPanel {
	private List<JButton> buttons;
	private Set<String> filePaths;
	private JPanel buttonPanel, selectionPanel;
	private JButton selectionOne, selectionTwo, selectionThree, back, login;
	private int n;
	
	public UserLoginSelectionPanel() {
		this.setLayout(new BorderLayout());
		this.filePaths = new HashSet<String>();
		setButtonPanel();
		setSelectionPanel();
//		setUpCards();
	}

	private void setSelectionPanel() {
		this.selectionPanel = new JPanel();
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.selectionOne = new JButton();
		this.selectionTwo = new JButton();
		this.selectionThree = new JButton();
		this.login = new JButton("Log In");
		this.login.setActionCommand("LOGIN");
		this.selectionPanel.add(back);
		this.selectionPanel.add(selectionOne);
		this.selectionPanel.add(selectionTwo);
		this.selectionPanel.add(selectionThree);
		this.selectionPanel.add(this.login);
		this.add(this.selectionPanel, BorderLayout.SOUTH);
	}

	private void setButtonPanel() {
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(4,5));
		this.add(this.buttonPanel, BorderLayout.CENTER);
	}
	
	private void setButtons() {
		int n = 0;
		this.buttons = new ArrayList<JButton>();
		Iterator<String> it = this.filePaths.iterator();
		while (it.hasNext()) {
			String aPath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(n).setIcon(new ImageIcon(aPath));
			this.buttons.get(n).setActionCommand("" + n);
			this.buttons.get(n).setMargin(new Insets(0, 0, 0, 0));
			this.buttonPanel.add(this.buttons.get(n));
			n++;
		}
	}
	
	public void getFilePaths(Set<String> list) {
		this.filePaths.addAll(list);
		setButtons();
	}
	
	public void addListener(ActionListener alistener) {
//		for (JButton button : this.buttons) {
//			button.addActionListener(alistener);
//
//		}
		for (int i = 0; i < this.buttons.size(); i++) {
			buttons.get(i).addActionListener(alistener);
		}
		
		this.back.addActionListener(alistener);
		
		this.login.addActionListener(alistener);
	}
	

	private int getn() {
		// TODO Auto-generated method stub
		return n;
	}

	public void setImage(Object source) {
		Icon iconImage = ((JButton) source).getIcon();
		System.out.println(iconImage.toString());
		if (n == 0) {
			selectionOne.setIcon(iconImage);
		} else if (n == 1) {
			selectionTwo.setIcon(iconImage);
		} else if (n == 2) {
			selectionThree.setIcon(iconImage);
		}
		n++;
	}
	
	/*
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		UserLoginSelectionPanel u = new UserLoginSelectionPanel();
//		UserLoginModel m = new UserLoginModel();
//		UserLoginSelectionController c = new UserLoginSelectionController(m, u);
		
//		UserLoginSuccessPanel uu = new UserLoginSuccessPanel();
//		UserLoginSuccessController cc = new UserLoginSuccessController(uu, m);
//		JPanel cardsPanel = new JPanel(new CardLayout());
//		cardsPanel.add(u, "LOGINSELECTION");
//		cardsPanel.add(uu, "SUCCESS");
		
		UserLoginModel m = new UserLoginModel();
		u.getFilePaths(m.getFilePaths());
		UserLoginController c = new UserLoginController(u);
		f.add(u);
		f.setVisible(true);
		f.pack();
	}
*/
}

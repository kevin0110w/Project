import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
//	 Set<String> filePaths;
	List<String> filePaths;
	private JPanel buttonPanel, selectionPanel;
	private JButton back, login;
	private JLabel selectionOne, selectionTwo, selectionThree;
	private int n;
	private boolean successfulPastLogin;
	
	public UserLoginSelectionPanel() {
		this.setLayout(new BorderLayout());
//		this.filePaths = new HashSet<String>();
		this.filePaths = new ArrayList<String>();
		setButtonPanel();
		setSelectionPanel();
	}

	private void setSelectionPanel() {
		this.selectionPanel = new JPanel();
		
		this.back = new JButton("Back");
		this.back.setActionCommand("BACK");
		this.selectionPanel.add(back);
		
		this.selectionOne = new JLabel();
		this.selectionTwo = new JLabel();
		this.selectionThree = new JLabel();
		this.selectionPanel.add(selectionOne);
		this.selectionPanel.add(selectionTwo);
		this.selectionPanel.add(selectionThree);
		
		this.login = new JButton("Log In");
		this.login.setActionCommand("LOGIN");
		this.selectionPanel.add(login);
		
		this.add(this.selectionPanel, BorderLayout.SOUTH);
	}

	private void setButtonPanel() {
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(4,5));
		this.add(this.buttonPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set the buttons - i.e. set the icon, action command and finally attach it to the button JPanel
	 */
	private void setButtons() {
		int n = 0;
		this.buttons = new ArrayList<JButton>();
		Iterator<String> it = this.filePaths.iterator();
		while (it.hasNext()) {
			String aPath = it.next();
			this.buttons.add(new JButton());
			this.buttons.get(n).setIcon(new ImageIcon(aPath));
			this.buttons.get(n).setDisabledIcon(new ImageIcon(aPath));
			this.buttons.get(n).setActionCommand("" + n);
//			this.buttons.get(n).setMargin(new Insets(0, 0, 0, 0));
			this.buttonPanel.add(this.buttons.get(n));
			n++;
		}
	}
	
	/*
	 * Getting a registered users 20 picture file paths, these will be shuffled only if the user most recently successfully logged in
	 */
	public void getFilePaths(List<String> list) {
//		if (this.successfulPastLogin) {
//		Collections.shuffle(list);
//		}
		this.filePaths.addAll(list);
		setButtons();
	}
	
	/**
	 * Add listeners to each interactive JComponent that is on the JFrame for the user to click on (e.g. each image button, back and login buttons)
	 * @param alistener
	 */
	public void addListener(ActionListener alistener) {
		for (JButton button : this.buttons) {
			button.addActionListener(alistener);

		}
		this.back.addActionListener(alistener);
		this.login.addActionListener(alistener);
	}
	

	private int getn() {
		return n;
	}

	/*
	 * Set the image underneath the image buttons to let the user know which ones have been selected so far
	 * @Param source is the returned object from the action listener
	 */
	public void setImage(Object source) {
		Icon iconImage = ((JButton) source).getIcon();
		if (n == 0) {
			this.selectionOne.setIcon(iconImage);
		} else if (n == 1) {
			this.selectionTwo.setIcon(iconImage);
		} else if (n == 2) {
			this.selectionThree.setIcon(iconImage);
		}
		n++;
	}
	
	
//	public static void main(String[] args) {
//		UserLoginSelectionPanel panel = new UserLoginSelectionPanel();
//		UserLoginModel model = new UserLoginModel();
//		model.setUserID(200);
//		model.setLoginMethod(1);
//		panel.getFilePaths(model.getDecoyImages());
////		Iterator<String> iterator = panel.filePaths.iterator();
////		while (iterator.hasNext()) {
////			System.out.println(iterator.next());
////		}
//	}
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

	public void setSuccess(boolean successfulPastLogin) {
		this.successfulPastLogin = successfulPastLogin;
	}

	public void disableButtons() {
		for (JButton button : this.buttons) {
			button.setEnabled(false);
		}
		
	}

	public void clear() {
		this.filePaths.clear();
//		for (JButton button : this.buttons) {
//			this.buttonPanel.remove(button);
//			
//		}
		this.buttons.clear();
		this.remove(this.buttonPanel);
		this.remove(this.selectionPanel);
		this.n = 0;
		setButtonPanel();
		setSelectionPanel();
		revalidate();
		repaint();
	}
}

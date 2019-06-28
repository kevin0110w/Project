import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserRegistrationPanel extends JPanel {
	private Integer counter = 1;
	private JButton userRegistrationButton, left, right, one, two, three, back, next;
	private JPanel imagesPanel, imageSetOne, imageSetTwo, imageSetThree, selectedImages;
	private JTextField userRegistrationInputField;
	private CardLayout cl;
	private final static String ONE = "one";
	private final static String TWO = "two";
	private final static String THREE = "three";
	private JButton scrollL, scrollR;
	JLabel iC3, selectedImageOne, selectedImageTwo, selectedImageThree, iC, iC2;
	MainWindow window;
	DBConnect db;
	User user;
	private LocalDateTime startTime;
	FileNames fnt;
	private JButton firstButton;
	private JButton secondButton;
	private JButton thirdButton;
	private JButton fourthButton;
	private JTextField input;
	private JComboBox selection;
	private String[] choices;
	private int loginattempt;
	private int userid;
	
	public UserRegistrationPanel(MainWindow w) {
		fnt = new FileNames();
//		this.user = new User();
		window = w;
		this.setLayout(new BorderLayout());
		this.imagesPanel = new JPanel(new CardLayout());
//		imageSetOne = new JPanel();
		imageSetTwo = new JPanel();
		imageSetThree = new JPanel();
//		iC = new JLabel();
		one = new JButton();
		ImageIcon o = new ImageIcon("1.jpg");
		one.setIcon(o);
//		iC.setIcon(o);
//		iC2 = new JLabel();
		two = new JButton();
		ImageIcon oo = new ImageIcon("2.jpg");
		two.setIcon(oo);
//		iC2.setIcon(oo);
//		iC3 = new JLabel();
		three = new JButton();
		ImageIcon ooo = new ImageIcon("3.jpg");
		three.setIcon(ooo);
//		iC3.setIcon(ooo);
//		iC3.addMouseListener(new Listener());
//		imageSetOne.add(iC);
//		imageSetTwo.add(iC2);
//		imageSetThree.add(iC3);
//		imageSetOne.add(one);
		imageSetTwo.add(two);
		imageSetThree.add(three);
		setUpTopPanel();
		three.addActionListener(new Listener());
		UserRegistrationImagePanel p = new UserRegistrationImagePanel(this);
		UserRegistrationImagePanel j = new UserRegistrationImagePanel(this);
		UserRegistrationImagePanel k = new UserRegistrationImagePanel(this);
		this.imagesPanel.add(p, ONE);
		this.imagesPanel.add(j, TWO);
		this.imagesPanel.add(k, THREE);
		UserRegistrationImageController r = new UserRegistrationImageController(p);
		UserRegistrationImageController r1 = new UserRegistrationImageController(j);
		UserRegistrationImageController r2 = new UserRegistrationImageController(k);
//		this.imagesPanel.add(imageSetTwo, TWO);
//		this.imagesPanel.add(imageSetThree, THREE);
		this.imagesPanel.setVisible(true);
		startTime = LocalDateTime.now();
		this.add(imagesPanel, BorderLayout.CENTER);
		this.scrollL = new JButton("<<");
		this.scrollR = new JButton(">>");
		this.add(scrollL, BorderLayout.WEST);
		this.add(scrollR, BorderLayout.EAST);
		this.selectedImages = new JPanel();
		this.add(selectedImages, BorderLayout.SOUTH);
		back = new JButton("Back");
		back.setActionCommand("BACK");
//		back.addActionListener(new Listener());
		selectedImages.add(back);
		selectedImageOne = new JLabel();
		selectedImageTwo = new JLabel();
		selectedImageThree = new JLabel();
		this.selectedImages.add(selectedImageOne);
		this.selectedImages.add(selectedImageTwo);
		this.selectedImages.add(selectedImageThree);

		this.next = new JButton("Next");
		this.next.setEnabled(false);
		this.next.addActionListener(new Listener());
		selectedImages.add(this.next);

		this.cl = (CardLayout) (this.imagesPanel.getLayout());
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

	class Listener implements MouseListener, ActionListener {

		

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == iC3) {
				setImage(iC3.getIcon());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == three) {
				setImage(three.getIcon());
			} else if (arg0.getActionCommand().equals("BACK")) {
				window.showMainPage();
			} else if (arg0.getSource() == next) {
				db = new DBConnect();
//				user.setUserid(userid);
//				user.setLoginattempt(loginattempt);
//				user.setPasswordOne(selectedImageOne.getIcon().toString());
//				user.setPasswordTwo(selectedImageTwo.getIcon().toString());
//				user.setPasswordThree(selectedImageThree.getIcon().toString());
				setUserID();
//				setLogInAttempt();
				System.out.println(loginattempt);
				user = new User(userid, loginattempt, selectedImageOne.getIcon().toString(), selectedImageTwo.getIcon().toString(), selectedImageThree.getIcon().toString() );
				LocalDateTime endTime = LocalDateTime.now();
				System.out.println(startTime);
				System.out.println(endTime);
				db.addUserToDatabase(user);
				Duration betweenStartandEnd = Duration.between(startTime, endTime);
				long milliseconds = betweenStartandEnd.toMillis();
				System.out.println(milliseconds);
				/* window.showMainPage(); */
				window.showCompletePage();
				refresh();
			}

		}

		private void refresh() {
			
			
		}
	}
	
	

	public void adduserRegistrationButtonListener(ActionListener aListener) {
		this.scrollL.addActionListener(aListener);
		this.scrollR.addActionListener(aListener);
		this.selection.addActionListener(aListener);
//		this.firstButton.addActionListener(aListener);
	}

	public void setImage(Icon icon) {
		if (counter == 1) {
			selectedImageOne.setIcon(icon);
			System.out.println("test");
		} else if (counter == 2) {
			selectedImageTwo.setIcon(icon);
		} else if (counter == 3) {
			selectedImageThree.setIcon(icon);
			next.setEnabled(true);
		}
		counter++;
	}
	
	public void setUserID() {
		String id = input.getText();
		System.out.println(id);
		userid = Integer.parseInt(id);
		System.out.println(userid);
	}

	public String getUserEntry() {
		return this.userRegistrationInputField.getText();
	}

	public void setUserEntry() {
		this.userRegistrationInputField.setText("");
	}

	public JButton returnL() {
		return this.scrollL;
	}

	public JButton returnR() {
		return this.scrollR;
	}
	
	public void showPrevious() {
		this.cl.previous(this.imagesPanel);
	}

	public void showNext() {
		this.cl.next(this.imagesPanel);
	}
	
	public JPanel imagePanelOne() {
		imageSetOne = new JPanel();
		Iterator<Map<String, String>> it = fnt.getImageSet().iterator();
		List<String> filePaths = new ArrayList<String>();
		while (it.hasNext()) {
			for (String s: it.next().values()) {
				filePaths.add(s);
			}
		}
		System.out.println(filePaths.get(0));
		firstButton = new JButton();
		secondButton = new JButton();
		thirdButton = new JButton();
		fourthButton = new JButton();
		firstButton.setIcon(new ImageIcon(filePaths.get(0)));
		secondButton.setIcon(new ImageIcon(filePaths.get(1)));
		thirdButton.setIcon(new ImageIcon(filePaths.get(2)));
		fourthButton.setIcon(new ImageIcon(filePaths.get(3)));
		imageSetOne.add(firstButton);
		imageSetOne.add(secondButton);
		imageSetOne.add(thirdButton);
		imageSetOne.add(fourthButton);
		return imageSetOne;
		
	}
	
	public JPanel imagePanelTwo() {
		return imageSetOne;
		
	}
	
	public JPanel imagePanelThree() {
		return imageSetOne;
		
	}

	public JButton returnFirst() {
		// TODO Auto-generated method stub
		return firstButton;
	}

	public JComboBox getSelection() {
		return selection;
	}

	public void setLogInAttempt() {
		int login = selection.getSelectedIndex();
//		System.out.println(selection.getSelectedItem());
		this.loginattempt = Integer.parseInt(choices[login]);
		System.out.println(loginattempt);
	}
};
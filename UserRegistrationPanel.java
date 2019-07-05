import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class UserRegistrationPanel extends JPanel {
	private Integer counter = 1;
	private JButton userRegistrationButton, left, right, one, two, three, back, next;
	private JPanel imagesPanel, imageSetOne, imageSetTwo, imageSetThree, selectedImages;
	private CardLayout cl;
	private final static String ONE = "one";
	private final static String TWO = "two";
	private final static String THREE = "three";
	private final static String FOUR = "four";
	private JButton scrollL, scrollR;
	JLabel iC3, selectedImageOne, selectedImageTwo, selectedImageThree, iC, iC2;
	MainWindow window;
	DBConnect db;
	User user;
	private LocalDateTime startTime;
	FileNames fnt;
	private JButton firstButton, secondButton, thirdButton, fourthButton;
//	private JTextField input;
//	private JComboBox selection;
//	private String[] choices;
	private int loginattempt;
	private int userid;
//	UserImagesFilePaths imageSet;
	private Random random;
//	private Printer p;
//	List<String> decoys = new ArrayList<String>();
	List<String> images = new ArrayList<String>();
	List<String> imageListOne, imageListTwo, imageListThree;
	private UserRegistrationModel model;
	private Object imageSet;
	
//	public UserRegistrationPanel(MainWindow w, FileNames fnt) {
	public UserRegistrationPanel(MainWindow w) {
		imageListOne = new ArrayList<String>();
		imageListTwo = new ArrayList<String>();
		imageListThree = new ArrayList<String>();
//		p = new Printer();
//		imageSet = new UserImagesFilePaths();
//		fnt =  new FileNames();
//		this.user = new User();
		window = w;
		this.setLayout(new BorderLayout());
		this.imagesPanel = new JPanel(new CardLayout());
//		imageSetOne = new JPanel();
//		imageSetTwo = new JPanel();
//		imageSetThree = new JPanel();
//		iC = new JLabel();
//		one = new JButton();
//		ImageIcon o = new ImageIcon("1.jpg");
//		one.setIcon(o);
//		iC.setIcon(o);
//		iC2 = new JLabel();
//		two = new JButton();
//		ImageIcon oo = new ImageIcon("2.jpg");
//		two.setIcon(oo);
//		iC2.setIcon(oo);
//		iC3 = new JLabel();
//		three = new JButton();
//		ImageIcon ooo = new ImageIcon("3.jpg");
//		three.setIcon(ooo);
//		iC3.setIcon(ooo);
//		iC3.addMouseListener(new Listener());
//		imageSetOne.add(iC);
//		imageSetTwo.add(iC2);
//		imageSetThree.add(iC3);
//		imageSetOne.add(one);
//		imageSetTwo.add(two);
//		imageSetThree.add(three);
		setUpTopPanel();
//		three.addActionListener(new Listener());
//		UserRegistrationImagePanel p = new UserRegistrationImagePanel(this);
//		UserRegistrationImagePanel j = new UserRegistrationImagePanel(this);
//		UserRegistrationImagePanel k = new UserRegistrationImagePanel(this);
//		UserRegistrationImagePanel imagePanelOne = new UserRegistrationImagePanel(this, fnt.getListOne());
//		UserRegistrationImagePanel imagePanelTwo = new UserRegistrationImagePanel(this, fnt.getListTwo());
//		UserRegistrationImagePanel imagePanelThree = new UserRegistrationImagePanel(this, fnt.getListThree());
//		UserRegistrationImagePanel imagePanelOne = new UserRegistrationImagePanel(this, fnt.getListOne());
//		UserRegistrationImagePanel imagePanelTwo = new UserRegistrationImagePanel(this, fnt.getListTwo());
//		UserRegistrationImagePanel imagePanelThree = new UserRegistrationImagePanel(this, fnt.getListThree());
//		this.imagesPanel.add(imagePanelOne, ONE);
//		this.imagesPanel.add(imagePanelTwo, TWO);
//		this.imagesPanel.add(imagePanelThree, THREE);
//		Set<String> x = new HashSet<String>();
//		List<String> x = new ArrayList<String>();
//		DBConnect db = new DBConnect();
//		x = db.getUsersFilePathsFromDatabase(Integer.parseInt(input.getText()), selection.getSelectedIndex());
//		x = db.getUsersFilePathsFromDatabase(22222, 1);
//		this.images.addAll(x);
//		UserLoginImagePanel u = new UserLoginImagePanel(this, this.images);
//		this.imagesPanel.add(u, FOUR);
//		UserRegistrationModel model = new UserRegistrationModel();
//		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(imagePanelOne, window, model);
//		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(imagePanelTwo, window, model);
//		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(imagePanelThree, window, model);
//		this.imagesPanel.add(imageSetTwo, TWO);
//		this.imagesPanel.add(imageSetThree, THREE);
//		this.imagesPanel.setVisible(true);
		startTime = LocalDateTime.now();
		this.add(imagesPanel, BorderLayout.CENTER);
		this.scrollL = new JButton("<<");
		this.scrollR = new JButton(">>");
		this.add(scrollL, BorderLayout.WEST);
		this.add(scrollR, BorderLayout.EAST);
		this.selectedImages = new JPanel();
		this.add(selectedImages, BorderLayout.SOUTH);
		this.back = new JButton("BACK");
		this.back.setActionCommand("BACK");
		this.selectedImages.add(back);
		this.selectedImageOne = new JLabel();
		this.selectedImageTwo = new JLabel();
		this.selectedImageThree = new JLabel();
		this.selectedImages.add(selectedImageOne);
		this.selectedImages.add(selectedImageTwo);
		this.selectedImages.add(selectedImageThree);
		this.next = new JButton("Next");
		this.next.setEnabled(false);
		this.next.setActionCommand("NEXT");
//		this.next.addActionListener(new Listener());
		selectedImages.add(this.next);
		this.cl = (CardLayout) (this.imagesPanel.getLayout());
	}


	private void setUpTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
//		input = new JTextField(10);
//		input.setText("Please enter your userID here");
//		choices = new String[3];
//		setChoices();
//		selection = new JComboBox(choices);
//		topPanel.add(input);
//		topPanel.add(selection);
		this.add(topPanel, BorderLayout.NORTH);
	}


////	
////	class Listener implements ActionListener {
////		
////		private Random random;
////
////		@Override
////		public void actionPerformed(ActionEvent arg0) {
//////			if (arg0.getSource() == three) {
//////				setImage(three.getIcon());
//////			} else if (arg0.getActionCommand().equals("BACK")) {
//////				window.showMainPage();
//////			}
//////			else
////				if (arg0.getSource() == next) {
////				db = new DBConnect();
//////				user.setUserid(userid);
//////				user.setLoginattempt(loginattempt);
//////				user.setPasswordOne(selectedImageOne.getIcon().toString());
//////				user.setPasswordTwo(selectedImageTwo.getIcon().toString());
//////				user.setPasswordThree(selectedImageThree.getIcon().toString());
////				setUserID();
//////				setLogInAttempt();
////				System.out.println(loginattempt);
//////				p.setSeenImages(fnt.getAllImages());
////
////				user = new User(userid, loginattempt, selectedImageOne.getIcon().toString(), selectedImageTwo.getIcon().toString(), selectedImageThree.getIcon().toString());
//////				createDecoySet(selection.getSelectedIndex());
//////				p.printToFile();
////				LocalDateTime endTime = LocalDateTime.now();
////				System.out.println(startTime);
////				System.out.println(endTime);
////				
//////				random = new Random();
//////				while(user.decoySize() < 17) {
//////					int n = random.nextInt(60);
//////					user.addImageToDecoySet(fnt.getAllImages().get(n).getImagePath());
//////				}
//////				user.printPaths();
////				db.addUserToDatabase(user);
////				Duration betweenStartandEnd = Duration.between(startTime, endTime);
////				long milliseconds = betweenStartandEnd.toMillis();
////				System.out.println(milliseconds);
//////				window.showMainPage(); 
////				window.showCompletePage();
////				refresh();
////			}
////
////		}
//
//		private void refresh() {
//			
//			
//		}
//	}
	
	
	/*
	public void createDecoySet(int option) {
		random = new Random();
		int n = 0;
		while(user.decoySize() < 17) {
			
			if (option == 1) {
				 n = random.nextInt(60);
			if (user.addImageToDecoySet(fnt.getAllImages().get(n).getImagePath())) 
			{
				decoys.add(fnt.getAllImages().get(n).getImagePath());		
			}
			
			
		} else if (option == 2) {
			 n = random.nextInt(85);
			user.addImageToDecoySet(fnt.getHidden().get(n).getImagePath());
			decoys.add(fnt.getHidden().get(n).getImagePath());
		}
		
		}
		user.printPaths();
		p.setHiddenImages(decoys);
		}*/
	
	public void adduserRegistrationButtonListener(ActionListener aListener) {
		this.scrollL.addActionListener(aListener);
		this.scrollR.addActionListener(aListener);
		this.next.addActionListener(aListener);
		this.back.addActionListener(aListener);
		
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
	}
	
	public void setImage(Icon filePath, int counter) {
		if (counter == 1) {
			selectedImageOne.setIcon(filePath);
			System.out.println("test");
		} else if (counter == 2) {
			selectedImageTwo.setIcon(filePath);
		} else if (counter == 3) {
			selectedImageThree.setIcon(filePath);
			next.setEnabled(true);
		}
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
	
//	public JPanel imagePanelOne() {
//		imageSetOne = new JPanel();
//		Iterator<Map<String, String>> it = fnt.getImageSet().iterator();
//		List<String> filePaths = new ArrayList<String>();
//		while (it.hasNext()) {
//			for (String s: it.next().values()) {
//				filePaths.add(s);
//			}
//		}
//		System.out.println(filePaths.get(0));
//		firstButton = new JButton();
//		secondButton = new JButton();
//		thirdButton = new JButton();
//		fourthButton = new JButton();
//		firstButton.setIcon(new ImageIcon(filePaths.get(0)));
//		secondButton.setIcon(new ImageIcon(filePaths.get(1)));
//		thirdButton.setIcon(new ImageIcon(filePaths.get(2)));
//		fourthButton.setIcon(new ImageIcon(filePaths.get(3)));
//		imageSetOne.add(firstButton);
//		imageSetOne.add(secondButton);
//		imageSetOne.add(thirdButton);
//		imageSetOne.add(fourthButton);
//		return imageSetOne;
//		
//	}
	
	public JPanel imagePanelTwo() {
		return imageSetOne;
		
	}
	
	public JPanel imagePanelThree() {
		return imageSetOne;
		
	}

	public JButton returnFirst() {
		return firstButton;
	}

	
	public void addToUserImages(String filePath) {
		((UserRegistrationImagePanel) this.imageSet).addImage(filePath);
	}

	

	public void setImageSetOne(List<String> listOne) {
		this.imageListOne = listOne;
	}

	public void setImageSetTwo(List<String> listTwo) {
		this.imageListTwo = listTwo;
		
	}

	public void setImageSetThree(List<String> listThree) {
		this.imageListThree = listThree;
		
	}

	public void setModel(UserRegistrationModel model) {
		this.model = model;
	}


	public void setUpImagePanels() {
		UserRegistrationImagePanel imagePanelOne = new UserRegistrationImagePanel(this, this.imageListOne);
		UserRegistrationImagePanel imagePanelTwo = new UserRegistrationImagePanel(this, this.imageListTwo);
		UserRegistrationImagePanel imagePanelThree = new UserRegistrationImagePanel(this, this.imageListThree);
		this.imagesPanel.add(imagePanelOne, ONE);
		this.imagesPanel.add(imagePanelTwo, TWO);
		this.imagesPanel.add(imagePanelThree, THREE);
		this.imagesPanel.setVisible(true);
		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(imagePanelOne, window, model);
		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(imagePanelTwo, window, model);
		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(imagePanelThree, window, model);
	}


	
};
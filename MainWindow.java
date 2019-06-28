import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener{
	private static final String MAIN = "main";
	private static final String REGISTRATION = "registration";
	private static final String LOGIN = "log-in";
	private static final String REGISTRATIONINSTRUCTIONS = "registration instructions";
	private static final String COMPLETE = "complete";
	private JFrame frame;
	private JLabel label, label2;
	private JButton loginButton, registerButton, userRegistrationButton;
	private JPanel mainPanel, registrationPanel, loginPanel, cardsPanel;
	private JTextField input;
	private JTextField userRegistrationInputField;
	private UserList ul;
	private DBConnect db;
	private JTextField userLoginInputField;
	private JButton userLoginButton;
	private CardLayout cl;
	User user;
	public MainWindow() {
		ul = new UserList();
	}

	public void setUp(Container container) {
		JPanel x = new JPanel();
		 label2 = new JLabel("Main Menu");
//		 x.setBackground(Color.WHITE);
		 label2.setBackground(Color.WHITE);
		x.add(label2);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		label = new JLabel("This is the label", SwingConstants.CENTER);
		/**
		 * left = new JButton("<<"); left.setIcon(new ImageIcon("1.jpg")); right = new
		 * JButton(">>"); // setImages(component); JPanel bottomPanel = new JPanel();
		 * firstImage = new JButton(); secondImage = new JButton(); thirdImage = new
		 * JButton(); bottomPanel.add(firstImage); bottomPanel.add(secondImage);
		 * bottomPanel.add(thirdImage); left.setMargin(new Insets(0, 0, 0, 0)); // to
		 * add a different background // button.setBackground( ... ); // to remove the
		 * border left.setBorder(null); panel.add(label, BorderLayout.NORTH);
		 * panel.add(left, BorderLayout.WEST); panel.add(right, BorderLayout.EAST);
		 * panel.add(bottomPanel, BorderLayout.SOUTH);
		 */
		mainPanel.add(label, BorderLayout.NORTH);
		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel thirdPanel = new JPanel();
		loginButton = new JButton(LOGIN);
		registerButton = new JButton(REGISTRATION);
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		thirdPanel.add(loginButton);
		thirdPanel.add(registerButton);
		secondPanel.add(thirdPanel, BorderLayout.CENTER);
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		registrationPanel = getRegistration();
		loginPanel = getLogin();
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(mainPanel, MAIN);
//		addUserToDB();
//		UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel(user, this);
//		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(userRegistrationPanel, this);
		UserInstructionPanel userInstructionPanel = new UserInstructionPanel();
		UserInstructionController userInstructionController = new UserInstructionController(userInstructionPanel, this);
		UserRegistrationCompletePanel urcp = new UserRegistrationCompletePanel();
		UserRegistrationCompleteController urcpc = new UserRegistrationCompleteController(urcp, this);
		
		cardsPanel.add(userInstructionPanel, REGISTRATIONINSTRUCTIONS);
//		cardsPanel.add(registrationPanel, REGISTRATION);
		cardsPanel.add(loginPanel, LOGIN);
		cardsPanel.add(urcp, COMPLETE);
//		cardsPanel.setVisible(false); // only show the next screen once a button is pressed
		
		container.add(x, BorderLayout.PAGE_START);
		container.add(cardsPanel);
		this.cl = (CardLayout) (cardsPanel.getLayout());
		cardsPanel.setVisible(true);
	}

	public JPanel getRegistration() {
		JPanel r = new JPanel();
		r.setLayout(new GridLayout(2,0));
		userRegistrationInputField = new JTextField(10);
		userRegistrationButton = new JButton("Register User");
		userRegistrationButton.addActionListener(this);
		r.add(userRegistrationInputField);
		r.add(userRegistrationButton);
		return r;
	}

	public JPanel getLogin() {
		JPanel l = new JPanel(new GridLayout(2,0));
		userLoginInputField = new JTextField(100);
		userLoginButton = new JButton("Log In");
		userLoginButton.addActionListener(this);
		l.add(userLoginInputField);
		l.add(userLoginButton);
		return l;
	}

	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(850, 1050));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MainWindow view = new MainWindow();
		view.setUp(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	public JPanel getRegistrationPanel() {
		JPanel registration = new JPanel(new GridLayout(2, 0));
		input = new JTextField("Input Name");
		userRegistrationButton = new JButton("Register");
		registration.add(input);
		registration.add(userRegistrationButton);
//		registration.setVisible(false);
//		changePanels(this.registration);
		return registration;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.db = new DBConnect();
		if (e.getSource() == registerButton) {
			setLabelText("Registration");
			System.out.println("Hello");
			cl.show(cardsPanel, REGISTRATIONINSTRUCTIONS);
		} else if (e.getSource() == loginButton) {
			setLabelText("Login");
			cl.show(cardsPanel, LOGIN);
			ul.printUsers();
		} else if (e.getSource() == userRegistrationButton) {
			String password = userRegistrationInputField.getText();
			int nextUserID = db.returnLatestAddedUserID();
			if (nextUserID < 1) {
				nextUserID = 0;
			}
			System.out.println(nextUserID);
			nextUserID++;
			System.out.println(nextUserID);
			this.user = new User(nextUserID, 1, "1", "1", "1");
			this.ul.addUser(user);
			db.addUserToDatabase(user);
			userRegistrationInputField.setText("");
		} else if (e.getSource() == userLoginButton) {
			String ID = null;
			String password = null;
			int UserID = 0;
			try {
				ID = userLoginInputField.getText();
				UserID = Integer.parseInt(ID);
				password = db.getPasswordFromDatabase(UserID);
				System.out.println(password);
				userLoginInputField.setText("");
			} catch (Exception f) {

			}
			if (password == "") {
				JOptionPane.showMessageDialog(frame, "Your UserID was not recognised. Please try again.", "Login error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				System.out.println("Welcome " + UserID + " Your're password is: " + password);
				cl.show(cardsPanel, MAIN);
			}
		}
	}

	private void setLabelText(String string) {
		this.label2.setText(string);
	}
	
	public void showMainPage() {
		cl.show(cardsPanel, MAIN);
	}

	public void showRegistrationPage() {
		UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel(this);
		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(userRegistrationPanel, this);
		cardsPanel.add(userRegistrationPanel, REGISTRATION);
		cl.show(cardsPanel, REGISTRATION);
	}
	
	public void showCompletePage() {
		cl.show(cardsPanel, COMPLETE);
	}
	public void addUserToDB() {
	db = new DBConnect();
	String password = userRegistrationInputField.getText();
	int nextUserID = db.returnLatestAddedUserID();
	if (nextUserID < 1) {
		nextUserID = 0;
	}
	System.out.println(nextUserID);
	nextUserID++;
	System.out.println(nextUserID);
	this.user = new User(nextUserID, 1, "1", "1", "1");
	this.ul.addUser(user);
//	db.addUserToDatabase(user);
	userRegistrationInputField.setText("");
	}
}
//}

//	public void changePanels(JPanel newPanel) {
//		frame.getContentPane().remove(this.main);
////		this.currentPanel = newPanel;
//		frame.getContentPane().add(newPanel);
//		frame.getContentPane().invalidate();
//		frame.getContentPane().validate();
//	}

//	public void addListener(ActionListener registerListener) {
//		this.register.addActionListener(registerListener);
//	}
//	
//	public void addUserRegistrationListener(ActionListener userRegistrationListener) {
//		this.userRegistrationButton.addActionListener(userRegistrationListener);
//	}
//	public void loggedInScreen() {
//		frame.getContentPane().removeAll();
////		frame.getContentPane().remove();
//		frame.getContentPane().add(this.main);
//		frame.getContentPane().invalidate();
//		frame.getContentPane().validate();
//		
//	}

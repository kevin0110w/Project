import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
	private static final String MAIN = "main";
	private static final String REGISTRATION = "Register";
	private static final String LOGIN = "Log-in";
	private static final String REGISTRATIONINSTRUCTIONS = "registration instructions";
	private static final String COMPLETE = "complete";
	private JFrame frame;
	private JLabel headerLabel;
	private JButton loginButton, registerButton;
	private JPanel mainPanel, registrationPanel, loginPanel, cardsPanel;
	private CardLayout cl;
//	private UserRegistrationModel model;
	private UserRegistrationModel model;
	private UserLoginModel loginModel;
	
	public MainWindow() {
	
	}

	public void setUp(Container container) {
		this.model = new UserRegistrationModel();
		JPanel aPanel = new JPanel();
		headerLabel = new JLabel("Main Menu");
		headerLabel.setBackground(Color.WHITE);
		aPanel.add(headerLabel);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
//		mainPanel.add(label, BorderLayout.NORTH);
		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel thirdPanel = new JPanel();
		loginButton = new JButton(LOGIN);
		loginButton.setActionCommand("LOGIN");
		registerButton = new JButton(REGISTRATION);
		registerButton.setActionCommand("REGISTER");
		thirdPanel.add(loginButton);
		thirdPanel.add(registerButton);
		secondPanel.add(thirdPanel, BorderLayout.CENTER);
		mainPanel.add(secondPanel, BorderLayout.CENTER);
//		registrationPanel = getRegistration();
//		UserLoginInstructionPanel loginPanel = new UserLoginInstructionPanel();
//		UserLoginController loginController = new UserLoginController(loginPanel, this);
		loginModel = new UserLoginModel();
		UserLoginCardsPanel loginPanel = new UserLoginCardsPanel(this);
		UserLoginController loginController = new UserLoginController(loginPanel, loginModel);
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(mainPanel, MAIN);
//		addUserToDB();
//		UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel(user, this);
//		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(userRegistrationPanel, this);
		createRegistrationInstructionPage();
		UserRegistrationCompletePanel userRegistrationCompletePanel = new UserRegistrationCompletePanel();
		UserRegistrationCompleteController urcpc = new UserRegistrationCompleteController(userRegistrationCompletePanel,
				this);

		
//		cardsPanel.add(registrationPanel, REGISTRATION);
		cardsPanel.add(loginPanel, LOGIN);
		cardsPanel.add(userRegistrationCompletePanel, COMPLETE);
//		cardsPanel.setVisible(false); // only show the next screen once a button is pressed

		container.add(aPanel, BorderLayout.PAGE_START);
		container.add(cardsPanel);
		this.cl = (CardLayout) (cardsPanel.getLayout());
		cardsPanel.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void run() {
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

	private void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(695, 695));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MainWindow mainWindowView = new MainWindow();
		mainWindowView.setUp(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		@SuppressWarnings("unused")
		MainWindowController mainWindowController = new MainWindowController(mainWindowView);
	}

	public void setLabelText(String string) {
		this.headerLabel.setText(string);
	}

	public void showMainPage() {
		this.headerLabel.setText("Main Menu");
		cl.show(cardsPanel, MAIN);
	}
	
	public void createRegistrationInstructionPage() {
		UserRegistrationInstructionPanel userInstructionPanel = new UserRegistrationInstructionPanel();
		UserRegistrationInstructionController userInstructionController = new UserRegistrationInstructionController(
				userInstructionPanel, this, model);
		cardsPanel.add(userInstructionPanel, REGISTRATIONINSTRUCTIONS);
	}
	public void createRegistrationImagePage() {
		UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel();
		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(
				userRegistrationPanel, this, model);
		userRegistrationPanel.setUpImagePanels();
		cardsPanel.add(userRegistrationPanel, REGISTRATION);
	}
	public void showRegistrationPage() {
		createRegistrationImagePage();
		cl.show(cardsPanel, REGISTRATION);
	}
	public void showLoginPage() {
		cl.show(cardsPanel, LOGIN);
	}

	public void showCompletePage() {
		cl.show(cardsPanel, COMPLETE);
	}

	public void addListeners(ActionListener listener) {
		this.registerButton.addActionListener(listener);
		this.loginButton.addActionListener(listener);

	}

	public void showRegistrationInstructions() {
		cl.show(cardsPanel, REGISTRATIONINSTRUCTIONS);
	}
	
	public void clearModel() {
		this.loginModel.clear();
	}
}
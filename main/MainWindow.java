package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
	private static final String MAIN = "main";
	private static final String REGISTRATION = "Register";
	private static final String LOGIN = "Log-in";
	private static final String REGISTRATIONINSTRUCTIONS = "Registration Instructions";
	private static final String COMPLETE = "complete";
	private static final String LOGINSUCCESSFUL = "Login Successful";
	private static final String LOGINUNSUCCESSFUL = "Login Unsuccessful";
	private static final String SELECTTHREE = "Select 3 Images";
	private static final String LOGININSTRUCTIONS = "Login Instructions";
	private static final String MAINMENU = "Main Menu";
	private static final String REGISTRATIONCOMPLETE = "Registration Complete";
	private JLabel headerLabel;
	private JButton loginButton, registerButton;
	private JPanel mainPanel, cardsPanel, aPanel;
	private CardLayout cl;
	private JFrame aFrame;
	private JPanel otherPanel;
	private JPanel thirdPanel;
//	private UserRegistrationModel registrationModel;
//	private UserLoginModel loginModel;
//	
	public MainWindow() {
		
	}

	public void setUp(Container container) {
		aPanel = new JPanel();
		headerLabel = new JLabel();
		setLabelText("Main Menu");
		aPanel.add(headerLabel);
		
		
		
		mainPanel = new JPanel(new BorderLayout());		
		thirdPanel = new JPanel();
		setWelcomeText();
		loginButton = new JButton(LOGIN);
		loginButton.setActionCommand("LOGIN");
		registerButton = new JButton(REGISTRATION);
		registerButton.setActionCommand("REGISTER");
		thirdPanel.add(loginButton);
		thirdPanel.add(registerButton);
		mainPanel.add(thirdPanel, BorderLayout.SOUTH);
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(mainPanel, MAIN);
		container.add(aPanel, BorderLayout.PAGE_START);
		container.add(cardsPanel);
		this.cl = (CardLayout) (cardsPanel.getLayout());
		this.cardsPanel.setVisible(true);
	}

	private void setWelcomeText() {
		JPanel textPanel = new JPanel();
		JTextArea textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setLineWrap(true);
		String text = "Welcome User\n\n";
		text += "New user? \nRegister an account\n\n";
		text += "Already a User? \nPlease login";
		textArea.setText(text);
		textArea.setEditable(false);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textPanel.add(textArea);
		mainPanel.add(textPanel, BorderLayout.CENTER);	
	}

	public void createCardLayout() {
		this.cl = (CardLayout) (cardsPanel.getLayout());
		this.cardsPanel.setVisible(true);
	}
//	private void createLoginPanel() {
//		this.loginModel = new UserLoginModel();
//		UserLoginCardsPanel loginPanel = new UserLoginCardsPanel();
////		UserLoginController loginController = new UserLoginController(this, loginPanel, loginModel);
//		UserLoginInstructionController UserLoginInstructionController  = new UserLoginInstructionController(this, loginPanel, loginModel);
//		
//		UserLoginCompleteController UserLoginCompleteController = new UserLoginCompleteController(this, loginPanel);
//		this.cardsPanel.add(loginPanel, LOGIN);
//	}
//	
//	private void createRegistrationPanel() {
//		this.registrationModel = new UserRegistrationModel();
//		UserRegistrationCardsPanel registrationPanel = new UserRegistrationCardsPanel(this);
//		UserRegistrationControllerNew registrationController = new UserRegistrationControllerNew(this, registrationPanel, registrationModel);
//		this.cardsPanel.add(registrationPanel, REGISTRATION);
////		UserRegistrationController userRegistrationController = new UserRegistrationController(registrationPanel, registrationModel);
//	}
	public void addPanel(UserRegistrationCardsPanel panel) {
		this.cardsPanel.add(panel, "REGISTRATION");
	}
	
	public JFrame getFrame() {
		return aFrame;
	}

	/**
	 * Create a show the gui
	 */
	public void createMainWindow() {
		aFrame = new JFrame();
		aFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		MainWindow mainWindowView = new MainWindow();
		setUp(aFrame.getContentPane());
		updateSmallFrameSize();
		aFrame.setVisible(true);
	}
	
	/**
	 * Method to set the main label text
	 * @param string
	 */
	public void setLabelText(String string) {
		Font aFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		this.headerLabel.setText(string);
		this.headerLabel.setFont(aFont);
	}

//	/**
//	 * Create the registration instruction panel and the associated controller
//	 */
//	public void createRegistrationInstructionPage() {
//		UserRegistrationInstructionPanel userInstructionPanel = new UserRegistrationInstructionPanel();
//		UserRegistrationInstructionController userInstructionController = new UserRegistrationInstructionController(
//				userInstructionPanel, this, this.registrationModel);
//		cardsPanel.add(userInstructionPanel, REGISTRATIONINSTRUCTIONS);
//	}
	
//	/**
//	 * Create the registration image page with the 60 images and the controller
//	 */
//	public void createRegistrationImagePage() {
//		UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel();
//		UserRegistrationController userRegistrationPanelController = new UserRegistrationController(
//				userRegistrationPanel, this, this.registrationModel);
//		userRegistrationPanel.setUpImagePanels();
//		cardsPanel.add(userRegistrationPanel, REGISTRATION);
//	}
	
//	private void createRegistrationCompletePage() {
//		UserRegistrationCompletePanel userRegistrationCompletePanel = new UserRegistrationCompletePanel();
//		UserRegistrationCompleteController urcpc = new UserRegistrationCompleteController(userRegistrationCompletePanel,
//				this);
//		this.cardsPanel.add(userRegistrationCompletePanel, COMPLETE);
//	}
	
	/**
	 * Show the home page
	 */
	public void showMainPage() {
		this.headerLabel.setText("Main Menu");
		cl.show(cardsPanel, MAIN);
	}
	
//	/**
//	 * Show the registration instructions panel
//	 */
//	public void showRegistrationInstructions() {
//		cl.show(cardsPanel, REGISTRATIONINSTRUCTIONS);
//	}
	
	/**
	 * show the registration page
	 */
	public void showRegistrationPage() {
		cl.show(cardsPanel, "REGISTRATION");
	}
	
	/**
	 * Show the login page
	 */
	public void showLoginPage() {
		cl.show(cardsPanel, "LOGIN");
	}

	/**
	 * show the user registration complete page
	 */
	public void showCompletePage() {
		cl.show(cardsPanel, COMPLETE);
	}
	
	public void setLabelLoginSuccessful() {
		this.headerLabel.setText(LOGINSUCCESSFUL);
	}
	
	public void setLabelLoginUnsuccessful() {
		this.headerLabel.setText(LOGINUNSUCCESSFUL);
	}
	
	public void setLabelSelectImages() {
		this.headerLabel.setText(SELECTTHREE);
	}
	
	public void setLabelRegistrationInstructions() {
		this.headerLabel.setText(REGISTRATIONINSTRUCTIONS);
	}
	
	public void setLabelLoginInstructions() {
		this.headerLabel.setText(LOGININSTRUCTIONS);
	}
	
	public void setLabelMainMenu() {
		this.headerLabel.setText(MAINMENU);
		
	}
	
	public void setLabelRegistrationComplete() {
		this.headerLabel.setText(REGISTRATIONCOMPLETE);
	}
	/**
	 * Add action listeners to the interactive buttons
	 * @param listener
	 */
	public void addListeners(ActionListener listener) {
		this.registerButton.addActionListener(listener);
		this.loginButton.addActionListener(listener);
	}

	public JPanel getCardsPanel() {
		return this.cardsPanel;
	}
	
	public void updateBigFrameSize() {
		aFrame.setPreferredSize(new Dimension(695, 695));		
		aFrame.revalidate();
		aFrame.repaint();
		aFrame.pack();
	}
	
	public void updateSmallFrameSize() {
		aFrame.setPreferredSize(new Dimension(300, 300));		
		aFrame.revalidate();
		aFrame.repaint();
		aFrame.pack();
	}


}
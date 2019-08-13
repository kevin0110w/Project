package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
	private static final String MAIN_MENU = "Main Menu";
	private static final String REGISTRATION = "Registration";
	private static final String LOGIN = "Log in";
	private static final String REGISTRATION_INSTRUCTIONS = "Registration Instructions";
	private static final String LOGIN_INSTRUCTIONS = "Login Instructions";
	private static final String LOGIN_SUCCESSFUL_MESSAGE = "Login Successful";
	private static final String LOGIN_UNSUCCESSFUL_MESSAGE = "Login Unsuccessful";
	private static final String SELECT_THREE_IMAGES = "Select 3 Images";
	private static final String REGISTRATION_COMPLETE = "Registration Complete";
	private JFrame aFrame;
	private JPanel mainPanel, cardsPanel, labelPanel, buttonPanel;
	private JLabel headerLabel;
	private JButton loginButton, registerButton;
	private CardLayout cardLayout;

	
	public MainWindow() {
//		run();
	}

	public void setUp(Container container) {
		labelPanel = new JPanel();
		headerLabel = new JLabel();
		setLabelFont();
		setLabelMainMenu();
		labelPanel.add(headerLabel);
		
		mainPanel = new JPanel(new BorderLayout());		
		buttonPanel = new JPanel();
		setWelcomeText();
		loginButton = new JButton(LOGIN);
		loginButton.setActionCommand("LOGIN");
		registerButton = new JButton(REGISTRATION);
		registerButton.setActionCommand("REGISTER");
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(mainPanel, MAIN_MENU);
		container.add(labelPanel, BorderLayout.PAGE_START);
		container.add(cardsPanel);
		this.cardLayout = (CardLayout) (cardsPanel.getLayout());
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
		this.cardLayout = (CardLayout) (cardsPanel.getLayout());
		this.cardsPanel.setVisible(true);
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
		setUp(aFrame.getContentPane());
		updateSmallFrameSize();
		aFrame.setVisible(true);
	}
	
	/**
	 * Method to set the main label text
	 * @param string
	 */
	public void setLabelFont() {
		Font aFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		this.headerLabel.setFont(aFont);
	}
	
	/**
	 * Show the home page
	 */
	public void showMainPage() {
		this.headerLabel.setText(MAIN_MENU);
		cardLayout.show(cardsPanel, MAIN_MENU);
	}
	
	/**
	 * show the registration page
	 */
	public void showRegistrationPage() {
		cardLayout.show(cardsPanel, REGISTRATION);
	}
	
	/**
	 * Show the login page
	 */
	public void showLoginPage() {
		this.headerLabel.setText(LOGIN_INSTRUCTIONS);
		cardLayout.show(cardsPanel, LOGIN);
	}
	
	public void setLabelLoginSuccessful() {
		this.headerLabel.setText(LOGIN_SUCCESSFUL_MESSAGE);
	}
	
	public void setLabelLoginUnsuccessful() {
		this.headerLabel.setText(LOGIN_UNSUCCESSFUL_MESSAGE);
	}
	
	public void setLabelSelectImages() {
		this.headerLabel.setText(SELECT_THREE_IMAGES);
	}
	
	public void setLabelRegistrationInstructions() {
		this.headerLabel.setText(REGISTRATION_INSTRUCTIONS);
	}
	
	public void setLabelLoginInstructions() {
		this.headerLabel.setText(LOGIN_INSTRUCTIONS);
	}
	
	public void setLabelMainMenu() {
		this.headerLabel.setText(MAIN_MENU);
		
	}
	
	public void setLabelRegistrationComplete() {
		this.headerLabel.setText(REGISTRATION_COMPLETE);
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
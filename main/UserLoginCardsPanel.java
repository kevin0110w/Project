package main;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import main.UserLoginController.UserSelectionListener;

/*
 * This class is responsible for containing the views for the instructions, image selection panel and complete panel in one card layout panel
 * and allow these views to be scrolled
 */
public class UserLoginCardsPanel extends JPanel {
	private CardLayout cardLayout;
	private MainWindow mainWindow;
	private UserLoginInstructionPanel loginInstructionPanel;
	private UserLoginSelectionPanel loginSelectionPanel;
	private UserLoginCompletePanel userLoginCompletePanel;
	private static final String LOGIN_INSTRUCTIONS ="LOGININSTRUCTIONS"; 
	private static final String LOGIN = "LOGIN";
	private static final String LOGIN_COMPLETE = "LOGINCOMPLETE";

	
	public UserLoginCardsPanel(MainWindow mw) {
		this.mainWindow = mw;
		this.setLayout(new CardLayout()); // make this view have a card layout view so that other image panels added to it can be scrolled
		setupCards();
	}
	
	/**
	 * Show the login page
	 */
	public void showLoginPanel() {
		this.cardLayout.show(this, LOGIN);
	}

	/**
	 * Show the login complete page
	 */
	public void showLoginCompletePage() {	
		this.cardLayout.show(this, LOGIN_COMPLETE);
	}
	
	/**
	 * Show the main page
	 */
	public MainWindow getMainPage() {
		return this.mainWindow;
	}
	
	/**
	 * Get the login method selection from the login instruction panel
	 * @return an index
	 */
	public int getLoginSelection() {
		return this.getLoginInstructionPanel().getSelection();
	}

	/**
	 * Get the text input from the login instruction panel
	 * @return string user id
	 */
	public String getUseridInput() {
		return this.getLoginInstructionPanel().getInput();
	}
	
	/**
	 * Get the picture set selection from the instruction panel drop down box
	 * @return an index
	 */
	public int getPictureSelection() {
		return this.getLoginInstructionPanel().getPictureSelection();
	}

	/**
	 * Set the buttons with action listeners in the instruction panel
	 * @param listener
	 */
	public void setLoginInstructionPanelListeners(ActionListener listener) {
		this.loginInstructionPanel.addUserLoginListener(listener);
	}
	
	/**
	 * Set the mouse listeners to the text field in the instruction panel
	 * @param l
	 */
	public void setLoginInstructionMouseListener(MouseListener l) {
		this.getLoginInstructionPanel().addUserLoginMouseListener(l);
	}
	
//	/**
//	 * set the action listeners in the login selection panel
//	 * @param listener
//	 */
//	public void setLoginSelectionPanel(ActionListener listener) {
//		this.loginSelectionPanel.addListener(listener);	
//	}

	/**
	 * call the method that will select a chosen image to the panel in the selection panel
	 * @param source
	 */
	public void setImage(Object source) {
		this.getLoginSelectionPanel().setImage(source);
	}

	/**
	 * Show the login instruction page
	 */
	public void showInstructionPanel() {
		this.cardLayout.show(this, LOGIN_INSTRUCTIONS);
	}

	/**
	 * Set the action listeners in the log complete page
	 * @param userLoginSuccessListener
	 */
	public void setLoginSuccessPanelListeners(ActionListener userLoginSuccessListener) {
		this.getUserLoginSuccessPanel().addListeners(userLoginSuccessListener);
	}

	/**
	 * Set up the cards and show the login instructions panel
	 */
	public void setupCards() {
		this.setLoginInstructionPanel(new UserLoginInstructionPanel());
		this.setLoginSelectionPanel(new UserLoginSelectionPanel());
		this.setUserLoginCompletePanel(new UserLoginCompletePanel());
		this.add(getLoginInstructionPanel(), LOGIN_INSTRUCTIONS);
		this.add(getLoginSelectionPanel(), LOGIN);
		this.add(getUserLoginSuccessPanel(), LOGIN_COMPLETE);
		this.cardLayout = (CardLayout) this.getLayout();
		this.setVisible(true);
		this.cardLayout.show(this, LOGIN_INSTRUCTIONS);	
	}

	/**
	 * Reset the login instruction panel view
	 */
	public void clearInstructionPanel() {
		this.getLoginInstructionPanel().clear();
	}

	/**
	 * Reset the login selection panel
	 */
	public void clearSelectionPanel() {
		this.getLoginSelectionPanel().clear();
	}

	public UserLoginSelectionPanel getLoginSelectionPanel() {
		return loginSelectionPanel;
	}

	public void setLoginSelectionPanel(UserLoginSelectionPanel loginSelectionPanel) {
		this.loginSelectionPanel = loginSelectionPanel;
	}

	public UserLoginInstructionPanel getLoginInstructionPanel() {
		return loginInstructionPanel;
	}

	public void setLoginInstructionPanel(UserLoginInstructionPanel loginInstructionPanel) {
		this.loginInstructionPanel = loginInstructionPanel;
	}

	public UserLoginCompletePanel getUserLoginSuccessPanel() {
		return userLoginCompletePanel;
	}

	public void setUserLoginCompletePanel(UserLoginCompletePanel userLoginCompletePanel) {
		this.userLoginCompletePanel = userLoginCompletePanel;
	}

	public void addLoginSelectionMouseListener(MouseListener userSelectionListener) {
		loginSelectionPanel.addMouseListeners(userSelectionListener);
		
	}

	public void addUserSelectionListener(ActionListener userSelectionListener) {
		loginSelectionPanel.addUserSelectionListener(userSelectionListener);
		
	}

	public void setLoginCompletePanelText(String text) {
		userLoginCompletePanel.setTextArea(text);	
	}
	
	public UserLoginSelectionPanel getUserLoginSelectionPanel() {
		return this.loginSelectionPanel;
	}
}


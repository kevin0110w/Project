package main;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * This class is responsible for holding the views for the instructions, image selection panel and complete panel in one card layout panel
 * and allow these views to be scrolled
 */
public class UserLoginCardsPanel extends JPanel {
	private CardLayout cardLayout;
	private UserLoginInstructionPanel loginInstructionPanel;
	private UserLoginSelectionPanel loginSelectionPanel;
	private UserLoginCompletePanel userLoginCompletePanel;
	private static final String LOGIN_INSTRUCTIONS ="LOGININSTRUCTIONS"; 
	private static final String LOGIN = "LOGIN";
	private static final String LOGIN_COMPLETE = "LOGINCOMPLETE";

	
	public UserLoginCardsPanel() {
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
	 * Get the login method selection from the login instruction panel
	 * @return an index
	 */
	public int getLoginSelection() {
		return this.getLoginInstructionPanel().getLoginSelection();
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

	/**
	 * call the method that will select a chosen image to the panel in the selection panel
	 * @param source
	 */
	public void setImage(String filePath) {
		this.getLoginSelectionPanel().setImage(filePath);
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
		this.userLoginCompletePanel.addListeners(userLoginSuccessListener);
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
		this.add(getUserLoginCompletePanel(), LOGIN_COMPLETE);
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

	/**
	 * @Return the login selection panel - i.e. the panel with the 20 clickable images
	 */
	public UserLoginSelectionPanel getLoginSelectionPanel() {
		return loginSelectionPanel;
	}

	/**
	 * Set the login selection panel
	 * @param loginSelectionPanel
	 */
	public void setLoginSelectionPanel(UserLoginSelectionPanel loginSelectionPanel) {
		this.loginSelectionPanel = loginSelectionPanel;
	}

	/**
	 * @return the login instruction panel
	 */
	public UserLoginInstructionPanel getLoginInstructionPanel() {
		return loginInstructionPanel;
	}

	/**
	 * Set the login instruction panel
	 * @param loginInstructionPanel
	 */
	public void setLoginInstructionPanel(UserLoginInstructionPanel loginInstructionPanel) {
		this.loginInstructionPanel = loginInstructionPanel;
	}

	/**
	 * @return the login complete panel 
	 */
	public UserLoginCompletePanel getUserLoginCompletePanel() {
		return userLoginCompletePanel;
	}

	/**
	 * Set the user login complete panel
	 * @param userLoginCompletePanel
	 */
	public void setUserLoginCompletePanel(UserLoginCompletePanel userLoginCompletePanel) {
		this.userLoginCompletePanel = userLoginCompletePanel;
	}

	/**
	 * Add listeners to the interactive buttons in the login selection panel
	 * @param userSelectionListener
	 */
	public void addUserSelectionListener(ActionListener userSelectionListener) {
		loginSelectionPanel.addUserSelectionListener(userSelectionListener);
		
	}

	/**
	 * Set the text in the login complete panel detailing whether a login attempt was successful or unsuccessful
	 * @param text
	 */
	public void setLoginCompletePanelText(String text) {
		userLoginCompletePanel.setTextArea(text);	
	}
	
	/**
	 * Get the user selection panel
	 * @return
	 */
	public UserLoginSelectionPanel getUserLoginSelectionPanel() {
		return this.loginSelectionPanel;
	}
}


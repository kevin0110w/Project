import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*
 * This class is responsible for containing the views for the instructions, image selection panel and complete panel in one card layout panel
 * and allow these views to be scrolled
 */
public class UserLoginCardsPanel extends JPanel {
	private CardLayout cl;
	private MainWindow mw;
	private UserLoginInstructionPanel loginInstructionPanel;
	private UserLoginSelectionPanel loginSelectionPanel;
	private UserLoginSuccessPanel userLoginSuccessPanel;
	private static final String LOGININSTRUCTIONS ="LOGININSTRUCTIONS", LOGIN = "LOGIN", LOGINCOMPLETE = "LOGINCOMPLETE";

	
	public UserLoginCardsPanel(MainWindow mw) {
		this.setMainWindow(mw);
		this.setLayout(new CardLayout()); // make this view have a card layout view so that other image panels added to it can be scrolled
		setupCards();
	}
	
	/**
	 * Show the login page
	 */
	public void loginPanel() {
		this.cl.show(this, LOGIN);
	}

	/**
	 * Show the login complete page
	 */
	public void showLoginCompletePage() {	
		this.cl.show(this, LOGINCOMPLETE);
	}
	
	/**
	 * Show the main page
	 */
	public void showMainPage() {
		this.getMainWindow().showMainPage();
	}
	
	/**
	 * Get the login method selection from the login instruction panel
	 * @return an index
	 */
	public int getSelection() {
		return this.getLoginInstructionPanel().getSelection();
	}

	/**
	 * Get the text input from the login instruction panel
	 * @return string user id
	 */
	public String getInput() {
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
	public void setLoginInstructionPanel(ActionListener listener) {
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
	 * set the action listeners in the login selection panel
	 * @param listener
	 */
	public void setLoginSelectionPanel(ActionListener listener) {
		this.loginSelectionPanel.addListener(listener);	
	}

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
		this.cl.show(this, LOGININSTRUCTIONS);
	}

	/**
	 * Set the action listeners in the log complete page
	 * @param userLoginSuccessListener
	 */
	public void setLoginSuccessPanel(ActionListener userLoginSuccessListener) {
		this.getUserLoginSuccessPanel().addListeners(userLoginSuccessListener);
	}

	/**
	 * Set up the cards and show the login instructions panel
	 */
	public void setupCards() {
		this.setLoginInstructionPanel(new UserLoginInstructionPanel());
		this.setLoginSelectionPanel(new UserLoginSelectionPanel());
		this.setUserLoginSuccessPanel(new UserLoginSuccessPanel());
		this.add(getLoginInstructionPanel(), LOGININSTRUCTIONS);
		this.add(getLoginSelectionPanel(), LOGIN);
		this.add(getUserLoginSuccessPanel(), LOGINCOMPLETE);
		this.cl = (CardLayout) this.getLayout();
		this.setVisible(true);
		this.cl.show(this, LOGININSTRUCTIONS);	
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

	public MainWindow getMainWindow() {
		return mw;
	}

	public void setMainWindow(MainWindow mw) {
		this.mw = mw;
	}

	public UserLoginInstructionPanel getLoginInstructionPanel() {
		return loginInstructionPanel;
	}

	public void setLoginInstructionPanel(UserLoginInstructionPanel loginInstructionPanel) {
		this.loginInstructionPanel = loginInstructionPanel;
	}

	public UserLoginSuccessPanel getUserLoginSuccessPanel() {
		return userLoginSuccessPanel;
	}

	public void setUserLoginSuccessPanel(UserLoginSuccessPanel userLoginSuccessPanel) {
		this.userLoginSuccessPanel = userLoginSuccessPanel;
	}
}


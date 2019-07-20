package main;
import java.awt.CardLayout;

import javax.swing.JPanel;
/**
 * This class is responsible for "holding" the other registration views and allow them to be scrolled back and forth
 *
 */
public class UserRegistrationCardsPanel extends JPanel {
	private MainWindow theMainWindow;
	private UserRegistrationInstructionPanel userRegistrationInstructionPanel;
	private UserRegistrationCompletePanel userRegistrationCompletePanel;
	private UserRegistrationPanel userRegistrationPanel;
	private CardLayout cl;
	private static final String INSTRUCTIONS = "INSTRUCTION", REGISTRATION = "REGISTRATION", COMPLETE = "COMPLETE";
	
	public UserRegistrationCardsPanel(MainWindow mw) {
		this.theMainWindow = mw;
		this.setLayout(new CardLayout()); // make this view have a card layout view so that other image panels added to it can be scrolled
		setupCards();
	}

	private void setupCards() {
		this.setUserRegistrationInstructionPanel(new UserRegistrationInstructionPanel());
		this.setUserRegistrationPanel(new UserRegistrationPanel());
		this.setUserRegistrationCompletePanel(new UserRegistrationCompletePanel());
		this.add(getUserRegistrationInstructionPanel(), INSTRUCTIONS);
		this.add(getUserRegistrationPanel(), REGISTRATION);
		this.add(getUserRegistrationCompletePanel(), COMPLETE);
		this.cl = (CardLayout) this.getLayout();
		this.setVisible(true);
		this.cl.show(this, INSTRUCTIONS);	
	}

	/**
	 * @return the userRegistrationInstructionPanel
	 */
	public UserRegistrationInstructionPanel getUserRegistrationInstructionPanel() {
		return userRegistrationInstructionPanel;
	}

	/**
	 * @param userRegistrationInstructionPanel the userRegistrationInstructionPanel to set
	 */
	public void setUserRegistrationInstructionPanel(UserRegistrationInstructionPanel userRegistrationInstructionPanel) {
		this.userRegistrationInstructionPanel = userRegistrationInstructionPanel;
	}

	/**
	 * @return the userRegistrationCompletePanel
	 */
	public UserRegistrationCompletePanel getUserRegistrationCompletePanel() {
		return userRegistrationCompletePanel;
	}

	/**
	 * @param userRegistrationCompletePanel the userRegistrationCompletePanel to set
	 */
	public void setUserRegistrationCompletePanel(UserRegistrationCompletePanel userRegistrationCompletePanel) {
		this.userRegistrationCompletePanel = userRegistrationCompletePanel;
	}

	/**
	 * @return the userRegistrationPanel
	 */
	public UserRegistrationPanel getUserRegistrationPanel() {
		return userRegistrationPanel;
	}

	/**
	 * @param userRegistrationPanel the userRegistrationPanel to set
	 */
	public void setUserRegistrationPanel(UserRegistrationPanel userRegistrationPanel) {
		this.userRegistrationPanel = userRegistrationPanel;
	}

	/**
	 * @return the cl
	 */
	public CardLayout getCl() {
		return cl;
	}

	/**
	 * @param cl the cl to set
	 */
	public void setCl(CardLayout cl) {
		this.cl = cl;
	}
	
	public void showInstructionPanel() {
		this.cl.show(this, INSTRUCTIONS);
	}
	
	public void showRegistrationPanel() {
		this.cl.show(this, REGISTRATION);
	}
	
	public void showCompletePanel() {
		this.cl.show(this, COMPLETE);
	}
	
	/**
	 * @return the mw
	 */
	public MainWindow getMw() {
		return theMainWindow;
	}

	/**
	 * @param mw the mw to set
	 */
	public void setMw(MainWindow mw) {
		this.theMainWindow = mw;
	}

}

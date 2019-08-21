package main;
import java.awt.CardLayout;

import javax.swing.JPanel;
/**
 * This class is responsible for "holding" the other registration views and allow them to be scrolled back and forth
 *
 */
public class UserRegistrationCardsPanel extends JPanel {
	private UserRegistrationInstructionPanel userRegistrationInstructionPanel;
	private UserRegistrationCompletePanel userRegistrationCompletePanel;
	private UserRegistrationPanel userRegistrationPanel;
	private CardLayout cardLayout;
	private static final String INSTRUCTIONS = "Instructions"; 
	private static final String REGISTRATION = "Registration";
	private static final String COMPLETE = "Complete";
	
	public UserRegistrationCardsPanel() {
		this.setLayout(new CardLayout()); // make this view have a card layout view so that other image panels added to it can be scrolled
		setupCards();
	}

	/**
	 * Set up this panel to contain an instruction panel, a registration panel that'll contain three different image panels and a complete panel.
	 */
	private void setupCards() {
		this.setUserRegistrationInstructionPanel(new UserRegistrationInstructionPanel());
		this.setUserRegistrationPanel(new UserRegistrationPanel());
		this.setUserRegistrationCompletePanel(new UserRegistrationCompletePanel());
		this.add(getUserRegistrationInstructionPanel(), INSTRUCTIONS);
		this.add(getUserRegistrationPanel(), REGISTRATION);
		this.add(getUserRegistrationCompletePanel(), COMPLETE);
		this.cardLayout = (CardLayout) this.getLayout();
		this.setVisible(true);
		this.cardLayout.show(this, INSTRUCTIONS);	
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
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	/**
	 * @param cl the cl to set
	 */
	public void setCardLayout(CardLayout cl) {
		this.cardLayout = cl;
	}
	
	public void showInstructionPanel() {
		this.cardLayout.show(this, INSTRUCTIONS);
	}
	
	public void showRegistrationPanel() {
		this.cardLayout.show(this, REGISTRATION);
	}
	
	public void showCompletePanel() {
		this.cardLayout.show(this, COMPLETE);
	}
}

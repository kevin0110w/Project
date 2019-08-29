package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		MainWindow mw = new MainWindow();
		main.run(mw);
		main.createLoginPanel(mw);
		main.createRegistrationPanel(mw);
		MainWindowController mainWindowController = new MainWindowController(mw);	
	}
	
	public void createLoginPanel(MainWindow mw) {
		UserLoginModel loginModel = new UserLoginModel();
		UserLoginCardsPanel loginPanel = new UserLoginCardsPanel();
		UserLoginInstructionController UserLoginInstructionController  = new UserLoginInstructionController(mw, loginPanel, loginModel);
		UserLoginCompleteController UserLoginCompleteController = new UserLoginCompleteController(mw, loginPanel);
		mw.getCardsPanel().add(loginPanel, "Log in");
	}
	
	public void createRegistrationPanel(MainWindow mw) {
		UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
		UserRegistrationCardsPanel registrationPanel = new UserRegistrationCardsPanel();
		UserRegistrationInstructionController registrationController = new UserRegistrationInstructionController(mw, registrationPanel, userRegistrationModel);
		UserRegistrationCompleteController userRegistrationCompleteController = new UserRegistrationCompleteController(registrationPanel, mw);
		mw.getCardsPanel().add(registrationPanel, "Registration");
	}
	
	public void run(MainWindow mw) {
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
				mw.createMainWindow();
			}
		});
	}
}

package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for co'ordinating the user's interaction with the main Window screen view
 *
 */
public class MainWindowController {
	private MainWindow mainWindow;

	public MainWindowController(MainWindow mainwindow) {
		this.mainWindow = mainwindow;
		this.mainWindow.addListeners(new MainWindowListener()); // add action listeners to the buttons in the view
	}

	
	class MainWindowListener implements ActionListener {
		/**
		 * This method will set the label with the correct text and show the relevant panel depending on the button pressed.
		 */
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "REGISTER":
				mainWindow.showRegistrationPage();
				mainWindow.updateBigFrameSize();
				break;
			case "LOGIN":
				mainWindow.showLoginPage();
				mainWindow.updateBigFrameSize();
				break;
			}
		}
	}
}

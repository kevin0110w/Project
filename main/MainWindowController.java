package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for listening to the jbuttons in the mainwindow view firing.
 *
 */
public class MainWindowController {
	private MainWindow mainWindow;

	public MainWindowController(MainWindow mainwindow) {
		this.mainWindow = mainwindow;
		this.mainWindow.addListeners(new MainWindowListener()); // add action listeners to the buttons in the view
	}

	class MainWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "REGISTER":
				mainWindow.setLabelText("Registration"); 
				mainWindow.showRegistrationPage();
				mainWindow.updateBigFrameSize();
				mainWindow.setLabelRegistrationInstructions();
				break;
			case "LOGIN":
				mainWindow.setLabelText("Login");
				mainWindow.showLoginPage(); // show login page if user clicks login
				mainWindow.updateBigFrameSize();
				mainWindow.setLabelLoginInstructions();
				break;
			}
		}
	}
}

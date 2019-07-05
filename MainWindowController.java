import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowController {
	private MainWindow mainWindow;

	public MainWindowController(MainWindow mainwindow) {
		this.mainWindow = mainwindow;
		this.mainWindow.addListeners(new MainWindowListener());
	}

	class MainWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "REGISTER":
				mainWindow.setLabelText("Registration");
				mainWindow.showRegistrationInstructions();
				break;
			case "LOGIN":
				mainWindow.setLabelText("Login");
				mainWindow.showLoginPage();
				break;
			}
		}
	}
}

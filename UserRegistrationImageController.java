import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class UserRegistrationImageController {
	private UserRegistrationImagePanel panel;
	private MainWindow window;
	private UserRegistrationModel model;
	
	public UserRegistrationImageController(UserRegistrationImagePanel panel, MainWindow window, UserRegistrationModel model) {
		this.panel = panel;
		this.window = window;
		this.model = model;
		this.panel.addListener(new UserRegistrationImageListener());
	}
	
	class UserRegistrationImageListener implements ActionListener{
		int n = 1;
		String filePath;
		Icon icon;
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "" + 0:
			case "" + 1:
			case "" + 2:
			case "" + 3:
			case "" + 4:
			case "" + 5:
			case "" + 6:
			case "" + 7:
			case "" + 8:
			case "" + 9:
			case "" + 10:
			case "" + 11:
			case "" + 12:
			case "" + 13:
			case "" + 14:
			case "" + 15:
			case "" + 16:
			case "" + 17:
			case "" + 18:
			case "" + 19:
			case "" + 20:
				icon = ((JButton) e.getSource()).getIcon();
				filePath = icon.toString();
				if (n == 1) {
					model.setFirstSelectedImageFilePath(filePath);
				} else if (n == 2) {
					model.setSecondSelectedImageFilePath(filePath);
				} else if (n == 3) {
					model.setThirdSelectedImageFilePath(filePath);
				}
				panel.setImage(icon, n);
				model.addTimeTaken();
				n++;
//				panel.printString(e.getSource());
//				panel.setImage(e.getSource());
//				panel.addImage(e.getSource());
				break;
			}
		}
	}
}
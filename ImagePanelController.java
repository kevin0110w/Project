import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImagePanelController {
	private UserRegistrationImagePanel panel;
	
	public ImagePanelController(UserRegistrationImagePanel p) {
		this.panel = p;
		this.panel.addListener(new ImagePanelListener());
	}
	
	class ImagePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(arg0.getSource());
		}
	}
}

package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserLoginControllerT {
	
	private UserLoginImagePanel panel;
	public UserLoginControllerT(UserLoginImagePanel panel) {
		this.panel = panel;
	}
	
	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (arg0.getActionCommand()) {
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
				System.out.println(arg0.getSource());
			case "NEXT":
				panel.LogIn();
			case "Selection:"
				panel.setSelection();
			
			}
			
		}
		
	}

}

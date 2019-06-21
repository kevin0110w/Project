import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View_2 extends JFrame {
	private JFrame frame;
	private JLabel label;
	private JButton left, right, firstImage, secondImage, thirdImage;
	private JButton login;
	private JButton register;
	private JPanel main, registration;
	private JButton userRegistrationButton;
	private JTextField input;
	private JPanel currentPanel;
	
	public View_2() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(1500, 1500));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUp(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
/*
	@Override
	public void run() {
		
	}
*/
	public void setUp(Container component) {
		main = new JPanel();
		main.setLayout(new BorderLayout());
		label = new JLabel("This is the label", SwingConstants.CENTER);
		/**
		 * left = new JButton("<<"); left.setIcon(new ImageIcon("1.jpg")); right = new
		 * JButton(">>"); // setImages(component); JPanel bottomPanel = new JPanel();
		 * firstImage = new JButton(); secondImage = new JButton(); thirdImage = new
		 * JButton(); bottomPanel.add(firstImage); bottomPanel.add(secondImage);
		 * bottomPanel.add(thirdImage); left.setMargin(new Insets(0, 0, 0, 0)); // to
		 * add a different background // button.setBackground( ... ); // to remove the
		 * border left.setBorder(null); panel.add(label, BorderLayout.NORTH);
		 * panel.add(left, BorderLayout.WEST); panel.add(right, BorderLayout.EAST);
		 * panel.add(bottomPanel, BorderLayout.SOUTH);
		 */
		main.add(label, BorderLayout.NORTH);
		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel thirdPanel = new JPanel(new GridLayout(2,0));
		login = new JButton("Log In");
		register = new JButton("Register Here");
		thirdPanel.add(login);
		thirdPanel.add(register);
		secondPanel.add(thirdPanel, BorderLayout.CENTER);
		main.add(secondPanel, BorderLayout.CENTER);
		component.add(main);
	}

	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		View_2 gui = new View_2();
		RegisterController rc = new RegisterController(gui);
		UserRegistrationController urc = new UserRegistrationController(gui);
//		SwingUtilities.invokeLater(gui);
//		gui.setVisible(true);
	}

	public void registerScreen() {
		this.registration = new JPanel(new GridLayout(2,0));
		input = new JTextField("Input Name");
		userRegistrationButton = new JButton("Register");
		registration.add(input);
		registration.add(userRegistrationButton);
//		registration.setVisible(false);
		changePanels(this.registration);
	}
	
	public void changePanels(JPanel newPanel) {
		frame.getContentPane().remove(this.main);
//		this.currentPanel = newPanel;
		frame.getContentPane().add(newPanel);
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
	}

	public void addListener(ActionListener registerListener) {
		this.register.addActionListener(registerListener);
	}
	
	public void addUserRegistrationListener(ActionListener userRegistrationListener) {
		this.userRegistrationButton.addActionListener(userRegistrationListener);
	}
	public void loggedInScreen() {
		frame.getContentPane().removeAll();
//		frame.getContentPane().remove();
		frame.getContentPane().add(this.main);
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
		
	}
}
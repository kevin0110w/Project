import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationPanel extends JPanel {
	private JButton back, next, scrollL, scrollR;
	private JPanel imagesCardPanel, imageSetOne, bottomPanel;
	private CardLayout cl;
	private final static String ONE = "one";
	private final static String TWO = "two";
	private final static String THREE = "three";
	private JLabel selectedImageOne, selectedImageTwo, selectedImageThree;
//	private Printer p;
	List<String> imageListOne, imageListTwo, imageListThree;
	private UserRegistrationModel model;

	public UserRegistrationPanel() {
		imageListOne = new ArrayList<String>();
		imageListTwo = new ArrayList<String>();
		imageListThree = new ArrayList<String>();
		this.setLayout(new BorderLayout());
		this.imagesCardPanel = new JPanel(new CardLayout());

		this.add(imagesCardPanel, BorderLayout.CENTER);
		
		this.bottomPanel = new JPanel(new GridLayout(2,0));
		
		setupSelectedImagePanel();
		setupButtons();
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.cl = (CardLayout) (this.imagesCardPanel.getLayout());
	}

	private void setupButtons() {
		JPanel buttonPanel = new JPanel();
		this.back = new JButton("BACK");
		this.back.setActionCommand("BACK");
		this.scrollL = new JButton("Scroll Left");
		this.scrollL.setActionCommand("SCROLLLEFT");
		this.scrollR = new JButton("Scroll Right");
		this.scrollR.setActionCommand("SCROLLRIGHT");
		this.bottomPanel.add(this.scrollL);
		this.bottomPanel.add(this.scrollR);
		this.next = new JButton("Next");
		this.next.setEnabled(false);
		this.next.setActionCommand("NEXT");
		buttonPanel.add(this.back);
		buttonPanel.add(this.scrollL);
		buttonPanel.add(this.scrollR);
		buttonPanel.add(this.next);
		this.bottomPanel.add(buttonPanel);
	}

	private void setupSelectedImagePanel() {
		JPanel imagePanel = new JPanel();

		this.selectedImageOne = new JLabel();
		this.selectedImageOne.setMinimumSize(new Dimension(90, 90));
		this.selectedImageOne.setPreferredSize(new Dimension(90, 90));
		this.selectedImageOne.setMaximumSize(new Dimension(90, 90));
		
		this.selectedImageTwo = new JLabel();
		this.selectedImageTwo.setMinimumSize(new Dimension(90, 90));
		this.selectedImageTwo.setPreferredSize(new Dimension(90, 90));
		this.selectedImageTwo.setMaximumSize(new Dimension(90, 90));
		
		this.selectedImageThree = new JLabel();
		this.selectedImageThree.setMinimumSize(new Dimension(90, 90));
		this.selectedImageThree.setPreferredSize(new Dimension(90, 90));
		this.selectedImageThree.setMaximumSize(new Dimension(90, 90));
		
		imagePanel.add(selectedImageOne);
		imagePanel.add(selectedImageTwo);
		imagePanel.add(selectedImageThree);

		this.bottomPanel.add(imagePanel);
	}

	public void adduserRegistrationButtonListener(ActionListener aListener) {
		this.scrollL.addActionListener(aListener);
		this.scrollR.addActionListener(aListener);
		this.next.addActionListener(aListener);
		this.back.addActionListener(aListener);
	}

	public void setImage(Icon filePath, int counter) {
		if (counter == 1) {
			selectedImageOne.setIcon(filePath);
		} else if (counter == 2) {
			selectedImageTwo.setIcon(filePath);
		} else if (counter == 3) {
			selectedImageThree.setIcon(filePath);
			next.setEnabled(true);
		}
	}


	public void showPrevious() {
		this.cl.previous(this.imagesCardPanel);
	}

	public void showNext() {
		this.cl.next(this.imagesCardPanel);
	}

	public JPanel imagePanelTwo() {
		return imageSetOne;

	}

	public JPanel imagePanelThree() {
		return imageSetOne;

	}

	public void setImageSetOne(List<String> listOne) {
		this.imageListOne = listOne;
	}

	public void setImageSetTwo(List<String> listTwo) {
		this.imageListTwo = listTwo;

	}

	public void setImageSetThree(List<String> listThree) {
		this.imageListThree = listThree;

	}

	public void setModel(UserRegistrationModel model) {
		this.model = model;
	}


	public void setUpImagePanels() {
		UserRegistrationImagePanel imagePanelOne = new UserRegistrationImagePanel(this, this.imageListOne);
		UserRegistrationImagePanel imagePanelTwo = new UserRegistrationImagePanel(this, this.imageListTwo);
		UserRegistrationImagePanel imagePanelThree = new UserRegistrationImagePanel(this, this.imageListThree);
		this.imagesCardPanel.add(imagePanelOne, ONE);
		this.imagesCardPanel.add(imagePanelTwo, TWO);
		this.imagesCardPanel.add(imagePanelThree, THREE);
		this.imagesCardPanel.setVisible(true);
		UserRegistrationImageController controllerPanelOne = new UserRegistrationImageController(imagePanelOne, model);
		UserRegistrationImageController controllerPanelTwo = new UserRegistrationImageController(imagePanelTwo, model);
		UserRegistrationImageController controllerPanelThree = new UserRegistrationImageController(imagePanelThree, model);
	}
}

import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.io.*;
public class View implements Runnable {
  private JFrame frame;
  private JLabel label;
  private JButton left, right, firstImage, secondImage, thirdImage;
  public View() {

  }
  @Override
  public void run() {
    frame = new JFrame();
    frame.setPreferredSize(new Dimension(1500, 1500));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setUp(frame.getContentPane());
    frame.pack();
    frame.setVisible(true);

  }
  public void setUp(Container component) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    label = new JLabel("This is the label");
    left = new JButton("<<");
    left.setIcon(new ImageIcon("1.jpg"));
    right = new JButton(">>");
    // setImages(component);
    JPanel bottomPanel = new JPanel();
    firstImage = new JButton();
    secondImage = new JButton();
    thirdImage = new JButton();
    bottomPanel.add(firstImage);
    bottomPanel.add(secondImage);
    bottomPanel.add(thirdImage);
    left.setMargin(new Insets(0, 0, 0, 0));
    // to add a different background
    // button.setBackground( ... );
    // to remove the border
    left.setBorder(null);
    panel.add(label, BorderLayout.NORTH);
    panel.add(left, BorderLayout.WEST);
    panel.add(right, BorderLayout.EAST);
    panel.add(bottomPanel, BorderLayout.SOUTH);
    component.add(panel);
  }
  public JFrame getFrame() {
    return frame;
  }
  public static void main(String[] args){
    View gui = new View();
    SwingUtilities.invokeLater(gui);
  }
}

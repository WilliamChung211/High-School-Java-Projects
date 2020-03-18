import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name: William Chung
 * 
 * This class represents how a calculator looks like with a math related background.
 */
public class Calculator extends JFrame {

	public Calculator() {
		
		setTitle("Calculator");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//makes the picture background 
		PicPanel mainPanel = new PicPanel("math3.jpg");
		mainPanel.setLayout(null);

		//makes the textbox and sets where it is
		JTextField numText = new JTextField(25);
		numText.setBounds(70, 36, 240, 25);

		//makes a panel for the buttons
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(4, 4, 20, 18));
		String[] buttons = { "7","8","9", "/", "4", "5", "6", "*",  "1", "2", "3", "+",  ".","0", "=", "-" };

		
		//makes a panel for the buttons for the calculator and adds all the buttons to the panel in a proper location
		for (int i = 0; i < buttons.length; i++) {
			boxPanel.add(new JButton(buttons[i]));
		}

		boxPanel.setBounds(50, 105, 276, 172);
		boxPanel.setOpaque(false);

		mainPanel.add(numText);
		mainPanel.add(boxPanel);
		add(mainPanel);
		setVisible(true);

	}

	//class that puts the picture in the frame
	class PicPanel extends JPanel {

		private BufferedImage image;
		private int w, h;

		public PicPanel(String fname) {

			// this reads the picture
			try {
				image = ImageIO.read(new File(fname));
				w = image.getWidth();
				h = image.getHeight();

			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				System.exit(0);
			}

		}

		public Dimension getPreferredSize() {
			return new Dimension(w, h);
		}

		//draws the picture
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	//runs the code
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}

}
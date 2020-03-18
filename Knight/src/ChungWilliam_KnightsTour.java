import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*
 * Name: William Chung
 * 
 * This class represents a grid where a knight moves in an L shape around the grid.
 * The knight cannot move to a location it already has been to and places a number 
 * to the location it just left.
 */
public class ChungWilliam_KnightsTour extends JFrame implements ActionListener {

	private enum STATUS {BLANK, KNIGHT, NUMBER};

	private BufferedImage image;

	private PicPanel[][] allPanels;
	private JLabel stepLabel;
	private JButton stepButton;

	public final int[] HORZDISP = { 1, 2, 2, 1, -1, -2, -2, -1 };
	public final int[] VERTDISP = { -2, -1, 1, 2, 2, 1, -1, -2 };

	private int stepNum = 1;
	private int knightRow = 0;
	private int knightCol = 0;

	public ChungWilliam_KnightsTour() {

		//makes the frame and sets the background color of cyan
		setTitle("Knight's Tour");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(1000, 925);
		setLayout(null);
		getContentPane().setBackground(Color.CYAN);
		
		//gets the knight image
		try {
			image = ImageIO.read(new File("knight.jpg"));

		} catch (IOException ioe) {
			System.out.println("Could not read in the pic");
			System.exit(0);
		}


		//makes the panel to place the grid 
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(8, 8, 2, 2));
		boxPanel.setBackground(Color.BLACK);
		boxPanel.setBounds(86, 45, 608, 800);
		boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
		//makes the grid by making and adding a matrix of 76 by 100 Pic Panels
		allPanels = new PicPanel[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				allPanels[row][col] = new PicPanel(76, 100);
				boxPanel.add(allPanels[row][col]);
			}
		}

		//makes the labels needed to show the total steps and a button below it to make a step happen
		JLabel lab = new JLabel("Total Steps:");
		lab.setFont(new Font("", 0, 28));
		lab.setBounds(748, 312, 550, 100);
		stepLabel = new JLabel(stepNum + "");
		stepLabel.setBounds(806, 258, 300, 300);
		stepLabel.setFont(new Font("", 0, 28));
		stepButton = new JButton("Step");
		stepButton.addActionListener(this);
		stepButton.setBounds(769, 432, 100, 28);
		
		add(lab);
		add(stepLabel);
		add(stepButton);
		add(boxPanel);
	
		//puts the knight at its first location of (0,0)
		allPanels[knightRow][knightCol].setKnight();
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae) {

		ArrayList<Integer> possIndex = new ArrayList<Integer>();

		//finds every valid location in the grid that the knight can move (knights move in L shapes)
		for (int i = 0; i < 8; i++) {
			
			int checkRow = knightRow + HORZDISP[i];
			int checkCol = knightCol + VERTDISP[i];
			
			//a knight can only move to locations in the grid and where the knight has not been before (aka no number)
			if (checkBounds(checkRow, allPanels.length) && checkBounds(checkCol, allPanels[0].length)) {
				if (allPanels[checkRow][checkCol].stat != STATUS.NUMBER) {
					possIndex.add(i);
				}
				
			}
		}

		//if there is no location the knight can move to, the button is disabled and the knight is stuck
		if (possIndex.size() == 0) {
			stepButton.setEnabled(false);
		}
		else {
		
			//places a number in its current location
			allPanels[knightRow][knightCol].setNumber();
			
			//randomly generates a location, out of the possible locations, that the knight can go to and puts the knight there
			int newIndex = possIndex.get((int) (Math.random() * possIndex.size()));
			knightRow = knightRow + HORZDISP[newIndex];
			knightCol = knightCol + VERTDISP[newIndex];
			allPanels[knightRow][knightCol].setKnight();
			
			//updates and shows the number of steps
			stepNum++;
			stepLabel.setText(stepNum + "");
		}

	}

	//checks if the the location is in the grid based on the grids' bounds and number given
	private static Boolean checkBounds(int num, int bound) {
		return num >= 0 && num < bound;
	}

	// will maintain either a knight or a number
	class PicPanel extends JPanel {

		private int width, height;

		private STATUS stat;

		public PicPanel(int w, int h) {
			setBackground(Color.white);
			width = w;
			height = h;
			stat = STATUS.BLANK;
			setLayout(null);

		}

		// changes the panel to show the knight pic
		public void setKnight() {

			stat = STATUS.KNIGHT;
			repaint();
		}

		// changes the panel to show the stepnum in the pic
		public void setNumber() {

			JLabel text = new JLabel("" + stepNum, SwingConstants.CENTER);
			text.setFont(new Font("Calibri", Font.PLAIN, 55));
			text.setBounds(0, 35, 70, 50);
			this.add(text);
			stat = STATUS.NUMBER;
			repaint();
		}

		// called by the machine
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}

		// called automatically by repaint
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			if (stat == STATUS.KNIGHT)
				g.drawImage(image, 0, 0, this);
		}
	}

	//runs the program
	public static void main(String[] args) {
		ChungWilliam_KnightsTour t = new ChungWilliam_KnightsTour();
	}
}

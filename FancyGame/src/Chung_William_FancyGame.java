
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

/*
 * Name: William Chung
 * 
 * This program is a game that has points moving around, colliding, and bouncing 
 * off a window. The user must break points into smaller points and get rid of all
 * the points. A point cannot be less that 15 pixels or out of bounds.
 *
 *
 */
public class Chung_William_FancyGame extends JFrame implements ActionListener {

	private final int WIDTH = 300;
	private final int HEIGHT = 575;
	private final int SPLIT_DIST = 40; // how far to shoot the split piece away
	private final int STEP = 5; // how far it moves per iteration

	private DrawPanel canvas;
	private Timer timer;
	private int numClicks;

	public Chung_William_FancyGame() {
		
		//makes the frame
		setSize(315, 638);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		//makes the canvas
		canvas = new DrawPanel();
		canvas.setBounds(0, 0, 300, 575);
		canvas.setBackground(Color.white);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));

		JMenuBar jmb = new JMenuBar();
		
		//makes the difficulty menu item and different difficulty menu options
		JMenu jmDif = new JMenu("Difficulty");
		JMenuItem jmiBab = new JMenuItem("Baby");
		JMenuItem jmiNorm = new JMenuItem("Normal");
		JMenuItem jmiNigh = new JMenuItem("Nightmare");

		//adds all the menu items and canvas and the action listeners
		jmDif.add(jmiBab);
		jmDif.add(jmiNorm);
		jmDif.add(jmiNigh);
		jmb.add(jmDif);
		setJMenuBar(jmb);
		add(canvas);
		jmiBab.addActionListener(this);
		jmiNorm.addActionListener(this);
		jmiNigh.addActionListener(this);

		//puts the timer for every 25 milliseconds 
		timer = new Timer(25, new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				//makes every point on the canvas move or change directions
				for (int i = 0; i < canvas.allPoints.size(); i++) {
					canvas.allPoints.get(i).action();
				}
				
			}
		});
		
		//starts the timer and sets invisible
		timer.start();
		setVisible(true);

	}

	//checks if something is in bounds of an area based on the coordinate, its size, and bound
	private boolean checkBounds(int coord, int size, int lowBound, int  upBound) {
		return coord >= lowBound && coord + size < upBound;
	}
	
	public void actionPerformed(ActionEvent ae) {

		String command = ae.getActionCommand();

		//the game restarts anytime the user selects a menu item
		canvas.allPoints.clear();
		numClicks=0;
		
		//difficulty determines the number of starting points and size
		int numPoints;
		int size;

		//baby mode starts with 1 point at size 40
		if (command.equals("Baby")) {
			numPoints = 1;
			size = 40;
		} 
		
		//normal mode starts with 2 points at size 50
		else if (command.equals("Normal")) {
			numPoints = 2;
			size = 50;
		} 
		
		//nightmare mode starts with 3 points at size 75
		else {
			numPoints = 3;
			size = 75;
		}

		//adds the points based on the difficulty chosen
		for (int i = 0; i < numPoints; i++) {
			canvas.allPoints.add(new Point(size));
		}
		
		canvas.repaint();

	}

	//class that dictates how to draw points at their current location out to the window
	public class DrawPanel extends JPanel implements MouseListener {

		private ArrayList<Point> allPoints;

		public DrawPanel() {
			allPoints = new ArrayList<Point>();
			this.addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (Point next : allPoints) {

				g.setColor(next.pColor);
				g.fillOval(next.xLoc, next.yLoc, next.size, next.size);

			}
		}

		public void mouseClicked(MouseEvent me) {

	
			//counts all the total number of mouse clicks
			numClicks++;
		
			//searches through each point
			for (int i = 0; i < allPoints.size(); i++) {

				int x = allPoints.get(i).xLoc;
				int y = allPoints.get(i).yLoc;
				int diam = allPoints.get(i).size;

				//checks if the mouseclick falls inside the bounds of a point
				if ((checkBounds(me.getX(),0,x,x+diam) && checkBounds(me.getY(),0,y,y+diam))) {

					//removes the point
					allPoints.remove(i);

					//only a new points can be made if their size is 15 or above
					if (diam >= 30) {

						int[] vertical = { -1, -1, 1, 1 };
						int[] horizontal = { -1, 1, -1, 1 };

						//splits the points into smaller points in 45 degrees angles a certain distance away where it was clicked on
						for (int ind = 0; ind < 4; ind++) {

						
							//makes a possible neighbor point
							Point possPoint = new Point (me.getX() + SPLIT_DIST * horizontal[ind], me.getY() + SPLIT_DIST * vertical[ind], diam/2);
						
							//a point cannot be generated out of bounds
							if(!(possPoint.detectHorBound()||possPoint.detectVertBound())) {
								allPoints.add(possPoint);
							}
							
						}

					}

					//repaints the canvas
					repaint();

					//report out the total number of mouse clicks once all pointts have been removed 
					if (allPoints.size() == 0) {
						JOptionPane.showMessageDialog(null, "YOU WIN!!!! Total Number of Clicks: " + numClicks);
					}

					return;
					
				}

			}

		}

		public void mouseEntered(MouseEvent arg0) {
			// do not implement

		}

		public void mouseExited(MouseEvent arg0) {
			// do not implement

		}

		public void mousePressed(MouseEvent arg0) {
			// do not implement

		}

		public void mouseReleased(MouseEvent arg0) {
			// do not implement

		}
	}

	public class Point {
		private int xLoc; // this is the top left corner of the "box" that the
							// point is drawn in.
		private int yLoc;
		private Color pColor;
		private int size;
		private int xDir; // direction it's currently moving.
		private int yDir;

		// randomly makes a point of size s
		public Point(int s) {
			this((int) (Math.random() * (WIDTH - s)), (int) (Math.random() * (HEIGHT - s)), s);

		}

		// makes a point of size s at a given location
		public Point(int xL, int yL, int s) {

			pColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
			size = s;
			xLoc = xL;
			yLoc = yL;
			int[] directions = genDirection();
			xDir = directions[0];
			yDir = directions[1];
		}

		//detects if you have a it a vertical boundary
		public boolean detectVertBound() {
			return !(checkBounds(xLoc, size, 0,WIDTH));

		}
		
		//detects if you have a it a horizontal boundary
		public boolean detectHorBound() {
			return !checkBounds(yLoc, size,0, HEIGHT);
			
		}

		//move the point by one step and may flip the directional components if the point has hit a wall.
		public void action() {

			//flips the direction components if it hit a wall/out of bounds
			if (detectVertBound()) {
				xDir *= -1;
			} 
			if (detectHorBound()) {
				yDir *= -1;
			}

			//moves the point by one step and repaints
			xLoc += xDir * STEP;
			yLoc += yDir * STEP;

			repaint();

		}

		//generates two directions
		private int[] genDirection() {

			int xVal;
			int yVal;

			do {
				xVal = (int) (Math.random() * 3 - 1);
				yVal = (int) (Math.random() * 3 - 1);
			} while (xVal == 0 && yVal == 0);

			int[] toRet = { xVal, yVal };

			return toRet;
		}

		public boolean equals(Object other) {
			Point op = (Point) other;
			return this.xLoc == op.xLoc && this.yLoc == op.yLoc && pColor.equals(op.pColor);
		}
		
	}

	//runs the game
	public static void main(String[] args) {
		new Chung_William_FancyGame();
	}

}

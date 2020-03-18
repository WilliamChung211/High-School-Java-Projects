import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Name: William Chung
 * This program is a GUI that will allow the user to draw points 
 * as long as the mouse is dragged. You can also save, load, impose 
 * a file, and select the point size and color
 */

public class ChungWilliam_Paint extends JFrame implements ActionListener {


	private Color currentColor;
	private JColorChooser colorChooser;
	private int curSize;
	private DrawPanel canvas;

	public ChungWilliam_Paint() {

		//makes the panel
		setSize(375, 596);
		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		colorChooser = new JColorChooser();

		//sets default size and color
		currentColor = Color.black;
		curSize = 10;

		//makes the canvas
		canvas = new DrawPanel();
		canvas.setBounds(0, 0, 375, 482);

		// makes the original menu bar
		JMenuBar jmb = new JMenuBar();

		// makes the file menu options and its sub options
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiLoad = new JMenuItem("Load");
		JMenuItem jmiSave = new JMenuItem("Save");
		JMenuItem jmiImp = new JMenuItem("Impose");

		// adds the file items to the file menu option
		jmFile.add(jmiLoad);
		jmFile.add(jmiSave);
		jmFile.add(jmiImp);

		// can makes the options menu's options and sub options and their sub options
		JMenu jmOptions = new JMenu("Options");
		JMenuItem jmiCol = new JMenuItem("Color");
		JMenu jmiSize = new JMenu("Size");
		JMenuItem jmi14 = new JMenuItem("10");
		JMenuItem jmi25 = new JMenuItem("25");
		JMenuItem jmi50 = new JMenuItem("50");

		// adds the sub options of the size sub options
		jmiSize.add(jmi14);
		jmiSize.add(jmi25);
		jmiSize.add(jmi50);

		// adds the options items to the option menu option
		jmOptions.add(jmiCol);
		jmOptions.add(jmiSize);

		// add all menu options to the original menu bar and sets it to the bar
		jmb.add(jmFile);
		jmb.add(jmOptions);
		setJMenuBar(jmb);

		//makes the button that clears everything
		JButton clearButt = new JButton("Clear");
		clearButt.setBounds(0, 483, 380, 52);

		//adds everything and their action listeners
		add(clearButt);
		add(canvas);
		jmiLoad.addActionListener(this);
		jmiSave.addActionListener(this);
		jmiImp.addActionListener(this);
		jmiCol.addActionListener(this);
		jmi14.addActionListener(this);
		jmi25.addActionListener(this);
		jmi50.addActionListener(this);
		clearButt.addActionListener(this);
		
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae) {
	
		String command = ae.getActionCommand();

		//clear button empties out the list of points and shows no more points
		if (command.equals("Clear")) {
			canvas.points.clear();
			canvas.repaint();
			
			//resets the color to black and size to 10
			currentColor = Color.black;
			curSize = 10;
		} 
		
		//pops up color chooser window to change future point's color
		else if (command.equals("Color")) {
			Color newColor = colorChooser.showDialog(this, "Select a color", currentColor);
			
			if (newColor != null) {
				currentColor = newColor;
			}
			
		} 
		
		//clears the canvas and draws points based off the information stored in the chosen file
		else if (command.equals("Load")) {
			canvas.points.clear();
			drawFile();
		}

		//asks user where they would like to save and all data is written back in a specific file format
		else if (command.equals("Save")) {
			
			//lets the user choose a file
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = jfc.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {

				try {

					FileWriter outfile  = new FileWriter(jfc.getSelectedFile());

					for (Point nextP : canvas.points) {

						//for each point in the arraylist, it will write out x, y, red, green, blue, and size in each line in the file
						Color col = nextP.ptColor;
						int red = col.getRed();
						int gr = col.getGreen();
						int bl = col.getBlue();
						outfile.write(nextP.x + " " + nextP.y + " " + red + " " + gr + " " + bl + " " + nextP.ptSize + "\r\n");

					}

					//finalizes data writing
					outfile.close();
					
				} catch (IOException e) {
					System.out.println("There is an IO issue.");
					System.exit(0);
				}
				
			}

		} 
		
		//superimposes a chosen file on top of whatever is already in the window
		else if (command.equals("Impose")) {
			drawFile();
		}
		
		//changes the radii of the next points to the chosen size
		else {
			curSize = Integer.parseInt(command);
		}
	}
	
	//draws a chosen image on the caanvas
	private void drawFile() {
		
		// pops up the file chooser box
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = jfc.showOpenDialog(null);

		Scanner scan = null;


		if (result == JFileChooser.APPROVE_OPTION) {

			// gets and scans the chosen file if the user did not cancel out
			try {
				scan = new Scanner(jfc.getSelectedFile());
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found!");
				System.exit(-1);
			}

			//adds the points of the chosen file (based on its x, y, RGB, and radius size) to the campas
			while (scan.hasNextInt()) {
				canvas.points.add(new Point(scan.nextInt(), scan.nextInt(),new Color(scan.nextInt(), scan.nextInt(), scan.nextInt()), scan.nextInt()));
			}
			
			//repaints the canvas after adding all the points from the chosen file
			repaint();
		}
	}

	//An inner class that maintains the location, color, and size of any given point
	public class Point {

		private int x;
		private int y;
		private Color ptColor;
		private int ptSize;

		public Point(int xLoc, int yLoc, Color col, int size) {
			x = xLoc;
			y = yLoc;
			ptColor = col;
			ptSize = size;
		}

	}

	//this panel maintains a list of points tht will be drawn long as the mouse button is dragged
	public class DrawPanel extends JPanel implements MouseMotionListener {

		private ArrayList<Point> points;
		
		public DrawPanel() {

			points = new ArrayList<Point>();
			addMouseMotionListener(this);
			
		}

		//paints the points
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			setBackground(Color.white);

			//draws every single point on the campus based on its location and radius size
			for (Point nextP : points) {
				g.setColor(nextP.ptColor);
				g.fillOval(nextP.x, nextP.y, nextP.ptSize, nextP.ptSize);
			}

		}

	
		
		//when the mouse is dragged, it will insert the new point with its chosen size and color and redraw every single point
		public void mouseDragged(MouseEvent me) {
			
				points.add(new Point(me.getX(), me.getY(), currentColor, curSize));
				repaint();
			
		}

		public void mouseMoved(MouseEvent me) {

		}
		
		public void mouseClicked(MouseEvent me) {
		
		}

		public void mouseEntered(MouseEvent arg0) {


		}

		public void mouseExited(MouseEvent arg0) {

		}

		
		public void mouseReleased(MouseEvent arg0) {

		}

	}

	//runs the program
	public static void main(String[] args) {
		new ChungWilliam_Paint();
	}
	
}

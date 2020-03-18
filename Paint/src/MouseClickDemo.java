
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MouseClickDemo extends JFrame implements ActionListener{

	private JButton selectColor;	
	private Color currentColor;
	private JColorChooser colorChooser;

	public MouseClickDemo(){

		setSize(375,590);
		setTitle("Click Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		colorChooser= new JColorChooser();

		currentColor = Color.red;

		DrawPanel canvas = new DrawPanel();	
		canvas.setBounds(0,0,375,510);


		selectColor = new JButton("Select Color");
		selectColor.setBounds(0,510,375,45);
		selectColor.addActionListener(this);

		add(canvas);
		add(selectColor);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae){

		Color newColor = colorChooser.showDialog(this, "Select a color", currentColor);
		if(newColor !=null)
			currentColor = newColor;
	}

	public class Point{

		private int xLoc;
		private int yLoc;

		public Point(int x, int y){
			xLoc = x;
			yLoc = y;
		}
	}

	public class DrawPanel extends JPanel implements MouseListener{

		private boolean leftClick;
		private ArrayList<Point> points; //stores points to draw a polygon

		public DrawPanel(){
			this.addMouseListener(this);    //listens for mouse clicks
			points = new ArrayList<Point>();
		}

		public void paintComponent(Graphics g){		

			super.paintComponent(g);
			setBackground(Color.white);
			g.setColor(currentColor);
			
			//draw a point
			if(leftClick){

				for(Point nextP: points){

					g.fillOval(nextP.xLoc, nextP.yLoc, 10, 10);
				}
			}
			//draw a polygon
			else {

				int[] x = new int[points.size()];
				int[] y = new int[points.size()];

				for(int i = 0; i < points.size(); i++){
					x[i] = points.get(i).xLoc;
					y[i] = points.get(i).yLoc;				

				}

				g.fillPolygon(x, y, points.size());

				points.clear();
			}
		}


		public void mouseClicked(MouseEvent me) {

			//if it's a left moues click
			if(me.getButton() == MouseEvent.BUTTON1){
				leftClick = true;
				points.add(new Point(me.getX(),me.getY()));

			}

			else if(me.getButton() == MouseEvent.BUTTON3){

				leftClick=false;

			}

			this.repaint();
		}
		//these methods are stubbed out and not used
		public void mouseEntered(MouseEvent arg0) {


		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}
	}

	public static void main(String[] args){
		System.out.println(new ArrayList(10));
		new MouseClickDemo();
	}
}

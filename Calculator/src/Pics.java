import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class Pics extends JFrame{

	public Pics(){
		
		this.setTitle("Pic Tester");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(950,440);

		PicPanel mainPanel = new PicPanel("skyview.jpg");
		mainPanel.setLayout(null);

		JCheckBox good = new JCheckBox("Trees");
		good.setForeground(Color.BLUE);
		good.setOpaque(false);
		JCheckBox sqr = new JCheckBox("Squirrels");
		sqr.setOpaque(false);
		sqr.setForeground(Color.BLUE);
		JCheckBox tourists = new JCheckBox("Tourists");
		tourists.setOpaque(false);
		tourists.setForeground(Color.BLUE);
		
		
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(3,1));
		boxPanel.add(good); 
		boxPanel.add(tourists);
		boxPanel.add(sqr);
		
		boxPanel.setBounds(425,175,100,80);
		boxPanel.setOpaque(false);
		boxPanel.setBackground(Color.GREEN);

		mainPanel.add(boxPanel);
		this.add(mainPanel);

		this.setVisible(true);
	}


	class PicPanel extends JPanel{

		private BufferedImage image;
		private int w,h;
		public PicPanel(String fname){

			//reads the image
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
			return new Dimension(w,h);
		}
		//this will draw the image
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image,0,0,this);
		}
	}

	public static void main(String[] args){
		new Pics();
	}
}

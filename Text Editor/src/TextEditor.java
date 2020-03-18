import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/*
 *  Name: William Chung
 *  
 * This program reprents a text editors with menu items and options
 * to help you type into a window, edit a text's font size, style or color 
 * , and also open or save a file. 
 */

public class TextEditor extends JFrame implements ActionListener {

	private JTextPane pane;

	public TextEditor() {
		
		//makes the panel
		setTitle("Text Editor");
		setSize(606, 430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//sets the file background picture and the text panel 
		PicPanel pic = new PicPanel("file.jpeg");
		pic.setLayout(null);
		
		//makes the text panel and makes it scrollable
		pane = new JTextPane();
		JScrollPane jscp = new JScrollPane(pane); 
		jscp.setBounds(84, 49, 422, 275);

		pane.setFont(new Font("Comic Sans", Font.PLAIN,14 ));
		//makes the original menu bar
		JMenuBar jmb = new JMenuBar();
	
		//makes the file menu options and its sub options
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiClose = new JMenuItem("Save");
		JMenuItem jmiExit = new JMenuItem("Exit");

		//adds the file items to the file menu option
		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiExit);

		// can makes the font menu options and sub options and their sub options
		JMenu jmFont = new JMenu("Font");
		JMenuItem jmiCol = new JMenuItem("Color");
		JMenu jmiSize = new JMenu("Size");
		JMenu jmiStyle = new JMenu("Style");
		JMenuItem jmi14 = new JMenuItem("14");
		JMenuItem jmi16 = new JMenuItem("16");
		JMenuItem jmiCMS = new JMenuItem("Comic Sans");
		JMenuItem jmiTNR = new JMenuItem("Times New Roman");

		//adds the sub options of the font sub options
		jmiStyle.add(jmiCMS);
		jmiStyle.add(jmiTNR);
		jmiSize.add(jmi14);
		jmiSize.add(jmi16);
		
		//adds the font items to the font menu option
		jmFont.add(jmiCol);
		jmFont.add(jmiSize);
		jmFont.add(jmiStyle);

		// add all menu options to the original menu bar and sets it to the bar
		jmb.add(jmFile);
		jmb.add(jmFont);
		setJMenuBar(jmb);

		jmiOpen.addActionListener(this);
		jmiClose.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiCol.addActionListener(this);
		jmi14.addActionListener(this);
		jmi16.addActionListener(this);
		jmiCMS.addActionListener(this);
		jmiTNR.addActionListener(this);

		//adds the picture and text panel
		pic.add(jscp);
		add(pic);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String command = ae.getActionCommand();

		//if the open box pops up, it allows the user to insert any txt file to the panel
		if (command.equals("Open")) {
			
			//pops up the file chooser box
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));

			int result = jfc.showOpenDialog(null);
			
			Scanner scan = null;

			String text = "";

			if (result == JFileChooser.APPROVE_OPTION) {
			
				//gets and scans the chosen file if the user did not cancel out
				try {
					scan = new Scanner(jfc.getSelectedFile());
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found!");
					System.exit(-1);
				}

				//gets every line of the chosen text file
				while (scan.hasNextLine()) {
					text += scan.nextLine() + "\n";
				}

				//sets the panel text to the text of the chosen text file
				pane.setText(text);

			}
			
		} 
		
		else if (command.equals("Save")) {
			
			//if the user picked save, a save dialog box pops up allowing the user to select a text file or new
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));

			int result = jfc.showOpenDialog(null);

			FileWriter outfile = null;

			if (result == JFileChooser.APPROVE_OPTION) {
				
				//all data in the text pane is written back to the chosen file
				try {
					
					outfile = new FileWriter(jfc.getSelectedFile());
					outfile.write(pane.getText());
					outfile.close();
					
				} catch (IOException e) {
					System.out.println("IOException");
					System.exit(-1);
				}

			}

		} 
		
		//if Exit is pressed, the program terminates
		else if (command.equals("Exit")) {
			System.exit(0);
		} 
		
		//wen color is chosen, a JColormenu pops up and the text color will change to what the user selected
		else if (command.equals("Color")) {
			pane.setForeground(JColorChooser.showDialog(null, "Select a color", Color.black));
		} 
		
		//changes the size of the text in the text pane to 14
		else if (command.equals("14")) {
			Font curFont = pane.getFont();
			pane.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 14));
		} 
		
		//changes the size of the text in the text pane to 16
		else if (command.equals("16")) {
			Font curFont = pane.getFont();
			pane.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 16));
		} 
		
		//changes the style of the text pane's text to the font chosen
		else {
			Font curFont = pane.getFont();
			pane.setFont(new Font(command, curFont.getStyle(), curFont.getSize()));
		}
		
	}

	//class that draws the picture
	class PicPanel extends JPanel {

		private BufferedImage image;
		private int w, h;

		public PicPanel(String fname) {

			// reads the image
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

		// this will draw the image
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	//runs the program
	public static void main(String[] args) {
		new TextEditor();
	}
	
}

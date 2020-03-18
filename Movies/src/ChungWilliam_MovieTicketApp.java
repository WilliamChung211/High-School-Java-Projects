import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChungWilliam_MovieTicketApp extends JFrame implements ActionListener, ListSelectionListener {

	private ArrayList<Movie> movies;
	private DefaultComboBoxModel movieModel;
	private JComboBox theBox;
	private JList time;
	private DefaultListModel timeModel;
	private JButton button;
	private JLabel seatsLab;
	private PicPanel picture;

	public ChungWilliam_MovieTicketApp(String file) {

		// makes the frame
		setTitle("Movie Tix");
		setSize(448, 678);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		Scanner fileIn = null;

		// gets the scores file
		try {
			fileIn = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}

		movieModel = new DefaultComboBoxModel();
		movies = new ArrayList<Movie>();
		// fills the list of movies for all except the last movi
		while (fileIn.hasNextLine()) {
			// puts the first line of each pair of lines as the name of movie
			String name = fileIn.nextLine();
			String fName = fileIn.nextLine();
			String[] times = fileIn.nextLine().split(",");
			Movie move = new Movie(name, fName);
			for (String tim : times) {
				int sepIndex = tim.indexOf(" ");
				Time t = new Time(Integer.parseInt(tim.substring(0, sepIndex)),
						Integer.parseInt(tim.substring(sepIndex + 1)));

				move.add(new Screening(t));
			}

			movies.add(move);
			movieModel.addElement(move.getName());
			// fills the list with a movie based on that name and time
		}

		timeModel = new DefaultListModel();
		ArrayList<Screening> screens = movies.get(0).getScreens();
		for (Screening screen : screens) {
			timeModel.addElement(screen.getTime());
		}

		time = new JList(timeModel);
		time.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		time.addListSelectionListener(this);

		picture = new PicPanel();
		picture.setPic(movies.get(0).getFile());
		picture.setBounds(42, 60, 352, 232);

		JScrollPane jscp = new JScrollPane(time);
		jscp.setBounds(40, 340, 100, 176);
		jscp.setOpaque(false);

		Border border = BorderFactory.createLineBorder(Color.black, 2);
		jscp.setBorder(BorderFactory.createTitledBorder(border, "Select Time"));

		theBox = new JComboBox(movieModel);
		theBox.setBounds(140, 8, 202, 29);
		theBox.addActionListener(this);
		theBox.setActionCommand("movies");

		JLabel selectLab = new JLabel("Select Movie:");
		selectLab.setBounds(40, 10, 178, 28);
		selectLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		seatsLab = new JLabel("Seats Left:");
		seatsLab.setBounds(195, 344, 180, 28);
		seatsLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JLabel quantLab = new JLabel("Quantity:");
		quantLab.setBounds(194, 393, 178, 28);
		quantLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JTextField numText = new JTextField(25);
		numText.setBounds(262, 398, 38, 19);

		JButton buyTic = new JButton("Buy Tickets");
		buyTic.setBounds(162, 438, 225, 74);

		add(picture);
		add(numText);
		add(buyTic);
		add(jscp);
		add(seatsLab);
		add(theBox);
		add(selectLab);
		add(quantLab);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

	
			int selected = theBox.getSelectedIndex();
			picture.setPic(movies.get(selected).getFile());
			
			ArrayList<Screening>screens = movies.get(selected).getScreens();
			
			timeModel.removeAllElements();
			for(int i =0;i<screens.size();i++ ) {
				timeModel.addElement(screens.get(i).getTime());
			}
		

	}

	public void valueChanged(ListSelectionEvent le) {

		int index = time.getSelectedIndex();
		// if (index != -1)
		// jlab.setText("Current Selection: " + myModel.get(index));

	}

	class Movie {

		private String name;
		private String fName;
		private ArrayList<Screening> screens;
		
		public Movie(String mName, String file){
			screens = new ArrayList<Screening>();
			name = mName;
			fName = file;
		}
		
		public void add(Screening screen){
			
			int ind=0;
			while( ind<screens.size() &&(screens.get(ind).getTime().compareTo(screen.getTime())<=0)){
				ind++;
			}
			
			screens.add(ind,screen);
		}
		
		
		public ArrayList<Screening> getScreens(){
			return screens;
		}
		
		public String getName(){
			return name; 
		}
		
		public String getFile(){
			return fName; 
		}
	}
	
	class Screening {

		private Time movTime;
		private int maxOccu;
		private int totTick;

		public Screening(Time time) {

			maxOccu = (int) (Math.random() * 56) + 65;
			movTime = time;

		}

		// verifies if they can attend the movie and adds a customer to the list if
		// they can.
		public void sellTicket(Time time, int numTic) {

			// checks if a seat is available
			if ((maxOccu <totTick+numTic)||(time.compareTo(movTime) >= 0)) {
				throw new IllegalArgumentException("Cannot sell tickets. Max Occupation hit");
			}

			totTick+=numTic;

		}

		public Time getTime(){
			return movTime;
		}
		
		//delete later
		public String toString(){
			return movTime + " "+  maxOccu;
		}
		
	}
	
	class Time {

		private int hour;
		private int minute;

		public Time(int our, int min) {

			if ((our > 23) || (min >= 60)) {
				throw new IllegalArgumentException("Invalid time");
			}

			hour = our;
			minute = min;
		}

		// returns the time in HH::MM format. pads a 0 if min or hour is single digit.
		public String toString() {
			String time = "";
			
			if (hour < 10) {
				time += "0";
			}
			
			time += hour + ":";
			
			if (minute < 10) {
				time += "0";
			}
			
			return time + minute;

		}

		//Returns negative value if calling object come before parameter. Positive if after. 0 if same
		public int compareTo(Time other) {
			
			int diff = this.hour - other.hour;
			
			if (diff == 0) {
				return this.minute - other.minute;
			}
		 
			
			return diff;
		}
	}
	
	class PicPanel extends JPanel {

		private BufferedImage image;
		private int w, h;
		private final int MAX_WIDTH = 350;
		private final int MAX_HEIGHT = 232;

		public void setPic(String fname) {

			// reads the image
			try {
				image = ImageIO.read(new File(fname));
				w = image.getWidth();
				h = image.getHeight();

			} catch (IOException ioe) {
				System.out.println("Could not read in the pic: " + fname);
				System.exit(0);
			}

			this.setBounds(40, 75, Math.min(w, MAX_WIDTH), Math.min(h, MAX_HEIGHT));
			this.repaint();

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

	public static void main(String[] args) {
		new ChungWilliam_MovieTicketApp("movies");

	}
}

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
import java.time.LocalDateTime;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * Name: William Chung
 * 
 * This program represents a GUI to represent an app that has a combo box of movie names and displays
 * a image, a list of times for screenings, and number of numbers of seats left fror a selected 
 * corresponding movie. You can also buy a valid number of tickets with this app depending on the 
 * current time and screening of the movie.
 */

public class ChungWilliam_MovieTicketApp extends JFrame implements ActionListener, ListSelectionListener {

	private ArrayList<Movie> movies;
	private JComboBox theBox;
	private JList time;
	private DefaultListModel timeModel;
	private JButton button;
	private JLabel seatsLab;
	private PicPanel picture;
	private JTextField numText;

	public ChungWilliam_MovieTicketApp() {

		// makes the frame
		setTitle("Movie Tix");
		setSize(448, 678);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		// gets the movies file
		Scanner fileIn = null;
		
		try {
			fileIn = new Scanner(new File("movies.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}

		DefaultComboBoxModel movieModel = new DefaultComboBoxModel();
		movies = new ArrayList<Movie>();
		
		//goes through each 3 lines to create and add a movie object to the movies list
		while (fileIn.hasNextLine()) {
			
			// gets the movie name, the picture file name, and the specific times for screenings in order to create a movie object 
			String name = fileIn.nextLine();
			
			//adds the movie name to the movie combo box model
			movieModel.addElement(name);
			String fName = fileIn.nextLine();
			String[] times = fileIn.nextLine().split(",");
			Movie move = new Movie(name, fName);
			
			//add the screenings for the movie based on the specific times in chronological order 
			for (String tim : times) {
				int sepIndex = tim.indexOf(" ");
				Time t = new Time(Integer.parseInt(tim.substring(0, sepIndex)),Integer.parseInt(tim.substring(sepIndex + 1)));
				move.add(new Screening(t));
			}

			//adds the newly created movie objejct to the movie list
			movies.add(move);
		
		}

		//prompts user to use the combo box of movie names with a label
		JLabel selectLab = new JLabel("Select Movie:");
		selectLab.setBounds(40, 10, 178, 28);
		selectLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		//makes a combo box of the movie names
		theBox = new JComboBox(movieModel);
		theBox.setBounds(140, 8, 202, 29);
		theBox.addActionListener(this);
		theBox.setActionCommand("movies");
		
		//shows the picture of the first movie in the combo box 
		picture = new PicPanel();
		picture.setPic(movies.get(0).picFil);
		
		//the default times for screenings that the time list will show will be of the first movie in the combo box. adds those times by default
		timeModel = new DefaultListModel();
		ArrayList<Screening> screens = movies.get(0).screens;
		for (Screening screen : screens) {
			timeModel.addElement(screen.movTime);
		}

		//creates the list based on the model that will show screening times of the selected movie shown that day
		time = new JList(timeModel);
		time.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		time.addListSelectionListener(this);

		//wraps the list in a scroll panel and sets border title
		JScrollPane timeScp = new JScrollPane(time);
		timeScp.setBounds(40, 340, 100, 176);
		timeScp.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		timeScp.setBorder(BorderFactory.createTitledBorder(border, "Select Time"));

		//label that eventually displays the number of seats left based on screening selected
		seatsLab = new JLabel("Seats Left:");
		seatsLab.setBounds(195, 344, 180, 28);
		seatsLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		//textfield for the user to put the amount of tickets he or she wants to buy
		numText = new JTextField(25);
		numText.setBounds(262, 398, 38, 19);
		
		//labels the textfield
		JLabel quantLab = new JLabel("Quantity:");
		quantLab.setBounds(194, 393, 178, 28);
		quantLab.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		//button that the user will press the purchase a tickete
		JButton buyTic = new JButton("Buy Tickets");
		buyTic.setBounds(162, 438, 225, 74);
		buyTic.addActionListener(this);

		//adds everything
		add(selectLab);
		add(theBox);
		add(picture);
		add(timeScp);
		add(seatsLab);
		add(numText);
		add(quantLab);
		add(buyTic);
		
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		//gets the selected movie from the combo box and the screenings of the selected movie
		Movie selecMov = movies.get(theBox.getSelectedIndex());
		ArrayList<Screening> screens = selecMov.screens;

		//if the buy tickets button is pressed, then the amount of tickets requested will be sold or an error will happen
		if (ae.getActionCommand().equals("Buy Tickets")) {
			
			//checks and gets the time for the screening selected from the list
			int timeInd = time.getSelectedIndex();
			
			if (timeInd != -1) {
				
				//gets the screening based on the time selected and gets the current time
				Screening selScreen = screens.get(timeInd);
				int hr = LocalDateTime.now().getHour();
				int min = LocalDateTime.now().getMinute();

				try {
					
					//gets the quantity of tickets requested from the textfield
					int numTickets = Integer.parseInt(numText.getText());
					
					//atempts to sell the number of tickets requested
					selScreen.sellTicket(new Time(hr, min), numTickets);
					
					//if it is a valid quantity and earlier current time, it will show an appropriate message and update the amount of seats left
					JOptionPane.showMessageDialog(null, "You sucessfully purchased " + numTickets + " ticket(s) of " + selecMov.name );
					Screening curScreen = selecMov.screens.get(timeInd);
					seatsLab.setText("Seats Left: " + (curScreen.maxOccu - curScreen.totTick));
					
				} catch (IllegalArgumentException e) {
					
					//if the quantity requested is higher than the tickets left or current time is later than the screening time, an error messages pops up
					JOptionPane.showMessageDialog(null, "Error: Cannot make purchase due to too big/invalid quantity or too late time.");
					
				} 
				
			}
			
		}
		
		//when the movie in the combo box is changed, the picture panel is changed to reflect on the selected movie
		else {
			
			picture.setPic(selecMov.picFil);

			//also it changes the list of times to show the list of times of screenings where the movie will be shown that day
			timeModel.removeAllElements();
			
			for (int i = 0; i < screens.size(); i++) {
				timeModel.addElement(screens.get(i).movTime);
			}
			
		}
		
	}

	public void valueChanged(ListSelectionEvent le) {

		//gets the selected time index
		int timeInd = time.getSelectedIndex();
		
		//when the user selects a time in the list, the number of seats left will be shown
		if (timeInd != -1) {
			Movie selecMov = movies.get(theBox.getSelectedIndex());
			Screening curScreen = selecMov.screens.get(timeInd);
			seatsLab.setText("Seats Left: " + (curScreen.maxOccu - curScreen.totTick));
		} 
		
		//if no time is selected (such as when the movie is reseted), no number of seats left will be shown
		else {
			seatsLab.setText("Seats Left:");
		}

	}

	//represents a movie shown in theatre that day with a name, a file name of an image, and a list of all screenings of that movie
	class Movie {

		private String name;
		private String picFil;
		private ArrayList<Screening> screens;

		public Movie(String mName, String file) {
			screens = new ArrayList<Screening>();
			name = mName;
			picFil = file;
		}
		
		//adds a screening parameter to the screening list in chronological order
		public void add(Screening screen) {

			int ind = 0;
			
			while (ind < screens.size() && (screens.get(ind).movTime.compareTo(screen.movTime) <= 0)) {
				ind++;
			}

			screens.add(ind, screen);
		}

	}

	//represents a single showing a movie with a starting time, max theatre capacity, and total number of tickets sold so far
	class Screening {

		private Time movTime;
		private int maxOccu;
		private int totTick;

		public Screening(Time time) {

			//sets the max capacity of seats at a random number [65,110]
			maxOccu = (int) (Math.random() * 46) + 65;
			movTime = time;

		}

		//checks if a a certain number of tickets can be sold to a customer and then sells if they can 
		public void sellTicket(Time curTime, int numTic) {

			// checks if a seat is available and if the current time is earlier than the screening time. throws error message if now
			if ((maxOccu < totTick + numTic) || (curTime.compareTo(movTime) >= 0)) {
				throw new IllegalArgumentException("Cannot sell tickets. Max Occupation hit or too late time");
			}

			//updates the overall tickets sold if successful
			totTick += numTic;
		}
		
	}

	//Represents a time in military time and stores the hour and minute
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

		// Returns negative value if calling object come before parameter. Positive if after. 0 if same
		public int compareTo(Time other) {

			int diff = this.hour - other.hour;

			if (diff == 0) {
				return this.minute - other.minute;
			}

			return diff;
		}

	}

	//class that draws an image in a panel
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

	//runs the program
	public static void main(String[] args) {
		new ChungWilliam_MovieTicketApp();
	
	}
	
}

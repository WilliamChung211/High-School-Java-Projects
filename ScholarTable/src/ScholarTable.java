import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/*
 * Name: William Chung
 * 
 * This program represents a GUI that shows a table of students and their respective names, 
 * amount of types of AP scores, and AP Exam average (originally hidden). It can hide
 * or show their awards and averages based on their AP Scores.
 */

public class ScholarTable extends JFrame implements ActionListener {

	private enum STATUS {REGULAR, SCHOLAR, HONOR};
	private JTable theTable;
	private DefaultTableModel theModel;
	private AllStudents theStudents;
	private JCheckBox averages;
	private JCheckBox awards;
	private TableColumn average;

	public ScholarTable() {

		//makes the frame
		setTitle("Scholar Table");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//makes the AP background picture
		PicPanel mainPanel = new PicPanel("ap_logo.jpg");
		mainPanel.setLayout(null);
		
		String[] colNames = { "Name", "1", "2", "3", "4", "5", "Average" };

		//makes the model for the table and makes it so no cell is edible
		theModel = new DefaultTableModel(colNames, 0) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		Object[] row = new Object[7];
	
		//puts all the students' names, the amount of AP score types, and their averages from one file in an arrayList of Student objects
		theStudents = new AllStudents("apscores.txt");
		
		//adds a row for each students name, amount of AP score types, and their average in the model
		for (APStudent stud : theStudents) {
			row[0] = stud.name;
			for (int i = 0; i < 5; i++) {
				row[i + 1] = stud.scores[i];
			}
			row[6] = String.format("%.2f", stud.testAvg);
			theModel.addRow(row);
		}
		
		//makes the table based off the model and initially hides the average
		theTable = new JTable(theModel);
		average = hideColumn(theTable, "Average");

		//makes sure they cannot reorder the collumns on the table header
		theTable.getTableHeader().setReorderingAllowed(false);

		//wraps t he table and sets the bounds
		JScrollPane jsp = new JScrollPane(theTable);
		jsp.setBounds(15, 40, 550, 200);

		//makes the checkboxes to show averages or awards.
		averages = new JCheckBox("Show Averages");
		averages.setBounds(48, 247, 150, 20);
		averages.addActionListener(this);
		averages.setOpaque(false);
		awards = new JCheckBox("Show Awards");
		awards.setBounds(396, 247, 150, 20);
		awards.addActionListener(this);
		awards.setOpaque(false);
		
		//adds everything
		mainPanel.add(jsp);
		mainPanel.add(averages);
		mainPanel.add(awards);
		add(mainPanel);

		setVisible(true);
	}

	// shows a column that is presumably hidden
	private void showColumn(JTable theTable, TableColumn toShow) {
		theTable.getColumnModel().addColumn(toShow);
	}

	// hides a column from theTable based off colName
	// returns that hidden column
	private TableColumn hideColumn(JTable theTable, String colName) {
		TableColumn toReturn = theTable.getColumn(colName);
		theTable.getColumnModel().removeColumn(toReturn);
		return toReturn;
	}
	
	public void actionPerformed(ActionEvent ae) {

		String command = ae.getActionCommand();
		
		//if the show averages check box is clicked, it will show the averages and if unclicked, it hides it
		if (command.equals("Show Averages")) {
		
			if ((averages.isSelected())) {
				showColumn(theTable, average);
			} 
			else {
				hideColumn(theTable, "Average");

			}
			
		}
		
		//if the show awards button was clicked, it highlights or leaves some unchanged. if unclicked, whitens everything back.
		//if averages are just revealed after the average box was just clicked/unclicked and the show awards button was selected, it will appropriately highlights or unhighlight the averages column
		if (command.equals("Show Awards") || (averages.isSelected())) {

			DefaultTableCellRenderer theRenderer;
			
			if (awards.isSelected()) {

				theRenderer = new DefaultTableCellRenderer() {
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int col) {

						Component toReturn = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, col);

						// if the APStudent's status is regular, there will be no (white) highlighter.
						if (theStudents.get(row).status == STATUS.REGULAR) {
							toReturn.setBackground(Color.white);
							toReturn.setForeground(Color.black);
						}

						else {

							// Scholar students get red highlights.
							if ((theStudents.get(row).status == STATUS.SCHOLAR)) {
								toReturn.setBackground(Color.BLUE);
							}
							// Honors students get blue highlights
							else {
								toReturn.setBackground(Color.red);
							}

							toReturn.setForeground(Color.white);

						}

						return toReturn;
					}

				};

			}

			// this will make it so it whitens everything back to normal
			else {
				theRenderer = new DefaultTableCellRenderer();
			}

			int ind;

			// if the average box was just clicked while the award box was already selected, only the newly revealed averages need to be (un)highlighted
			if (command.equals("Show Averages")) {

				ind = theTable.getColumnCount() - 1;
			}

			// if the award box was just clicked or unclicked, it will go through everyncollumn and highlight or unhighlight appropriately
			else {
				ind = 0;
			}

			// sets the renders that need to be highlighted or unhighlighted
			for (; ind < theTable.getColumnCount(); ind++) {

				theTable.getColumnModel().getColumn(ind).setCellRenderer(theRenderer);
			}

			// does the highlinghting and unlighting
			theTable.repaint();

		}
	}

	//this class represents a list of all the students in the school
	class AllStudents extends ArrayList<APStudent> {

		public AllStudents(String fName) {

			Scanner file = null;

			//gets the scores file
			try {
				file = new Scanner(new File("apscores.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				System.exit(-1);
			}

			//checks each two lines of the file
			while (file.hasNextLine()) {

				//gets the name and scoreList and adds a APStudent object based on them to the list
				String name = file.nextLine();
				String scoresList = file.nextLine();
				add(new APStudent(name, scoresList));
				
			}

			file.close();
		}

	}

	// this class that represent a student who takes AP Exams with a name, scores, and award status
	class APStudent {

		private String name;
		private int[] scores;
		private double testAvg;
		private STATUS status;

		public APStudent(String n, String scoresList) {

			name = n;
			int scoreTot = 0;
			int total = 0;
			scores = new int[5];

			// goes through each score in the list
			for (int i = 0; i < 5; i++) {
				int score = i + 1;

				// translates the score amount to an integer and puts it into array
				scores[i] = Integer.parseInt(scoresList.substring(i, score));

				// adds to the total amount of score points and total amount of quantity of scores
				scoreTot += scores[i] * score;
				total += scores[i];
			}

			// calculates average
			testAvg = (double) scoreTot / total;

			//an AP Scholar student is a student who scored a 3 or better on 3 or more exams
			if (scores[2] + scores[3] + scores[4] >= 3) {

				//An AP Honors Student is a scholar with an average of 3.75 or better on all tests 
				if (testAvg >= 3.75) {
					status = STATUS.HONOR;
				} 
				else {
					status = STATUS.SCHOLAR;
				}
			} 
			
			//A student with is not Honors or Scholar is just regular
			else {
				status = STATUS.REGULAR;
			}

		}
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
		new ScholarTable();
	}

}
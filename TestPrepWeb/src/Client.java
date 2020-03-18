import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

/*
 * Name: William Chung
 * 
 * This program represents a GUI that helps student prepare for
 * a certain test and communicates with the server that has the 
 * questions
 */

public class Client extends JFrame implements ActionListener{

	private int questInd;
	private DefaultListModel ansModel;
	private JList ansList;
	private int numCorrect;
	private JLabel percLabel;
	private JLabel numberLabel;
	private JButton button;
	private JTextArea qArea;
	private final String SERVER_IP = "10.104.7.250";
	private final int SERVER_PORT = 4242;
	private final String name;
	private Socket sock;
	private Scanner in;
	private PrintWriter out;
	private int numQuests;
	
	public Client(String fName) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is your name?");
		name = keyboard.nextLine();

		try {
			sock = new Socket(SERVER_IP, SERVER_PORT);
			in = new Scanner(sock.getInputStream());
			out = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();

		}
		
		out.println(name);
		out.flush();
		numQuests = Integer.parseInt(in.nextLine());
		
		// makes the frame and launches the GUI after getting the needed info
		setTitle("AP Prep");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 756);

		// makes the background
		PicPanel mainPanel = new PicPanel("Aplus3.jpg");
		mainPanel.setLayout(null);

		// makes the text area for the given question from the server and a blue border and border title
		qArea = new JTextArea(in.nextLine());
		qArea.setBounds(50, 75, 381, 194);
		qArea.setEditable(false);
		qArea.setLineWrap(true);
		qArea.setWrapStyleWord(true);
		Border blueBorder = BorderFactory.createLineBorder(new Color(100, 166, 215));
		qArea.setBorder(BorderFactory.createTitledBorder(blueBorder, "Question"));

		// makes the list that can only have single selection based on all answers of
		// the current question
		ansModel = new DefaultListModel();

		//puts in the answer choices from the server
		for (int i = 0; i < 4; i++) {
			ansModel.addElement(in.nextLine());
		}

		ansList = new JList(ansModel);
		ansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ansList.setBounds(50, 318, 381, 176);

		// sets a blue border and a border title for the list
		ansList.setBorder(BorderFactory.createTitledBorder(blueBorder, "Answer"));

		// makes a label to show the question number
		numberLabel = new JLabel("Question 1 of " + numQuests);
		numberLabel.setBounds(172, 10, 300, 60);
		numberLabel.setFont((new Font("Times New Roman", 0, 22)));

		// makes a label to show the percentage correct
		percLabel = new JLabel("Percentage Correct: 0.0");
		percLabel.setBounds(150, 500, 290, 200);
		percLabel.setFont((new Font("Times New Roman", 0, 22)));

		// makes a button that the user can press to submit their answer
		button = new JButton("Submit");
		button.setBounds(194, 520, 96, 34);
		button.addActionListener(this);

		// adds everything where it needs to be
		mainPanel.add(qArea);
		mainPanel.add(ansList);
		mainPanel.add(numberLabel);
		mainPanel.add(percLabel);
		mainPanel.add(button);
		add(mainPanel);

		setVisible(true);
	}

	//happens when user presses a button
	public void actionPerformed(ActionEvent ae) {

		//sends the user the given index
		out.println(ansList.getSelectedIndex());
		out.flush();
		
		String result = in.nextLine();
		
		//pops up a box that informs the user if they answered the question correctly or not. updates numCorrect based on it
		if((result.equals("You answered it correctly"))) {
			numCorrect++;
		} 

		
		JOptionPane.showMessageDialog(null, result);
		
		//updates the question index after the previous has been answered and percentage correct
		questInd++;
		double percCorrect = (double) numCorrect / (questInd) * 100;
		percLabel.setText("Percentage Correct: " + (percCorrect));

		//if the user has answered all questions, the button is disabled and it shows the high score player name or quantity
		if (questInd == numQuests) {
			button.setEnabled(false);
			out.println(numCorrect);
			out.flush();
			JOptionPane.showMessageDialog(null, in.nextLine());
		}

		//if not, it updates the question label, the question text, and the possible answers list with the server's info
		else {

			numberLabel.setText("Question " + (questInd + 1) + " of " + numQuests);
			qArea.setText(in.nextLine());
			for (int i =0; i<4;i++ ){
				ansModel.setElementAt(in.nextLine(),i);
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


	public static void main(String[] args) {
		Client t = new Client("questions.txt");
	}

}

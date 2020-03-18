import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
 * Uses the AllQuestions project to create a GUI to prepare students for a
 * given test.
 */
public class TestPrep extends JFrame implements ActionListener{

	private int questInd;
	private DefaultListModel ansModel;
	private JList ansList;
	private AllQuestions quests;
	private int numCorrect;
	private JLabel percLabel;
	private JLabel numberLabel;
	private JButton button;
	private JTextArea qArea;

	public TestPrep(String fName) {

		//gets all the questions from the file
		quests = new AllQuestions(fName);

		//makes the frame
		setTitle("AP Prep");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 756);

		//makes the background
		PicPanel mainPanel = new PicPanel("Aplus3.jpg");
		mainPanel.setLayout(null);

		//makes the text area for the given question and a blue border and border title
		qArea = new JTextArea(quests.get(questInd).getQuestion());
		qArea.setBounds(50, 75, 381, 194);
		qArea.setEditable(false);
		qArea.setLineWrap(true);
		qArea.setWrapStyleWord(true);
		Border blueBorder = BorderFactory.createLineBorder(new Color(100, 166, 215));
		qArea.setBorder(BorderFactory.createTitledBorder(blueBorder, "Question"));

		//makes the list that can only have single selection based on all answers of the current question
		ansModel = new DefaultListModel();
		
		for (String ans: quests.get(questInd).getPossAns()){
			ansModel.addElement(ans);
		}
		
		ansList = new JList(ansModel);
		ansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ansList.setBounds(50, 318, 381, 176);

		//sets a blue border and a border title for the list
		ansList.setBorder(BorderFactory.createTitledBorder(blueBorder, "Answer"));

		//makes a label to show the question number
		numberLabel = new JLabel("Question 1 of " + quests.size());
		numberLabel.setBounds(172, 10, 300, 60);
		numberLabel.setFont((new Font("Times New Roman", 0, 22)));

		//makes a label to show the percentage correct
		percLabel = new JLabel("Percentage Correct: 0.0");
		percLabel.setBounds(150, 500, 290, 200);
		percLabel.setFont((new Font("Times New Roman", 0, 22)));

		//makes a button that the user can press to submit their answer
		button = new JButton("Submit");
		button.setBounds(194, 520, 96, 34);
		button.addActionListener(this);

		//adds everything where it needs to be 
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

		//gets current question
		Question quest = quests.get(questInd);

		//pops up a box that the informs the user if they answered the question correctly or not. updates numCorrect based on it
		if((ansList.getSelectedIndex() == quest.getAnsLoc())) {
			JOptionPane.showMessageDialog(null, "You answered it correctly");
			numCorrect++;
		} else {
			JOptionPane.showMessageDialog(null, "You answered it incorrectly");
		}

		//updates the question index after the previous has been answered and percentage correct
		questInd++;
		percLabel.setText("Percentage Correct: " + ((double) numCorrect / (questInd) * 100));

		//if the user has answered all questions, the button is disabled
		if (questInd == quests.size()) {
			button.setEnabled(false);
		}

		//if not, it updates the question label, the question tet, and the possible answers list
		else {

			numberLabel.setText("Question " + (questInd + 1) + " of " + quests.size());

			for (int i =0; i<4;i++ ){
				ansModel.setElementAt(quests.get(questInd).getPossAns().get(i),i);
			}

			qArea.setText(quests.get(questInd).getQuestion());
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
		TestPrep t = new TestPrep("questions.txt");
	}

}

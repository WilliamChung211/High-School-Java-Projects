import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

/*
 * Name: William Chung
 * 
 * The class represents the server which will maintain the questions
 * and checking if it correct
 */
public class Server {

	private AllQuestions quests;
	private int numOfTopPlayers;
	private int highNum;
	private String highScorePlayer;

	public Server() {

		
		highNum=-1;
		quests = new AllQuestions("questions.txt");

		try {
			ServerSocket server = new ServerSocket(4242); 

			//keeps accepting clients that try to connect forever and creates threads for them
			while (true) {
				
				Socket theSock = server.accept();
				ClientHandler handler = new ClientHandler(theSock);
				new Thread(handler).start();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	// handles the client that connects to the server prints out requests and sends requested file, if it exists
	private class ClientHandler implements Runnable {

		private Scanner in;
		private PrintWriter out;

		// creates a thread that handles a client
		public ClientHandler(Socket s) {

			try {
				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.exit(2);
			}
		}

		//sends questions, gets answers, gives results to the player
		public void run() {

			String playName = in.nextLine();

			out.println(quests.size());
			out.flush();

			for (int i = 0; i < quests.size(); i++) {
				
				//gives the client the question and answer choices to put in the GUI
				out.println(quests.get(i).quest);
				out.flush();

				for (String ans : quests.get(i).getPossAns()) {
					out.println(ans);
					out.flush();
				}

				//gets the guess from the client and gives by result
				int selectedInd = Integer.parseInt(in.nextLine());

				if ((selectedInd == quests.get(i).getAnsLoc())) {
					out.println("You answered it correctly");
				} 
				else {
					out.println("You answered it incorrectly");
				}
				
				out.flush();

			}

			int score = Integer.parseInt(in.nextLine());
			
			//updates the player with the high score depending on the player's score
			synchronized (quests) {
				
				if (score > highNum) {
					highNum = score;
					highScorePlayer = playName;
					numOfTopPlayers=1;
				}
				else if (score == highNum) {
					numOfTopPlayers++;
				}
	
				//gives a message with the important info
				if(numOfTopPlayers==1) {
					out.println( highScorePlayer + " has the high score with " + highNum + " correct!");
				}
				else {
					out.println(numOfTopPlayers + " players have the high score with "+ highNum + " correct!");
				}
				
				out.flush();
			
			}

		}

	}

	public class AllQuestions extends ArrayList<Question> {

		public AllQuestions(String fileName) {

			Scanner fileIn = null;

			try {
				fileIn = new Scanner(new File(fileName));

			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				System.exit(-1);
			}

			// goes through each question and answers in the formatted lines of the file
			while (fileIn.hasNextLine()) {

				// gets the questions
				String question = fileIn.nextLine();
				ArrayList<String> ans = new ArrayList<String>();

				// gets the possible answers
				for (int i = 0; i < 4; i++) {
					ans.add(fileIn.nextLine());
				}

				// gets the location of the correct answer
				int loc = fileIn.nextInt();
				fileIn.nextLine();

				// adds a constructed question based on what it recently got
				add(new Question(question, ans, loc));

			}

			fileIn.close();

			// shuffles the all the questions
			shuffle();
		}

		// picks a question and moves it to the end
		public void shuffle() {

			int randNum = (int) (Math.random() * 50) + 1;

			for (int i = 0; i < randNum; i++) {

				// gets a random index
				int randInd = (int) (Math.random() * size());

				// puts the question at the end based on the index (if the index is not
				// alreadyat the end)
				if (randInd != 3) {
					add(get(randInd));
					remove(randInd);
				}

			}

		}

	}

	public class Question {
		private String quest;
		private ArrayList<String> possAns;
		private int ansLoc;

		public Question(String ques, ArrayList<String> ans, int loc) {

			if ((loc > 3) || (loc < 0)) {
				throw new IllegalArgumentException("Has to be in [0,3]");
			}

			if (ans.size() != 4) {
				throw new IllegalArgumentException("All questions can only have 4 possible answers.");
			}

			ansLoc = loc;
			quest = ques;
			possAns = ans;

			// shuffles all the answer choices
			shuffle();

		}

		// randomly selects one of the options and moves it to the end of the array in a
		// random number of times [0,50]
		private void shuffle() {

			int randNum = (int) (Math.random() * 50) + 1;
			for (int i = 0; i < randNum; i++) {

				// gets a random index
				int randInd = (int) (Math.random() * 4);

				if (randInd != 3) {

					// moves it to the end of the array (if not already at it)
					possAns.add(possAns.get(randInd));
					possAns.remove(randInd);

					// updates the correct answer location if needed
					if (randInd == ansLoc) {
						ansLoc = 3;
					} else if (ansLoc > randInd) {
						ansLoc--;
					}

				}

			}

		}

		public ArrayList<String> getPossAns() {
			return possAns;
		}

		public String getQuestion() {
			return quest;
		}

		public int getAnsLoc() {
			return ansLoc;
		}
	}

	// starts the server
	public static void main(String[] args) {
		new Server();
	}
}
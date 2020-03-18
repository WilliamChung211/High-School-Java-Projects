import java.util.ArrayList;

/*
 * Name: William Chung
 * 
 * This class represents a single question, maintains the question, an arraylist
 * of the possible answers, and correct answer;s location in the arraylist
 */

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
		
		//shuffles all the answer choices
		shuffle();

	}

	// randomly selects one of the options and moves it to the end of the array in a random number of times [0,50]
	private void shuffle() {

		int randNum = (int) (Math.random() * 50) + 1;
		for (int i = 0; i < randNum; i++) {

			//gets a random index
			int randInd = (int) (Math.random() * 4);

			if (randInd != 3) {
				
				//moves it to the end of the array (if not already at it)
				possAns.add(possAns.get(randInd));
				possAns.remove(randInd);
				
				//updates the correct answer location if needed
				if (randInd == ansLoc) {
					ansLoc = 3;
				} 
				else if (ansLoc > randInd) {
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

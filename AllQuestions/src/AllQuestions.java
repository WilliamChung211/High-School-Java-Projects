import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * This class represents all of the questions by being a list of 
 * all the questions. It takes in the questions from a properly
 * formatted file.
 */
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
			
			//gets a random index
			int randInd = (int) (Math.random() * size());

			//puts the question at the end based on the index (if the index is not alreadyat the end)
			if (randInd != 3) {
				add(get(randInd));
				remove(randInd);
			}

		}

	}
}

import java.io.*;
import java.util.Scanner;
/* Name: William Chung
 * Description: Prompts the user for a phone number, generates all 7 letter
 *  combinations based on the digit in the phone number, and prints 
 *  it out if it is found in the world list dictionary file.
 */

public class Chung_WilliamPhoneNumberWordGenerator {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		// prompts user for a 7 digit phone number with all digits being between 2 and 7
		System.out.println("Enter a phone number.");
		String pNumber = keyboard.nextLine();
		
		//stores all the words in the dictionary word list file in an array
		String [] dicWords = loadWords ("wordList.txt");
		
		generateWord(pNumber, 0, "", dicWords);
		
	}

	// generates all potential 7 letter combinations based on the phone number and prints the combo if it is found in the dictionary
	public static void generateWord(String pNumber, int index, String wordSoFar, String [] dicWords) {
		
		//makes a string for all 24 letters that correspond to a digit between 2 and 7
		String abc = "ABCDEFGHIJKLMNOPRSTUVWXY";

		// if the letter combination has 7 letters, it prints it if it is a word in the dictionary
		if (wordSoFar.length() == 7) {
			
			for (int i = 0; i<dicWords.length;i++){
				
				if (dicWords[i].equalsIgnoreCase(wordSoFar)){
				System.out.println(wordSoFar);
				}
				
			}
			
		}
		else {
			
			int dig = Integer.parseInt(pNumber.substring(index, index + 1));
			dig = (dig - 2) * 3;

			// adds one of the three letter corresponding the digit to the potential letter combinations and repeats
			generateWord(pNumber, index + 1, wordSoFar + abc.substring(dig, dig + 1), dicWords);
			generateWord(pNumber, index + 1, wordSoFar + abc.substring(dig + 1, dig + 2), dicWords);
			generateWord(pNumber, index + 1, wordSoFar + abc.substring(dig + 2, dig + 3), dicWords);

		}

	}
	
	//loads and reads the dictionary word list file and stores each line/word into an array. 
	public static String[] loadWords (String fname) {

		Scanner fileIn = null;
		String word = "";
		String[] words = new String[0];
		try {
			fileIn = new Scanner(new File(fname));
		} 
		catch (FileNotFoundException e) {
			return words;
		}

		String[] tempWords = new String[1];
		
		//makes every new line/word one part of the array
		while (fileIn.hasNextLine()) {

			word = fileIn.nextLine();
			tempWords = new String[words.length + 1];
			
			for (int i = 0; i < words.length; i++) {
				tempWords[i] = words[i];
			}
			
			tempWords[tempWords.length - 1] = word;
			words = tempWords;
		}
		
		return words;
	}
	

}

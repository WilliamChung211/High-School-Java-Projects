/*
 * Name: William Chung
 * This class counts the total number of each letter of the alphabet in a given word 
 * and also checks if two objects arrays have the same data.
 */

public class StringFrequency {

	private int[] letCounter;
	private String word;
	
	//takes in a String parameter and builds an array to count how many letters are in a word. 
	public StringFrequency(String wor) {
		
		word = wor;
		wor = wor.toUpperCase();
		letCounter = new int[26];
		
		for (int i = 0; i < wor.length(); i++) {
		
			//adds one to the counter for a letter in the array if that letter is found
			int let = wor.charAt(i) - 65;
			letCounter[let]++;
			
		}

	}

	public StringFrequency(StringFrequency other) {

		this(other.word);

	}

	public String getWord() {
		
		return word;
		
	}

	//returns true if the calling object's array and the parameters array have same data for counting letters
	public boolean hasSameFrequency(StringFrequency other) {

		for (int i = 0; i < letCounter.length; i++) {
			
			if (other.letCounter[i] != letCounter[i]) {
				return false;
			}
			
		}
		
		return true;
	}

}

	import java.util.Scanner;

	/* Name: William Chung
	 * Description: Translates an English Sentence to Pig Latin based on an algorithm and vowel number.
	 */


	public class Chung_William__Pig_Latin{
		
		public static void main(String[] args) {
			Scanner keyboard = new Scanner(System.in);
			//prompts the user for an entire sentence in English
			System.out.println("Enter a full English sentence to be translated to Pig Latin");
			String sentence = keyboard.nextLine();
			//translates the sentence and displays the translation
			System.out.println(phraseToPig(sentence));

		}

		
		// returns true if world is Capitalized
		public static boolean isCapitalized(String word) {
			int result = word.compareTo("a");
			if (result >= 0) {
				return false;
			}
			
			return true;

		}

		
		// capitalizes the first letter
		public static String capitalize(String word) {
			String firstLetter = word.substring(0, 1);
			firstLetter = firstLetter.toUpperCase();
			word = firstLetter + word.substring(1);
			return word;
		}

		
		// converts english word to PL
		public static String wordToPig(String english) {
			int vowelSpot = hasAVowel(english);
			
			//the result if the English word has a vowel that is not first letter
			if (vowelSpot > 0) {
				String firsthalf = english.substring(0, vowelSpot);
				String secondhalf = english.substring(vowelSpot, english.length());
				
				if ((isCapitalized((firsthalf)) == true)) {
					secondhalf = capitalize(secondhalf);
					firsthalf = firsthalf.toLowerCase();
				}
				
				english = secondhalf + firsthalf;
			}
			
			//the result if the English word has a vowel that is the first letter
			if (vowelSpot == 0)
				english += "y";
			
			//all pig latin words end with ay
			return english + "ay";
		}

		
		// converts sentence to PL
		public static String phraseToPig(String word) {
			String[] words = word.split(" ");
			String pigLatin = "";
			
			//seperates every english word to be translated
			for (int i = 0; i < words.length; i++) {
				String singleWord = ((words[i]));
				pigLatin += wordToPig(singleWord) + " ";
			}
			
			return pigLatin;
		}

		
		// returns index of first vowel (all 10)
		// returns -1 if no vowel in word
		public static int hasAVowel(String word) {
			word = word.toUpperCase();
			String vowels = "AEIOU";
			
			//checks if and when the word has a vowel
			for (int i = 0; i < word.length(); i++) {
				
				for (int k = 0; k<=4; k++){
					
					if (word.charAt(i)==vowels.charAt(k)){
						return i;
					}
					
				}
			}
			return -1;
		}
	}
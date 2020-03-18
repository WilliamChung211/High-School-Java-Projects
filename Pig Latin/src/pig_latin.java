import java.util.Scanner;
public class pig_latin {
	
	public static void main(String[]args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter English to be translated to Pig Latin");
		String translated = keyboard.nextLine();
		if (isCapitalized(translated)==true) {
			System.out.println("True");
		}; 
	
		System.out.println(capitalize(translated));
			

	
	}
	
	//returns true if world is Capitalized
	public static boolean isCapitalized (String word) { 
		int result = word.compareTo("a");
		if (result>=0) {
			return false;
		}
	return true;
		
	}
	
	//capitalizes the first letter 
	public static String capitalize (String word) {
		String firstLetter = word.substring(0,1);
		firstLetter = firstLetter.toUpperCase();
		return firstLetter;
		
	}
	
	//converts english word to PL
	public static String wordToPig (String english) {
		int vowelSpot = hasAVowel(english);
		if (vowelSpot>-1) {
		String firsthalf = english.substring(0,vowelSpot);
		String secondhalf = 
		}
	}
	
	//coverts sentence to PL
	public static String phraseTopig (String word) {
		
	}
	
	//returns index of first voewl (all 10)
	//returns -1 if no vowel in word
	public static int hasAVowel (String word) {
		
	
	}
	
}

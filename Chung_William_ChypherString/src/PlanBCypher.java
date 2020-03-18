import java.util.Random;
import java.util.Scanner;



public class PlanBCypher {
	
	public static void main (String[] aggs) {
		Scanner keyboard = new Scanner(System.in);
		String abc = "abcdefghijklmnopqrstuvwxyz";
		String key = keyMaker (abc);
		System.out.println(key);
		
		System.out.println("Enter a word");
		String input = keyboard.nextLine();
		
		String translated = "";
	
		for (int i = 0; i<input.length();i++){
			String letterInp = input.substring(i,i+1);
			int loc = abc.indexOf(letterInp);
			translated+= key.substring(loc,loc+1);
	
		}
	
		System.out.println(translated);
		
	}
	
	public static String keyMaker (String abc) {
		Random rGen = new Random(0);
		for (int i = 0; i<=25; i++){
			int letLoc = rGen.nextInt(26-i);
			String letter = abc.substring(letLoc,letLoc+1);
			abc+=letter;
			abc = abc.substring(0,letLoc)+ abc.substring(letLoc+1);
		}
		return abc;
	}
}

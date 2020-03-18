	/* Name: William Chung
	 * Program Discription: This program gives the user menu options repeatedly and does things to a  based on the option chosen.
	 */

import java.io.*;
import java.util.*;

public class Chung_William_FileEditor{

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int input;
		String[] beforeChange = new String[0];
		String fName = "";
		String[] lines = new String[0];
		
		//prompts user to choose either of the 7 menu options until the user picks 7
		do {
			System.out.println("1. Load ");
			System.out.println("2. Display ");
			System.out.println("3. Save ");
			System.out.println("4. Find Word");
			System.out.println("5. Replace Word");
			System.out.println("6. Undo Last Change");
			System.out.println("7. Exit");
			input = keyboard.nextInt();
			keyboard.nextLine();
			
			//does the command based on the option
			if (input == 1) {
				beforeChange = copy(lines);
				System.out.println("Enter ");
				fName = keyboard.nextLine();
				lines = loadFile(fName);
			}
			
			else if (input == 2) {
				displayFile(lines);
			}
			
			else if (input == 3) {
				saveFile(fName, lines);
			}
			
			else if (input == 4) {
				System.out.println("What word do you want to find?");
				String word = keyboard.nextLine();
				findWord(lines, word);
			}
			
			else if (input == 5) {
				System.out.println("What word do you want to replace?");
				String oldWord = keyboard.nextLine();
				System.out.println("What do you want to replace the word with?");
				String newWord = keyboard.nextLine();
				beforeChange = copy(lines);
				replaceAllLines(lines, oldWord, newWord);
			}
			
			//makes the lines the same as before the most recent loading of a  or replacing of word
			else if (input == 6) {
				lines = beforeChange;
			}
		
		} while (input != 7);

	}
	
	//Builds and returns an array representing all of the lines in file fname. 
	public static String[] loadFile(String fname) {

		Scanner fileIn = null;
		String line = "";
		String[] lines = new String[0];
		try {
			fileIn = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			System.out.println("not found");
			return lines;
		}

		String[] tempLines = new String[1];
		
		//makes every new line one part of the array
		while (fileIn.hasNextLine()) {

			line = fileIn.nextLine();
			tempLines = new String[lines.length + 1];
			
			for (int i = 0; i < lines.length; i++) {
				tempLines[i] = lines[i];
			}
			
			tempLines[tempLines.length - 1] = line;
			lines = tempLines;
		}
		return lines;
	}

	//Prints all lines in the file out to the screen
	public static void displayFile(String[] lines) {
		
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
		
	}
	
	//Writes all lines back to the file represented by fname
	public static void saveFile(String fname, String[] lines) {
		Scanner fileIn = null;
		
		try {
			FileWriter outfile = new FileWriter(fname);
			for (int i = 0; i < lines.length; i++) {
				outfile.write(lines[i] + "\r\n");
			}
			
			outfile.close();
		} catch (IOException e) {
			System.out.println("There is an IO issue.");
			System.exit(0);
		}
		
	}
	
	//Prints all lines that contain the word and the line number.
	public static void findWord(String[] lines, String word) {
		
		for (int i = 0; i < lines.length; i++) {
			int wordLoc = lines[i].indexOf(word);
			
			if (wordLoc != -1) {
				int lineNum = i + 1;
				System.out.println(lineNum + lines[i]);
			}
		
		}
	}
	
	//Replaces all occurences of a word with a new word and returns the updated line
	public static String replaceLine(String line, String toReplace, String word) {
		String newLine = "";
		
		if (line.indexOf(toReplace) == -1) {
			return line;
		}
		
		while (line.indexOf(toReplace) != -1) {
			int replacedLoc = line.indexOf(toReplace);
			newLine += (line.substring(0, replacedLoc) + word);
			line = line.substring(replacedLoc + toReplace.length());
		}

		return newLine + line;
	}

	//Replaces all lines in the array so a word is replaced to a new word
	public static void replaceAllLines(String[] lines, String toReplace, String word) {
		
		for (int i = 0; i < lines.length; i++) {
			lines[i] = replaceLine(lines[i], toReplace, word);
		}

	}

	//Returns a completely new array containing the same context as lines
	public static String[] copy(String[] lines) {
		String[] copiedLines = new String[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			copiedLines[i] = lines[i];
		}
		
		return copiedLines;
	}

}



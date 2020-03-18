/*
 * Name: William Chung
 * This class finds anagrams which is a word whose letters can be rearranged to form a diffrent word.
 */

import java.io.*;
import java.util.Scanner;


public class AnagramFinder {
	
	private StringFrequency [] wordsCounter;
	
	//takes in the file of words and builds an array of StringFrequency objects
	public AnagramFinder(String wordList){
		
		Scanner fileIn = null;
		String word = "";
		wordsCounter = new StringFrequency[0];
		
		try {
			fileIn = new Scanner(new File(wordList));
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}

		StringFrequency[] tempCounter = new StringFrequency[1];
		
		//goes through every word in the file
		while (fileIn.hasNextLine()) {

			word = fileIn.nextLine();
			
			
			tempCounter = new StringFrequency [wordsCounter.length + 1];
			
			for (int i = 0; i < wordsCounter.length; i++) {
				tempCounter[i] = wordsCounter[i];
			}
			
			//builds the StringFrequency array based on each word                                                                                                                                                                                                                            
			tempCounter[tempCounter.length - 1] = new StringFrequency(word);
			wordsCounter = tempCounter;
		}
	}

	//finds the matching anagrams, words with the same frequency of letters, of a word and prints them out. 
	public void printAngrams(String word){
		StringFrequency inWordCount = new StringFrequency(word);
		
		for (int i =0; i<wordsCounter.length;i++){
			
			if (inWordCount.hasSameFrequency(wordsCounter[i])){
				System.out.println(wordsCounter[i].getWord());
			}
			
		}
	}
	
	//prompts the user for a word and prints out matching anagrams for it.
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a word");
		String word = keyboard.nextLine();
		AnagramFinder finder = new AnagramFinder("WordsList");
		finder.printAngrams(word);
		
	
	
	}
	
	

}

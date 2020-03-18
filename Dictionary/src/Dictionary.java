import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * Name: William Chung
 * 
 * This is a subclass of the ArrayList that stores Definition objects and builds a dictionary of 
 * words and their definitions
 */

public class Dictionary extends ArrayList<Definition>{
	
	public Dictionary(){
		super();
	}
	
	//builds an entire dictionary of definition based on the file
	public Dictionary (String fileName){
		
		Scanner fileIn = null;

		try {
			fileIn = new Scanner(new File(fileName));

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}
		
		//goes through each line of the file
		while (fileIn.hasNextLine()) {
			
			String line = fileIn.nextLine();
			int sep = line.indexOf(" ");
			
			//adds a definition object to the list based on the word and the definition in the line (that were seperated by a space).
			add(new Definition(line.substring(0,sep),line.substring(sep+1)));

		}
		   
		fileIn.close();
	}
	
	//takes in a String and returns a Dictionary of words in the file that either partially or completely matches the input
	public Dictionary getHits(String word) {
		
		Dictionary dic = new Dictionary();
		
		for (int i = 0; i<size();i++) {
			
			//checks to see if a word in the list at least partially matches the word parameter and adds it to the new object if it does.
			if (get(i).getWord().indexOf(word)!=-1) {
				dic.add(get(i));
			}
		}
		
		return dic;
	
	}
	
}
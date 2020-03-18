/*
 * Name: William Chung
 * 
 * This program is an extension of linked list and takes in a text file
 * and adds every line in the text file as a separate element to the linked list
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileLinkedList extends LinkedList<String> {

	public FileLinkedList(String fileName){

		Scanner fileIn = null;

		//scans the file
		try {
			fileIn = new Scanner(new File(fileName));

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}
		
		//goes through each line of the file and adds it as a separate element to the linked list
		while (fileIn.hasNextLine()) {
			add(fileIn.nextLine());
		}
		   
		fileIn.close();
	}
	
}

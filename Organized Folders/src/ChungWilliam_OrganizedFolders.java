import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/*
 * Name: William Chung
 * 
 * This program takes in a file that contains entries and sub entries nested
 * to several levels. Sub-entries by deeper indentation. The program checks if
 * it is alphabetical. We assume the person indents correctly.
 */
public class ChungWilliam_OrganizedFolders {

	public static void main(String[] args) {

		Scanner file = null;

		//takes in the file
		try {
			file = new Scanner(new File("fileTree.txt"));
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(-1);
		}

		//checks if there is an entry in the file
		if (file.hasNext()) {

			//if so, we assume the file is correctly indented, so there is no indentation in the first one. we also store those values
			String trimPLine = file.nextLine();
			int prevWSpace = 0;
			Stack<String> stck = new Stack<String>();

			//keeps checking till there is no more entries
			while (file.hasNext()) {

				//gets the next entry, its trimmed version, and the amount of relative white space it has
				String nextLine = file.nextLine();
				String trimNLine = nextLine.trim();
				int nextWSpace = nextLine.length() - trimNLine.length();

				//if the next entry is a sub-entry of the previous entry, it stores the previous entry and moves on
				if (prevWSpace < nextWSpace) {
					stck.push(trimPLine);
				}
				
				else {
					
					//if the next entry is not at the same level of the previous entry, it compares it with the last non sub-entry stored
					if (prevWSpace > nextWSpace) {
						trimPLine = stck.pop();
					}
					
					//checks if the previous line and next level at the same level is in alphabetical order
					if (trimPLine.compareTo(trimNLine) > 0) {
						
						//if not it prints out those two lines and tells the user about it
						System.out.println(trimPLine);
						System.out.println(trimNLine);
						System.out.println("^These two lines violate the entries' alphabetical order");
						return;
					}

				}
				
				//updates what was the previous line as it moves to the next line
				trimPLine = trimNLine;
				prevWSpace = nextWSpace;
			}

		}

		//after it is done confirming everything, it prints out a victory message
		System.out.println("The entries are in alphabetical order!");
		
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Cow {

	public static void main(String[] args) {

		Scanner fileIn = null;

		try {
			fileIn = new Scanner(new File("Sudoku"));
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}
		
		int numCows = fileIn.nextInt();
		for(int i =0;i<numCows;i++) {
		
		}

		FileWriter outfile  = new FileWriter("file escape.out");
	}
}

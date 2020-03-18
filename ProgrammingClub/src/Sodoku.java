import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sodoku {

	public static void main (String[]args) {
	Scanner fileIn = null;

	try {
		fileIn = new Scanner(new File ("Sudoku"));
	} catch(FileNotFoundException e) {
		System.exit(-1);
	}
	
		int[][]matrix = new int[9][9];
		
		for(int r =0;r<9;r++) {
			for(int c = 0;c<9;c++) {
				
	 
				matrix[r][c]= fileIn.nextInt();
				System.out.println(matrix[r][c]);
			}
		}
	
	}

}

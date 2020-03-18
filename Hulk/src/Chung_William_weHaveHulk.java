
/*
 * Name: William Chung
 * 
 * This program reads a city grid from a file
 * and reports the largest number of minions destroyed 
 * on the optimal path. We assume the file is correctly
 * put. A -1 represents an impassable sector. A positive
 * number is the maximize number of minions. Now the function
 * is recursive. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chung_William_weHaveHulk {

	private int[][] matrix;
	
	public Chung_William_weHaveHulk(String fName, int dim) {

		Scanner file = null;

		// takes in the file
		try {
			file = new Scanner(new File(fName));
		}

		catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(-1);
		}

		matrix = new int[dim][dim];

		// makes the matrix based on the grid
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				matrix[row][col] = file.nextInt();
			}
		}

	}

	// this method returns the most baddies fought starting at row, col through the bottom right corner of the matrix
	public int getTotal(int row, int col) {
		if ((row+1==matrix.length)&&(col+1==matrix.length)) {
			return matrix[row][col];
		}
		
		int rightVal = Integer.MIN_VALUE;
		int downVal = Integer.MIN_VALUE;
		
		if(canDown(row,col)) {
			downVal = getTotal(row+1,col);
		}
		
		if(canRight(row,col)) {
			rightVal = getTotal(row,col+1);
		}
		
		return matrix[row][col] + Math.max(downVal,rightVal);
		
	}
	
	private boolean canDown(int row,int col) {
		return (row+1<matrix.length)&&(matrix[row+1][col]!=-1);
	}

	private boolean canRight(int row,int col) {
		return (col+1<matrix.length)&&(matrix[row][col+1]!=-1);
	}

	// prints out max number of baddy minions hulk can smash
	public static void main(String[] args) {
		Chung_William_weHaveHulk hi = new Chung_William_weHaveHulk("CP74.txt", 12);
		System.out.println(hi.findPath(0, 0));
	}

}
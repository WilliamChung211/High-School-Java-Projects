/*
 * Name: William Chung
 * 
 * This program reads a city grid from a file
 * and reports the largest number of minions destroyed 
 * on the optimal path. We assume the file is correctly
 * put. A -1 represents an impassable sector. A positive
 * number is the maximize number of minions. Now the function
 * is recursive. Also, now there is dynamic programming and a block
 * class to avoid recalculating.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chung_William_HulkTired {

	private Block[][] matrix;

	public Chung_William_HulkTired(String fName, int dim) {

		Scanner file = null;

		// takes in the file
		try {
			file = new Scanner(new File(fName));
		}

		catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(-1);
		}

		matrix = new Block[dim][dim];

		// makes the matrix based on the grid. initializes everything here
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				matrix[row][col] = new Block();
				matrix[row][col].local=file.nextInt();
				matrix[row][col].best = Integer.MIN_VALUE;
			}
		}

	}

	// this method returns the most baddies fought starting at row, col through the bottom right corner of the matrix
	public int getTotal(int row, int col) {
		
		//if it is at the end, there is more best path and it returns the local
		if ((row+1==matrix.length)&&(col+1==matrix.length)) {
			matrix[row][col].best =matrix[row][col].local;
			return matrix[row][col].best;
		}
		
		int rightVal = -1;
		int downVal = -1;
		
		if(canDown(row,col)) {
		
			//if we did not examine the down path yet, we find its best path
			if(matrix[row+1][col].best==Integer.MIN_VALUE) {
				downVal = getTotal(row+1,col);
			}
			
			//if we did, we set the down value to the best path
			else {
				downVal = matrix[row+1][col].best;
			}
		}
		
		if(canRight(row,col)) {
			
			//if we did not examine the right path yet, we find its best path
			if(matrix[row][col+1].best==Integer.MIN_VALUE) {
				rightVal = getTotal(row,col+1);
			}
			
			//if we did, we set it to the right value
			else {
				rightVal =  matrix[row][col+1].best;
			}
			
		}

		int bestPath = Math.max(downVal,rightVal);
		
		//if there is no valid path, the best path would then be -1
		if(bestPath==-1) {
			matrix[row][col].best = -1;
		}
		
		else {
			matrix[row][col].best = matrix[row][col].local+bestPath;
		}
		
		return matrix[row][col].best;
		
	}
	
	//can't be out of matrix or -1
	private boolean canDown(int row,int col) {
		return (row+1<matrix.length)&&(matrix[row+1][col].local!=-1);
	}

	private boolean canRight(int row,int col) {
		return (col+1<matrix.length)&&(matrix[row][col+1].local!=-1);
	}
	

	//Inner class that maintains a variable which is the number of local baddies fought and the largest number of baddies fought
	public class Block{
		private int local;
		private int best;
	}
	
	// prints out max number of baddy minions hulk can smash
	public static void main(String[] args) {
		Chung_William_HulkTired hi = new Chung_William_HulkTired("CP147.txt", 12);
		System.out.println(hi.getTotal(0, 0));
	
	}

}

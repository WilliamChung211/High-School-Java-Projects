
/*
 * Name: William Chung and Corwin Zhang
 * 
 * This program reads a city grid from a file
 * and reports the largest number of minions destroyed 
 * on the optimal path. We assume the file is correctly
 * put. A -1 represents an impassable sector. A positive
 * number is the maximize number of minions.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Will_Cor_HulkSmash {

	private int[][] matrix;
	
	public Will_Cor_HulkSmash (int[][]mat) {
		
		matrix = new int[mat.length][mat[0].length];
		
		for (int row = 0; row < mat.length; row++) {
			for (int col = 0; col < mat[0].length; col++) {
				matrix[row][col] = mat[row][col];
			}
		}
	}

	public Will_Cor_HulkSmash(String fName, int dim) {

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

	// returns the largest of minions destroyed in the most optimal path. returns -1 if no path found
	public int findPath() {

		int curRow = 0;
		int curCol = 0;
		int curTot = matrix[0][0];
		int maxTotal = -1;

		Stack<Sector> canDownSects = new Stack<Sector>();
		boolean checkDown = true;
		boolean checkedAllSects = false;

		// goes through all sectors till it checks the down paths and right paths of all sectors
		do {

			// if we have not checked down for the spot
			if (checkDown) {

				// we must check if it can go down
				if ((curRow + 1 < matrix.length) && (matrix[curRow + 1][curCol] != -1)) {

					// saves the sector to stack of sectors that we need to check right later
					canDownSects.push(new Sector(curRow, curCol, curTot));
					curRow++;
					curTot += matrix[curRow][curCol];

				}

				// if it cannot go down, we will immediately check right in the next iteration
				else {
					checkDown = false;
				}
				
			}

			else {

				// check to see if it can go right
				if (curCol + 1 < matrix.length && matrix[curRow][curCol + 1] != -1) {
					curCol++;
					curTot += matrix[curRow][curCol];


					// makes it so you do not check down if it is at the last row
					if (curRow != matrix.length - 1) {
						checkDown = true;
					}
					
				}

				else {

					// if it has hit the end of the maze, change the max possible total of baddy minions that can be killed
					if (curCol + 1 >= matrix.length && curRow == matrix.length - 1) {

						if (curTot > maxTotal) {
							maxTotal = curTot;
						}

					}

					// if nothing is inside the stack, no more paths to check
					if (canDownSects.isEmpty()) {
						checkedAllSects = true;
					}

					// pop back towards the last sector we need to check to the right
					else {
						Sector popped = canDownSects.pop();
						curCol = popped.curCol;
						curRow = popped.curRow;
						curTot = popped.curTot;
					}

				}

			}

		} while (!checkedAllSects);

		// returns the total when done
		return maxTotal;

	}

	// this is an inner class that consists of the current row and col of the hulk and total number of minions he so far fought
	public class Sector {

		private int curRow;
		private int curCol;
		private int curTot;

		public Sector(int cRow, int cCol, int cTot) {
			curRow = cRow;
			curCol = cCol;
			curTot = cTot;
		}

	}

	
	
}
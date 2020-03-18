/* Name: William Chung
 * Description: Makes a matrix and checks to see if it is a perfect square (a square with unique values and same sum of numbers in every column, row, and diagonal).
 */

public class Chung_William_PerfectSquare {

	//Makes a matrix and says true if it is a perfect square and false if it is not
	public static void main(String[] args) {

		int[][] w = { { 16, 3, 2, 13 }, { 5, 10, 11, 8 }, { 9, 6, 7, 12 }, { 4, 15, 14, 1 } };
		System.out.println(isPerfect(w));

	}

	//sees if there are only unique values through 1 and the length squared in the square
	public static boolean unique(int[][] square) {
		int max = square.length * square.length;
		int[] nums = new int[max];
		for (int row = 0; row < square.length; row++) {
			
			for (int col = 0; col < square.length; col++) {
				int num = square[row][col];
			
				if ((num <= 0) || (num > max) || (nums[num - 1] > 0)) {
					return false;
				}
				
				nums[num - 1] = num;

			}

		}
		return true;

	}
	
	//finds the sum of a specific row
	public static int sumRow(int[][] square, int row) {
		int sum = 0;
		
		for (int i = 0; i < square.length; i++) {
			sum += square[row][i];
		}
		
		return sum;
	}
	
	//finds the sum of a specific column
	public static int sumCol(int[][] square, int col) {
		int sum = 0;
		
		for (int i = 0; i < square.length; i++) {
			sum += square[i][col];
		}
		
		return sum;
	}
	
	//finds the sum of the diagonal from top left to bottom right
	public static int fowardDiag(int[][] square) {
		int sum = 0;
		
		for (int i = 0; i < square.length; i++) {
			sum += square[i][i];
		}
		
		return sum;
	}
	
	//finds the sum of the diagonal from top right to bottom left
	public static int backDiag(int[][] square) {
		int sum = 0;
		
		for (int i = 0; i < square.length; i++) {
			sum += square[i][square.length - i - 1];
		}
		
		return sum;
	}
	
	//checks if it is a perfect square
	public static boolean isPerfect(int[][] square) {
		
		//checks to see if sum of a row and diagonals are the same.
		if (sumRow(square, 0) != fowardDiag(square) || (sumRow(square, 0) != backDiag(square))) {
			return false;
		}
		
		//checks if a sum of a row is the same as a sum of a column
		if (sumRow(square, 0) != sumCol(square, 0)) {
			return false;
		}
		
		//checks if the sums of all rows are equal and the sums of all columns are equal
		for (int num = 1; num < square.length; num++) {
			
			if (sumRow(square, 0) != sumRow(square, num)) {
				return false;
			}
			
			if (sumCol(square, 0) != sumCol(square, num)) {
				return false;
			}

		}

		if (unique(square) == false) {
			return false;
		}
		return true;

	}

}


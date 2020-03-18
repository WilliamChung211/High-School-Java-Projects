import java.util.PriorityQueue;

/*
 * Name: William Chung
 * 
 * This program uses an algorithm that optimally solves the Sliding Totoro puzzle. 
 * This program also handles when the puzzle is unsolvable for everyone and if the
 * Algorithm can't solve it. 
 */
public class ChungWilliam_TotoroSolver {

	private Board gBoard;

	public ChungWilliam_TotoroSolver(int[][] board) {
		gBoard = new Board(board, 0, null);

	}
	
	public void search() {

		Board bestBoard = gBoard;
		PriorityQueue<Board> boardQ = new PriorityQueue<Board>();
		int[] vertDisp = { 1, 0, -1, 0 };
		int[] horDisp = { 0, 1, 0, -1 };
		
		//algorithim continues till we have a winning board
		while (!bestBoard.gameOver()) {

			for (int i = 0; i < 4; i++) {

				int newRow = bestBoard.totLoc[0] + vertDisp[i];
				int newCol = bestBoard.totLoc[1] + horDisp[i];

				//removes the board with the highest priority from the queue and then adds all possible moves to queue
				if (newRow >= 0 && newRow < bestBoard.curBoard.length && newCol >= 0&& newCol < bestBoard.curBoard.length) {
					Board addStep = bestBoard.swap(newRow, newCol, bestBoard.movesMade + 1, bestBoard);
					if (!addStep.equals(bestBoard.previousBoard) ) {
						boardQ.add(addStep);
					}

				}

			}

			bestBoard = boardQ.remove();
			
			//if we have a cycle back to the beginning, it either means this algorithm can't solve it or the puzzle is just impossible to solve in general
			if(bestBoard.equals(gBoard)) {
				System.out.println("THIS BOARD IS IMPOSSIBLE to solve!!!!");
				return;
			}
		}

		System.out.println("Steps: ");
		output(bestBoard);
		System.out.println("Total Moves: " + bestBoard.movesMade);

	}

	//prints out the steps from 1st to the winning board
	private void output(Board board) {
		if (board.previousBoard != null) {
			output(board.previousBoard);
		}
		board.print();
		System.out.println("-------------");
	}

	public class Board implements Comparable<Board> {

		private int[][] curBoard;
		private int movesMade; // how many moves to reach this board
		private Board previousBoard;
		private int[] totLoc; // location of Totoro. [0] == row and [1] == col

		public Board(int[][] board, int movesM, Board prev) {
			curBoard = new int[board.length][board.length];
			totLoc = new int[2];

			// finds the row and col coordinates of the location of Totoro and fills values
			// for the board
			for (int row = 0; row < board.length; row++) {

				for (int col = 0; col < board[0].length; col++) {

					curBoard[row][col] = board[row][col];

					if (curBoard[row][col] == 0) {

						totLoc[0] = row;
						totLoc[1] = col;

					}

				}
			}

			movesMade = movesM;
			previousBoard = prev;
		}

		// returns a brand new Board object with Totoro's location swapped with row and
		// col
		public Board swap(int row, int col, int movesM, Board prev) {
			Board newBoard = new Board(curBoard, movesM, prev);

			int horDist = Math.abs(row - totLoc[0]);
			int vertDist = Math.abs(col - totLoc[1]);

			// checks if the swapped spot is directly next to totoro
			if ((horDist > 0 && vertDist > 0) || (horDist > 1 && vertDist == 0) || (vertDist > 1 && horDist == 0)) {
				throw new IllegalArgumentException("You cannot swap. Totoro is not directly next to that spot");
			}

			else if ((row < 0) && (col < 0)) {
				throw new IllegalArgumentException("There cannot be a negative row or col.");
			}

			// swaps totoro and sets a new location
			newBoard.curBoard[totLoc[0]][totLoc[1]] = curBoard[row][col];
			newBoard.curBoard[row][col] = 0;
			newBoard.totLoc[0] = row;
			newBoard.totLoc[1] = col;

			return newBoard;

		}

		// prints out the contents of the board as a grid
		public void print() {

			for (int row = 0; row < totLoc[0]; row++) {

				printVals(row, 0, curBoard.length);
				System.out.println();

			}

			printVals(totLoc[0], 0, totLoc[1]);

			// prints the 0 value or content of totoro's location as "totoro"
			System.out.print("totoro  ");

			printVals(totLoc[0], totLoc[1] + 1, curBoard.length);
			System.out.println();

			for (int row = totLoc[0] + 1; row < curBoard.length; row++) {

				printVals(row, 0, curBoard.length);
				System.out.println();

			}

		}

		// prints the values/contents of a row based on set boundaries
		private void printVals(int row, int firstInd, int rightBounds) {

			for (int col = firstInd; col < rightBounds; col++) {
				System.out.print(curBoard[row][col] + "     ");
			}

		}

		// determines how close the current board is to the final solution based on the
		// sum of all the numbers' vertical distances and horizontal distances from its
		// correct location and moves made.
		public int manhattan() {

			int total = movesMade;

			for (int row = 0; row < totLoc[0]; row++) {

				total += findDist(row, 0, curBoard.length);

			}

			total += findDist(totLoc[0], 0, totLoc[1]);

			total += findDist(totLoc[0], totLoc[1] + 1, curBoard.length);

			for (int row = totLoc[0] + 1; row < curBoard.length; row++) {

				total += findDist(row, 0, curBoard.length);

			}

			return total;
		}

		// finds the sum of the horizontal distance and vertical distance of all the
		// entries in specified boundaries in a specified row from its correct location
		private int findDist(int row, int firstInd, int rightBounds) {

			int totalDist = 0;

			for (int col = firstInd; col < rightBounds; col++) {

				int horDist = Math.abs((curBoard[row][col] - 1) / curBoard.length - row);
				int vertDist = Math.abs((curBoard[row][col] - 1) % curBoard.length - col);
				totalDist += vertDist + horDist;

			}

			return totalDist;

		}

		// returns true if all board pieces are in the correct order
		public boolean gameOver() {

			// checks if Totoro is in the correct location of the last spot
			if (curBoard[curBoard.length - 1][curBoard.length - 1] != 0) {
				return false;
			}

			int corVal = 1;

			// checks if every entry in every row before the last row is in the correct
			// entry
			for (int r = 0; r < curBoard.length - 1; r++) {
				for (int c = 0; c < curBoard[0].length; c++) {

					if (curBoard[r][c] != corVal) {
						return false;
					}

					corVal++;
				}
			}

			// checks if every entry in the last row before the last spot is in the correct
			// location
			for (int c = 0; c < curBoard.length - 1; c++) {

				if (curBoard[curBoard.length - 1][c] != corVal) {
					return false;
				}

				corVal++;

			}

			return true;
		}

		public int compareTo(Board arg0) {

			return this.manhattan() - arg0.manhattan();

		}

		public boolean equals(Object o) {
			if (o == null)
				return false;

			Board otherBoard = (Board) o;

			for (int r = 0; r < curBoard.length; r++) {
				for (int c = 0; c < curBoard[0].length; c++)
					if (curBoard[r][c] != otherBoard.curBoard[r][c])
						return false;
			}

			return true;
		}

	}
	
	public static void main(String args[]) {
		int[][] matrix = {{3,0,5},{2,6,4},{8,7,1}};
		new ChungWilliam_TotoroSolver(matrix).search();
		
	}
}

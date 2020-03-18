public class Board implements Comparable<Board> {

	private int[][] curBoard;
	private int movesMade; // how many moves to reach this board
	private Board previousBoard;
	private int[] totLoc; // location of totoro. [0] == row

	public Board(int[][] board, int movesM, Board prev) {
		curBoard = new int[board.length][board.length];
		totLoc = new int[2];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				curBoard[row][col] = board[row][col];
				if (curBoard[row][col] == 0) {
					totLoc[0] = row;
					totLoc[1] = col;
				}

			}
		}

		movesM = movesMade;
		previousBoard = prev;
	}

	public Board swap(int row, int col, Board prev, int movesM) {
		int[][] newBoard = new int[curBoard.length][curBoard.length];

		newBoard[row][col] = curBoard[totLoc[0]][totLoc[1]];
		newBoard[totLoc[0]][totLoc[1]] = 0;

		return new Board(newBoard, movesM, prev);

	}

	private void manhatin(int r, int c) {
		System.out.print(curBoard[r][c] + ": ");
		int moves = Math.abs((curBoard[r][c] - 1) / curBoard.length - r)
				+ Math.abs((curBoard[r][c] - 1) % curBoard.length - c);
		System.out.println(moves);

	}

	// make helper fuction later
	public int manhattan() {
		int total = movesMade;
		int moves = movesMade;
		for (int r = 0; r < totLoc[0]; r++) {

			for (int c = 0; c < curBoard[0].length; c++) {
				System.out.print(curBoard[r][c] + ": ");
				moves = Math.abs((curBoard[r][c] - 1) / curBoard.length - r)
						+ Math.abs((curBoard[r][c] - 1) % curBoard.length - c);
				System.out.println(moves);
				total += moves;
			}
		}
		for (int c = 0; c < totLoc[1]; c++) {
			System.out.print(curBoard[totLoc[0]][c] + ": ");
			moves = Math.abs((curBoard[totLoc[0]][c] - 1) / curBoard.length
					- totLoc[0])
					+ Math.abs((curBoard[totLoc[0]][c] - 1) % curBoard.length
							- c);
			System.out.println(moves);
			total += moves;

		}
		for (int c = totLoc[1] + 1; c < curBoard[0].length; c++) {
			System.out.print(curBoard[totLoc[0]][c] + ": ");
			moves = Math.abs((curBoard[totLoc[0]][c] - 1) / curBoard.length
					- totLoc[0])
					+ Math.abs((curBoard[totLoc[0]][c] - 1) % curBoard.length
							- c);
			System.out.println(moves);
			total += moves;

		}

		for (int r = totLoc[0] + 1; r < curBoard.length; r++) {
			for (int c = 0; c < curBoard[0].length; c++) {
				System.out.print(curBoard[r][c] + ": ");
				moves = Math.abs((curBoard[r][c] - 1) / curBoard.length - r)
						+ Math.abs((curBoard[r][c] - 1) % curBoard.length - c);
				System.out.println(moves);
				total += moves;
			}
		}

		return total + moves;
	}

	public boolean gameOver() {
		return this.manhattan() == 0;
	}

	public boolean canMoveUp() {

		return totLoc[0] - 1 >= 0;
	}

	public boolean canMoveDown() {

		return totLoc[0] + 1 < curBoard.length;
	}

	public boolean canMoveLeft() {

		return totLoc[1] - 1 >= 0;
	}

	public boolean canMoveRight() {

		return totLoc[1] + 1 < curBoard.length;
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

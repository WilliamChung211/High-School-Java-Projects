/*
 * Name: William Chung, Bruce, Korina
 * This class creates a 10 by 10 board that the player will attempt to target. There will be two matrixes. One matrix
 * will represent the hits and misses. The other will represent the locations of the ships.It will create an array of gamepieces 
 * on this board  place them in locations in one matrix, update the hit and miss board
 *  and also check if all of them are sunk.
 */


public class BattleBoard {


	private GamePiece[] pieces;
	private int shipsLeft;
	private int[][] shipLocs;
	private String[][] hitAndMiss;
	private final int SIZE = 10;


	public BattleBoard() {

		shipLocs = new int[SIZE][SIZE];
		makeDefault(SIZE);
		shipsLeft = 5;
		pieces = new GamePiece[5];

		for (int i = 0; i < 5; i++) {

			int size = i + 2;
			boolean isVertical = randVertical();
			int[] startLoc = getValidLoc(shipLocs, size, isVertical);
			pieces[i] = new GamePiece(size, startLoc, isVertical);
			putLocs(shipLocs, startLoc, isVertical, size);
		}
	}


	// This gets a valid location a gamepiece on the board
	private int[] getValidLoc(int[][] matrix, int size, boolean isVertical) {

		int[] loc = new int[2];

		do {

			loc[0] = (int) (Math.random() * 10);
			loc[1] = (int) (Math.random() * 10);

		} while (((loc[0] + size >= matrix.length) && !(isVertical) || (loc[1] + size >= matrix[0].length) && (isVertical)) || !(checkLoc(loc, size, isVertical)));

		return loc;
	}


	// Checks if the location is not going to place a ship on another ship
	private boolean checkLoc(int[] loc, int size, boolean IsVertical) {

		if (IsVertical) {

			for (int i = 0; i < size; i++) {

				if (shipLocs[loc[0]][loc[1] + i] != 0) {

					return false;
				}
			}
		}

		else {

			for (int i = 0; i < size; i++) {
				if (shipLocs[loc[0] + i][loc[1]] != 0) {

					return false;
				}
			}
		}

		return true;
	}


	// based on the location given and the size of the ship, it places the ships sizes on the location board matrix
	private void putLocs(int[][] shipLocs, int[] loc, boolean isVertical, int size) {

		// if it the ship is placed vertically, it fills the board with its size from up to down
		if (isVertical) {

			for (int i = 0; i < size; i++) {

				shipLocs[loc[0]][loc[1] + i] = size;
			}
		}

		// if it the ship is placed horizontally, it fills the board with its size from left to right
		else {

			for (int i = 0; i < size; i++) {

				shipLocs[loc[0] + i][loc[1]] = size;
			}
		}
	}


	// randomly determines if vertical or not. if it it is true, it puts the location of ship from up to down. If it isfalse, it puts the location of ship from left to right.
	private boolean randVertical() {

		double eitherOr = Math.random();
		return eitherOr < .5;
	}


	//makes an empty default hits and miss board/matrix
	private void makeDefault(int size) {

		hitAndMiss = new String[size][size];

		for (int r = 0; r < SIZE; r++) {

			for (int c = 0; c < SIZE; c++) {

				hitAndMiss[r][c] = " ";
			}
		}
	}


	// shows the location of the game pieces on the board by representing the piece with its corresponding size.
	public String toString() {

		String board = "   ";

		for (int i = 0; i < SIZE; i++) {

			board += i + 1 + " ";
		}

		board += "\n";

		for (int r = 0; r < SIZE; r++) {

			board += "ABCDEFGHIJ".substring(r, r + 1) + " |";

			for (int c = 0; c < SIZE; c++) {

				board += shipLocs[r][c] + "|";
			}

			board += "\n  ---------------------\n";
		}

		// this is a key to show what is ships the sizes are
		SHIP[] names = SHIP.values();

		board += "\nKEY: \n0 = ship-less ocean space \n";

		for (int i = 2; i < 7; i++) {

			board += i + " = " + names[i - 2] + "\n";
		}

		return board;
	}


	// this shows the previous hits and misses on the ships on the board
	public void showBoard() {

		System.out.print("   ");

		for (int i = 0; i < SIZE; i++) {

			System.out.print(i + 1 + " ");
		}

		System.out.println();

		for (int r = 0; r < SIZE; r++) {

			System.out.print("ABCDEFGHIJ".substring(r, r + 1) + " |");

			for (int c = 0; c < SIZE; c++) {

				System.out.print(hitAndMiss[r][c] + "|");
			}

			System.out.println("\n  ---------------------");
		}

		//this shows a key for the board
		System.out.println("Key: ");
		System.out.println("X= hit");
		System.out.println("0= miss \n");
	}


	//determines if location given was already hit at on the board
	public boolean alrHit(int[] loc) {

		return !hitAndMiss[loc[0]][loc[1]].equals(" ");
	}


	// updates the hit and miss board based on the location given.
	public void updateBoard(int[] loc) {

		//checks if there is a ship at the location and places the corresponding symbol at the location
		if (shipLocs[loc[0]][loc[1]] != 0) {

			System.out.println("You hit it!!!!");

			hitAndMiss[loc[0]][loc[1]] = "X";

			//inputs the corresponding action to ship if it is hit
			pieces[shipLocs[loc[0]][loc[1]] - 2].isHit();

			//subtracts from the ship counter if the thing is sunk
			if (pieces[shipLocs[loc[0]][loc[1]] - 2].ifSunk()) {

				shipsLeft--;
			}
		}

		else {

			System.out.println("You missed.");
			hitAndMiss[loc[0]][loc[1]] = "O";
		}
	}

	
	//Checks if there are no ships left and all are sunk
	public boolean checkAllSunk() {

		return (shipsLeft == 0);
	}

	
	public int getBoardSize() {
		
		return SIZE;
	}

	
}
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Name: William Chung and Justin Johnson
 * 
 * This class represents a Tic Tac Toe game and implements the TwoPlayerGame interface.
 * It contains the methods to properly runs the game.
 */

public class TicTacToe implements TwoPlayerGame {

	private String players[];
	private String[][] board;

	public TicTacToe(String firstPlayer, String nextPlayer) {

		System.out.println(firstPlayer);
		players = new String[2];
		
		//makes firstPlayer on odd turns. Makes nextPlayer go on even turns
		players[1] = new String(firstPlayer);
		players[0] = new String(nextPlayer);
		
		//makes a tic tac toe board
		board = new String[3][3];
		for (int i = 1; i <= 9; i++) {
			board[(i - 1) / 3][(i - 1) % 3] = i + "";
		}

	}

	public void displayRules() {

		System.out.println("To win, a player must get three in a row. ");
		System.out.println("You players will play three by three game board.");
		System.out.println("The first player is known as O and the second is X.");
		System.out.println("Players alternate placing Os and Xs on the game board until");
		System.out.println("one of the players has three in a row or all nine spots in the board are filled.");

	}

	// this prompts the user, identified by the player index, for a move and converts/inputs that move into a location on the board.
	public int[] promptMove(int playerInd) {

		//prompts the user and makes sure it is an actual coordinate on the board
		int coord = promptLoc(playerInd);
		int[] loc = { (coord - 1) / 3, (coord - 1) % 3 };
		return loc;
	}

	//makes sure the coordinate is an actual coordinate on the board (an integer and in bounds)
	private int promptLoc(int playerInd){

		Scanner keyboard = new Scanner(System.in);
		int coord;

		// prompts the user and checks if the response is formatted correctly
		do {

			try{
				System.out.println("Hi "+ players[playerInd]+ "! What coordinate do you want? Pick any coordinate from 1-9");
				coord = keyboard.nextInt();
			} 
			catch(InputMismatchException e){
				System.out.println("This is not an integer. Try again.");
				return promptLoc(playerInd);
			}

		} while((coord<1)||(coord>9));

		return coord;

	}

	//checks if the location was already played
	public boolean isValid(int[] loc, int playerInd) {
		return !(board[loc[0]][loc[1]].equals("X") || board[loc[0]][loc[1]].equals("O"));
	}

	//places an "O" or "X" on a location depending on the player Index given 
	public void updateBoard(int[] loc, int playerInd) {

		String hit;

		if (playerInd == 1) {
			hit = "O";
		}
		else {
			hit = "X";
		}

		board[loc[0]][loc[1]] = hit;

	}

	//shows the board in proper/traditional tic tac toe format
	public void displayBoard(int playerInd) {

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 2; col++) {
				System.out.print(board[row][col] + "|");
			}

			System.out.println(board[row][2]);
			System.out.println("------");

		}

		for (int col = 0; col < 2; col++) {
			System.out.print(board[2][col] + "|");
		}

		System.out.println(board[2][2]);
		
	}

	//checks if there are 3 Xs or Os in a row or if the board is full and prints the winning result based on the totTurns made and last location made
	public boolean deterWinner(int[] loc, int totTurn) {

		//It is mathematically impossible for a player to win ticTacToe in less than 5 turns
		if (totTurn <5){
			return false;
		}
		
		int loRow = loc[0];
		int loCol = loc[1];
		


		//checks if the row the move was made in has 3 of a kind
		if ((board[loRow][0].equals(board[loRow][1]) && (board[loRow][0].equals(board[loRow][2])))) {
			System.out.println("Three in a row in row #" + (1+loRow) + "!\n"  + players[totTurn % 2] + " wins!");
			return true;
		}

		//checks if the column the move was made has 3 of a kind
		if ((board[0][loCol].equals(board[1][loCol]) && (board[0][loCol].equals(board[2][loCol])))) {
			System.out.println("Three in a row in collumn #" + (loCol+1) + "!\n" + players[totTurn % 2] + " wins!");
			return true;
		}


		//checks that the location is in the top left to bottom right diagonal and that the diagonal has 3 of a kind
		if (loRow==loCol){

			if ((board[0][0].equals(board[1][1]) && (board[0][0].equals(board[2][2])))) {
				System.out.println("Three in a row in the top left to bottom right diagonal!\n" + players[totTurn % 2] + " wins!");
				return true;
			}
			
		}

		//checks that the location is in the bottom left to top right diagonal and that the diagonal has 3 of a kind
		if (loRow+loCol==2){

			if ((board[2][0].equals(board[1][1]) && (board[2][0].equals(board[0][2])))) {
				System.out.println("Three in a row in the bottom left to top right diagonal!\n" + players[totTurn % 2] + " wins!");
				return true;
			}
			
		}

		//checks whether or not the board is completely full of Xs and Os (meaning 9 turns have been played) so it's a draw
		if(totTurn==9){
			System.out.println("All locations have been selected.\nIt's a DRAW!!!");
			return  true;
		}

		return false;

	}
	
}

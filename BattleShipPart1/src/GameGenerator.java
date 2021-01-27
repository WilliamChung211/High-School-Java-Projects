import java.util.Scanner;

public class GameGenerator {

	
	public static void main(String [] args) {
	Scanner keyboard = new Scanner(System.in);
	

	System.out.println("Player. What is your name? ");
	String p1name = keyboard.nextLine();

	System.out.println("Other player. What is your name? ");
	String p2name = keyboard.nextLine();

	String firstPlayer;
	String nextPlayer;

	if (Math.random() > .5) {

		firstPlayer = p1name;
		nextPlayer = p2name;
	}

	else {

		firstPlayer = p2name;
		nextPlayer = p1name;
	}
	
			// prompts the user for the type of encryption
			System.out.println("Enter the type of Game: Tic Tac Toe or Battleship?");
			String input = keyboard.nextLine();
	
			if (input.equals("Tic Tac Toe")) {
				runGame(new TicTacToe(firstPlayer,nextPlayer));
			}
	
			else if (input.equals("Unstoppable")) {
				runGame(new BattleShip(firstPlayer,nextPlayer));
			}
	
			else {
				System.out.println("Wrong input try again");
				main(args);
			}
	}
	
	public static void runGame(TwoPlayerGame game) {
		
		
		//randomly determines which player is going first
		

		game.displayRules();

		//counts total turn run
		int totTurn = 0;

		//runs the game for multiple turns and includes modulus to keep up with player identification
		do {
			
			totTurn%=2;
			//gives players instructions and displays the previous hits and misses on the target board
			game.displayBoard(totTurn );

			int loc[] = game.promptMove(totTurn );

			if (game.isValid(loc, totTurn )) {

				//updates the board with location
				game.updateBoard(loc, totTurn );
			}

			//checks if player already attempted this location and redos the turn for the player if he did
			else {

				System.out.println("That location was already hit. Try again");
				totTurn--;
			}

			totTurn++;

			//checks if all the boards on the next players ships are sunk so they can end the game
		} while (!game.deterWinner(totTurn + 1));
	}
	
}

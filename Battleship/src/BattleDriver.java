import java.util.*;

/*
 * Name: William Chung and Justin Johnson
 * 
 * This program runs the game of battleship. It uses all the methods of battleship
 */


public class BattleDriver {


	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Player. What is your name? ");
		String p1name = keyboard.nextLine();

		System.out.println("Other player. What is your name? ");
		String p2name = keyboard.nextLine();

		String firstPlayer;
		String nextPlayer;

		//randomly determines which player is going first
		if (Math.random() > .5) {

			firstPlayer = p1name;
			nextPlayer = p2name;
		}

		else {

			firstPlayer = p2name;
			nextPlayer = p1name;
		}

		BattleShip game = new BattleShip(firstPlayer, nextPlayer);
		game.displayRules();

		//counts total turn run
		int totTurn = 0;
		
		int []loc = new int[2];
		//runs the game for multiple turns and includes modulus to keep up with player identification
		do {

			//gives players instructions and displays the previous hits and misses on the target board
			game.displayBoard(totTurn % 2);

			loc = game.promptMove(totTurn % 2);

			if (game.isValid(loc, totTurn % 2)) {

				//updates the board with location
				game.updateBoard(loc, totTurn % 2);
			}

			//checks if player already attempted this location and redos the turn for the player if he did
			else {

				System.out.println("You already attempted to hit that location. Try again");
				totTurn--;
			}

			totTurn++;

			//checks if all the boards on the next players ships are sunk so they can end the game
		} while (!game.deterWinner(loc, totTurn+1));
	}


}


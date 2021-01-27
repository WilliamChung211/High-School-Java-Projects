/*
 * Name: William Chung and Justin Johnson
 * 
 * This class prompts two users for a two player game (Battleship or Tic Tac Toe),
 * determines who goes first, and runs it
 */
import java.util.Scanner;
import java.util.*;
public class GameGenerator {
	

		public static void main(String[] args) {
			
			Scanner keyboard = new Scanner(System.in);
			String game;
			
			//prompts the player for a game to play
			do{
			
				System.out.println("What game do you people want to play: Battleship or Tic Tac Toe");
				game = keyboard.nextLine();
				
			}while(!game.equals("Battleship")&&(!game.equals("Tic Tac Toe")));
			
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
			
			//plays the specific game
			if(game.equals("Battleship")){
				playGame(new BattleShip(firstPlayer, nextPlayer));
			}
			
			else if (game.equals("Tic Tac Toe")){
				playGame(new TicTacToe(firstPlayer, nextPlayer));
			}
		
		}
		
		//Runs any two player game
		public static void playGame(TwoPlayerGame game){

			game.displayRules();

			//counts the numbers of turns being played
			int totTurn= 0;
			int loc[] = new int [2];
			
			//runs the game for multiple turns and includes modulus to keep up with player identification
			do {
				
				//adds the turn
				totTurn++;
				//gives players instructions and displays the previous hits and misses on the target board
				game.displayBoard(totTurn%2);
				
				//prompts player with move
				loc = game.promptMove(totTurn%2);

				if (game.isValid(loc, totTurn%2)) {

					//updates the board with location
					game.updateBoard(loc, totTurn%2);
				}

				//checks if player already attempted this location and redos the turn for the player if he did
				else {
					System.out.println("You already attempted to hit that location. Try again");
					totTurn--;
				}

			

				//checks if anyone won the game
			} while (!game.deterWinner(loc,(totTurn)));
			
			//displays the final board
			System.out.println("THE FINAL BOARD: ");
			game.displayBoard(totTurn%2);
			
		}
		
}

	



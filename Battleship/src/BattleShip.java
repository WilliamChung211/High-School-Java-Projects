import java.util.*;


/*
 * Name: William Chung and Justin Johson
 * 
 * This program runs the game of battleship and has all the methods to help run it. It also creates two player
 * objects who will play it.
 */


public class BattleShip implements TwoPlayerGame{

	
	private BattlePlayer players[];

	
	public BattleShip(String firstPlayer, String nextPlayer){
		
		System.out.println(firstPlayer);
		players = new BattlePlayer[2];
		players[1] = new BattlePlayer(firstPlayer);
		players[0] = new BattlePlayer(nextPlayer);
	}


	public void displayRules(){

		System.out.println("You are given a board with 5 ships that that you must sink");
		System.out.println("Rows are labeled A through J. Colums are labeled 1 through 10");
		System.out.println("Select a coordinate for a location to try to hit a battleship");
		System.out.println("Coordinates are given in the following input. (Row Letter and Col Numbers: Ex: A9");
		System.out.println("A ship is sunk if it's hit the corresponding amount of times");
		System.out.println("You win if all of your opponents ships are sunk");
	}

	
	//this prompts the user, identified by the player index, for a move and converts/inputs that move into a location on the board.
	public int [] promptMove(int playerInd){

		Scanner keyboard = new Scanner(System.in);
		String coord = "";

		//prompts the user and checks if the response is formatted correctly
		do {
			
			System.out.println("Hi " + players[playerInd].getName() + "! What coordinate do you want? (Input in correct format. Ex: J7)");

			coord = keyboard.nextLine();
		}while(coord.length()<1);

		int []loc = new int[2];

		try{

			loc[0] = "ABCDEFGHIJ".indexOf(coord.substring(0,1));
			loc[1] = Integer.parseInt(coord.substring(1))-1;

			//checks if the location is on the board and repeats the prompt if not
			if ((loc[0]==-1)||(loc[1]>9)||(loc[1]<0)){
				
				System.out.println("Try again. Put a valid, labeled location that is in bounds of the board");
				loc = promptMove(playerInd);
			}
		//check if the coordinate was formatted correctly and repeats if not
		}catch(NumberFormatException e){
			
			System.out.println("Try again. Put the right format. (Ex:A9)");
			loc = promptMove(playerInd);
		}

		return loc;
	}

	
	//checks if the player has not already attempted a hit on this location
	public boolean isValid(int[] loc, int playerInd){

		return loc[0]>=0&&loc[1]>=0&&loc[0]<players[playerInd].getSize()&&loc[1]<players[playerInd].getSize()&&!players[playerInd].alrHit(loc);

	}

	
	//updates the move with the proper move
	public void updateBoard(int[] loc, int playerInd){
		players[playerInd].updateBoard(loc);
	}

	
	//shows the previous hits and misses the player attempted on the board
	public void displayBoard(int playerInd){
	
		players[playerInd].displayHits();
	}

	
	//returns true if there is a winner. Then prints out who wins
	public boolean deterWinner(int[] loc, int totTurn){

		//it is mathematically impossible for someone to win in battleship in less than 39 turns
		if (totTurn <20){
			return false;
		}
		
		if (players[totTurn%2].checkIfGameOver()){
			
			System.out.println("All of the ships of " + players[(totTurn+1)%2].getName() + " are sunk. " + players[(totTurn)%2].getName() + " wins!"  );
			System.out.println("Game Over!");
			return true;
		}
		return false;
	}
	
	
}

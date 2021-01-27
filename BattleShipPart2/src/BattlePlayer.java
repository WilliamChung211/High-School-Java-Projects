/*
 * Name: William Chung and Justin Johnson
 * 
 * This class makes a player object and stores name and the board that he will target.
 */


public class BattlePlayer {


	private String name;
	private BattleBoard board; //this is the opposing board that the player sees and targets


	public BattlePlayer(String nam){

		name = nam;
		board = new BattleBoard();
	}


	//checks if the location given was already hit on the player's target board
	public boolean alrHit (int[]loc){
		
		return board.alrHit(loc);
	}


	//updates the player's target board based on the location and if it was a hit or miss
	public void updateBoard (int []loc){

		board.updateBoard(loc);

	}


	//this shows the board that the player is targeting and will display his previous hits and misses
	public void displayHits(){
		
		board.showBoard();
		
	}


	//returns true if all the player's opponent's ships are all sunk on the board
	public boolean checkIfGameOver(){

		return board.checkAllSunk();
	}


	public int getSize(){

		return board.getBoardSize();
	}


	public String getName(){

		return name;
	}


}

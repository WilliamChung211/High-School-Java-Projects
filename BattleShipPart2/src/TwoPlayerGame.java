/*
 * Name: William Chung and Justin Johnson
 * 
 * This interface contains the methods to run for a two player game that alternates turns 
 */

public interface TwoPlayerGame {

	public void displayRules();
	
	public int [] promptMove(int playerInd);
	
	public boolean isValid(int[] loc, int playerInd);
	
	public void updateBoard(int[] loc, int playerInd);
	
	public void displayBoard(int playerInd);
	
	public boolean deterWinner(int [] loc, int totTurn);
	
	
	
}

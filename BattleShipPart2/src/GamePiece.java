
/*	Name: William Chung and Justin Johnson
 * 
 * This GamePiece class creates a piece based on certain properties, counts hits, and checks if it is sunk.
 */

public class GamePiece {


	private int size;
	private SHIP name;
	private int [] startLoc;
	private boolean isVertical; //if true, it is created down. if false, it is created right.
	private boolean isSunk;
	private int hitCount;


	public GamePiece(int s, int[] startL,boolean isVert){

		if (s<=0){	

			throw new IllegalArgumentException("Not Possible Size");
		}

		size = s;
		SHIP[]names = SHIP.values();
		name = names[size-2];
		startLoc = new int [2];

		for (int i = 0; i<2; i++){

			startLoc[i]= startL[i];
		}

		isVertical = isVert;
		isSunk = false;
		hitCount = 0;

	}


	//adds to the hit counter if the piece is hit. Then it checks if it was sunk after.
	public void isHit(){

		hitCount++;

		isSunk = ifSunk();

		if (isSunk) {

			System.out.println(name + " was sunken");
		}

	}


	//checks if every location of the ship was hit.
	public boolean ifSunk(){

		return hitCount == size;
	}


}

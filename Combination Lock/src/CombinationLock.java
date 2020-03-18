import java.util.Random;

/*
 * Name: William Chung
 * This class simulates a lock with 3 key values to unlock it. If the 3 guesses are equal to the 3 key values, it opens. 
 */

public class CombinationLock {

	private int[] guesses;
	private int[] keys;
	private int spot;
	private boolean secRight;

	public CombinationLock() {
		Random rGen = new Random();
		keys = new int[3];
		guesses = new int[3];
		
		for (int i = 0; i < guesses.length; i++) {
			
			//generates  3 random key values.
			keys[i] = rGen.nextInt(32) + 1;
			guesses[i] = 0;
			
		}
		
		spot = 1;
		secRight = false;
	}

	//shifts and increases the spot by 1
	public void spinLeft() {
		
		spot++;
		
		if (spot == 33) {
			spot =1;
		}

		guesses[1] = spot;
		
		//makes in known that the client locked in their first guess from the first spin.
		secRight = true;
	}
	
	//shifts and decreases the spot by 1
	public void spinRight() {
		
		spot--;
		
		if (spot == 0) {
			spot = 32;
		}
		
		//checks if it is the first time it spins right
		if (!secRight) {
			guesses[0] = spot;
		}
		else {
			guesses[2] = spot;
		}
	}

	//checks  if the client had successfully entered the combination or if he entered all three numbers. 
	public boolean open() {
		
		//checks if guesses are equal to the keys
		if ((guesses[2] == keys[2]) && (guesses[1] == keys[1]) && (guesses[0] == keys[0])) {
			return true;
		}
		
		return false;
		
	}
	
	//wipes out all current guesses and reverts the lock position back to number 1.
	public void reset() {
		
		for (int i = 0; i < guesses.length; i++) {
			guesses[i] = 0;
		}
		
		spot = 1;
		secRight = false;
	}

}

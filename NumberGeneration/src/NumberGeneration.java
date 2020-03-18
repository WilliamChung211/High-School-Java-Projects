/*
 * Name: William Chung
 * 
 * A class that contains one integer instance variable with a mutator that 
 * randomly sleeps and increases the variable 
 * 
 * Explanation: For the non-synchronized version, it will be a different
 * number each time you run it that will be less than 1000. For the synchronized version,
 * it will be the same number of 1000 each time. This is because with
 * synchronization, it forces the threads to wait until the mutator block is free,
 * so each thread will guarantee to mutate one at a time (resulting in 1000 
 * incrementations at a time). For non-synchronized version, the threads all try to
 * run at the concurrently, so (for instance) when two threads do run concurrently, it will only count
 * as mutating once. This will result in a couple times where it will not increment for
 * number of threads since it is not one at a time meaning that will put a number less than
 * 1000. Also, there is random execution, so it will not be the same number every time you run it.
 */

import java.util.Random;

public class NumberGeneration implements Runnable {

	private int inte;
	
	public int getInte() {
		return inte;
	}
	
	public void incInte() {
		inte++;
	}

	public synchronized void run() {
		Random r = new Random();
		try {
			Thread.sleep(r.nextInt(1000));
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		incInte();
	}

}

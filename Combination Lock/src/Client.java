/*
 * Nalock: William Chung
 * This runs the Combination Lock class and tests the methods of spinLeft, spinRight, open, and reset. 
 */
public class Client {
	public static void main(String [] args){
	CombinationLock lock = new CombinationLock();
	
	for (int i=0;i<9;i++){
		lock.spinRight();
	
	}
	
	for (int i=0;i<12;i++){
		lock.spinLeft();
		
		}
	for (int i=0;i<22;i++){
		lock.spinRight();
		
	
		}
	
	System.out.println(lock.open());
	lock.reset();

	
	}
}

/*
 * Name: William Chung
 * 
 * This is the client class that creates 1000 thread, starts it up,
 * makes sure the main does not move until all is terminated, and prints
 * out the integer at the end
 */
public class Client {

	public static void main(String[]args) {
	
		NumberGeneration nums = new NumberGeneration();
		Thread[]threads = new Thread[1000];
		for(int i =0;i<1000;i++) {
			threads[i] = new Thread(nums);
		}
		
		for(int i =0;i<1000;i++) {
			threads[i].start();
		}
		
		for(int i =0;i<1000;i++) {
			try {
				threads[i].join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(nums.getInte());
	}
}

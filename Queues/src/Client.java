
public class Client {
	
	public static void main(String[]args) {
		Queue<String> list = new ArrayQueue<String>();
		
		for(int i =0;i<999;i++) {
			list.add(i+ "");
			list.isEmpty();
		}
		while(!list.isEmpty()) {
			System.out.println(list.remove());
		}
		
		
		System.out.println("Next number:" + list.peek());
		
	}
}

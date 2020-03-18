
public class Client {

	public static void main(String[]args) {
		Stack<String>list = new ListStack<String>();

		for(int i =0;i<1021;i++) {
			list.push(i +"");
		}
	
	
		System.out.println(list.peek());
		while(!list.isEmpty()) {
			System.out.println(list.pop());
		}
		
	}
	
}


public class Client {
	
	public static void main(String[]args) {
		printSquares();
	}
	
	public static void printSquares(int n) {
		if(n==0) {
			return;
		}
		
		printSquares(n-1);
		System.out.println(n + " " + (n*n));
	}
	
	public static void printSquares() {
		printSquares(10);
	}
}

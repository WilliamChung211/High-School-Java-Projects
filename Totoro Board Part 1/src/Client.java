
public class Client {
	public static void main (String[]args){
		int[][] data = {{8,1,3},{4,0,2},{7,6,5}};
	
	Board testBoard = new Board(data,0,null);
	System.out.println(testBoard.manhattan());
	}	
}

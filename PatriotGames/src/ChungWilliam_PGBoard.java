import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * This class helps determine if one of the two players has won.
 * We are going to determine if the blue player is the winner, and
 * if they won, we print out the blob of all the winning peices. 
 */
public class ChungWilliam_PGBoard {

	char[][]board;
	
	//creates an empty board
	public ChungWilliam_PGBoard(int length) {
		board = new char[length][length];
	}
	
	//reads in a file and builds the character matrix
	public ChungWilliam_PGBoard(String fName, int size) {
		board = new char[size][size];
		
		Scanner file = null;
		try {
			file = new Scanner(new File(fName));
		}

		catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(-1);
		}

		//puts in a letter for each line
		for(int row = 0; row<board.length;row++) {
	
			String line = file.nextLine();
			
			for(int col =0;col<size;col++) {
				board[row][col]=line.charAt(col);
			}
			
		}
	
		
	}
	
	//generates a blob of all the neighboring blue pieces changing any blue pieces in the blob to white
	public void fillBlob(int row, int col) {
		
		//represents the locations of the 6 potential neighors
		int[] vertical = { -1, -1,  0, 0, 1, 1 };
		int[] horizontal = { -1, 0,  -1, 1, 0, 1 };
		
		for(int i =0;i<vertical.length;i++) {
			int checkRow = row+vertical[i];
			int checkCol = col+horizontal[i];
			if(checkBounds(checkRow, board.length)&&checkBounds(checkCol, board.length)) {
				
				//if it finds a neighboring blue piece, it makes it white and finds any neighboring blue piece for that piece
				if(board[checkRow][checkCol]=='b') {
					board[checkRow][checkCol]='w';
					fillBlob(checkRow,checkCol);
				}
				
			}
		}
		
	}
	
	//this method checks if the number is in range
	private boolean checkBounds(int num, int bound) {
		return num >= 0 && num < bound;
	}
	
	//returns a new PGBoard object consisting of only a blue piece blob that begins at the paramter's location
	public ChungWilliam_PGBoard getBoard(int row,int col) {
	
		ChungWilliam_PGBoard toReturn = new ChungWilliam_PGBoard(board.length);
		//if the starting position is a red stone, then the PGBoard returned is empty
		if(board[row][col]=='r') {
			return toReturn;
		}
		
		//makes the new PGBoard object
		for(int r = 0; r<board.length;r++) {
			for(int c =0;c<board.length;c++) {
				toReturn.board[r][c] =board[r][c];
			}
		}
		
		//gets the blue piece blob that was marked white from the new object
		toReturn.fillBlob(row,col);
		
		//unmarks all white pieces to blue and all other non blob pieces to empty
		for(int r = 0; r<board.length;r++) {
			for(int c =0;c<board.length;c++) {
				if(toReturn.board[r][c]=='w') {
					toReturn.board[r][c]='b';
				}
				else {
					toReturn.board[r][c]=' ';
				}
			}
		}
		
		return toReturn;
	}
	
	//assuming the board is just a blob of blue pieces, it determines if it has reached both ends of the board
	public boolean hasBlueWon() {
		
		//if it reached both ends of the board, it prints out the winning blob
		if(checkCol(0)&&checkCol(board.length-1)) {
			System.out.println("Winning blue blob:");
			System.out.print(this);
			return true;
		}	
				
		return false;
	}
	
	//checks if the there is blue at the collumn
	private boolean checkCol(int col) {
		for(int checkRow=0;checkRow<board.length;checkRow++) {
			if(board[checkRow][col]=='b') {
				return true;
			}
		}
		return false;
	}
	
	
	//returns the entire state of the board in the shape of a square board
	public String toString() {
		
		String toReturn = "";
		for(int row = 0; row<board.length;row++) {
			for(int col = 0;col<board[0].length;col++) {
				toReturn+=board[row][col];
			}
			toReturn+="\n";
		}
		
		return toReturn;
	}
	

	//checks if blue player is the winner
	public static void main(String[]args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		//gets the information
		System.out.println("What is the file name?");
		String name = keyboard.nextLine();
		System.out.println("What is the matrix length?");
		int size = keyboard.nextInt();
	
		ChungWilliam_PGBoard gameBoard = new ChungWilliam_PGBoard(name,size);
		
		//checks to see if the blue player made the path and prints result
		for(int col =0;col<size;col++) {
			ChungWilliam_PGBoard blob = gameBoard.getBoard(0, col);
			if (blob!=null&&blob.hasBlueWon()) {
				return;
			}
		}
		
		System.out.println("Blue lost!");
		
		
	}
	
}


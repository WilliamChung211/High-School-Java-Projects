import java.util.Scanner;

public class Prompt3 {

	private boolean[][]matrix;
	public static void main(String[]args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Input");
		String firstLine = keyboard.nextLine();
		Prompt3 newt = new Prompt3(firstLine);
		//String[]nextLines = new String[5];
		//for(int i =0;i<5;i++) {
	//		nextLines[i]=keyboard.nextLine();
	//	}
		
		System.out.println(newt.findPath(2,2,4,3));
	}
	
	public Prompt3(String line) {
		line = line.substring(3);
		String[]nums = line.split(",");
		matrix = new boolean[5][5];
		for(int i=0;i<nums.length-3;i+=2) {
			matrix[Integer.parseInt(nums[i])-1][Integer.parseInt(nums[i+1])-1]=true;
			System.out.println(Integer.parseInt(nums[i])-1);
		}
		
	}
	
	public int getNodes(String line) {
		line = line.substring(3);
		String[]nums = line.split(",");
		int[] firCoord = {Integer.parseInt(nums[0])-1,Integer.parseInt(nums[2])-1};
		int[] secCoord = {Integer.parseInt(nums[1])-1,Integer.parseInt(nums[3])-1};
		int[]smallCoord;
		int[]bigCoord;
		if(Math.max(firCoord[0],secCoord[0])==firCoord[0]) {
			smallCoord = secCoord;
			bigCoord = firCoord;
		}
		else {
			smallCoord = firCoord;
			bigCoord = secCoord;
		}
		
		int row = smallCoord[0];
		int col = smallCoord[1];
		int findRow = bigCoord[0];
		int findCol = bigCoord[1];
		findPath(row,col,findRow,findCol);
		
		
	}
	
	private int findPath(int row,int col,int findRow, int findCol) {
		
		
		int pathNodes; 
		if(row+1<matrix.length&&matrix[row+1][col]) {
			
			if((row+1==findRow) &&(col==findCol)){
				return 0;
			}
			else if(col+1<matrix.length&&matrix[row][col+1]) {
				pathNodes = Math.max(findPath(row+1,col,findRow, findCol),findPath(row,col+1,findRow,findCol));
			}
			else {
				pathNodes = findPath(row+1, col,findRow, findCol);
			}
			
			
		}
		
		else if(col+1<matrix.length&&matrix[row][col+1]) {
			pathNodes =  findPath(row,col+1,findRow,findCol);
		}
		
		else {
			return -1;
		}
		
		if(pathNodes==-1) {
			return -1;
		}
		
		return pathNodes +1;
		
	}
	
	
	
}

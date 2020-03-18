import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgrammingTry {

	public static void main (String[]args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Input:");
		String in = keyboard.nextLine();
		String [] nums = in.split(", ");
		boolean [] hasCoin = new boolean[Integer.parseInt(nums[0])];
		int numCoins = Integer.parseInt(nums[1]);
		//for(int i =0;i<hasCoin.length;i++) {
		//	hasCoin[i]=false;
		//}
		for(int i =2;i<numCoins;i++) {
			hasCoin[Integer.parseInt(nums[i])-1] = true;
			System.out.println(i + ". " +( Integer.parseInt(nums[i])-1));
		}
		
		int []moveArr = new int[5];
		for(int i =1;i<=moveArr.length;i++) {
			int tot = 0;
			for(int ind = 2;ind<nums.length;ind++) {
				int spot = Integer.parseInt(nums[ind])-1;
				if(checker(i,hasCoin, spot)) {
					tot++;
				
				}
			}
			
			moveArr[i-1]=tot;
		}
		
		for (int i =0;i<moveArr.length-1;i++) {
			System.out.print(moveArr[i] + ",");
		}
		
		System.out.print(moveArr[moveArr.length-1]);
		
		
	}
	
	private static boolean checker(int left, boolean[]hasCoin, int spot) {
		
		for(int i=1;i<left;i++) {
			System.out.println(spot-i);
			if(!(spot-i>=0&&!hasCoin[spot-i])) {
				return false;
			}
		}
	
		System.out.println("index" + left);
		System.out.println("num" + spot);
		return true;
	}
}

//12, 7, 1, 4, 6, 8, 9, 10, 12
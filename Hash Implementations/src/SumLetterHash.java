/*
 * Name: William Chung
 * 
 * This class implements the Hashable interface and its function returns 
 * the sum of the first letter of the name
 */

public class SumLetterHash implements Hashable {

	public int computeHash(String name) {
		int sum = 0;
		for(int i =0;i<name.length();i++) {
			sum+=(int)name.charAt(i);
		}
		
		return sum;
	}
}

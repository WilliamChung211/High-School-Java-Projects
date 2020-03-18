/*
 * Name: William Chung
 * 
 * This class implements the Hashable interaface with a function of my
 * design performing more than 4 operations
 */
public class MyHashFunction implements Hashable{

	public int computeHash(String name) {
		
		if(name==null||name.length()==0) {
			return 0;
		}
		
		//finds the product of the first and last letter and the sum of the letters in between
		int prodfFirAndLast = name.charAt(0)*name.charAt(name.length()-1);
		int sumOfInBetween =0;
		
		for(int i =1;i<name.length()-1;i++) {
			sumOfInBetween+=(int)name.charAt(i);
		}
		
		//then it returns the truncated version of the product of the sum of the two previous values found
		//times a char value divided by (the length + .1415)
		return (int)((prodfFirAndLast+sumOfInBetween)*name.charAt(name.charAt(0)%name.length())/(name.length()+.1415));
	}
}

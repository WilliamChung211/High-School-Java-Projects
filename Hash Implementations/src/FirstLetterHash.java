/*
 * Name: William Chung
 * 
 * This class implements the Hashable interface and its function returns the ascii
 * value of the first letter of the name
 */

public class FirstLetterHash implements Hashable {
	public int computeHash(String name) {
		
		if(name==null||name.length()==0) {
			throw new IllegalArgumentException("There is no first letter");
		}
		return name.charAt(0);
	}
}

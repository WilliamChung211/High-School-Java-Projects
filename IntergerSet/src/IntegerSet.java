/*
 * Name: William Chung
 * The class Integer set maintains a set of unique integers which is represeted by an array of booleans 
 */
public class IntegerSet {
	private boolean[] set;
	
	//makes an object with an empty array with a range of integers from 0 to the given max.
	public IntegerSet(int max) {
		set = new boolean[max + 1];
	}

	public IntegerSet(IntegerSet other) {

		this((other.set).length - 1);
		
		for (int i = 0; i < other.set.length; i++) {
			set[i] = other.set[i];
		}

	}
	
	//inserts integer into set
	public void add(int addition) {
		fill(addition,true);
	}
	
	//removes integer from set
	public void remove(int removal) {
		fill(removal,false);
	}
	
	//fills the array
	private void fill (int k, boolean condition){
		
		if ((k< 0)||(k>set.length-1)) {
			throw new IllegalArgumentException("That number is out of bounds");
		}
		
		set[k] = condition;
	}
	
	//checks if the set is empty
	public boolean isEmpty() {
		
		for (int i = 0; i < set.length; i++) {
			if (set[i] == true) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public String toString() {
		
		String sentence = "{";
		
		for (int i = 0; i < set.length; i++) {
			if (set[i] == true) {
				sentence = sentence + i + ",";
			}
		}
		
		if (sentence.equals("{")) {
			return sentence + "}";
		}
		
		return sentence.substring(0, sentence.length() - 1) + "}";

	}
	
	//creates a third sets that is a union of the two sets
	public IntegerSet union(IntegerSet other) {
		boolean[] maxSet;
		boolean[] minSet;
		
		if (set.length>=other.set.length){
			maxSet = set;
			minSet = other.set;
		}
		else {
			maxSet = other.set;
			minSet = set;
		}
		
		IntegerSet uniSet= new IntegerSet(maxSet.length - 1);

		for (int i = 0; i < minSet.length; i++) {
			
			if ((set[i] == true) || (other.set[i] == true)) {
				uniSet.set[i] = true;
			}
			
		}
		
		for (int i = minSet.length; i < maxSet.length; i++) {
			uniSet.set[i] = maxSet[i];
		}
		
		return uniSet;
	}
	
	//creates and returns a third set that is an intersection of the two sets.
	public IntegerSet intersection(IntegerSet other) {

		IntegerSet interSet = new IntegerSet(Math.max(set.length, other.set.length) - 1);

		for (int i = 0; i < Math.min(set.length, other.set.length); i++) {
			
			if ((set[i] == true) && (other.set[i] == true)) {
				interSet.set[i] = true;
			}
			
		}
		
		return interSet;
	}

	//determines whether the calling object and parameter have the same values.
	public boolean equals(IntegerSet other) {
		
		for (int i = 0; i < set.length; i++) {
			
			if (set[i] != other.set[i]) {
				return false;
			}
			
		}
		
		return true;
	}
	

}

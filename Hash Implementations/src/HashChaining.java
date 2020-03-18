import java.util.LinkedList;

/*
 * Name: William Chung
 * 
 * This structure uses hashing techniques to store data by maintaining an
 * array and hash function. It also handles collisions via separate chaining 
 * by storing an array of LinkedLists.
 * 
 * Answer to runtime question: It goes through each bucket to make an iterator. Then, it will add to the
 * returning String for each element in the bucket. In total, the adding part will happen for each element
 * in the table and since we will never overflow, the worst-case runtime of the toString method will be the
 * number of elements in the table no matter how large it is. 
 */
public class HashChaining {

	private LinkedList<String>[]hashTable;
	private Hashable hashFunction;
	private int numCol;
	
	
	public HashChaining(int size, Hashable hFunc) {
		if(size<=0) {
			throw new IllegalArgumentException("Bad size");
		}
		hashTable = new LinkedList[size];
		for(int i =0;i<hashTable.length;i++) {
			hashTable[i]= new LinkedList<String>();
		}
		hashFunction = hFunc;
		
	}

	public boolean contains(String name) {
		return hashTable[Math.abs(hashFunction.computeHash(name)%hashTable.length)].contains(name);
	}	
	
	//returns true if we can insert the element to the table (which is always)
	public boolean insert(String name) {
	
		//converts number from hash function to a number in the index range
		int modHash = Math.abs(hashFunction.computeHash(name)%hashTable.length);
		hashTable[modHash].add(name);
		
		//checks if a collision happens
		if(hashTable[modHash].size()!=1) {
			numCol++;
		}
		
		return true;
		
	}
	
	//returns true if we can remove the name from the hashTable
	public boolean remove(String name) {
		return hashTable[Math.abs(hashFunction.computeHash(name)%hashTable.length)].remove(name);
	}
	
	//returns all data separated by commas
	public String toString() {
		
		String toReturn = "";
		for(int i =0;i<hashTable.length;i++) {
			for(String name: hashTable[i]) {
				toReturn+=name + ",";
			}
		
		}
		
		if(toReturn.length()==0) {
			return toReturn;
		}
		
		return toReturn.substring(0,toReturn.length()-1);
	}
	
	public int numCollisions() {
		return numCol;
	}
	
	//prints out the percentage of empty buckets in the hashtable, average lengths of active lists, and length of longest list
	public void diagnostic() {
		
		int numEmpty=0;
		int totLength=0;
		int maxLength = 0;
		
		for(int i =0;i<hashTable.length;i++) {
			int checkSize = hashTable[i].size();
			if(checkSize==0) {
				numEmpty++;
			}
			totLength+=checkSize;
			if (checkSize>maxLength) {
				maxLength = checkSize;
			}
			
		}
		
		System.out.println("Percentage of empty buckets: " + ((double)numEmpty/hashTable.length*100)+ "%");
		System.out.println("Average active list length: " + ((double)totLength/(hashTable.length-numEmpty)));
		System.out.println("Max Length: " + maxLength);
		
	}
}

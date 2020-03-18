/*
 * Name: William Chung
 * 
 * This structure uses hashing techniques to store data by maintaining an
 * array and hashfunction. This class handles collission via probing with its
 * own probFunction.
 * 
 * Answer to RunTime Question: When toString runs, it has to go through the entire table size to
 * check if it is null and if it should add to the string (this action won't take long). It adds to the string 
 * for the times of the element/data size in the table. The worst case is that the entire table is full, so it 
 * would end up checking and adding to the string for the entire table length.
 *  
 */
public class HashProbing {

	private String[]hashTable;
	private Hashable hashFunction;
	private int numCol;
	private Probable probFunction;
	
	public HashProbing(int size, Hashable hFunc, Probable prFunc) {
		if(size<=0) {
			throw new IllegalArgumentException("Bad size");
		}
		hashTable = new String[size];
		hashFunction = hFunc;
		probFunction = prFunc;
	}

	//returns true if we can insert the element to the table
	public boolean insert(String name) {
	
		//converts number from hash function to a number in the index range
		int modHash = Math.abs(hashFunction.computeHash(name)%hashTable.length);
		if(hashTable[modHash]==null) {
			hashTable[modHash]=name;
			return true;
		}
		
		//a collision happened so
		numCol++; 
		
		//we need to get a new index via the probe function till we get an empty index or we probed too many times
		for(int i =0;i<hashTable.length;i++) {
			modHash = probFunction.probe(modHash)%hashTable.length;
			if(hashTable[modHash]==null) {
				hashTable[modHash]=name;
				return true;
			}
			numCol++;
		}
		
		//if we probed too many times, that means we cannot insert anymore
		return false;
	}
	
	//gets the index of a name in the hashTable based on its hash value and the probfunction
	private int getInd(String name) {
		
		int modHash = Math.abs(hashFunction.computeHash(name)%hashTable.length);
		if(hashTable[modHash]==name) {
			return modHash;
		}
		
		
		for(int i =0;i<hashTable.length;i++) {
			modHash= probFunction.probe(modHash)%hashTable.length;
			if(hashTable[modHash]==name) {
				return modHash;
			}
		}
		
		//returns -1 if it is not contained in the table
		return -1;
	}
	
	public boolean contains(String name) {
		return getInd(name)!=-1;
	}
	
	//returns true if the name can be removed
	public boolean remove(String name) {
		int removeInd = getInd(name);
		if(removeInd==-1) {
			return false;
		}
		
		hashTable[removeInd]=null;
		return true;
		
	}
	
	//returns all data separated by commas
	public String toString() {
			
		String toReturn = "";
		for(int i =0;i<hashTable.length;i++) {
			if(hashTable[i]!=null) {
				toReturn+=hashTable[i] + ",";
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
}

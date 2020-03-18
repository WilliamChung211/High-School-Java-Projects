import java.util.ArrayList;

/*
 * Name: William Chung
 * 
 * This structure uses hashing techniques to store data by maintaining an
 * array and hashfunction. This class handles collision via probing with its
 * own probFunction. We store the probe index whenever we calculate it. We also
 * assume that all these functions will eventually end.
 *  
 */
public class ChungWilliam_HashProbing<E> {

	private ArrayList<Bucket> hashTable;
	private Probable probFunction;
	private int dontExc;
	private int numPrevOcc;
	private boolean[]prevOccBucks;

	public ChungWilliam_HashProbing(int size, Probable prFunc, int perc) {
		if (size <= 0) {
			throw new IllegalArgumentException("Bad size");
		}
	
		hashTable = new ArrayList<Bucket>(size);
		for (int i = 0; i < size; i++) {
			hashTable.add(new Bucket());
		}

		probFunction = prFunc;
		dontExc = perc;
		
		//all false since nothing is previously occupied
		prevOccBucks=new boolean[size];
	}

	// returns true if we can insert the element to the table (which is all the time since we assume client made big enough table size)
	public boolean add(E item) {

		
		int potHashVal = Math.abs(item.hashCode() % hashTable.size());
		
		// we need to get a new index via the probe function till we get an empty data index which will always happen since we assume the client made a good table size
		while(hashTable.get(potHashVal).data!=null) {
			
			if(hashTable.get(potHashVal).nextLoc==-1) {
				hashTable.get(potHashVal).nextLoc= probFunction.probe(potHashVal) % hashTable.size();
				potHashVal =hashTable.get(potHashVal).nextLoc;
			}
			
			else {
				potHashVal = hashTable.get(potHashVal).nextLoc;
			}
		}
		
		hashTable.get(potHashVal).data = item;
		
		//if this spot was previously removed, we update the counter and boolean array
		if(prevOccBucks[potHashVal]) {
			prevOccBucks[potHashVal]=false;
			numPrevOcc--;
		}
		
		return true;
	}

	// gets the index of an item in the hashTable based on its hash value and the probfunction
	private int getInd(E item) {
	
		int potHashVal = Math.abs(item.hashCode() % hashTable.size());
		if (hashTable.get(potHashVal) != null) {
		
			while (hashTable.get(potHashVal).nextLoc != -1) {
			
				if (hashTable.get(potHashVal).data!=null&&hashTable.get(potHashVal).data.equals(item)) {
					return potHashVal;
				}

				potHashVal = hashTable.get(potHashVal).nextLoc;
				
			}

			//after we quit early, we need to check if this last element's data is the one we need to find
			if (hashTable.get(potHashVal).data!=null&&hashTable.get(potHashVal).data.equals(item)) {
				return potHashVal;
			}
			
			
		}
		
		// returns -1 if it is not contained in the table
		return -1;
	}

	
	public boolean contains(E name) {
		return getInd(name) != -1;
	}

	// returns true if the name can be removed
	public boolean remove(E item) {
		
		int removeInd = getInd(item);
		
		if (removeInd == -1) {
			return false;
		}


		hashTable.get(removeInd).data = null;
		numPrevOcc++;
		prevOccBucks[removeInd]=true;
		
		//if ratio of prev occupied, empty locations to table size exceeds percentage, we rehash
		if((double)numPrevOcc/hashTable.size()*100>dontExc) {
			rehashing();
		}
		return true;

	}



	//double size of the hash table and redistribute elements
	private void rehashing() {
		
		ChungWilliam_HashProbing<E> temp = new ChungWilliam_HashProbing<E>(hashTable.size()*2, probFunction, dontExc);
		for (int i = 0; i < hashTable.size(); i++) {
			if (hashTable.get(i).data != null) {		
				temp.add(hashTable.get(i).data);
			}
		}

		hashTable = temp.hashTable;
		prevOccBucks=temp.prevOccBucks;
		numPrevOcc=0;
	}
	
	public class Bucket {
		private E data;
		private int nextLoc;

		public Bucket() {
			nextLoc = -1;
		}

	}
}

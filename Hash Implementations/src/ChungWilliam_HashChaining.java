import java.util.Iterator;
import java.util.LinkedList;


/*
 * Name: William Chung
 * 
 * For add, it takes constant time since it takes constant time for both adding to
 * the end of a doubly linked list and adding to a hashtable with buckets since
 * buckets a relatively small, to the size of the table. For contains and remove it takes
 * constant time since the buckets are relatively small to the size of the table. InOrder
 * takes the time of the number of elements since we are traversing through the doubly linked
 *  list
 * 
 */

public class ChungWilliam_HashChaining<E> {

	private LinkedList<ListNode>[] hashTable;
	private ListNode front;
	private ListNode end;

	public ChungWilliam_HashChaining(int size) {

		if (size <= 0) {
			throw new IllegalArgumentException("Bad size");
		}

		hashTable = new LinkedList[size];

		for (int i = 0; i < size; i++) {
			hashTable[i] = new LinkedList<ListNode>();
		}

	}

	//adds to the end of the list and returns true if we did it
	public boolean add(E item) {

		end = new ListNode(end, item, null);
		
		if (front == null) {
			front = end;
		}
		
		//connects the new last element to the new 2nd to last element of the doubly linked list
		else {
			ListNode oldEnd = end.prev;
			oldEnd.next = end;
		}
		
		//it then adds to hashtable
		int modHash = Math.abs(item.hashCode() % hashTable.length);
		hashTable[modHash].add(end);
		return true;

	}
	
	public boolean contains(E item) {
		return hashTable[Math.abs(item.hashCode()%hashTable.length)].contains(new ListNode(null,item,null));
	}

	//removes first instance of item and returns true if we did it
	public boolean remove(E item) {
		
		//retrieves the bucket
		LinkedList<ListNode> bucket= hashTable[Math.abs(item.hashCode()%hashTable.length)];		
		if(bucket.isEmpty()) {
			return false;
		}
		

		Iterator<ListNode> ite = bucket.iterator();
		
		while(ite.hasNext()) {

			ListNode check = ite.next();
			if(check.data.equals(item)) {
				
				if(check==front) {
					
					ListNode toRem = front;
					front = front.next;
					
					if (toRem.next== null) {
						end = null;
					} 
					
					else {
						// completely disconnects the new front and old front from each other
						toRem.next = null;
						front.prev = null;
					}
				}
				
				else if (check==end){
					ListNode toRem = end;
					end = end.prev;
					toRem.prev = null;
					end.next = null;
				}
				
				else {
					check.prev.next = check.next;
					check.next.prev = check.prev;

					// gets rid of any connections the removed data had from the list
					check.prev = null;
					check.next = null;
				}
				 
				ite.remove();
				return true;
			}
			
		
		
		}
		
		return false;
	}
	
	//prints in order it was inserted in
	public void inOrder() {

		ListNode current = front;
		while (current != null) {
			System.out.println(current);
			current = current.next;
		}
	}

	
	public class ListNode {

		private E data;
		private ListNode next;
		private ListNode prev;

		public ListNode(ListNode p, E data, ListNode n) {

			this.data = data;
			prev = p;
			next = n;
		}

		public boolean equals(Object other) {

			E otherData = ((ListNode) other).data;
			return data.equals(otherData);
		}

		public String toString() {
			return "" + data;
		}
	}
}

import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This class represents a PriorityQueue with a max capacity that is positive. It cannot
 * go above the max elements and is a doubly linked list implementation. 
 * 
 * Answer to TPQ: This is because if we extended linkedList, we would not have 
 * access to its instance variables (since sub classes can't have access to super
 * class IVs and no accessors). This means we could not traverse the list when sorting
 * the element when adding since we do not have direct access to the list. We instead
 * use an instance variable so we can have direct access to the nodes in the list when
 * making our add method.
 */
public class ChungWilliam_LimitedPQ <E extends Comparable<E> >{

	private int MAX_ELEMENTS;
	private int numElements;
	private ListNode front;
	private ListNode end;
	
	public ChungWilliam_LimitedPQ(int maxCap) {
		
		if(maxCap<=0) {
			throw new IllegalArgumentException("BAD");
		}
		MAX_ELEMENTS = maxCap;
	}
	
	public boolean isEmpty() {
		return front ==null;
	}
	
	//returns null or if PQ is full and removes and returns the element with worst priority
	public E add(E item) {
	
		if(isEmpty()) {
			front = new ListNode(null,item,null);
			end = front;
			numElements++;
			return null;
		}
		
		ListNode current = front;
	
		//iterating through list
		do  {
			
			//found one with better priority or equal and put earlier, so we insert
			if(item.compareTo(current.data)<=0) {
								
				current.prev = new ListNode(current.prev,item,current);
				
				if(current==front) {
					front = current.prev;
					
				}
				else {
					current.prev.prev.next = current.prev;
				}
			
				//removes the most priority element if we are at max capacity
				if(numElements==MAX_ELEMENTS) {
					ListNode toRem = end;
					end =end.prev;
					end.next = null;
					toRem.prev=null;
					return toRem.data;
				}
				else {
					numElements++;
					return null;
				}
				
			}
			current = current.next;
		}while(current!=null);
		
		//does not insert if we reached max capacity and item is most priority
		if(numElements==MAX_ELEMENTS) {
			return item;
		}
		else {
			end = new ListNode(end,item,null);
			ListNode oldEnd = end.prev;
			oldEnd.next = end;
			numElements++;
			return null;
		}
		
		
	}
	
	//removes most priority
	public E remove() {
		
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		
		ListNode toRem = end;
	
		if (numElements==1) {
			end = null;
			front = null;
		}
		else {
			end = end.prev;
			end.next = null;
			toRem.prev=null;
		}
		numElements--;
		return toRem.data;
	}
	
	//accesses most priority
	public E peek() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		
		return end.data;
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

import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This a circular LinkedList implementation of a Queue. 
 * The first thing added in the queue is the first one to
 * be removed/accessed.
 */
public class ListQueue <E>implements Queue<E>{

	ListNode end;
	
	public boolean isEmpty() {
		return end==null;
	}
	
	//adds the item end of the queue 
	public void add(E item) {
		
		//adds an element to the queue if empty
		if(isEmpty()) {
			end = new ListNode(item,null);
			end.next = end;
		}
		
		//if not makes a queue next to the old end and updates
		else {
			end.next = new ListNode(item,end.next);
			end = end.next;
		}
		
	}
	
	//returns first element in the list without removing
	public E peek() {
		
		//can't return anything if queue is empty
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return end.next.data;
	}

	//returns and removes the first element in the queue
	public E remove() {
		
		//can't remove something in an empty queue
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		//there's nothing in the queue if its only element is removed
		else if(end.next==end) {
			E toRemove = end.data;
			end = null;
			return toRemove;
		}
		
		else {
			ListNode toRemove = end.next;
			end.next = end.next.next;
			toRemove.next = null;
			return toRemove.data;	
		}
		
	}

	
	public class ListNode {
		private E data;
		private ListNode next;

		public ListNode(E d, ListNode n) {
			data = d;
			next = n;
		}
		
	}
	
	
}

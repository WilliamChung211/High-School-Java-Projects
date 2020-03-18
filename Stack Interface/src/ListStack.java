import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This is a implementation of stack using a singly linked list
 */

public class ListStack <E> implements Stack<E> {

	ListNode top;

	
	//adds the item top of the stack 
	public void push(E item) {
		top = new ListNode(item, top);
	}

	//removes from the top of the stack and returns what was just removed
	public E pop() {
		
		//cannot remove from an empty stack
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		ListNode toRem = top;
		top = top.next;
		toRem.next = null;
		return toRem.data;
	}

	//returns the top without deleting it
	public E peek() {
		
		//cannot return anything from an empty list
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return top.data;
		
	}


	public boolean isEmpty() {
		return top==null;
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

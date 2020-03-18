import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This class implements a generic Double Linked List which tracks the total 
 * elements in the list and first and last list node. It also has the list node 
 * class which maintains a data, next, and previous component.
 *  It now incorporates a listIterator method with methods relating
 *  the next and previous element. 
 */

public class ChungWilliam_DoublyLinkedList<E> {
	
	public ListIterator<E> listIterator() {

		return new ListIterator<E>() {

			private ListNode iterLoc = front;
			private STATUS stat = STATUS.NONE;

			//returns if there is another element when sliding forwards
			public boolean hasNext() {
				return iterLoc != null;
			}


			//returns the next element and slides to the right
			public E next() {

				if (!hasNext()) {
					throw new NoSuchElementException();
				}

				stat = STATUS.NEXT;
				E toReturn = iterLoc.data;
				
				//slides to the right
				iterLoc = iterLoc.next;
				return toReturn;

			}

			//checks if the list has more elements when going in the reverse direction
			public boolean hasPrevious() {	
				return !isEmpty()&&(iterLoc == null || iterLoc.prev != null);
			}

			
			//returns the previous location and slides back to that location
			public E previous() {
				
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				
				
				stat = STATUS.PREVIOUS;
				E toReturn = null;
				
				// if the iterator is slid down to the right, it goes back to the end and return the end
				if (iterLoc == null) {
					toReturn = end.data;
					iterLoc = end;
				}
				
				//if not, it just returns the previous element and slides in the reverse
				else {
					
					toReturn = iterLoc.prev.data;
					// slides to the left
					iterLoc = iterLoc.prev;
				}
				
				return toReturn;
			}

			//removes whatever was just called by previous or next
			public void remove() {

				//if previous and next was not called, this method errors which is bad
				if (stat == STATUS.NONE) {
					throw new IllegalStateException("Bad");
				}
				
				else if (stat == STATUS.PREVIOUS) {
				
					//if previous was called and it is the first in the list, it removes the first in the list
					if ( iterLoc.prev == null) {
						removeFirstInList();
					}

					//if it was not the first in the list, but instead the end, it completely removes the last element in the list
					else if (iterLoc== end) {
						ListNode toRem = end;
						end = end.prev;
						toRem.prev = null;
						end.next = null;
					}
					
					//disconnects the previous node from the list
					else {
						
						ListNode toRem = iterLoc;
						
						//slides back to the right
						iterLoc = iterLoc.next;
						
						//removes the element from the list entirely
						toRem.prev.next = toRem.next;
						toRem.next.prev = toRem.prev;
						toRem.prev = null;
						toRem.next = null;
						
					}
				}

				else {

					//if the last element was called for next, it removes the last element
					if (iterLoc == null) {
						removeLastInList();

					}

					//if not the last element, but the first element was called for next, it removes the first 
					else if (iterLoc.prev == front) {
						
						ListNode toRem = front;
						front = front.next;
						numElements--;
						toRem.next = null;
						front.prev = null;
						
					}

					//disconnects the node that was just called by data
					else {
						ListNode toRem = iterLoc.prev;

						toRem.prev.next = iterLoc;
						iterLoc.prev = toRem.prev;

						// gets rid of any connections the removed data had from the list
						toRem.prev = null;
						toRem.next = null;
						numElements--;
					}
					
				}
				
				stat = STATUS.NONE;
			}

			//inserts the element into the list before the element that would be returned by next
			public void add(E item) {
				
				if (iterLoc!=null) {
					
					//inserts the element before the iterloc
					iterLoc.prev = new ListNode(iterLoc.prev, item, iterLoc);
					
					//connects the element before the newly placed element with it if there is one
					if (iterLoc.prev.prev != null) {
						iterLoc.prev.prev.next = iterLoc.prev;
					} 
					
					//if there is not one, then the newly placed element is at the front of the list
					else {
						front = iterLoc.prev;
					}
					
				} 
				
				//if the iterLoc is at the very very end with no next, it adds to the last
				else {
					addLast(item);
				}

				//resets whether next or previous was called since the list changed
				stat = STATUS.NONE;
				numElements++;

				
			}
			
			//Replaces the last element returned by next or previous
			public void set(E item) {
				
				//next or previous must be called or this does not work which is bad
				if (stat == STATUS.NONE) {
					throw new IllegalStateException("Bad");
				}

				ListNode current = null;
				
				//checks if previous or next was last called
				if (stat == STATUS.PREVIOUS) {
					current = iterLoc;
				} 
				
				else {
					current = iterLoc.prev;
				}

				//sets the data of what was last called 
				current.data = item;
				
			}
			
			//overriding
			public int previousIndex() {
				return 0;
			}
			
			//overriding
			public int nextIndex() {
				return 1;
			}

		};
		
	}

	private enum STATUS {
		NONE, NEXT, PREVIOUS
	};
	
	private ListNode front;
	private ListNode end;
	private int numElements;

	// checks if there are no elements in the list
	public boolean isEmpty() {
		return numElements == 0;
	}

	// Adds a new element to the front of the list
	public void addFront(E data) {

		front = new ListNode(null, data, front);

		// updates the list since after adding the first element
		numElements++;

		// if the new first element is the only element, it is also the last element
		if (numElements == 1) {
			end = front;
		}

		// connects the new first element to the new 2nd element
		else {
			ListNode oldFront = front.next;
			oldFront.prev = front;
		}

	}

	// inserts data after target
	public void addAfter(int index, E data) {

		// checks if the index exists
		if (index < 0 || index >= numElements) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}

		// if the index is the last index, it puts the data to the last
		if (index == numElements - 1) {
			end = new ListNode(end, data, null);
			end.prev.next = end;
			
		}

		else {

			// gets the element at the index
			ListNode current = getNode(index);

			// connects the data to the list after the indexed element
			current.next = new ListNode(current, data, current.next);
			current.next.next.prev = current.next;

		}
		
		numElements++;

	}

	// adds data to the end of the list
	public void addLast(E data) {

		end = new ListNode(end, data, null);
		numElements++;

		// if the new last element is the only element, it is also the first element
		if (numElements == 1) {
			front = end;
		}

		// connects the new last element to the new 2nd to last element
		else {
			ListNode oldEnd = end.prev;
			oldEnd.next = end;
		}

	}

	// removes first occurrence of data
	public void remove(E data) {

		// data cannot be found in an empty list
		if (isEmpty()) {
			throw new NoSuchElementException("We cannot remove an element in an empty list.");
		}

		// if the first data is the first occurrence, it removes at the first
		if (front.data.equals(data)) {
			removeFirstInList();
			return;
		}

		ListNode current = front;

		// jumps through each node after the first data till it reaches the last one or
		// finds the data
		while (current != null) {

			// if the next data is equal to the data, it de-links the data from the list
			if (current.data.equals(data)) {

				current.prev.next = current.next;
				current.next.prev = current.prev;

				// gets rid of any connections the removed data had from the list
				current.prev = null;
				current.next = null;

				// updates the list size
				numElements--;
				return;

			}

			// goes to next node
			current = current.next;
		}

		// if the last element is the only occurence, it removes it.
		if (end.data.equals(data)) {

			ListNode toRem = end;
			end = end.prev;
			numElements--;
			
			// completely disconnects the new last and old last from each other
			toRem.prev = null;
			end.next = null;
			
			return;
		}

		// no element if there is no first occurrence
		throw new NoSuchElementException("That data does not occur in this list");

	}

	// removes the first element from the list
	public void removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException("We cannot remove an element in an empty list.");
		}
		
		removeFirstInList();
		
	}
	
	//removes the first element from the list assuming the list is not empty
	private void removeFirstInList() {
		ListNode toRem = front;
		front = front.next;

		numElements--;

		if (numElements == 0) {
			end = null;
		} else {
			// completely disconnects the new front and old front from each other
			toRem.next = null;
			front.prev = null;
		}
	}

	// removes the last element from the list
	public void removeLast() {
		
		if (isEmpty()) {
			throw new NoSuchElementException("We cannot remove an element in an empty list.");
		}

		removeLastInList();
		

	}
	
	//removes the last element from the list assuming the list is not empty
	private void removeLastInList() {
		
		ListNode toRem = end;
		end = end.prev;

		numElements--;
		
		if(numElements==0) {
			front =null;
		} else {
		// completely disconnects the new last and old last from each other
		toRem.prev = null;
		end.next = null;
		}
		
	}

	// returns the total number of elements in the list
	public int size() {
		return numElements;
	}

	// returns data based on the index passed in
	public E get(int index) {
		return getNode(index).data;
	}

	// sets an element at an index as a new value and returns the old value
	public E set(int index, E value) {

		// gets the node at the element and changes the data contained inside it to be
		// the new value
		ListNode current = getNode(index);
		E replaced = current.data;
		current.data = value;

		return replaced;

	}

	// Gets the node at the index
	private ListNode getNode(int index) {

		// Index must be in the list
		if (index < 0 || index >= numElements) {
			throw new IllegalArgumentException("The index is out of bounds");
		}

		ListNode current = front;

		// goes through the nodes till it reaches the index
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current;

	}

	// The listNode classes manages the data it holds and the prvious and next
	// listNode it is linked to
	public class ListNode {

		private E data;
		private ListNode next;
		private ListNode prev;

		public ListNode(ListNode p, E d, ListNode n) {
			prev = p;
			data = d;
			next = n;
		}

	}

}

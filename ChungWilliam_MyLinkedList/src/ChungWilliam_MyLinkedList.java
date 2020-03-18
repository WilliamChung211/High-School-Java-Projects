/*
 * Name: William Chung
 * 
 * This class is a LinkedList class that maintains one ListNode instance variable that 
 * refers to the first node in the singly linked list
 */

public class ChungWilliam_MyLinkedList<E> {

	private ListNode front;

	private ListNode getNode(int index) {
		ListNode current = front;
		int ind = 0;

		// jumps through each data till it gets to the data at the specified
		// index or there are no more nodes left
		while (current != null) {

			// if it gets to the index, it returns the data
			if (ind == index) {
				return current;
			}

			current = current.next;
			ind++;
		}

		// Throws exception if the index does not exist if the data was null at
		// the index or empty
		throw new IndexOutOfBoundsException("The index does not exist");

	}

	// returns true if the list is empty
	public boolean isEmpty() {
		return front == null;
	}

	// Adds data in front of the list
	public void addFront(E data) {
		front = new ListNode(data, front);
	}

	// Inserts data at index and all elements after that slide to the right by
	// one
	public void addAfter(int index, E data) {

		if (index < 0) {
			throw new IllegalArgumentException("The index is out of bounds");
		}

		// if the index is 0, it will add in front
		if (index == 0 && front != null) {
			addFront(data);
			return;
		}

		ListNode current = getNode(index - 1).next;

		// jumps through each node till it gets to the node before the specified
		// index or there are no more nodes left
		current.next = new ListNode(data, current.next);

	}

	// Removes first occurrence of data
	public void remove(E data) {

		// removes noting if list is empty
		if (isEmpty()) {
			return;
		}

		// if the first data is the first occurrence, it removes at the first
		if (front.data.equals(data)) {
			remFront();
		}

		ListNode current = front;

		// jumps through each node after the first data
		while (current.next != null) {

			// if the next data is equal to the data, it de-links the data from
			// the list
			if (current.next.data.equals(data)) {
				removeNext(current);
				return;
			}

			// goes to next node
			current = current.next;
		}

		// removes nothing if there is no first occurrence

	}

	// Removes data at index and returns that removed data
	public E remove(int index) {

		// if the list is empty, the index is out of bounds
		if (isEmpty()) {
			throw new IllegalArgumentException("The index does not exist");
		}

		// if the index is 0, it disconnects the disconnects the first data from
		// the list
		if (index == 0) {
			return remFront();
		}

		return removeNext(getNode(index - 1));
	}

	// disconnects the first data from the list and returns that data
	private E remFront() {
		ListNode toRem = front;
		front = front.next;
		toRem.next = null;
		return toRem.data;
	}

	// disconnects the node after the given from the list.
	private E removeNext(ListNode node) {

		ListNode toRem = node.next;

		// makes the node of the given node, the node after the removed node
		node.next = toRem.next;

		// disconnects the removed node completely from the list and returns the
		// data
		toRem.next = null;
		return toRem.data;
	}

	// Returns the size of the current number of elements in the list
	public int size() {

		int size = 0;

		ListNode current = front;

		// goes through each node of the list
		while (current != null) {
			size++;
			current = current.next;
		}

		// returns the size based on the number of nodes
		return size;
	}

	// returns the list of the first occurance of the list. Returns -1 if not
	// found
	public int indexOf(E data) {

		ListNode current = front;
		int index = 0;

		// goes through each data of the list
		while (current != null) {

			// if the data in the index is the same as the given data, it
			// returns the index
			if (current.data.equals(data)) {
				return index;
			}

			// goes to next data and updates the index
			current = current.next;
			index++;
		}

		return -1;
	}

	// Returns an element based on the index passed
	public E get(int index) {

		return getNode(index).data;

	}

	// Replaces the value at index with toReplace and returns the previous value stored at index
	public E set(int index, E toReplace) {

		ListNode current = getNode(index);
		E replaced = current.data;
		current.data = toReplace;
		return replaced;

	}

	// Returns a String of all data with a \n between each piece
	public String toString() {

		String toReturn = "";
		ListNode current = front;

		// goes through each node till it equals null
		while (current != null) {

			// builds a string by adding the data and a \n
			toReturn += current.data + "\n";
			current = current.next;

		}

		// returns the data
		return toReturn;

	}

	// The listNode classes manage the data it holds and the next listNode it is linked to
	public class ListNode {
		private E data;
		private ListNode next;

		public ListNode(E d, ListNode n) {
			data = d;
			next = n;
		}

	}

}

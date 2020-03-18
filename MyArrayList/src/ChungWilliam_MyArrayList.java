import java.util.*;

/*
 * Name: William Chung
 * This class makes an array of a generic type that will represent
 *  is always full array that can be added to or removed. It 
 *  also implements the iterable interface with the iterator 
 * method that returns its own iterator for arraylist
 *
 */

public class ChungWilliam_MyArrayList<E> implements Iterable<E> {
	private E[] list;
	private int numElements;

	public ChungWilliam_MyArrayList() {
		this(10);
	}

	public ChungWilliam_MyArrayList(int max) {

		if (max < 0) {
			throw new IllegalArgumentException("Size cannot be negative");
		}

		list = (E[]) new Object[max];
		numElements = 0;
	}

	public ChungWilliam_MyArrayList(ChungWilliam_MyArrayList other) {

		this(other.list.length);
		numElements = other.numElements;

		for (int i = 0; i < other.list.length; i++) {
			list[i] = (E) other.list[i];
		}

	}

	// adds an element to the end
	public void add(E element) {

		numElements++;
		resize();
		list[numElements - 1] = element;

	}

	// adds an element onto an index and shifts the other elements to the or equal to the index to the right by 1
	public void add(int index, E element) {

		if ((index < 0) || (index > numElements)) {
			throw new IndexOutOfBoundsException("That number is out of bounds");
		}

		numElements++;
		resize();

		for (int i = numElements - 1; i > index; i--) {
			list[i] = list[i - 1];
		}

		list[index] = element;

	}

	// resizes the list if it is full
	private void resize() {

		if (numElements == list.length) {

			E[] temp = list;
			this.list = (E[]) new Object[numElements * 2];

			for (int i = 0; i < temp.length; i++) {
				list[i] = temp[i];
			}
		}

	}

	// returns an element based on index
	public E get(int index) {

		boundChecker(index, numElements);
		return list[index];

	}

	// Returns index of first location of element
	public int indexOf(E element) {

		for (int i = 0; i < numElements; i++) {

			if (list[i].equals(element)) {
				return i;
			}

		}

		return -1;

	}

	// checks if the array is empty
	public boolean isEmpty() {
		return (numElements == 0);
	}

	// removes a value at the index and shifts remaining elements down
	public void remove(int index) {

		boundChecker(index, numElements);
		for (int i = index; i < numElements - 1; i++) {
			list[i] = list[i + 1];
		}

		numElements--;
	}

	// removes the first instance of the element and returns true if the element
	// was found
	public boolean remove(E element) {

		int index = indexOf(element);

		if (index != -1) {
			remove(index);
		}

		return index != -1;

	}

	// Changes the element at a given position and returns original value
	public E set(int index, E element) {

		boundChecker(index, numElements);
		E old = list[index];
		list[index] = element;
		return old;

	}

	// check if index is legal
	private void boundChecker(int index, int numElements) {

		if ((numElements < 0) || (index >= numElements)) {
			throw new IndexOutOfBoundsException("That number is out of bounds");
		}

	}

	// returns the total number of active elements
	public int size() {
		return numElements;
	}

	//Returns an iterator of arraylist
	public Iterator<E> iterator() {

		//makes a new anonymous class
		return new Iterator<E>() {

			int nextLoc = 0;
			boolean moveOk = false;

			//returns true if there are more elements left to be examined
			public boolean hasNext() {
				return (nextLoc < numElements);
			}

			//returns the next element in the ArrayLisst
			public E next() {
				
				//exception if there are no elements left to be examined
				if (!hasNext()) {
					throw new NoSuchElementException("No elements left to be examined");
				}
				
				//goes on to the next element after; also a remove method can be called after
				nextLoc++;
				moveOk = true;
				
				return list[nextLoc - 1];
				
			}

			//removes the element just returned by next
			public void remove() {

				//next must be called prior to remove or exception
				if (!moveOk) {
					throw new IllegalStateException("You did not call the next method before this :(");
				}

				//Removes the element just called by next and updates the nextLoc back to it
				nextLoc--;
				ChungWilliam_MyArrayList.this.remove(nextLoc);
				
				//Remove cannot be called twice in a row
				moveOk = false;
				
			}

		};
		
	}

}

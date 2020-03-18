import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This is a implementation of stack using an array
 */

public class ArrayStack <E> implements Stack<E> {

	private E [] data = (E[])new Object[10];
	private int topLoc = -1;
	
	//resizes the list by twice the size
	private void resize() {

		//checks if the new top location is a valid index
		if (topLoc == data.length) {

			E[] temp = data;
			
			//doubles the list length by 2
			data = (E[]) new Object[topLoc * 2];

			//puts all the data in the newly sized list
			for (int i = 0; i < temp.length; i++) {
				data[i] = temp[i];
			}
			
		}

	}
	
	//makes the next index the top of the stack
	public void push(E item) {
		topLoc++;
		resize();
		data[topLoc]=item;
	}

	//removes from the top of the stack and returns what was just removed
	public E pop() {
		
		//cannot remove from an empty list
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E toReturn =  data[topLoc];
		//makes the new top location the location before the old top location
		topLoc--;
		
		//returns the old top location
		return toReturn;
	}

	//returns the top of the stack without "removing" it
	public E peek() {

		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		//returns the top of stack's data
		return data[topLoc];
	}

	public boolean isEmpty() {	
		return topLoc==-1;
	}

	
}

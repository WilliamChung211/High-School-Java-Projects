import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * 
 * This is a ring buffer (circly array) implimentation 
 * of a Queue (FIFO). The addloc and front loc can loop around 
 * the array till the number of elements exceed the array
 * length. Then, the array will be resized.
 */
public class ArrayQueue <E> implements Queue<E> {

	private E [] data = (E[])new Object[10];
	private int frontLoc = 0;
	private int addLoc = 0;
	private int numElements = 0;
	
	public boolean isEmpty() {
		return numElements ==0;
	}

	// resizes the list by twice the size
	private void resize() {

		// checks if the array is full of elements of the queue
		if (numElements == data.length) {

			E[] temp = data;

			// doubles the array length by 2
			data = (E[]) new Object[numElements * 2];

			// puts all the data in the newly sized array, but this time in order from front loc to the add loc.
			for (int i = frontLoc; i < temp.length + frontLoc; i++) {
				data[i - frontLoc] = temp[i % temp.length];
			}

			frontLoc = 0;
			addLoc = numElements;
		}

	}

	//adds the item end of the queue 
	public void add(E item) {

		resize();
		data[addLoc] = item;
		
		//then it updates the add locaiton and updates the number of elements
		addLoc=(addLoc+1)%data.length;
		numElements++;
	}
	
	//returns first element in the list without removing
	public E peek() {
		
		//can't return anything if queue is empty
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return data[frontLoc];
	}

	//returns and removes the first element in the queue
	public E remove() {
		
		//can't remove something in an empty queue
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E toRem = data[frontLoc];
		
		//then it updates the front location and elements
		frontLoc=(frontLoc+1)%data.length;
		numElements--;
		
		return toRem;
	}

	
}

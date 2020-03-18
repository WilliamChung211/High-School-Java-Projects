import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * Name: William Chung
 * 
 * This program represents an array based implementation of a Heap that
 * implements the PriorityQueue interface. The priority queue is a queue
 * that prioritizes priority as the thingy that will be removed. Heaps are
 * complete binary trees that only cares about having the kid node(s) having less
 * priority than the parent node. Root always has most priority.
 *  */
public class ChungWilliam_ArrayHeap <E extends Comparable<E>> implements PriorityQueue <E>{

	private E[] data;
	private int numElements;
	
	public ChungWilliam_ArrayHeap() {
		
		data =  (E[])new Comparable[10];
		numElements = 0;
	}

	public boolean isEmpty() {
		return numElements==0;
	}

	//resizes the data if it is full
	private void resize(){ 
		
		if (numElements==data.length){
			
			E [] temp = data;
			 this.data = (E[])new Comparable[numElements*2];
			 
			 for (int i = 0; i < temp.length; i++) {
					data[i] = temp[i];
				}
		}

	}
	
	//adds based on priority
	public void add(E item) {	
		
		numElements++;
		resize();
		int insInd  = numElements-1;
		int checkInd = (insInd-1)/2;
		data[insInd]=item;
		
		
		//keeps swapping as long as the parent node has less priority 
		while(checkInd>=0&&data[insInd].compareTo(data[checkInd])<0) {
			swap(data,insInd,checkInd);
			insInd = checkInd;
			checkInd = (insInd-1)/2;
		}
	}

	private void swap(E[]arr, int firInd, int secInd) {
		E temp = arr[firInd];
		arr[firInd]=arr[secInd];
		arr[secInd]= temp;
	}

	//removes the node with most priority(smallest value)
	public E remove() {
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		numElements--;
		E toRet=data[0];
		data[0]=data[numElements];
		int checkInd = 0;
		
		//keeps swapping down with the most priority child as long as it has more priority than parent
		while(checkInd*2+2<numElements) {
			
			int swapInd;
			if(data[checkInd*2+1].compareTo(data[checkInd*2+2])<=0) {
				swapInd = checkInd*2+1;
			}
			else {
				swapInd = checkInd*2+2;
			}
			
			if(data[swapInd].compareTo(data[checkInd])<=0) {
				swap(data,checkInd,swapInd);
			}
			
			checkInd= swapInd;
			
		}
		
		return toRet;
		
	}

	//returns element with most priority
	public E peek() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[0];
	}
	
	

}

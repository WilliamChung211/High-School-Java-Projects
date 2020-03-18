/*
 * Name: William Chung
 * This class makes an array of objects that will represent is always full array that can be added to or removed.
 *
 * Answer: The datatype of result must be Object. This is because the get method returns an Object datatype.
 * The methods I can call on result can only be the public methods from the Object class such as the equals
 * method and the ToString method. This is because the reference parameter is of the Object datatype.
 *
 */

public class ArrayList1 {
	private Object [] list;
	private int numElements; 
	
	public ArrayList1() {
		this(10);
	}
	
	public ArrayList1(int max) {
		
		if(max<0){
			throw new IllegalArgumentException("Size cannot be negative");
		}
		
		list = new Object [max];
		numElements = 0;
	}
	
	public ArrayList1(ArrayList1 other) {
	
		this(other.list.length);	
		numElements = other.numElements;
		
		for (int i = 0; i < other.list.length; i++) {
			list[i] = other.list[i];
		}
	
	}
	
	//adds an element to the end
	public void add (Object element){
		
		numElements++;
		resize();
		list[numElements-1]=element;
		
	}
	
	//adds an element onto an index and shifts the other elements to the   or equal to the index to the right by 1
	public void add (int index, Object element){
		
		if ((index<0)||(index>numElements)){
			throw new IndexOutOfBoundsException("That number is out of bounds");
		}
		
		numElements++;
		resize();

		for(int i=numElements-1;i>index;i--){
			list[i]=list[i-1];
		}
		
		list[index]=element;
		
	}
	
	//resizes the list if it is full
	private void resize(){ 
		
		if (numElements==list.length){
			
			Object [] temp = list;
			 this.list = new Object [numElements*2];
			 
			 for (int i = 0; i < temp.length; i++) {
					list[i] = temp[i];
				}
		}

	}
	
	//returns an element based on index
	public Object get (int index){
		
		boundChecker(index,numElements);
		return list[index];

	}
	
	//Returns index of first location of element
	public int indexOf(Object element) {
		
		for(int i=0;i<numElements;i++){
			
			if(list[i].equals(element)){
				return i;		
			}
			
		}
		
		return -1;
		
	}
	
	//checks if the array is empty
	public boolean isEmpty(){
		return (numElements ==0);
	}
	
	//removes a value at the index and shifts remaining elements down
	public void remove(int index){
		
		boundChecker(index,numElements);
		for(int i=index;i<numElements-1;i++){
			list[i]=list[i+1];
		}
		
		numElements--;
	}
	
	//removes the first instance of the element and returns true if the element was found
	public boolean remove(Object element){
		
		int index= indexOf(element);
		System.out.println(index);
		
		if (index!=-1) {
			remove(index);
		}
		
		return index!=-1;	
		
	}
	
	//Changes the element at a given position and returns original value
	public Object set(int index, Object element){
		
		boundChecker(index,numElements);
		Object old = list[index];
		list[index]=element;
		return old;
		
	}
	
	//check if index is legal
	private void boundChecker(int index, int numElements){
		
		if ((numElements<0)||(index>=numElements)){
			throw new IndexOutOfBoundsException("That number is out of bounds");
		}
		
	}
	
	//returns the total number of active elements
	public int size(){
		return numElements;
	}
	
}

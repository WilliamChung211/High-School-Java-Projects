import java.util.*;


public class ListIteratorDemo {

	public static void main(String[] args){

		LinkedList<String> list = new LinkedList<String>();
		
		

		ListIterator<String> iter = list.listIterator();

	
		System.out.println(list);
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		iter.add("3000");
		iter.add("3030");
		iter.add("3=5400");
		iter.add("3000");
		iter.add("3030");
		iter.add("3=5400");
		iter.add("3000");
		iter.add("3030");
		iter.add("3=5400");
		
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}

		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.previous());
		System.out.println(iter.next());
		System.out.println(iter.previous());
		iter.set("5");
		
		System.out.println(list);

	}
	
	/*
	 * public String toString() {

		if(front==null) {
			return "[]";
		}
		ListNode current = front;
		String toReturn = "[";
		while (current != null) {
			toReturn += current.data + ", ";
			current = current.next;
		}

		
		return toReturn.substring(0,toReturn.length()-2) + "]";
	}
	 */
}

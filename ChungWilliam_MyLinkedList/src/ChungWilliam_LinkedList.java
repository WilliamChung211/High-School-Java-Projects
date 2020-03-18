

/*
 * Name: William Chung
 * 
 * This program is n updated version of the LinkedList class 
 * that cincluded a ListNode inner class and one outer class
 * instance variable representing the front of the list, but
 * now also include methods to add lists, remove occurances 
 * and dupicates, copy lists, and see if they equal
 */
public class ChungWilliam_LinkedList<E> extends  ChungWilliam_MyLinkedList<E>{

	private ListNode front;

	//returns true if other matches the calling object's linked lsit
	public boolean equals(Object other) {

		//checks even if other is a linked list
		if (!(other instanceof ChungWilliam_LinkedList)) {
			return false;
		}

		//undisguises other if it is a linked list
		ChungWilliam_LinkedList oth = (ChungWilliam_LinkedList) other;
		ListNode thisCur = front;
		ListNode othCur = oth.front;

		//goes through each node of the other list
		while (othCur != null) {

			//if the calling object's list has less elements or an element in the list that isn't the same, they are not a match
			if (thisCur == null || !othCur.data.equals(thisCur.data)) {
				return false;
			}

			thisCur = thisCur.next;
			othCur = othCur.next;
		}

		//assuming previous elements were a match, if the calling object does not have more elements than other, it is a match
		return thisCur == null;

	}


	public ChungWilliam_LinkedList(ChungWilliam_LinkedList<E> other) {

		//copies nothing if other is empty
		if (other.front == null) {
			return;
		}

		//makes the first element of the calling object, the same as the first element of the other object
		front = new ListNode(other.front.data, null);
		ListNode oth = other.front.next;
		ListNode copy = front;

		//goes through the rest of the nodes of other and makes copies of them to the calling element
		while (oth != null) {
			copy.next = new ListNode(oth.data, null);
			oth = oth.next;
			copy = copy.next;
		}

	}

	//Remove all occurrences of item from the list and quits if the item does not occur
	public void removeAll(E item) {

		//the item cannot occur if it is empty
		if (front == null) {
			return;
		}

		ListNode current = front;

		//goes through all the next nodes after
		while (current.next != null) {

			//keeps removing at the index, if the element at the index is the same as the item
			if(current.next.data.equals(item)) {
				ListNode toRem = current.next;
				current.next = toRem.next;
				toRem.next = null;
			}

			current = current.next;
		}
		
		if (front.data.equals(item)) {
			ListNode toRem = front;
			front = front.next;
			toRem.next = null;
		}
	}

	//Appends all elements in other to the end of the calling object's list
	public void addAll(ChungWilliam_LinkedList<E> other) {

		//Adds nothing if the other list contains nothing
		if (other.front == null) {
			return;
		}

		//gets the first elements of both lists
		ListNode current = front;
		ListNode oth = other.front;
		
		//if the calling object is empty, it starts appending by making the first element the same as other's first element
		if (front == null) {
			front = new ListNode(oth.data, front);
			oth = oth.next;
		}

		//goes through the remaining nodes of the calling object
		while (current.next != null) {
			current = current.next;
		}

		//adds the rest of the nodes of the other list to the calling object 
		addAll(current, oth);

	}

	//Inserts all items from other after an existing node represented by an existing index.
	public void addAll(int index, ChungWilliam_LinkedList<E> other) {

		//Adds nothing if there is nothing in other
		if (other.front == null) {
			return;
		}

		
		ListNode current = front;
		int ind = 0;

		//goes through each node of the calling object's list
		while (current != null) {

			//when it finds the specified node, it inserts all items from other after it
			if (ind == index) {
				addAll(current, other.front);
				return;
			}

			current = current.next;
			ind++;
		}

	}

	//Adds all items after an existing node in the linked list 
	private void addAll(ListNode current, ListNode oth) {
		
		//goes through each node in the other linked list
		while (oth != null) {
			
			//adds new nodes based on the all the items the other linked list
			current.next = new ListNode(oth.data, current.next);
			oth = oth.next;
			current = current.next;
		}

	}

	//Removes any consecutive duplicates from the list
	public void removeDups() {
		front = removeDupsHelper(front);
	}

	//Returns a list node with all its consecutive duplicates in its succeeding nodes removed
	private ListNode removeDupsHelper(ListNode n) {

		//if the next node does not exist, it returns the node
		if (n.next == null) {
			return n;
		}

		//removes the node if it succeeds a node with the same item
		if ((removeDupsHelper(n.next).data).equals(n.data)) {
			ListNode toRem = n.next;
			n.next = toRem.next;
			toRem.next = null;
		}

		return n;

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

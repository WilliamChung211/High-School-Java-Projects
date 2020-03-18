/*
 * Name: William Chung
 * This class represents a Bookstore and contains an array of book objects which will 
 * be kept full at all times
 * 
 * Thought Provoking Topic: If a client tries to add a magazine or travel guide object to the bookstore, it will
 * work. This is because a magazine or travel guide is a book since it is a subclass of a Book Object due to the
 * Is-A relationship between sub-classes and their respective superclass. Therefore, if you use a method with a parameter 
 * of a Book type, it will work because the a magazine class is a subclass of the book class, so it is technically is 
 * a book type, so it should work. 
 */

public class BookStore {

	private Book[] books;

	public BookStore() {
		books = new Book[0];
	}

	public BookStore(BookStore other) {

		books = new Book[other.books.length];

		for (int i = 0; i < books.length; i++) {
			books[i] = new Book(other.books[i]);
		}

	}

	// adds a new Book object to the array by ascending order if it exists or updates quantity
	public void add(Book toAdd) {

		int inder = searchInt((toAdd.getTitle()));

		// if the book exists in the array, it just adds the number of books specified to the quantity to the specified Book object
		if (inder != -1) {
			books[inder].setQuantity(books[inder].getQuantity() + toAdd.getQuantity());
		}

		else {

			// updates the array size
			Book[] temp = new Book[books.length + 1];
			int index = 0;

			// while resizing the array and copying the previous array's objects, it checks where to put the new Book object based on asscending order
			for (; index < books.length && !(books[index].compareTo(toAdd) > 0); index++) {
				temp[index] = books[index];
			}

			// when it finds out what index to put the Book Object, it places the BookObject on the appropriate index
			temp[index] = new Book(toAdd);

			// it then shifts the rest of the Book Objects to the right.
			for (; index < books.length; index++) {
				temp[index + 1] = books[index];
			}

			books = temp;
		}

	}

	// If allowed, it sells q amount of the Book containing the title parameter and returns the total amount sold.
	public int sell(String title, int q) {

		int ind = searchInt(title);

		// if the book is not found or the quantity is greater than the store has, the book cannot be sold
		if (ind != -1 || (books[ind].getQuantity() - q < 0)) {
			return -1;
		}

		else {
			books[ind].setQuantity(books[ind].getQuantity() - q);
			return q;
		}

	}

	// Removes the Book with the specified title from the array
	public void remove(String title) {

		// finds the index where the book is at
		int bookInd = searchInt((title));

		// if the specified book is not found in the array, nothing happens
		if (bookInd == -1) {
			return;
		}

		// resizes the array and copies back every book that is not the book with the specified title
		Book[] temp = new Book[books.length - 1];

		for (int j = 0; j < bookInd; j++) {
			temp[j] = books[j];
		}

		for (int j = bookInd; j < temp.length; j++) {
			temp[j] = books[j + 1];
		}

		books = temp;
	}

	// prints out all books in the store
	public void display() {

		for (int i = 0; i < books.length; i++) {
			System.out.println(books[i]);
		}

	}

	// returns true if the title exists, false otherwise
	public boolean search(String title) {

		return searchInt(title) != -1;
	}

	// using binary search, find the index in the array of the book that contains the specified title
	private int searchInt(String title) {

		int endLoc = books.length - 1;
		int startLoc = 0;

		while (startLoc <= endLoc) {

			int midIndex = (startLoc + endLoc) / 2;

			if ((books[midIndex].getTitle()).compareTo(title) == 0) {
				return midIndex;
			}

			else if (books[midIndex].getTitle().compareTo(title) > 0) {
				endLoc = midIndex - 1;
			}

			else {
				startLoc = midIndex + 1;
			}

		}

		return -1;

	}

}

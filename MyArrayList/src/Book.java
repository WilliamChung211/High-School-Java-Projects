/*
 * Name: William Chung
 * This class represents a book which has a title, price, and quantity the number of copies that the bookstore has of a particular book. 
 */
public class Book {
	
	private String title;
	private double price;
	private int quantity;
	
	
	public Book(String tit, double pri, int quant){
		
		setQuantity(quant);
		if (pri<0){
			throw new IllegalArgumentException("Price cannot be negative");
		}
		price = pri;
		title = tit;
		
	}
	
	public Book (Book other){
		this(other.title,other.price,other.quantity);
	}
	
	//this method compares books based on title and returns a negative number if the calling objects comes before the book parameter
	public int compareTo(Book other){
		return title.compareTo(other.title);
	}
	
	public String toString(){
		return "\n Title: " + title + "\n Price: " + price + "\n Quantity: " + quantity ;
	}
	
	public void setQuantity(int quant){
		
		if (quant<0){
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		quantity = quant;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	
}

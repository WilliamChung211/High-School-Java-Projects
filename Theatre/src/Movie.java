/*
 * Name: William Chung
 * This program maintains the name of the movie, the time of the movie, and the occupancy of the theatre 
 * and total tickets sold. It also stores all customers seeing the movie.
 */
public class Movie {

	private String movName;
	private Time movTime;
	private int maxOccu;
	private int totTick;
	private Customer[] custList;

	// initializes variables and makes occupancy of the theater a random numberbetween 65 and 120
	public Movie(String name, Time time) {
		movName = name;
		movTime = time;
		maxOccu = (int) (Math.random() * 56) + 65;
		totTick = 0;
		custList = new Customer[0];
	}

	public String getName() {
		return movName;
	}

	public Time getTime() {
		return movTime;
	}

	public int getMax() {
		return maxOccu;
	}

	public int getTick() {
		return totTick;
	}

	public Customer[] getCusts() {
		return custList;
	}

	// returns true if the movie is at capacity
	public boolean isFull() {
		return maxOccu == totTick;
	}

	// verifies if they can attend the movie and adds a customer to the list if they can.
	public boolean sellTicket(Customer other) {

		// checks if a seat is available
		if (this.isFull()) {
			return false;
		}

		// checks if they came before the start of the movie
		if (other.getTime().compareTo(movTime) >= 0) {
			return false;
		}

		totTick++;

	
		Customer[] temp = new Customer[custList.length + 1];

		for (int i = 0; i < custList.length; i++) {
			temp[i] = custList[i];
		}
		
		// adds the customer to the list
		temp[temp.length - 1] = other;
		custList = temp;
		return true;

	}

	public String toString() {
		return String.format("%-70s %5s", "Title: " + movName, "Show Time:    "+ movTime)+ "\n"+ String.format("%-70s %5s", "Max Occupency: " + maxOccu,"Seats Filled: " + totTick)	+ "\n \n"+ String.format("%-22s %5s", "Name", "Arrival");
	}

	// prints out all the movie information and also the customers watching it and their info
	public void printMovie() {
	
		System.out.println(this);
		
		for (int i = 0; i < custList.length; i++) {

			System.out.println(String.format("%-22s %5s", custList[i].getCust(), custList[i].getTime()));
			System.out.println();

		}
	}

}

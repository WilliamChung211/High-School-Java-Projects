/*
 * Name: William Chung
 * This class maintains a customer's name, the name of the movie they want to see,
 * and the Time they arrive to the theatre to try and purchase the ticket
 */
public class Customer {
	
	private String custName;
	private String movName;
	private Time arrTime;
	
	//initializes customer's name, movie name, and arrival time
	public Customer(String cust, String movie, Time arrival){
		
		custName = cust;
		movName = movie;
		arrTime = arrival;
		
	}
	
	public Time getTime(){
		return arrTime;
	}
	
	public String getCust(){
		return custName;
	}
	
	public String getMov(){
		return movName; 
	}
	
	public String toString(){
		return "Customer: " + custName + " Movie: " + movName + " Arrival Time: " + arrTime; 
	}
}

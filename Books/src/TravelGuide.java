/*
 * Name: William Chung
 * 
 * This is a subclass of the Book class that represents a travel guide which is a book 
 * that also maintains the destination country that is the travel topic. It also compares
 * destination countries
 */

public class TravelGuide extends Book implements Comparable <TravelGuide>{
	
	private String destCountry;
	
	public TravelGuide (String tit, double pri, int quant, String dest){
		super(tit,pri,quant);
		destCountry = dest;
	}
	
	//Compares alphabetically based on the destination country location
	public int compareTo(TravelGuide other) {
		return (destCountry.toLowerCase()).compareTo(other.destCountry.toLowerCase());
	}
	
	
	public String toString(){
		return super.toString() + "\n Destination Country: " + destCountry;
	}

	
	
}

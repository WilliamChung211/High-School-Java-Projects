public class Screening {

	private Time movTime;
	private int maxOccu;
	private int totTick;

	public Screening(Time time) {

		maxOccu = (int) (Math.random() * 56) + 65;
		movTime = time;

	}

	// verifies if they can attend the movie and adds a customer to the list if
	// they can.
	public void sellTicket(Time time, int numTic) {

		// checks if a seat is available
		if ((maxOccu <totTick+numTic)||(time.compareTo(movTime) >= 0)) {
			throw new IllegalArgumentException("Cannot sell tickets. Max Occupation hit");
		}

		totTick+=numTic;

	}

	public Time getTime(){
		return movTime;
	}
	
	//delete later
	public String toString(){
		return movTime + " "+  maxOccu;
	}
	
}

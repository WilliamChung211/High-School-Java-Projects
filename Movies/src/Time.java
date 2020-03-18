/*
 * Name: William Chung
 * This class maintains the hour and minute of a day in military time.
 */
public class Time {

	private int hour;
	private int minute;

	public Time(int our, int min) {

		if ((our > 23) || (min >= 60)) {
			throw new IllegalArgumentException("Invalid time");
		}

		hour = our;
		minute = min;
	}

	// returns the time in HH::MM format. pads a 0 if min or hour is single digit.
	public String toString() {
		String time = "";
		
		if (hour < 10) {
			time += "0";
		}
		
		time += hour + ":";
		
		if (minute < 10) {
			time += "0";
		}
		
		return time + minute;

	}

	//Returns negative value if calling object come before parameter. Positive if after. 0 if same
	public int compareTo(Time other) {
		
		int diff = this.hour - other.hour;
		
		if (diff == 0) {
			return this.minute - other.minute;
		}
	 
		
		return diff;
	}

}

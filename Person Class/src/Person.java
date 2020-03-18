
/*
 * Name: William Chung 
 * Description: Class for human's name, age, and birthday. Also, prints happy birthday and increases age by one if the birthday is same as date.
 *
 *`Explanation: An error pops up for an unresolved compilation problem stating that the constructor is undefined.
 */

public class Person {

	private String name;
	private int age;
	private String birthday;

	// initializes all three variables
	public Person(String nam, int ag, String bday) {
		
		if (ag<0){
			throw new IllegalArgumentException("Age is never negative.");
		}
		
		name = nam;
		age = ag;
		birthday = bday;
		
	}

	// initializes 2 variables
	public Person(String nam, String bday) {
		
		name = nam;
		age = 0;
		birthday = bday;
		
	}

	public String getName() {
		return name;	
	}

	public int getAge() {
		return age;	
	}

	public String getBirthday() {
		return birthday;	
	}

	// if the month and day of the date is the same as the month and date of birthday, it displays a message and increases age by one.
	public void isBirthday(String date) {
		
		if ((birthday.substring(0, 5).equals(date.substring(0, 5)))) {
			System.out.println("Happy Birthday!");
			age++;
		}

	}

	//
	public String toString() {
		return name + "'s age is " + age + " and birthday is " + birthday;
	}

}

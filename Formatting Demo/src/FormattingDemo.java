
public class FormattingDemo {

	public static void main(String[] args){
		
		// the static method format of the String
		// class can be used to store a String with
		// particular formatting that would appear
		// when outputted.  This is very helpful when you
		// don't want to ou0tput your data immediately.
		
		//for every piece of data that you would like to format
		// that piece of data will be represented by a %
		// every % inside your formatted string requries 
		// an additional argument.
	
		String myString = String.format(" %10s   %5s", "PT", "dJPT");
		System.out.println(myString);
		
		//The first argument is always a string.  It can contain
		// plain old text as well as spaces.  Above I want t.o format
		// two pieces of data which also happen to be strings.  I have
		// two additional arguments in format because I have two % within the first string arg.  If 
		// I wanted to format 3 pieces of data I would have had 3 % and 
		// 4 total arguments.
		
		// the numbers indicate how much space I want.  
		// Here I want the first piece of data to reserve 10 spaces
		// and the second to reserve 5.
		
		//After the number, I use 1 letter to represent the type of data.
		// s is for Strings.  d is for integers and f is for doubles. 
		// c is for a single character.  
		
		//All the data is currently right justified.  I would need a 
		// - sign to indicate if I want the data to be left justified.
		
		myString = String.format("%-10s  %5d", "Lost", 4);
		System.out.println(myString);
		// here I am formatting the word Lost to be left
		//justified with 10 spaces reserved for it.  
		// immediately following that I have reserved 5 spaces
		// at right justification for the number 4.

		//Here I'm specifying two digits to the right of the decimal.
		myString = String.format("%.2f Owed", 3.9997);
		System.out.println(myString);


	}
}

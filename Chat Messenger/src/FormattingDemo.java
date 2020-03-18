import java.util.*;
public class FormattingDemo {

	public static void main(String[] args){
		
		// the static method format of the String
		// class can be used to store a String with
		// particular formatting that would appear
		// when outputted.  This is very helpful for ensuring the same amount of blank
		// line spacing is reserved regardless of the length of variables being printed.
		
		//for every piece of data that you would like to format
		// that piece of data will be represented by a % inside a literal string
		// every % inside your formatted string requries 
		// an additional argument.
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String word1 = input.nextLine();

		System.out.print("Enter another word: ");
		String word2 = input.nextLine();
		System.out.println();
		
		String myString = String.format("Input: %15s %20s", word1, word2);
		System.out.println(myString);
		System.out.println();
		//The first paramter of the format method is always a string.  It can contain
		// plain old text as well as spaces.  Above I want to format
		// two pieces of data next to the word "Input". The two pieces of data I wanted to format are strings.  
		// I have two additional parameters in the format method because I have two % within the first string arg.  
		// If I wanted to format 3 pieces of data I would have had 3 % and 4 total parameters.
		
		// the numbers next to the % sign indicate how much space I want to reserve.  
		// Here I want to reserve 15 spaces for the first piece of data 
		// and 20 spaces for the second
		
		//After the number, I use 1 letter to represent the type of data.
		// s is for Strings.  d is for integers and f is for doubles. 
		// c is for a single character.  
		
		//All the data is currently right justified.  I would need a 
		// - sign to indicate if I want the data to be left justified.
		
		System.out.print("Enter a number: ");
		int val = input.nextInt();
		myString = String.format("%-15s %20d", word1, val);
		System.out.println(myString);
		System.out.println();
		// Above I am formatting the word Lost to be left
		//justified with 10 spaces reserved for it.  
		// immediately following that I have reserved 5 spaces
		// at right justification for the number 4.
		
		System.out.print("Enter a double with more than two decimals: ");
		double decimalVal = input.nextDouble();

		//Here I'm specifying two digits to the right of the decimal.
		myString = String.format("%.2f was rounded to two decimals", decimalVal);
		System.out.println(myString);

	}
}

/*
 * Name: William Chung
 * This client test the Rational class and its methods of add, subtract, multiply, and divide.
 */
public class Client {
	public static void main (String[] args){
		Rational first = new Rational (24,7);
		Rational second = new Rational (4,56);
		
		Rational result = first.add(second);
		System.out.println(result);
		
		 result = first.subtract(second);
		System.out.println(result);
		
		 result = first.multiply(second);
		System.out.println(result);
		
		 result = first.divide(second);
		System.out.println(result);
	}
}

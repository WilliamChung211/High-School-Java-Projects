/*
 * Name: William Chung
 * This class represents any rational number and its methods can add, subtract, multiply, or divide two rationals.
 */

public class Rational {

	private int num;
	private int den;

	public Rational(int first, int sec) {
		num = first;
		den = sec;
	}
	
	//adds two rational numbers together and simplifies
	public Rational add(Rational other) {
		
		Rational sum = new Rational(num * other.den + other.num * den, den* other.den);
		return sum.simplify();
		
	}
	
	//subtracts by adding one rational with the negative version of the other rational
	public Rational subtract(Rational other) {
		
		return this.add(new Rational(other.num*-1, other.den));
		

	}
	 
	//multiplies two rational numbers together and simplifies
	public Rational multiply(Rational other) {
		
		Rational multiple = new Rational(num * other.num, den * other.den);
		return multiple.simplify();
		
	}
	
	//divides two rational by multiplying one rational with the inverse version of the other rational
	public Rational divide(Rational other) {
		
		return this.multiply(new Rational(other.den, other.num));
		
	}

	public String toString() {
		return num + " / " + den;
	}

	//finds the greatest common denominator 
	private int gcd(int first, int sec) {

		int remainder = first % sec;
		if (remainder == 0) {

			return sec;
		}

		return gcd(sec, remainder);

	}

	//simplifies a rational by dividing both numerator and denominator by greatest common factor
	private Rational simplify() {
		
		int gcd = gcd(num, den);
		num /= gcd;
		den /= gcd;
		return this;
		
	}

}

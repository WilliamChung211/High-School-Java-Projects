/*
 * Name: William Chung
 * This client program tests the StringFrequency class, its constructors, its accessor,
 *  and its methods of hasSameFrequency and getWord
 */
public class Client1 {

	public static void main(String [] args){
		StringFrequency1 count = new StringFrequency1	("Mississipi");
		StringFrequency1 count1 = new StringFrequency1("SSSSiPIIIm");
		StringFrequency1 coun = new StringFrequency1(count);
		
		System.out.println(count.hasSameFrequency(coun));
		System.out.println(coun.getWord());
		System.out.println(count1.hasSameFrequency(coun));
		System.out.println(count1.getWord());
		System.out.println(count1.hasSameFrequency(count));
		}
}


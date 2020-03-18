/*
 * Name: William Chung
 * This client program tests the StringFrequency class, its constructors, its accessor,
 *  and its methods of hasSameFrequency and getWord
 */
public class Client {

	public static void main(String [] args){
		StringFrequency count = new StringFrequency("Mississipi");
		StringFrequency count1 = new StringFrequency("SSSSiPIIIm");
		StringFrequency coun = new StringFrequency(count);
		
		System.out.println(count.hasSameFrequency(coun));
		System.out.println(coun.getWord());
		System.out.println(count1.hasSameFrequency(coun));
		System.out.println(count1.getWord());
		System.out.println(count1.hasSameFrequency(count));
		}
}


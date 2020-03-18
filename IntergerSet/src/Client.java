/*
 * Name: William Chung
 * This client tests all the methods of the integerSet class
 */
public class Client {
	public static void main (String[]args){
		IntegerSet set = new IntegerSet(10);
		set.add(1);
		set.add(5);
		set.add(3);
		set.add(5);
		set.remove(5);
		set.add(7);

		IntegerSet set2 = new IntegerSet(set);
		System.out.println(set2.equals(set));
		
		set2 = new IntegerSet(11);
		set2.add(6);
		set2.add(5);
		
		IntegerSet set3 = set.union(set2);
		IntegerSet set4 = set3.intersection(set2);
		System.out.println(set.isEmpty());
		
		System.out.println(set);
		System.out.println(set2);
		System.out.println(set3);
		System.out.println(set4);
		
		System.out.println(set2.equals(set4));
		
		IntegerSet set5 = new IntegerSet(9);
		
		for (int i = 0;i<10; i++){
			set5.add(i);
		}

		System.out.println(set5);
		IntegerSet set6 = set5.intersection(set);
		System.out.println(set6.equals(set));
		
		for (int i = 0;i<10; i++){
			set5.remove(i);
		}
		System.out.println(set5);
		System.out.println(set5.isEmpty());

	}
}

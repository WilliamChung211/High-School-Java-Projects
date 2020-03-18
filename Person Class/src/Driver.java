/*
 * Name: William Chung
 * Description: Tests the class, Person, and its methods of getString and toString. 
 */
public class Driver {


	public  static void main(String[] args) {
	Person me = new Person();
	me.isBirthday("07/06/07");
	System.out.println(me.getAge());
	System.out.println(me.toString());
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * Using a file of words, we test each class to see which 
 * is the best function.
 * 
 */
public class HashTester {

	public static void main(String[] args) {
		String[]list = {"28","13","9","7","5","16","55"};
		HashChaining test = new HashChaining(7, new Testing());
		for(int i =0;i<list.length;i++) {
		
			test.insert(list[i]);
		}
		
		System.out.println(test.insert("24"));
		test.diagnostic();
		System.out.println(test);
	}
}

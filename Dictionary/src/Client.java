import java.util.*;

/*
 * Name: William Chung
 * This  client tests the Dictionary class and its method.
 */
public class Client {
	
	public static void main (String [] args){
		
		Dictionary dic = new Dictionary("wordlist");
		
		for (int i = 0; i<dic.size();i++) {
			System.out.println(dic.get(i).getWord() + " " + dic.get(i).getDef());
		}

		Dictionary dics = dic.getHits("cali");
		System.out.println();
		for (int i = 0; i<dics.size();i++) {
			System.out.println(dics.get(i).getWord() + " " + dics.get(i).getDef());
		}
		
	}

}

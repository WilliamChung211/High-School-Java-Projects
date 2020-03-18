/* Name: William Chung
 * Description: Translates a word input by the user based on a randomly generated cypher key and can encrypt
 * and decrypt words based on the key
 */

public class CypherString implements Cryptable {
	
	private String key;
	private static final String ABC = "abcdefghijklmnopqrstuvwxyz" ;
	
	public CypherString(){
		key = keyMaker(ABC);
	}
	
	//constructs a randomly generated key of a new alphabet
		private String keyMaker (String alpha) {
			
			//randomly adds a new letter to the key
			for (int i = 0; i<26; i++){
				
				int letLoc = (int)(Math.random()*(26-i));
				String letter = alpha.substring(letLoc,letLoc+1);
				alpha+=letter;
				alpha = alpha.substring(0,letLoc)+ alpha.substring(letLoc+1);
				
			}
		
			return alpha; 
		}
		
	public String encrypt(String plain) {
		return crypt(plain, ABC, key);
	}
	
	public String decrypt(String plain) {
		return crypt(plain,  key, ABC);
	}
	
	//encrypts the word based on its letters in connection and location with the key
	private String crypt(String text,String alpha, String ke) {
		
		String toReturn = "";
		
		for (int i = 0; i<text.length();i++){
			
			String letterCheck = text.substring(i,i+1);
			int letLoc = ke.indexOf(letterCheck);
			toReturn+= alpha.substring(letLoc,letLoc+1);
			
		}
		
		return toReturn;
	}
	
}


/*
 * Name: William Chung
 * This class represent and stores one word and its corresponding definition
 */

public class Definition  {
	
	private String word;
	private String defin;
	
	public Definition(String wor, String def){
		word = wor;
		defin = def;
	}
	
	public String getWord(){
		return word;
	}
	
	public String getDef(){
		return defin;
	}
	
	
}

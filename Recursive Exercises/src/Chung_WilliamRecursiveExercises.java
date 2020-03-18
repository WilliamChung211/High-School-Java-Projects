import java.io.*;
/* Name: William Chung
 * Description: Does a bunch of exercises that involves the use of recursion. Recursion is a when a function calls itself.
 */

public class Chung_WilliamRecursiveExercises {
	
	//returns the sum of 1 + 1/2 +...+ 1/n
	public static double HarmonicSum (int posNum){
		
		if (posNum ==1){
			return 1;
		}
		
		return HarmonicSum(posNum-1) + (double) 1/posNum; 
		
	}
	
	//multiplies each interger in an interger array by 3.
	public static void triples (int [] arrays, int length){
		
		if (length == 0) {
			return;
		}
		
		//goes through every interger in the array to multiply by 3 
		length--;
		arrays[length]*=3;
		triples(arrays,length);
		
	}
	
	//finds the amount of times the single character appears within the String parameter. 
	public static int charCount (String sentence, String character){
		
		if (sentence.indexOf(character)==-1){
			return 0;
		}
		
		//looks for an occurrence of the character to the right and repeats from that location.
		else {
			int loc = sentence.indexOf(character);
			return  charCount(sentence.substring(loc+1), character) + 1;
		}
		
	}
	
	//finds all possible combinations of a binary Strings of a length
	public static void BinaryGenerator (int length, String binary){
		
		if (length==binary.length()){
			System.out.println(binary);
			
	}
		
		//adds 0 or 1 to a potential binary Strings and repeats. 
		else{
		BinaryGenerator(length,binary + "0");
		BinaryGenerator(length,binary + "1");
		}
	}
	
	//finds all possible combinations of binary strings of a lengt3
	public static void NoConsecutiveZeros (int length, String binary){
		if (length==binary.length()){
			System.out.println(binary);
			
	}
		else{
			
			//checks if the previous number was not 0 or if there is no previous number to see if it can adds 0.
			if ((binary.length()>0)&&(!binary.substring(binary.length()-1).equals("0"))||binary.length()==0){
		NoConsecutiveZeros(length,binary + "0");
			}
			
		NoConsecutiveZeros(length,binary + "1");
		}
	}
	
	
	//looks for total number of files in a file.  
	public static int FileCounter (File file){
		
		//makes files into an array 
		File[] list = file.listFiles();
		int fileTot = 0;
		
		for (int i = 0; i<list.length; i++){
			//looks for amount of files in that directory and then adds the number of standard files in it to total.
			if (list[i].isDirectory()){
				fileTot+=FileCounter(list[i]);
			}
			
			//adds one to the total if it is a standard file
			else {
				fileTot++;
			}
		
		}
		return fileTot;
	}
	
}

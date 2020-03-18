import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
/*
 * Name: William Chung
 * 
 * This program gets an array of files, spawns a separate thread for every file,
 * and counts all the total number of non-blank characters within the file.
 */
public class ChungWilliam_ThreadedCharacterCount {

	private static int allCounter;
	private static Object lock = new Object();
	
	public ChungWilliam_ThreadedCharacterCount(String[] files) {
		
		Thread[] allThreads = new Thread[files.length];
		
		for (int i = 0; i < files.length; i++) {
			allThreads[i] = new Thread(new BlankCounter(files[i]));
			allThreads[i].run();
		}
		
		//guarantees that the threads count all the non-blank characters and update all counter before printing
		for(Thread nextThread: allThreads) {
			try {
				nextThread.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Total number of non-blank characters: " + allCounter );
	}

	//class that counts non-blankspace for a specific file
	class BlankCounter implements Runnable {
		
		private String fName;
		
		public BlankCounter(String file) {
			fName = file;
		}
		
		public void run() {
			
			Scanner file = null; 
			int notWhiteCounter=0;
			
			try {
				file = new Scanner(new File(fName));
			}catch (FileNotFoundException e) {
				System.out.println("file not found");
				System.exit(-1);
			}
			
			//goes through each character of each line and adds to the counter if it is a non-white space character
			while(file.hasNext()) {
				String line = file.nextLine();
				
				for(int i =0;i<line.length();i++) {
					if(!Character.isWhitespace(line.charAt(i))) {
						notWhiteCounter++;
					}
				}
				
			}
			
			//makes sure only one thread can update the allCounter one at a time. This is the critical section of data
			synchronized(lock) {
				allCounter+=notWhiteCounter;
			}
		}
	}
	
	public static void main(String[]args) {
		String[]files = {"file1","file2", "test3"};
		new ChungWilliam_ThreadedCharacterCount(files);
	}
}

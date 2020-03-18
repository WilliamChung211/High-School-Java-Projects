import java.util.*;
import java.net.*;
import java.io.*;
import javax.net.ssl.*;

 /*
  * Name: William Chung and George Li
  * 
  * This program allows a thread to connect and read HTML code.
  * It shares a directory of all schools and each staff member.
  */
public class Scrape implements Runnable {

	private String site;
	private String schoolName;
	private static Map<String, ArrayList<String>> directory = new HashMap<String, ArrayList<String>>();

	public Scrape(String s, String name) {
		site = s;
		schoolName = name;
	}

	
	//adds all members (and its respective school) from the HTMl into the directory
	public void run() {

		//This is a list of all the staff of the school
		ArrayList<String> names = new ArrayList<String>();
		try {

			URL url = new URL(site);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			if (connection == null) {
				System.out.println("No connection found");
				return;
			}

			Scanner reader = new Scanner(connection.getInputStream());

			String line = "";
			
			//builds the string of the HTML code
			while (reader.hasNextLine()) {
				line += reader.nextLine();
			}
			
			//goes to the employee name in the URl and translates it to an actual name (we know there is at least one employee name)
			do {
				line = line.substring(line.indexOf("employeename") + 14);
				String name = line.substring(0, line.indexOf("<"));
				names.add(replWithSp(name));

			} while (line.indexOf("employeename") != -1);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//makes sure only one thread can access the directory at a time when it puts something in it
		synchronized(directory) {
			directory.put(schoolName,names);
		}
	}

	//takes a string and replaces all "&nbsp;" with spaces assuming there is one "&nbsp;"
	private String replWithSp(String bigStr) {
		String toBuildUp = "";
		int spaceInd = bigStr.indexOf("&nbsp;");
		do  {
			
			toBuildUp += bigStr.substring(0, spaceInd) + " ";
			
			bigStr = bigStr.substring(spaceInd + 6);
			spaceInd = bigStr.indexOf("&nbsp;");
		}while((spaceInd!= -1));

		toBuildUp += bigStr;
		return toBuildUp;
	}


	public static Map<String, ArrayList<String>> getDirectory() {
		return directory;
	}

}

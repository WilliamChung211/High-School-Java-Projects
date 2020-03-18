import java.io.*;
import java.util.Scanner;
/*
 * Name: William Chung
 * This program tracks all movies across one day the theater
 */
public class MovieDatabase {

	private Movie[] list;

	//fills and sorts the movie list
	public MovieDatabase(String fileName) {
		fillsList(fileName);
		System.out.println(list.length + " Movies Listed.");
		selectionSort();
	}

	//loads file and fills the movie list based on that file
	private void fillsList(String fileName) {
		
		Scanner fileInCount = null;
		Scanner fileIn = null;

		try {
			fileInCount = new Scanner(new File(fileName));
			fileIn = new Scanner(new File(fileName));

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}

		int lines = 0;
		
		// Count up how many lines in file
		while (fileInCount.hasNextLine()) {
			lines++;
			fileInCount.nextLine();
		}
		
		fileInCount.close();
		list = new Movie[lines / 2];
		
		//fills the list of movies for all except the last movie
		for (int i = 0; i < lines / 2 - 1; i++) {

			//puts the first line of each pair of lines as the name of movie
			String name = fileIn.nextLine();
			
			//sets the second line of each of pair as the time of movie
			int hour = fileIn.nextInt();
			int min = fileIn.nextInt();
			Time movTime = new Time(hour, min);
			
			//fills the list with a movie based on that name and time
			list[i] = new Movie(name, movTime);
			fileIn.nextLine();
			
		}
		
		//fills the list with the last movie
		String name = fileIn.nextLine();
		int hour = fileIn.nextInt();
		int min = fileIn.nextInt();
		Time movTime = new Time(hour, min);
		list[lines / 2 - 1] = new Movie(name, movTime);
		fileIn.close();

	}

	//sorts the list of movies from earliest to latest
	private void selectionSort() {

		for (int n = list.length; n >= 2; n--) {

			int latIndex = 0; 
			
			// examines an area of the array that decreases in size for each interation
			for (int i = 1; i < n; i++)

				//finds if a movie is latest in the area
				if (list[i].getTime().compareTo(list[latIndex].getTime()) > 0) {
					latIndex = i;
				}
			
			// swap the latest value with where it belongs in the array
			Movie tempSwap = list[latIndex];
			list[latIndex] = list[n - 1];
			list[n - 1] = tempSwap;

		}
		
	}

	//attempts to sell the costumer a ticket for his/her earliest show and then informs
	public void sellTicket(Customer cust) {
		
		boolean sold = false;
		for (int i = 0; i < list.length; i++) {
			
			//finds the earliest available show the costumer can attend that he/she wants to see
			if (cust.getMov().equals(list[i].getName()) && (list[i]).sellTicket(cust)) {
	
				sold = true;
				i = list.length;
				
			}
		}

		if (sold) {
			System.out.println("Ticket Sold!");
		} 
		else {
			System.out.println("That movie is not available.");
		}
		
	}

	//Returns a 4 column list of the information in each movie in the list 
	public String toString() {
		
		String colums = String.format("%-30s %-20s %-20s %-20s ", "Movie", "Showtime", "Max Seats", "Seats filled" +"\n");
		
		for (int i = 0; i < list.length; i++) {
			colums +="\n" + String.format("%-30s %-20s %-20d %-20d ", list[i].getName(), list[i].getTime(), list[i].getMax(),
					list[i].getTick()) ;
		}
		
		return colums;
	}

	//prints a list of the movie information and the customers who purschased a ticket
	public void manifest() {

		for (int i = 0; i < list.length; i++) {
			list[i].printMovie();
			System.out.println();
		}
		
	}
	
}

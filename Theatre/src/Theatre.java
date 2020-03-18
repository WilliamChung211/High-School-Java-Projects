import java.util.Scanner;
/*
 * Name: William Chung
 * This driver creates a MovieDatabase and prompts the user to sell
 *  tickets or print information about the state of the theatre.
 */
public class Theatre {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		MovieDatabase hi = new MovieDatabase("movies");
		String choice = "";
		
		//prompts the user for an option till he wants to quit
		do {
			System.out.println("Menu Options.");
			System.out.println();
			System.out.println("B) Buy a ticket");
			System.out.println("L) List all movies");
			System.out.println("S) Show all purchased tickets");
			System.out.println("Q) Quit");
			System.out.println();

			System.out.print("Enter choice: ");
			choice = keyboard.nextLine();
			System.out.println();
			
			//if the user wants to list all movies, it outputs a list of the movies and information
			if (choice.equals("L")) {
				System.out.println(hi);
			}

			//if the user wants to buy a ticket, it asks for information and buys a ticket if possible
			else if (choice.equals("B")) {
				System.out.print("Enter Customer Name: ");
				String name = keyboard.nextLine();
				System.out.print("Enter Movie: ");
				String movie = keyboard.nextLine();
				System.out.print("Enter Arrival Time (hour space minute): ");
				int hour = keyboard.nextInt();
				int min = keyboard.nextInt();
				keyboard.nextLine();
				Customer cust = new Customer(name, movie, new Time(hour, min));
				hi.sellTicket(cust);
				System.out.println();

			}

			//shows all purchased tickets and information if asked to do so
			else if (choice.equals("S")) {
				hi.manifest();
			}
			
			System.out.println();

		} while (!(choice.equals("Q")));
		
	}
	
}

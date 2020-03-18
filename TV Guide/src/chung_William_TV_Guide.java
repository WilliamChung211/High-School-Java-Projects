import java.util.Scanner;

/*Name: William Chung
 * Description: Tells user what to watch based on criteria. There can only be 6 possible inputs.
 */

public class chung_William_TV_Guide {

		public static void main (String [] args) {
			//Prompts the User to ask 3 questions.
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Is it a weekday? Yes or No");
			String weekday = keyboard.nextLine(); 
			System.out.println("What genre do you want to watch? Reality, Drama, or Comedy?");
			String genre = keyboard.nextLine();
			System.out.println("Can you stay up past 9pm? Yes or No");
			String stayUp = keyboard.nextLine();
			
			//Tells user what to watch based on the three answers they made
			if (weekday.equals("Yes")){
				if (genre.equals("Drama")){
					if (stayUp.equals("Yes")){
					System.out.println("The Game of Thrones");
					}
					else {
						System.out.println("Power");
					}
				}
				
				else {
					if (stayUp.equals("Yes")){
					System.out.println("Will and Grace");
					}
					else {
						System.out.println("Parks and Rec");
					}
				}
			}
			
			//if it is not weekday
			else {
				if (stayUp.equals("Yes")){
					System.out.println("Survivor");
					}
					else {
						System.out.println("The Bachelor");
					}
			}
		}
}

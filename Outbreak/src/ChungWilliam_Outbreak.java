import java.util.*;
/*
 * Name: William Chung
 * Description: Displays how many sick and healthy students there are going to be in a classroom based on a probability and also displays the how many sick neigbors the healthy students have
 */

public class ChungWilliam_Outbreak{
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//prompts user for row and column number
		System.out.println("How many rows are there in the classroom?");
		int rowNum = keyboard.nextInt();
		System.out.println("How many collumns are there in the classroom?");
		int colNum = keyboard.nextInt();
		
		//prompts user for probability and makes it percent with 2 decimals places
		System.out.println("What is the probability that a student will be sick?");
		double prob = (int) (keyboard.nextDouble() * 10000);
		prob /= 100;
		System.out.println("Probability  " + prob);
		
		//shows an example classroom with sick and healthy students
		System.out.println("! means a student is sick");
		units(colNum);
		System.out.println();
		Boolean [][] desks = new Boolean[rowNum][colNum];
		prob(prob, desks);
		System.out.println();
		
		//shows an example classroom with desks showing how many sick students surround an unsick student.
		System.out.println("Surroundings");
		units(colNum);
		System.out.println();
		surroundings(desks);

	}

	//checks to see and displays which students in the classroom are going to be sick or healthy based on the probability
	public static void prob(double prob, Boolean [][] desks) {
		Random rGen = new Random();

		for (int row = 0; row < desks.length; row++) {
			//makes left border and row numbers
			System.out.print(row+1 + "|   ");
			
			//calculates if student is sick or not
			for (int col = 0; col < desks[0].length; col++) {
				double val = 100 * rGen.nextDouble();
				
				if (val <= prob) {
					desks[row][col] = true;
					System.out.print("   !");
				} 
				else {
					desks[row][col] = false;
					System.out.print("   0");
				}
				
			}
			
			System.out.println("");
		}
		
	}

	//makes a top border and units for the columns 
	public static void units(int rowNum) {
		System.out.print("        ");
		System.out.println();
		System.out.print("        ");
		for (int row = 1; row <= rowNum; row++) {
			System.out.print(row + "   ");
		}
		System.out.println();
		System.out.print("       ");
		for (int row = 1; row <= rowNum; row++) {
			System.out.print("----");
		}

	}

	//checks to see how many neighbors of each healthy person is sick and who has the most. 
	public static void surroundings(Boolean[][] desks) {
		//these are arrays to represent each vertical and horizontal displacement combo from -1 to 1 for surroundings
		int[] vertical = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] horizontal = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int maxCounter = -1;
		int maxRow = 0;
		int maxCol = 0;
		

		for (int row = 0; row < desks.length; row++) {
			System.out.print(row+1 + "|      ");
			
			for (int col = 0; col < desks[0].length; col++) {

				if (desks[row][col]) {
					System.out.print("!   ");
				} 
				else {

					int sickCounter = 0;
					for (int i = 0; i < 8; i++) {
						int surrRow = row + vertical[i];
						int surrCol = col + horizontal[i];
						
						//checks to see if there are neighbors in a displacement combination
						if (checkBounds(surrRow, desks.length) && checkBounds(surrCol, desks[0].length)) {
							
							if (desks[surrRow][surrCol]) {
								sickCounter++;
							}
						}

					}
					
					//checks which healthy person has the most sick neighbors and his and her row and col coordinates for location.
					if (sickCounter > maxCounter) {
						maxRow = row+1;
						maxCol = col+1;
						maxCounter = sickCounter;
					}
					System.out.print(sickCounter + "   ");
				}

			}
			System.out.println();

		}
		System.out.println();
		System.out.println("The student who is surrounded the most sits at desk (" + maxRow + "," + maxCol + ")");
	}
	

	//checks to see if a number is in bounds of something such as an array area
	public static Boolean checkBounds(int num, int bound) {
		return num >= 0 && num < bound;

	}
}


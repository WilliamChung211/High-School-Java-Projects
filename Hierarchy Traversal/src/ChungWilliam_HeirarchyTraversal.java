import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * Given a hierarchy that starts with one node and can contain 0 or more,
 * this program prints out all children one level at a time
 */
public class ChungWilliam_HeirarchyTraversal {

	String[] names;
	LinkedList<Integer>[] kids;

	// takes in the name of the file and creates the name array and linked list array
	public ChungWilliam_HeirarchyTraversal(String fName) {

		Scanner fileIn = null;

		try {
			fileIn = new Scanner(new File(fName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		names = new String[fileIn.nextInt()];
		fileIn.nextLine();

		// stores all names in the array
		for (int i = 0; i < names.length; i++) {
			names[i] = fileIn.nextLine();
		}

		kids = new LinkedList[names.length];

		// stores all children in a linked list array
		for (int i = 0; i < kids.length; i++) {

			kids[i] = new LinkedList<Integer>();
			String[] nums = fileIn.nextLine().split(" ");
			int checkFirst = Integer.parseInt(nums[0]);

			// if the line's only number is -1, it means the name in the position has no children
			if (checkFirst != -1) {
				kids[i].add(checkFirst);
			}

			// if not, it adds all numbers in the line to the children list of that position
			for (int ind = 1; ind < nums.length; ind++) {
				kids[i].add(Integer.parseInt(nums[ind]));
			}

		}

	}

	// prints out all names from the top level to the bottom assuming there is at least one top node
	public void traverse() {

		//the thing that is always printed first is the top level node
		Queue<Integer> printNames = new LinkedList<Integer>();
		printNames.add(0);

		do {
			
			//whatever index is next will be the position in names that is next to be printed out
			int printInd = printNames.remove();
			System.out.println(names[printInd]);
			
			//it will then add whatever children that printed name had to the end of the printing queue
			for (Integer childInd : kids[printInd]) {
				printNames.add(childInd);
			}
			
		} while (!printNames.isEmpty());
		
	}

	public static void main(String[] args) {
		new ChungWilliam_HeirarchyTraversal("data").traverse();
	}

}

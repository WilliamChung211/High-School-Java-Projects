import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * This program is for admission officers who are too lazy to read
 * an application for a minute and instead accept applications by laying
 * a pile in a circle and rejecting every other application till
 * there is one left. It runs every combination of acceptance scenarios
 * allowing each person to start first and outputs to the screen any time
 * the starting applicant is also accepted.
 *  
 * Answer to Thought Provoking Topic:
 * 
 *The pattern is that all powers of 2 had all their starting spots be accepted
 *and everyone other number had no one. This is because the taking away pattern 
 *is every  other (every 2nd), so for 2^1 applicants, the starting point always 
 *is accepted. This is the same for other powers of 2, because each elimination 
 *round reduces 2^(n) to 2^(n-1) applicants where the starting applicant is always
 *safe till it reaches 2^1 where the starting applicant always wins. This doesn't 
 *work for any non power of 2 since it cannot reach 2^1 after a number of rounds.
 */

public class ChungWilliam_Admissions {

	// shows the starting application who is also the accepted applicant for every acceptance scenario
	public void showAccepted(int maxNums) {

		// runs every combination of acceptance scenarios from 2 to the max number of applicants
		for (int totInd = 2; totInd <= maxNums; totInd++) {

			// allows each person to start first
			System.out.print("Total applications : " + totInd + ". Accepted starting spot: ");
			for (int startInd = 1; startInd <= totInd; startInd++) {

				// outputs any time the starting application is accepted
				if (new Application(totInd, startInd).accept() == startInd) {
					System.out.print(startInd + ", ");
				}

			}

			System.out.println();
		}

	}

	//this class handles college's amazing process given the total number and starting position
	public class Application {
		private Queue<Integer> applicants;
		private int total;

		public Application(int tot, int start) {

			total = tot;
			applicants = new LinkedList<Integer>();

			//adds all the applicants starting from the starting position
			for (int i = start; i < total + start; i++) {
				applicants.add((i - 1) % total + 1);
			}

		}

		//simulates the process and returns the accepted application number
		public int accept() {

			int appNum = total;
			Queue<Integer> simQue = new LinkedList<Integer>(applicants);

			while (appNum > 1) {

				int rndAppNum = appNum;

				//removes every other applicant from each round till they done walking in circle
				for (int i = 1; i < rndAppNum; i += 2) {
					simQue.add(simQue.remove());
					simQue.remove();
					appNum--;

				}

			}

			return simQue.peek();
		}
		
	}

	
	public static void main(String[] args) {
		System.out.println("How applicants are there?");
		Scanner keyboard = new Scanner(System.in);
		ChungWilliam_Admissions admission = new ChungWilliam_Admissions();
		admission.showAccepted(keyboard.nextInt());
	}

}

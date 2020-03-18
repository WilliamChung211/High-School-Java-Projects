import java.util.LinkedList;
import java.util.Queue;

public class Application {
	private Queue<Integer> applicants;
	private int total;
	private int startLoc;
public Application(int tot, int strt) {

		if (total < 1 && strt < 1) {
			throw new IllegalArgumentException("Bad");
		}

		total = tot;
		startLoc = strt;
		applicants = new LinkedList();
		for (int i = 1; i <= tot; i++) {
			applicants.add(i);
		}

	}

	public int accept() {

		int appNum = total;
		Queue<Integer> simQue = new LinkedList<Integer>(applicants);

		while (appNum > 1) {
			for (int i = startLoc; i < startLoc+appNum; i++) {
				simQue.add(simQue.remove());
				simQue.remove();
				appNum--;
			}
		}
		
		return simQue.peek();
	}
}

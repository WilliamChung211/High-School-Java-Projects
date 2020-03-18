import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {

		ArrayList<Integer> spots = new ArrayList<Integer>();
		int size = 4;
		int[][] allPanels = new int[size][size];
		for (int i = 1; i <= size * size; i++) {

			// while also constructing and adding the pic panels
			int row = (i - 1) / size;
			int col = (i - 1) % size;

			spots.add(i);
		}

		// randomly gives 15 spots in the pic panel matrix a unique number
		for (int i = 1; i <= size * size - 1; i++) {

			// randomy picks one of the 16 spots that have not been picked before (removes
			// from the list after being picked)
			int rand = spots.remove((int) (Math.random() * spots.size()));

			// converts that spot index to matrix index
			int randRow = (rand - 1) / size;
			int randCol = (rand - 1) % size;

			// assigns that spot a unique number
			allPanels[randRow][randCol] = i;
		}

		for(int row =0;row<allPanels.length;row++) {
			for(int col=0;col<allPanels.length;col++) {
				System.out.print(allPanels[row][col] + " ");
			}
			System.out.println();
		}
		int[][]matrix = {{0,1,3},{4,2,5},{7,8,6}};;
		ChungWilliam_TotoroSolver tot = new ChungWilliam_TotoroSolver(allPanels);
		tot.search();
	}
}

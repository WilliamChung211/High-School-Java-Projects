/*
 * Name: William Chung
 * Description: Sorts an array of Strings by checking two adjacent elements hat are out of order and swaps them and 
 * then checks this for every adjacent pair in the array. Then, it keeps doing this until everything is sorted alphabetically.
 * 
 */

public class Chung_WilliamAnotherSort {

	public static void main(String[] args) {
		String[] array = { "Jake", "Charles", "Terry", "Amy", "Gina" };
		sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
		
	}

	public static void sort(String[] array) {

		//looks through n elements. Then n-1 elements and so on till all numbers are examined.
		for (int n = array.length; n > 2; n--) {
			
			int sortCounter = 0;
			
			//looks through each adjacent pair one at a time and sorts them alphabetically 
			for (int i = 1; i < n; i++) {

				//if the pair is not sorted, it switches the spots of the pair
				if (array[i].compareTo(array[i - 1]) < 0) {
					String tempSwap = array[i];
					array[i] = array[i - 1];
					array[i - 1] = tempSwap;

					//counts the amount of times that it needs to sort a pair
					sortCounter++;
				}

			} 
			
			//if the sorting algorithm does not need to sort any pairs, it ends
			if (sortCounter == 0) {
				return;
			}
			
		}

	}

}

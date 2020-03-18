/*
 * Name: William Chung
 * Description: Sorts an array that does selection sort, but during the first iteration,  it finds the largest value and swaps it to the end 
 * during the second iteration, finds the smallest value and swap it to the end. Then, it alternates these two steps.
 * 
 *  Answer to #2: The final result of the array is the same whether or not you reverse 
 *  the order of the recursive calls.  This is because it  just sorts the right side 
 *  and then the left side instead of the left side and then the right side. Since,
 *  only the order of doing things  changes when you switch the recursive calls, 
 *  the overall array does not change. 
 */

public class Chung_WilliamComboSelectionSort {
	public static void main(String[] args) {
		
		int[] array = { 4,1,5,3,2};
		
		ComboSelectionSort(array);
		
		//prints sorted array
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+ " ");
		}
		
	}

	public static void ComboSelectionSort(int[] array) {

		for (int n = 0; n < array.length-1; n++) {

			// check if it is first step or second step
			if (n % 2 == 0) {
				
				int maxIndex = n / 2;
				
				//finds the largest value of the numbers out of the unsorted numbers so far
				for (int i = n / 2 + 1; i < array.length - n / 2; i++) {

					if (array[i] > array[maxIndex])
						maxIndex = i;
				}

				//switches the largest number to the largest index unchecked 
				swapArraySpots(array, maxIndex, array.length - n / 2 - 1);
			
			}

			else {
				
				int minIndex = array.length - n / 2 - 2;
				
				//finds the smallest value of the numbers so far unsorted numbers so far 
				for (int i = array.length - n / 2 - 3; i >= n / 2; i--) {

					if (array[i] < array[minIndex]) {
						minIndex = i;
					}
				}
				
				//switches the largest number to the smallest index unchecked
				swapArraySpots(array, minIndex, n / 2);
				
			}
		}
	}

	//switches a spot on an array with another spot on an array.
	public static void swapArraySpots(int[] array, int index, int indexSwapped) {
		int tempSwap = array[index];
		array[index] = array[indexSwapped];
		array[indexSwapped] = tempSwap;
	}

}

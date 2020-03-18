import java.util.ArrayList;

/*
 * Name: William Chung
 * This client tests the insertion sort function that takes in an array of Comparable objects and tests the
 * compare to method from the TravelGuide class.
 * 
 * Answer: The advantage of writing selection sorts of Comparables instead of an array of ints or Strings is that
 * with the Comparables, you can sort any array of objects that implemented the Comparable interface because
 * of disguising. This means you can sort a wide range of types of arrays instead of just ints or String arrays.
 * By writing a selection sort of Comparables, you can sort not only int arrays or String arrays, but any array
 * that have objects that implemented the Comparable interface like TravelGuide.
 */
public class Client {

	// sorts an array of Comparable objects using insertion sort in ascending order
	public static void sort(Comparable[] array) {

		// finds the right spot for one element at a time
		Comparable nextElement;

		// sorts the first 2, then 3, then 4...
		for (int n = 1; n < array.length; n++) {

			nextElement = array[n];
			int i = n;

			// shifts the elements to the right till it finds the correct spot for nextElement in ascending order
			while ((i > 0) && (nextElement.compareTo(array[i - 1]) < 0)) {

				array[i] = array[i - 1];
				i--;

			}

			array[i] = nextElement;

		}
	}

	public static void main(String[] args) {

		TravelGuide[] list = new TravelGuide[9];
		list[0] = new TravelGuide("Book", 2, 3, "ab");
		list[1] = (new TravelGuide("Book", 2, 3, "Yb"));
		list[2] = (new TravelGuide("Book", 2, 3, "Zb"));
		list[3] = (new TravelGuide("Book", 2, 3, "Bb"));
		list[4] = (new TravelGuide("Book", 2, 3, "Pb"));
		list[5] = (new TravelGuide("Book", 2, 3, "Fb"));
		list[6] = (new TravelGuide("Book", 2, 3, "Eb"));
		list[7] = (new TravelGuide("Book", 2, 3, "Db"));
		list[8] = new TravelGuide("Book", 2, 3, "Cb");
		sort(list);
		
		for (TravelGuide guide : list) {
			System.out.println(guide);
		}

		Integer[] num = new Integer[8];
		num[0] = 3;
		num[1] = 4;
		num[2] = 8;
		num[3] = 10;
		num[4] = 23;
		num[5] = 4;
		num[6] = 1;
		num[7] = 2;
		sort(num);

		for (Integer n : num) {
			System.out.println(n);
		}

		String[] words = new String[8];
		words[0] = "Steve";
		words[1] = "Deave";
		words[2] = "Neave";
		words[3] = "Leave";
		words[4] = "Weave";
		words[5] = "Qeave";
		words[6] = "Ceave";
		words[7] = "Beave";
		sort(words);

		for (String word : words) {
			System.out.println(word);
		}

	}

}

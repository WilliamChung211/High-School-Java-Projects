
public class Client {
	public static void main(String[] args) {
		WillTim_TernaryTree test = new WillTim_TernaryTree();

		String[] toInsert = { "wood", "would", "a", "woodchuck", "chuck"};
		int[] values = { 4, 8, 15, 16,23 };
		for (int i = 0; i < toInsert.length; i++) {
			test.insert(toInsert[i], values[i]);
		}

		test.prefixMatch("wo");
		
	}
}

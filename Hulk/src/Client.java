
public class Client {

	public static void main(String[] args) {
		System.out.println(test());
	}

	public static boolean test() {
		int[][] matrix = new int[12][12];
		for (int i = 0; i < 99; i++) {
			for (int row = 0; row < matrix.length; row++) {
				for (int col = 0; col < matrix[0].length; col++) {
					matrix[row][col] = (int) (Math.random() * 10) - 1;
					//System.out.print(matrix[row][col] + " ");
				}
			//	System.out.println();
			}

			int test1 = new Will_Cor_HulkSmash(matrix).findPath();
			int test2 = new Chung_William_weHaveHulk(matrix).findPath3(0, 0);
			if (test1 != test2) {
			
					for (int row = 0; row < matrix.length; row++) {
						for (int col = 0; col < matrix[0].length; col++) {
							System.out.print(matrix[row][col] + " ");
						}
						System.out.println();
					}
				System.out.println(test1);
				System.out.println(test2);
				return false;
			}
		}
		return true;
	}
}
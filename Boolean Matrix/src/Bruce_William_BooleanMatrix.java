/* Name: William Chung and Bruce Pahlavan
 * Description: Makes a boolean matrix with 3 quadrants that are identical to each other 
 * and the final/lower right quadrant always opposite to the other three.
 */


public class Bruce_William_BooleanMatrix {
	
	
	public static boolean [][] BooleanMatrix (int dim){

		boolean [] [] finMatrix = new boolean [dim][dim];

		//if the dimensions given is 1, the final matrix must only have the value of true.	
		if (dim==1){

			finMatrix[0][0]= true;

			return finMatrix;
			
		}

		//recursively calls a matrix the size of a quadrant of the final matrix.
		boolean [][] quadMatrix = BooleanMatrix(dim/2);

		//goes through every value of the quadrant matrix previous called
		for (int row = 0; row < quadMatrix.length; row++) {
			
			for (int col = 0; col < quadMatrix[0].length; col++) {

				//fills three quadrants of the final matrix with the same values of the quadrant matrix previously called. 
				finMatrix[row][col]= quadMatrix[row][col];

				finMatrix[row][col+quadMatrix.length] = quadMatrix[row][col];

				finMatrix[row+quadMatrix.length][col]= quadMatrix[row][col];

				//fills the bottom right quadrant of the final matrix with the opposite values of the quadrant previously called.
				finMatrix[row+quadMatrix.length][col+quadMatrix.length]= !quadMatrix[row][col];

			}
			
		}

		return finMatrix;
		
	}
	
	
}

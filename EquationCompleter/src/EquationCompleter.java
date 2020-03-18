import java.util.Scanner;
import java.util.Stack;

/*
 * Name: William Chung
 * 
 * This project will prompt the user to enter an equation in infix expression
 * and will output the result of the equation using a stack. We assume there are
 * at least 3 characters (2 numbers and an operator) and that aall characters
 * are seperated by a space.
 */
public class EquationCompleter {

	public static void main(String []args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		//prompt the user to enter an equation
		System.out.print("Gimme an equation: ");
		 
		//Separates the characters and makes the lists of numbers and operators
		String[] chars = keyboard.nextLine().split(" ");
		Stack <Integer> nums = new Stack<Integer>();
		Stack <String> ops = new Stack<String>();
		
		nums.push(Integer.parseInt(chars[0]));
		
		//goes through each operator
		for(int i =1;i<chars.length;i+=2) {
			
			//if the current operator has lower priority than the previous one, it performs all previous operations
			if(!ops.isEmpty()&&hasPriority(ops.peek(),chars[i])) {
				performPrevOps(nums,ops);
			}
			
			//then adds to the stacks
			ops.push(chars[i]);
			nums.push(Integer.parseInt(chars[i+1]));
			
		}
		
		//when it goes through all operators, it finishes
		performPrevOps(nums, ops);
		
		System.out.println("Answer: " + nums.peek());
	}
	
	private static void performPrevOps(Stack<Integer> nums, Stack<String>ops) {
		
		do  {
			int right = nums.pop();
			nums.push(performOp(nums.pop(),right,ops.pop()));
		} while(!ops.isEmpty());
	}
	
	private static boolean hasPriority(String left, String right) {
		
		if (isArithmetic(left)) {
			return isArithmetic(right);
		
		}
		else {
			return !right.equals("^");
		}
		
	}
	
	private static boolean isArithmetic(String op) {
		return op.equals("+")||op.equals("-");
	}
	
	private static int performOp (int left, int right, String op) {
		
		if(op.equals("^")) {
			return (int) Math.pow(left,right);
		}
		else if (op.equals("*")) {
			return left*right;
		}
		else if(op.equals("/")) {
			return left/right;
		}
		else if(op.equals("-")) {
			right*=-1;
		}
		
		return left+right;
		
	}
	
	
}

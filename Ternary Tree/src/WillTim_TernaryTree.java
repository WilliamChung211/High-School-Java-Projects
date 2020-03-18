/*
 * Name: William Chung and Timothy Yang
 * 
 * This class represents a Ternary Tree which stores Strings
 * that link to some sort of values. Each node has 0-3 children.
 * We can traverse in order, get values, insert, and find strings
 * with prefixes.
 */

public class WillTim_TernaryTree {

	private TreeNode root;

	public void insert(String key, Integer val) {
		
		//every word must have a value
		if(val==null) {
			throw new IllegalArgumentException("PUT IN A REAL VALUE");
		}
		if(key!=null) {
			root = insertNode(root, key, val);
		}
		
	}

	//assigns value to node if adding last letter
	private void equalsCase(TreeNode r, String key, Integer val) {
		
		if (key.length() <= 1) {
			r.value = val;
		} 
		else {
			r.equalsChild = insertNode(r.equalsChild, key.substring(1), val);
		}
		
	}

	//RECURSIVELY adds the key/val pair, building nodes as necessary according to the rules described above 
	private TreeNode insertNode(TreeNode r, String key, Integer val) {
		
		//base case if all letter nodes are added
		if (key.equals("")) {
			return null;
		}

		String letter = key.substring(0, 1);

		//reached the end of the tree, so it builds a new node
		if (r == null) {
			r = new TreeNode(letter, null, null);
			equalsCase(r, key, val);
		}

		else {
			int compareValue = r.letter.compareTo(letter);

			if (compareValue > 0) {
				r.leftChild = insertNode(r.leftChild, key, val);
			}

			else if (compareValue < 0) {
				r.rightChild = insertNode(r.rightChild, key, val);
			}

			else {
				equalsCase(r, key, val);
			}
		}

		return r;
	}
	
	
	public Integer get(String key) {
		if(key != null && key.length() > 0) {
			return get(root, key);
		}
		
		return null;
	}
	
	//RECURSIVELY determines and returns the value associated with the key parameter and returns null if key not found.
	private Integer get(TreeNode current, String key) {
		
		if(current == null) {
			return null;
		}
		
		String currentKeyLetter = key.substring(0, 1);
		
		int compareValue = current.letter.compareTo(currentKeyLetter);

		if(compareValue > 0) {
			return get(current.leftChild, key);
		}
		
		else if(compareValue < 0) {
			return get(current.rightChild, key);
		}
		
		//if we're at the last letter, we returns the value stored in the last letter
		else if(key.length() <= 1){
			return current.value;
		}
		
		else {
			return get(current.equalsChild, key.substring(1));
		}
	}

	public void inOrder() {
		inOrder(root, "");
	}

	//RECURSIVELY prints out all of the keys stored in the ternary tree in order
	private void inOrder(TreeNode current, String toPrint) {
		if (current != null) {
			String updatedToPrint = toPrint + current.letter;
			
			inOrder(current.leftChild, toPrint);
			
			if (current.value != null) {
				System.out.println(updatedToPrint);
			}

			inOrder(current.equalsChild, updatedToPrint);
			inOrder(current.rightChild, toPrint);
		}
	}

	//NON-RECURSIVELY searches and then prints out all matching keys that contain the prefix. 
	public void prefixMatch(String pre) {
		
		//no prefix does not print
		if(pre != null && pre.length() > 0) {
			TreeNode current = root;
			int index = 0;
			int nextIndex = 1;

			//keeps going till either we ran out of prefix letters or we didn't find it at all
			while(current != null) {
				String currentPreLetter = pre.substring(index, nextIndex);
				
				int compareValue = current.letter.compareTo(currentPreLetter);
				
				if(compareValue > 0) {
					current = current.leftChild;
				}
				
				else if(compareValue < 0) {
					current = current.rightChild;
				}
				
				//we can now get all the words with the prefix here by in order traversing
				else {
					
					if(pre.length() <= nextIndex) {
						if(current.value != null) {
							System.out.println(pre);
						}
						
						inOrder(current.equalsChild, pre);
						return;
					}
					
					index = nextIndex;
					nextIndex++;
					current = current.equalsChild;
				}
			}
			
		}

	}

	//this class represents the treeNode with a letter, value, and three children
	public class TreeNode {

		private String letter;
		private Integer value;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode equalsChild;

		public TreeNode(String let, Integer val, TreeNode equals) {
			letter = let;
			value = val;
			equalsChild = equals;
		}
		
	}
	
}

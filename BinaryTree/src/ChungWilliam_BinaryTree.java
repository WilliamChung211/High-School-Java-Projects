
/*
 * Name: William Chung
 * 
 * This is a BinaryTree data structure that works with a Comparable type
 * with a root instance variable. It also has an inner class of type tree node
 * that can have children of at max two other treeNodes. 
 */
public class ChungWilliam_BinaryTree<E extends Comparable<E>> {

	TreeNode root;

	//root is null by default
	public ChungWilliam_BinaryTree() {
	}

	public ChungWilliam_BinaryTree(E value) {
		root = new TreeNode(value, null, null);
	}

	
	//makes a new tree by connecting left and right to the root node containing value
	public ChungWilliam_BinaryTree(E value, ChungWilliam_BinaryTree left, ChungWilliam_BinaryTree right) {
		
		this(value);
		
		if (left != null) {
			root.leftNode = left.root;
		}
		if (right != null) {
			root.rightNode = right.root;
		}

	}

	public ChungWilliam_BinaryTree(ChungWilliam_BinaryTree other) {
		root = nodeCopy(other.root);
	}
	
	//makes a copy of a treeNode with the root's data and connects it to copies of all its children
	private TreeNode nodeCopy(TreeNode otherR) {

		if (otherR == null) {
			return null;
		}

		return new TreeNode(otherR.data, nodeCopy(otherR.leftNode),nodeCopy(otherR.rightNode));
	}

	//these three public and three private methods are three ways traverse the list
	public void inOrder() {
		traverseIn(root);
	}

	private void traverseIn(TreeNode r) {
		if (r != null) {
			traverseIn(r.leftNode);
			System.out.println(r.data);
			traverseIn(r.rightNode);
		}
	}

	public void preOrder() {
		traversePre(root);
	}

	private void traversePre(TreeNode r) {
		if (r != null) {
			System.out.println(r.data);
			traversePre(r.leftNode);
			traversePre(r.rightNode);
		}
	}

	public void postOrder() {
		traversePost(root);
	}

	private void traversePost(TreeNode r) {
		if (r != null) {
			traversePost(r.leftNode);
			traversePost(r.rightNode);
			System.out.println(r.data);
		}
	}
	
	
	//returns true if the item is found in the tree
	public boolean find(E item) {
		return findNode(root, item);
	}

	
	private boolean findNode(TreeNode r, E item) {
		
		//if there is no node or children, the item cannot be in it
		if (r == null) {
			return false;
		} 
		
		//we found it in the node
		else if (r.data.equals(item)) {
			return true;
		} 

		//the item can still be in the tree if it is in the node's children
		else {
			return findNode(r.leftNode, item) || findNode(r.rightNode, item);
		}
	}

	public int height() {
		return subTreeHeight(root);
	}

	private int subTreeHeight(TreeNode r) {
		
		//if there is no root, there is no height
		if (r == null) {
			return 0;
		} 
		
		//the actual height is one more than the height of the tree if its root was the actual root's child
		else {
			return 1 + Math.max(subTreeHeight(r.leftNode), subTreeHeight(r.rightNode));
		}
	}

	
	//returns the largest value in the tree
	public E max() {
		return maxInSubTree(root);
	}

	private E maxInSubTree(TreeNode r) {
		
		//there is no largest value if there is no root
		if (r == null) {
			return null;
		} 
		
		//the largest value of the tree is between the root and whatever was the largest value of the nodes in the lower levels
		else {
			E maxOfChildren = findMaxOfTwo(maxInSubTree(r.leftNode), maxInSubTree(r.rightNode));
			return findMaxOfTwo(r.data, maxOfChildren);
		}
	}

	//compares two values and gets the largest value 
	private E findMaxOfTwo(E leftValue, E rightValue) {

		//if one value is null, no comparison is needed and we just need to return the other value. 
		if (leftValue == null) {
			return rightValue;
		} 
		else if (rightValue == null) {
			return leftValue;
		} 
		else {
			if (leftValue.compareTo(rightValue) < 0) {
				return rightValue;
			} else {
				return leftValue;
			}
		}
	}

	//returns the number of nodes in the true
	public int count() {
		return countInSubTree(root);
	}

	public int countInSubTree(TreeNode r) {
		
		//there are no nodes of the tree if there is no root
		if (r == null) {
			return 0;
		} 
		
		//the number of nodes in the tree includes the root, its number of children, and the number of descendants it has based on the children
		else {
			return 1 + countInSubTree(r.leftNode) + countInSubTree(r.rightNode);
		}
	}

	
	//returns true if all nodes either have two children or are leafs or if the the tree is empty
	public boolean allLeafs() {
		
		if (root == null) {
			return true;
		} 
		else {
			return allLeafsSubTree(root);
		}
	}

	//assuming the root/parameter is not null (because it was checked previously), checks if all nodes have two children or have no children
	private boolean allLeafsSubTree(TreeNode r) {

		//checks if the nodes has zero children or only one right child
		if (r.leftNode == null) {
			return r.rightNode == null;
		} 
		
		//checks if the node has only one left child
		else if (r.rightNode == null) {
			return false;
		} 
		
		//if the node has two children, we need to also check the children for the number of their children each has
		else {
			return allLeafsSubTree(r.leftNode) && allLeafsSubTree(r.rightNode);
		}

	}
	
	//this class represents a treeNode with a value and two connecting nodes
	public class TreeNode {

		private E data;
		private TreeNode leftNode;
		private TreeNode rightNode;

		public TreeNode(E d, TreeNode left, TreeNode right) {
			data = d;
			leftNode = left;
			rightNode = right;
		}
		
	}
	
}

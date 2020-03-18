import java.util.LinkedList;
import java.util.Queue;

/*
 * Name: William Chung
 * 
 * This class represents a binary search tree which is a
 * recursive data structure full of TreeNodes. it handles 
 * a TreeNode that is the root. A treeNode handles its left 
 * node which will always be less than the node and right node
 * which always be greater than it. It also stores its occruence
 * numbers.
 */
public class HW<E extends Comparable<E>> {

	private TreeNode root;

	//returns true if the item is found in the BST
	public boolean contains(E item) {
		return containsNode(item, root);
	}

	//returns true if there is a node that has the item
	private boolean containsNode(E item, TreeNode r) {
		
		//there is no more nodes to check
		if (r == null) {
			return false;
		}

		int compare = item.compareTo(r.data);

		//if the item is less, there can only be on the left the node.
		if (compare < 0) {
			return containsNode(item, r.left);
		}

		if (compare == 0) {
			return true;
		}

		//if the item is more, there can only be on the right
		return containsNode(item, r.right);

	}

	//returns the smallest item in the BST
	public E findMin() {
		
		//there is no smallest item if the tree is empty
		if (root == null) {
			return null;
		} 
		else {
			return findMinHelper(root);
		}
		
	}

	//the smallest item on a non-empty tree is the most left from the root
	private E findMinHelper(TreeNode r) {

		if (r.left == null) {
			return r.data;
		}

		return findMinHelper(r.left);
	}

	//returns the largest item in the BST
	public E findMax() {
		
		//there is no largest item if the tree is empty
		if (root == null) {
			return null;
		} 
		
		else {
			return findMaxHelper(root);
		}
		
	}

	//the largest item on a non-empty tree is the most right from the root
	private E findMaxHelper(TreeNode r) {

		if (r.right == null) {
			return r.data;
		}

		return findMaxHelper(r.right);

	}
	
	//Inserts the tree given the rules of a BST
	public void insert(E item) {
		root = insertNode(item, root);
	}

	private TreeNode insertNode(E item, TreeNode r) {

		//if the node is empty AKA we reached the end of the tree, we insert a new node with them item
		if (r == null) {
			return new TreeNode(item, null, null, 0);
		}

		int compare = item.compareTo(r.data);
		
		//we will insert somewhere in the left if the data is smaller than the node
		if (compare < 0) {
			r.left = insertNode(item, r.left);
		} 
		
		//we update its occurrences if it is already in the tree
		else if (compare == 0) {
			r.occurs++;
		} 
		
		//we will insert somewhere in the right if the data is larger than the node
		else {
			r.right = insertNode(item, r.right);
		}
		
		return r;

	}

	//removes item from the tree if found. if not found, does nothing
	public void remove(E item) {
		root = getNewNode(item, root);
	}
	
	//finds the node with item that needs to be deleted and sets the new value
	private TreeNode getNewNode(E item, TreeNode r) {
		
		//if we check everything, we do nothing to nothing
		if (r==null) {
			return null;
		}
		
		int compare = item.compareTo(r.data);
		
		//we keep looking (and eventually set the value) to the left if the data is too small
		if (compare < 0) {
			r.left =  getNewNode(item, r.left);
		}

		//we either delete one of its occurrences or if there is only one, we delete the entire node from the tree
		else if (compare == 0) {
			
			if(r.occurs>1) {
				r.occurs--;
			}
			else {
				return removeNode(item, r);
			}
			
		}
		
		//we keep looking (and eventually set the value) to the right if the data is too large
		else {
			r.right = getNewNode(item,r.right);
		}
		
		return r;
		
	}

	//takes in the node to delete, determines the number of children,severs connections, and returns the state of the tree
	private TreeNode removeNode(E item, TreeNode toRem) {

		if (toRem.left == null) {

			//if there is no children, it goes DELETED! 
			if (toRem.right == null) {
				return null;
			} 
			
			//if there is only a right child, we replace the deleted node with its child
			else {
				TreeNode toRepl = toRem.right;
				toRem.right = null;
				return toRepl;
			}

		} 
		
		//if there is only a left child, we replace the deleted node with its child
		else if (toRem.right == null) {
			TreeNode toRepl = toRem.left;
			toRem.left = null;
			return toRepl;
		}
		
		//if there are two children, we replace the node with the in order successor
		else {
		
			//the in order successor of a node is the smallest node on the right of the node.
			toRem.right = removeMin(toRem.right, toRem);
			return toRem;
		}	
		
	
	}
	
	//finds the smallest node, replace's "toRem"'s data with the smallest node's data, and deletes the smallest node.
	private TreeNode removeMin(TreeNode node,TreeNode toRem) {
		
		if(node.left==null) {
			toRem.data = node.data;
			return null;
		}

		node.left = removeMin(node.left,toRem);
		return node;
	}

	//these three public and three private methods traverse through the tree. It also prints out repeating occurrences
	public void preOrder() {
		traversePre(root);
	}

	private void traversePre(TreeNode r) {
		
		if (r != null) {

			int ind = 0;
			do {
				System.out.println(r.data);
				ind++;
			} while (ind < r.occurs);
			traversePre(r.left);
			traversePre(r.right);
		}
		
	}

	public void inOrder() {
		traverseIn(root);
	}

	private void traverseIn(TreeNode r) {
	
		if (r != null) {
			traverseIn(r.left);
			int ind = 0;
			do {
				System.out.println(r.data);
				ind++;
			} while (ind < r.occurs);
			traverseIn(r.right);
		}
		
	}

	public void postOrder() {
		traversePost(root);
	}

	private void traversePost(TreeNode r) {
		
		if (r != null) {
			traversePost(r.left);
			traversePost(r.right);
			int ind = 0;
			do {
				System.out.println(r.data);
				ind++;
			} while (ind < r.occurs);
			
		}
	}

	//prints each elements from left to write 
	public void levelOrder() {
		
		//this queue manages what is going to get printed in what order
		Queue<TreeNode>printNodes = new LinkedList<TreeNode>();

		if(root!=null) {
			
			//for a non-empty list, a root is going to be the first thing printed
			printNodes.add(root);
			
			//keeps printing in level order till there is nothing left to be printed 
			do {
				TreeNode parentNode = printNodes.remove();
				
				int ind=0;
				
				//prints all occurrences of a data
				do {
					System.out.println(parentNode.data);
					ind++;
				} while (ind < parentNode.occurs);
				
				
				//when one level is getting printed, it adds its children in the lower level to the queue to be printed later
				if(parentNode.left!=null) {
					printNodes.add(parentNode.left);
				}
				
				if(parentNode.right!=null) {
					printNodes.add(parentNode.right);
				}
			
			} while(!printNodes.isEmpty());
		}
		
	}
	
	public void rotateLL () {
		root = rotateLL(root);
	}
	private TreeNode rotateLL(TreeNode r) {
		TreeNode newRoot = r.left;
		if(newRoot.right!=null) {
			r.left=newRoot.right;
		}
		else {
			r.left=null;
		}
		
		newRoot.right = r;
			r.height--;
		newRoot.height++;
		return newRoot;
	}
	//a tree node handles its left node, right node, data, and how many times the data occurs
	public class TreeNode {

		private E data;
		private TreeNode left;
		private TreeNode right;
		private int occurs;
		private int height;

		public TreeNode(E d, TreeNode l, TreeNode r, int h) {
			data = d;
			left = l;
			right = r;
			occurs = 1;
			height = h;
		}

	}
	
	
	
}

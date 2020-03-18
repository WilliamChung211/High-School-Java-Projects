import java.util.LinkedList;
import java.util.Queue;


/*
 * Name: William Chung
 * 
 * This class represents a balanced binary search tree which is a
 * recursive data structure full of TreeNodes. it handles 
 * a TreeNode that is the root. A treeNode handles its leftChild 
 * node which will always be less than the node and rightChild node
 * which always be greater than it. It also stores its occurrence
 * numbers. A balanced binary tree always makes sure the tree is
 * balanced meaning subtree levels cannot differ by more than 1
 */
public class ChungWilliam_BalancedBST<E extends Comparable<E>> {

	private TreeNode root;
	
	//Inserts the tree given the rules of a BST
	public void insert(E item) {
		root = insertNode(item, root);
	}

	private TreeNode insertNode(E item, TreeNode r) {

		//if the node is empty AKA we reached the end of the tree, we insert a new node with them item
		if (r == null) {
			return new TreeNode(item, null, null);
		}
	
		int compare = item.compareTo(r.data);
		
		//we will insert somewhere in the leftChild if the data is smaller than the node
		if (compare < 0) {
			
			r.leftChild = insertNode(item, r.leftChild);
		} 
		
		//we update its occurrences if it is already in the tree
		else if (compare == 0) {
			r.occurences++;
			return r;
		} 
		
		//we will insert somewhere in the rightChild if the data is larger than the node
		else {
			r.rightChild = insertNode(item, r.rightChild);
			
		}
		
		//after inserting, we go up the tree, updated the height and check if it is balanced
		return updAndChckNode(r);

	}
	
	//updates the node's height, checks if the node is balanced,and makes it balanced if it is not
	private TreeNode updAndChckNode(TreeNode r) {
		
		r.updateHeight();
		int heightDiff = heightDiff(r.leftChild,r.rightChild);
		
		//if the node is not balanced, it balances
		if ((heightDiff>1)||(heightDiff<-1)){
			return balance(r,heightDiff);
		}
		
		return r;
	}
	
	//takes in a subtree that should be balanced and its height diff and calls the appropriate rotation method
	private TreeNode balance(TreeNode r, int heightDiff) {
		
		if((heightDiff>0)) {
			if(heightDiff(r.rightChild.leftChild,r.rightChild.rightChild)>=0) {
				System.out.println("RR");
				return rotateRR(r);
			}
			else {
				System.out.println("RL");
				return rotateRL(r);
			}
		}
		else {
			if(heightDiff(r.leftChild.leftChild,r.leftChild.rightChild)>0) {
				System.out.println("LR");
				return rotateLR(r);
			}
			else {
				System.out.println("LL");
				return rotateLL(r);
			}
		}
		
	}
	
	//calculates the differences of the heights of two children (right - left)
	private int heightDiff(TreeNode leftChildNode, TreeNode rightChildNode) {	
		
		//null is treated as a height of 0
		if(leftChildNode==null) {
			if(rightChildNode == null) {
				return 0;
			}
			else {
				return rightChildNode.height;
			}
		}
		else if (rightChildNode==null) {
			return -leftChildNode.height;
		}
		else {
			return rightChildNode.height -leftChildNode.height;
		}
	}
	
	
	//removes item from the tree if found. if not found, does nothing
	public void remove(E item) {
		root = deleteHelper(item, root);
	}
	
	//finds the node with item that needs to be deleted and sets the new value
	private TreeNode deleteHelper(E item, TreeNode r) {
		
		//if we check everything, we do nothing to nothing
		if (r==null) {
			return null;
		}
		
		int compare = item.compareTo(r.data);
		
		//we keep looking (and eventually set the value) to the leftChild if the data is too small
		if (compare < 0) {
			r.leftChild =  deleteHelper(item, r.leftChild);
		}

		//we either delete one of its occurrences or if there is only one, we delete the entire node from the tree
		else if (compare == 0) {
			
			if(r.occurences>1) {
				r.occurences--;
			}
			else {
				return removeNode(item, r);
			}
			
		}
		
		//we keep looking (and eventually set the value) to the rightChild if the data is too large
		else {
			r.rightChild = deleteHelper(item,r.rightChild);
		}
		
		//after deleting, it goes up the tree, updates heights, and makes sure the tree is balanced
		return updAndChckNode(r);
		
	}

	//takes in the node to delete, determines the number of children,severs connections, and returns the state of the tree
	private TreeNode removeNode(E item, TreeNode toRem) {

		if (toRem.leftChild == null) {

			//if there is no children, it goes DELETED! 
			if (toRem.rightChild == null) {
				return null;
			} 
			
			//if there is only a rightChild child, we replace the deleted node with its child
			else {
				TreeNode toRepl = toRem.rightChild;
				toRem.rightChild = null;
				return toRepl;
			}

		} 
		
		//if there is only a leftChild child, we replace the deleted node with its child
		else if (toRem.rightChild == null) {
			TreeNode toRepl = toRem.leftChild;
			toRem.leftChild = null;
			return toRepl;
		}
		
		//if there are two children, we replace the node with the in order successor
		else {
		
			//the in order successor of a node is the smallest node on the rightChild of the node.
			TreeNode success = minHelper(toRem.rightChild);
			int succOccurence = success.occurences;
			success.occurences = 1;
			toRem.rightChild = deleteHelper(success.data,toRem.rightChild);
			
			toRem.data = success.data;
			toRem.occurences = succOccurence;
			
			//makes sure the new node that was replaced is balanced and updates its height
			return updAndChckNode(toRem);
		}	

	
	}
	
	//finds the smallest node, replace's "toRem"'s data with the smallest node's data, and deletes the smallest node.
	private TreeNode minHelper(TreeNode node) {
		
		if(node.leftChild==null) {
			return node;
		}

		return minHelper(node.leftChild);
	}
	
	//these four rotation methods rotate the tree, updates heights of nodes affected, and handles special cases
	private TreeNode rotateLL(TreeNode r) {
		TreeNode newRoot = r.leftChild;
		r.leftChild = newRoot.rightChild;
		
		if(r.leftChild!=null&&r.rightChild==null) {
			r.height--;
			newRoot.height++;
		}
		else {
			r.height-=2;
		}
		newRoot.rightChild=r;
		return newRoot;
	}
	
	private TreeNode rotateRR(TreeNode r) {
		TreeNode newRoot = r.rightChild;
		r.rightChild = newRoot.leftChild;
		
		if(r.rightChild!=null&&r.leftChild==null) {
			r.height--;
			newRoot.height++;
		}
		else {
			r.height-=2;
		}
		newRoot.leftChild=r;
		return newRoot;
	}
	
	private TreeNode rotateLR(TreeNode r) {
		TreeNode newRoot = r.leftChild.rightChild;
		TreeNode newLeOfNewRoot = r.leftChild;
		r.leftChild = newRoot.rightChild;
		r.height-=2;
		newLeOfNewRoot.rightChild = newRoot.leftChild;
		newLeOfNewRoot.height--;
		newRoot.leftChild = newLeOfNewRoot;
		newRoot.rightChild =r;
		newRoot.height++;
		return newRoot;
		
	}
	
	private TreeNode rotateRL(TreeNode r) {
		TreeNode newRoot = r.rightChild.leftChild;
		TreeNode newRiOfNewRoot = r.rightChild;
		r.rightChild = newRoot.leftChild;
		r.height-=2;
		newRiOfNewRoot.leftChild = newRoot.rightChild;
		newRiOfNewRoot.height--;
		newRoot.leftChild = r;
		newRoot.rightChild =newRiOfNewRoot;
		newRoot.height++;
		return newRoot;
	}

	
	//a tree node handles its leftChild node, rightChild node, data, and how many times the data occurs
	public class TreeNode {

		private E data;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private int occurences;
		private int height;

		public TreeNode(E d, TreeNode l, TreeNode r) {
			data = d;
			leftChild = l;
			rightChild = r;
			occurences = 1;
			height = 1;
		}
		
		//updates the height based on the storage in their children which is one plus the maximum child height
		private void updateHeight() {
			
			if(leftChild==null) {
				if(rightChild==null) {
					height = 1;
				}
				else {
					height = 1+rightChild.height;
				}
			}
			else if(rightChild==null) {
				height = 1+ leftChild.height;
			}
			else {
				height = 1 + Math.max(leftChild.height,rightChild.height);
			}
			
		}

	}
	
	
}

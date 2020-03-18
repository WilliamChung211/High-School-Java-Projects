import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * Name: William Chung
 * 
 * This program represents an node based implementation of a Heap that
 * implements the PriorityQueue interface. The priority queue is a queue
 * that prioritizes priority as the thingy that will be removed. Heaps are
 * complete binary trees that only cares about having the kid node(s) having less
 * priority than the parent node. Root always has most priority.
 */
public class ChungWilliam_NodeHeap<E extends Comparable<E>> implements PriorityQueue<E> {

	private TreeNode root;
	private int numElements;

	public boolean isEmpty() {
		return root == null;
	}

	public void add(E item) {

		numElements++;
		if (isEmpty()) {
			root = new TreeNode(item, null, null, null);
		}

		else {

			// gets the last node's parents and adds the nodes to parent
			String biSize = Integer.toBinaryString(numElements);
			TreeNode lastNode = lastNodePar(root, biSize);
			if (biSize.substring(biSize.length() - 1).equals("0")) {
				lastNode.left = new TreeNode(item, null, null, lastNode);
				lastNode = lastNode.left;
			} 
			else {
				lastNode.right = new TreeNode(item, null, null, lastNode);
				lastNode = lastNode.right;
			}

			// keeps swapping up as long as the node we are swapping up has more priority than parent
			while (lastNode.parent != null && lastNode.value.compareTo(lastNode.parent.value) < 0) {
				swap(lastNode.parent, lastNode);
				lastNode = lastNode.parent;
			}
		}

	}

	// gets lastNode parent using the binary trick
	private TreeNode lastNodePar(TreeNode node, String biSize) {

		for (int i = 1; i < biSize.length() - 1; i++) {
			if (biSize.substring(i, i + 1).equals("0")) {
				node = node.left;
			} 
			else {
				node = node.right;
			}
		}
		return node;

	}

	private void swap(TreeNode parent, TreeNode child) {
		E temp = parent.value;
		parent.value = child.value;
		child.value = temp;
	}

	public E remove() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		String biSize = Integer.toBinaryString(numElements);
		TreeNode lastNode = lastNodePar(root, biSize);
		numElements--;
		E toRem = root.value;

		if (numElements == 0) {
			root = null;
		} 
		else {

			// decconects nodes
			if (biSize.substring(biSize.length() - 1).equals("0")) {
				lastNode = lastNode.left;
				lastNode.parent.left = null;
			} 
			else {
				lastNode = lastNode.right;
				lastNode.parent.right = null;
			}

			lastNode.parent = null;
			root.value = lastNode.value;

			TreeNode current = root;
			
			//keeps swapping while there is a child with more priority or till there is no more to swap
			while (current.left != null) {

				TreeNode potSwap;
				if (current.right == null || current.left.value.compareTo(current.right.value) <= 0) {
					potSwap = current.left;
				}
				else {
					potSwap = current.right;
				}

				if (potSwap.value.compareTo(current.value) <= 0) {
					swap(potSwap, current);
				} 
				else {
					return toRem;
				}

				current = potSwap;
			}
		}
		return toRem;

	}

	public E peek() {
		return root.value;
	}

	public class TreeNode {

		private E value;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;

		public TreeNode(E v, TreeNode l, TreeNode r, TreeNode p) {
			value = v;
			left = l;
			right = r;
			parent = p;
		}

	}

}

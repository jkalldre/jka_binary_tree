// Package
package jka_binary_tree;

// Imports
import java.util.List;
import java.util.ArrayList;

/**
 * @author YZFFVR
 */
public class BinaryTree {
	// Private variables
	private Node root;
	private Node balancedRoot;

	// getters
	public Node getRoot() {
		return this.root;
	}

	public Node getBalanced() {
		return this.balancedRoot;
	}

	/**
	 * Constructor
	 */
	BinaryTree() {
		this.root = null;
	}

	/**
	 * Add node to tree using recursion.
	 * 
	 * @param value - The value of the node.
	 */
	public void add(int value) {
		this.root = addRecursive(this.root, value);
	}

	/**
	 * Add node to tree using recursion.
	 * 
	 * @param current - Pointer of current node in tree.
	 * @param value   - Value to be inserted
	 * @return - The node created or exiting node with listed value.
	 */
	public Node addRecursive(Node current, int value) {
		if (current == null) {
			current = new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		}

		if (value > current.value) {
			current.right = addRecursive(current.right, value);
		}

		return current;

	}

	public void balanceTree() {
		List<Integer> list = new ArrayList<>();
		traverseInOrder(this.root, list);

		this.balancedRoot = simpleTreeBalance(list, 0, list.size() - 1);
	}

	/**
	 * Balance and display tree
	 */
	public Node simpleTreeBalance(List<Integer> list, Integer start, Integer end) {

		if (start > end) {
			return null;
		}

		Double mid = Math.ceil((start + end) / 2);
		Node root = new Node(list.get(mid.intValue()));
		root.left = simpleTreeBalance(list, start, mid.intValue() - 1);
		root.right = simpleTreeBalance(list, mid.intValue() + 1, end);

		return root;
	}

	/**
	 * Search tree for given value.
	 * 
	 * @param value - Value being searched for.
	 * @return - True if value exists in tree.
	 */
	public Boolean contains(Integer value) {
		return containsNode(this.root, value);
	}

	/**
	 * Search tree for given value.
	 * 
	 * @param current - Used for recursion (Current node)
	 * @param value   - Value being searched for.
	 * @return - True if value exists in tree.
	 */
	public Boolean containsNode(Node current, Integer value) {
		if (current == null) {
			return false;
		}

		if (current.value == value) {
			return true;
		}

		return value < current.value ? containsNode(current.left, value) : containsNode(current.right, value);
	}

	/**
	 * Print tree in order.
	 */
	public void inOrder() {
		List<Integer> list = new ArrayList<>();
		traverseInOrder(this.root, list);

		System.out.println("");
		System.out.println("In Order Output:");
		for (Integer item : list) {
			System.out.print(item + " ");
		}
	}

	/**
	 * Print tree in reverse order.
	 */
	public void inReverse() {
		List<Integer> list = new ArrayList<>();
		traverseInReverseOrder(this.root, list);

		System.out.println("");
		System.out.println("In Reverse Order Output:");
		for (Integer item : list) {
			System.out.print(item + " ");
		}
	}

	/**
	 * Iterate and print tree in order.
	 * 
	 * @param current - Used for the recursion (Current node)
	 * @param list    - Tracks nodes as they are iterated.
	 */
	public void traverseInOrder(Node current, List<Integer> list) {
		if (current != null) {
			traverseInOrder(current.left, list);
			list.add(current.value);
			traverseInOrder(current.right, list);
		}
	}

	/**
	 * Iterate and print tree in reverse order.
	 * 
	 * @param current - Used for the recursion (Current node)
	 * @param list    - Tracks nodes as they are iterated.
	 */
	public void traverseInReverseOrder(Node current, List<Integer> list) {
		if (current != null) {
			traverseInReverseOrder(current.right, list);
			list.add(current.value);
			traverseInReverseOrder(current.left, list);
		}

	}

	/**
	 * Display's the tree with organized UTF-8 characters.
	 */
	public void displayTree(Node root) {
		StringBuilder buffer = new StringBuilder(50);
		buffer = print(buffer, "", "", root);
		System.out.println("");
		System.out.print(buffer.toString());
	}

	/**
	 * Build tree structure in UTF-8
	 * 
	 * @param buffer         - Used to build string.
	 * @param prefix         - Used to offset the nodes. (Call with "")
	 * @param childrenPrefix - Used to offset the nodes. (Call with "")
	 * @param current        - Used for the recursion.
	 * @return - A built string ready to be printed.
	 */
	private StringBuilder print(StringBuilder buffer, String prefix, String childrenPrefix, Node current) {
		if (current != null) {
			buffer.append(prefix);
			buffer.append(current.value);
			buffer.append('\n');

			if (current.numChild() == 2) {
				print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", current.right);
				print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", current.left);
			}
			if (current.numChild() == 1) {
				if (current.left != null) {
					print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", current.left);
				} else {
					print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", current.right);
				}
			}

		}

		return buffer;
	}
}

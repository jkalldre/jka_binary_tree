/**
 * 
 */
package jka_binary_tree;

import java.lang.ProcessBuilder;
import java.util.Scanner;
import java.util.Random;

/**
 * @author YZFFVR
 *
 */
public class Main {
	// Private variables
	private static BinaryTree binaryTree;
	private static Scanner s;

	/**
	 * Execute the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Local variables
		s = new Scanner(System.in);
		binaryTree = new BinaryTree();

		// Display menu
		displayMenu();

		// Build the tree according to user selected method.
		execute();

		// Build a balanced tree.
		binaryTree.balanceTree();
		
		// Display built tree.
		System.out.println("=Tree Structure=");
		binaryTree.displayTree(binaryTree.getRoot());
		
		// Display balanced tree.
		System.out.println("");
		System.out.println("=Balanced Tree Structure=");
		binaryTree.displayTree(binaryTree.getBalanced());
		
		
		// Iterate though the tree.
		// binaryTree.inOrder();
		// binaryTree.inReverse();

		s.close();
	}

	/**
	 * Build tree and request user input.
	 */
	private static void execute() {
		System.out.print("> ");
		switch (s.nextInt()) {
		case 1: // Pre-structured tree (good for testing)
			structured();
			break;
		case 2: // randomly generate and insert nodes
			Random random = new Random();
			System.out.println("Number of Nodes:");
			System.out.print("> ");
			Integer numNodes = s.nextInt();
			for (int i = 0; i < numNodes; i++) {
				binaryTree.add(random.nextInt(numNodes));
			}
			break;
		case 3: // build tree according to user inputs.
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "clear");

			Integer count = 0;

			while (count < 10000) {
				System.out.print("> ");
				binaryTree.add(s.nextInt());

				clear(pb);
				binaryTree.displayTree(binaryTree.getRoot());
				count++;
			}
			break;
		default:
			structured();
			break;

		}
	}

	private static void structured() {
		// root
		binaryTree.add(4);

		binaryTree.add(2);
		binaryTree.add(1);
		binaryTree.add(3);
		binaryTree.add(6);
		binaryTree.add(5);
		binaryTree.add(7);
		binaryTree.add(9);
		binaryTree.add(8);
		binaryTree.add(0);
	}

	/**
	 * Display the menu to user.
	 */
	private static void displayMenu() {
		System.out.println("==Tree Program==");
		System.out.println("Menu:");
		System.out.println("\t1: Prebuilt Tree");
		System.out.println("\t2: Randomly Generated Tree");
		System.out.println("\t3: Dynamically Built Tree");
	}

	/**
	 * Clear the shell screen to refresh the tree.
	 * 
	 * @param pb - ProcessBuilder to execute shell commands.
	 */
	private static void clear(ProcessBuilder pb) {
		try {
			pb.inheritIO().start().waitFor();
		} catch (Exception e) {
			System.out.println("Error: Failed to clear screen.");
		}
	}
}

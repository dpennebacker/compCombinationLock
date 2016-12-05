package com.company;//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.util.Random;
import java.util.Scanner;

/** CombinationPuzzle
 *
 * The objective is to solve a puzzle involving a cycle of combination
 * dials.
 */

public class CombinationPuzzle {

	/** 
	 *  Main Program - Reads the input and outputs the final answer.
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		try {
			int numTrials = scanner.nextInt();	// number of trials to run

			for(int t = 1; t <= numTrials; t++) {
				int n = scanner.nextInt();		// number of dials
				if (n <= 1) {
					System.err.println("Error - Must have at least one dial");
					return;
				}

				int[] dial = new int[n];		// the dials
				int[] dialCopy = new int[n];	// ...and a copy 

				for (int i = 0; i < n; i++) {	// input initial dial settings
					dialCopy[i] = dial[i] = scanner.nextInt();
												// check
					if (dial[i] < 0 || dial[i] > 9) {
						System.err.println("Error - Dial value must be in range 0..9");
						return;
					}
				}
												// summarize the input
				System.out.print("Initial dial settings = [");
				for (int i = 0; i < n; i++) {
					System.out.print(" " + dial[i]);
				}
				System.out.println(" ]");
												// solve it
				String solution = SolvePuzzle(dial);
												// summarize the results
				SummarizeSolution(solution, dialCopy);
			}
		}
		finally {								// close scanner resource
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	/**
	 * SummarizeSolution
	 *
	 * Summarize solution and check its basic validity.
	 *
	 * This does NOT check for optimality.
	 *
	 */
	static void SummarizeSolution(String solution, int[] dial) {
		System.out.println("Raw output = \"" + solution + "\"");
		System.out.println(" Analysis:");
		Scanner ssolution = new Scanner(solution);
		try {
												// no solution?
			if (solution.compareToIgnoreCase("X") == 0) {
				System.out.println("  >> No solution");
			}
			else {								// found a solution
				int totalCost = 0;
				while (ssolution.hasNext()) {
												// get the next step
					String step = ssolution.next();

					int di = 0;					// dial index
					int sign;					// increment or decrement
					if (step.charAt(0) == '+') {
						sign = +1;
					}
					else if (step.charAt(0) == '-') {
						sign = -1;
					}
					else {
						System.err.println("  !! Format error - Step must start with '+' or '-'");
						return;
					}

					try {						// get dial index
						di = Integer.parseInt(step);
						if (di < 0) di = -di;	// map +x and -x to x
						if (di > dial.length) {
							System.err.println("  !! Format error - invalid dial index");
							return;
						}
					}
					catch (NumberFormatException e) {
						System.err.println("  !! Format error - dial index is not an integer");
						return;
					}
												// next dial in the sequence
					int dii = (di + 1) % dial.length;
												// increment/decrement mod 10
					dial[di] = (dial[di] + sign + 10) % 10;
					dial[dii] = (dial[dii] - sign + 10) % 10;
					totalCost++;
				}
												// check final dials are zero
				boolean allZero = true;
				for (int i = 0; i < dial.length; i++) {
					if (dial[i] != 0) allZero = false;
				}
				if (!allZero) {					// incorrect final setting?
					System.err.println("  !! Error - Final dial settings must all be zero");
					System.out.print("     Final settings = [");
					for (int i = 0; i < dial.length; i++) {
						System.out.print(" " + dial[i]);
					}
					System.out.println(" ]");
					return;
				}
				System.out.println("  >> Valid solution. Total cost = " + totalCost);
			}
			System.out.println();
		}
		finally {								// close scanner resource
			if (ssolution != null) {
				ssolution.close();
			}
		}
	}

	private static String SolvePuzzle(int[] dial) {

		/* -------------------- INSERT CODE HERE ----------------------*/

		return "X";

		/* --------------------- END OF INSERTION ---------------------*/
	}
}

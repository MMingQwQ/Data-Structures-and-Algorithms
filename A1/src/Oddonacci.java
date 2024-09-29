//-------------------------------------------------------------------- 
//Assignment 1
//Written by: Mingming Zhang 
//
//For COMP 352 Section (AB) – Summer 2024
//--------------------------------------------------------------------

import java.io.*;
import java.util.Arrays;

/**
 * Name: Mingming Zhang
 * 
 * ID: 40258080
 * 
 * COMP352 Section (AB)
 * 
 * Assignment # 1
 * 
 * Due Date: 24/05/2024
 * 
 * @author Mingming Zhang
 * @version 20/05/2024
 */
public class Oddonacci {
	/**
	 * Recursive Multiple Oddonacci
	 * 
	 * @param n integer n representing position
	 * @return long (Oddonacci number at position n)
	 */
	public static long oddonacciMultiple(int n) {
		// Base case as: 1 1 1
		if (n == 1 || n == 2 || n == 3) {
			return 1;
		}
		return oddonacciMultiple(n - 1) + oddonacciMultiple(n - 2) + oddonacciMultiple(n - 3);
	}

	/**
	 * Recursive Linear Oddonacci
	 * 
	 * @param n integer n representing position
	 * @return long (Oddonacci number at position n)
	 */
	public static long oddonacciLinear(int n) {
		long[] A = new long[n + 1];
		Arrays.fill(A, -1);
		return oddonacci(n, A);
	}

	/**
	 * Linear helper function
	 * 
	 * @param n integer n representing position
	 * @param A array of size (n + 1) filled with -1
	 * @return long (Oddonacci number at position n)
	 */
	private static long oddonacci(int n, long[] A) {
		// Base case as: 1 1 1
		if (n == 1 || n == 2 || n == 3) {
			return 1;
		}
		if (A[n] != -1) {
			return A[n];
		}
		A[n] = oddonacci(n - 1, A) + oddonacci(n - 2, A) + oddonacci(n - 3, A);
		return A[n];
	}

	/**
	 * Initial call helper function with initial values
	 * 
	 * @param n integer n representing position
	 * @return long (Oddonacci number at position n)
	 */
	public static long oddonacciTail(int n) {
		return oddonacciHelper(n, 1, 1, 1);
	}

	/**
	 * Tail-recursive helper method
	 * 
	 * @param n integer n representing position
	 * @param a Oddonacci number at position n-3
	 * @param b Oddonacci number at position n-2
	 * @param c Oddonacci number at position n-1
	 * @return
	 */
	private static long oddonacciHelper(int n, long a, long b, long c) {
		if (n == 1)
			return a;
		if (n == 2)
			return b;
		if (n == 3)
			return c;
		return oddonacciHelper(n - 1, b, c, a + b + c);
	}

	/**
	 * Main funtion calling all functions and write the result to "OddoOut.txt"
	 * 
	 * @param args string[]
	 */
	public static void main(String[] args) {
		File file = new File("OddoOut.txt");
		// if file exist then append to it,otherwise overwrite
		boolean append = !file.exists();

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file, append));
			// Up to 40 since the waiting time is too long
			for (int i = 5; i <= 40; i += 5) {
				// Exponential calculation
				long startTimeMultiple = System.nanoTime();
				long resultMultiple = oddonacciMultiple(i);
				long endTimeMultiple = System.nanoTime();
				long durationMultiple = endTimeMultiple - startTimeMultiple;

				// Linear calculation
				long startTimeLinear = System.nanoTime();
				long resultLinear = oddonacciLinear(i);
				long endTimeLinear = System.nanoTime();
				long durationLinear = endTimeLinear - startTimeLinear;

				// Tail recursion calculation
				long startTimeTail = System.nanoTime();
				long resultTail = oddonacciTail(i);
				long endTimeTail = System.nanoTime();
				long durationTail = endTimeTail - startTimeTail;

				// Output results
				pw.println("oddonacciMultiple(" + i + ") = " + resultMultiple + " (Time: " + durationMultiple + " ns)");
				pw.println("oddonacciLinear(" + i + ") = " + resultLinear + " (Time: " + durationLinear + " ns)");
				pw.println("oddonacciTail(" + i + ") = " + resultTail + " (Time: " + durationTail + " ns)\n");

				System.out.println(
						"oddonacciMultiple(" + i + ") = " + resultMultiple + " (Time: " + durationMultiple + " ns)");
				System.out.println(
						"oddonacciLinear(" + i + ") = " + resultLinear + " (Time: " + durationLinear + " ns)");
				System.out.println("oddonacciTail(" + i + ") = " + resultTail + " (Time: " + durationTail + " ns)\n");
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Problem opening files.");
			e.printStackTrace();
		}
	}
}

package Practical3;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;

import javax.swing.JOptionPane;
public class fibonacci {

	/*Array for memoisation*/
	public static int seenArray[] = new int[10000];
	
	/*
	 * recursive implementation of the fibonacci sequence
	 */
	public static int fibRec(int n) {
	if(n <=1) return n; // if n reaches 0 or nth element return current n value
	
	return fibRec(n-1) + fibRec(n-2); // find fib value of n-1 and n-2
	}
	
	/*
	 * recursive implementation of fibonacci sequence using memoisation
	 */
	public static int fibMemo(int n) {
		
		int seen = 0; //val to be returned / added to array
		
		if(n <=1) return n; // base case
		else if(seenArray[n] != 0) {
		// if we have already computed this value
		// it will already been in the array and so we can just return this value
			return seenArray[n];
		}
		
		else {
		seen = fibMemo(n-1) + fibMemo(n-2);
		seenArray[n] = seen; //filling array with new value then returning
		return seen;
		}
	}
		

	public static int fibIt(int n) {
		//edge case
		if(n <= 1)
			return 1;
	
		
		int fib = 1;
		int prevFib = 1;
		
		for(int i =2;i<n;i++) {
			//temp stores fib so we dont lose its value
			int temp = fib;
			//fib = fibonnaci[n-1] +fibonacci[n-2]
			fib = fib+prevFib;
			//restoring value
			prevFib = temp;
		}
		
		return fib;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Program analysing fibonacci sequence implementations");
		boolean replay = true;
		
		while(replay) {
			Scanner in = new Scanner(System.in);
			int fibVal = 0;
			
			try {
				System.out.print("Enter nth fibonacci number to be calculated:");
				fibVal = in.nextInt();
			}
			catch(InputMismatchException e) {
				System.err.println("Input only integers");
			}
			
			double time1,time2,time3;
			Stopwatch recursiveTimer = new Stopwatch();
			fibRec(fibVal);
			time1 =recursiveTimer.elapsedTime();
			
			Stopwatch iterativeTimer = new Stopwatch();
			fibIt(fibVal);
			time2 = iterativeTimer.elapsedTime();
			
			Arrays.fill(seenArray, 0);
			//defining the array for memoization
			seenArray[0] = 1;
			seenArray[1] = 1;
			
			Stopwatch memoTimer = new Stopwatch();
			fibMemo(fibVal);
			time3 = memoTimer.elapsedTime();
			
			System.out.format("\n\n%25s%20s%20s%20s\n","Nth Fibonacci number", "Recursive time","Iterative time","Memoisation time");
			System.out.format("%25d%20f%20f%20f\n\n\n",fibVal,time1,time2,time3);
			
			replay = replay();
		}
	}
	
	public static boolean replay() {
		System.out.println("Do you want to run again?");
		System.out.println("Please enter (y/n)");
		Scanner in = new Scanner(System.in);
		
		String s = in.next();
		switch(s) {
		case "y":
			return true;
		case "n":
			return false;
		default:
			System.out.println("Please try again");
		}
	return false;	
	}

}

	
	


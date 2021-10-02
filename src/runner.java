import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Practical4.Sorting;

public class runner {

	
	
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		System.out.println("Welcome to Comp20290 Algorithms");
		
		boolean replay = true;
		while(replay) {
			displayOptions();
			chooseOption();
			replay = replay();
		}
	}
	
	//allows users to enter a number depending on which practical they want to view 
	private static void chooseOption() throws InterruptedException, FileNotFoundException {
		
		System.out.println("Enter value between 0 and 10");
		System.out.println("Note options 0 and 1 rely on JOption MessagePanes");
		System.out.println("These panes will open but wont be appear on top of IDE(Alt tab to find them)");
		
		Scanner in  = new Scanner(System.in);
		

		try {
			switch(in.nextInt()) {
			case 0:
				HanoiGame.Hanoi.main(null);
				break;
			case 1:
				Practical1.ThreeSum.main(null);
				break;
			case 2:
				Practical2.russianPeasants.main(null);
				break;
			case 3:
				Practical3.HanoiAnalysis.main(null);
				break;
			case 4:
				Practical3.fibonacci.main(null);
				break;
			case 5:
				Practical4.Sorting.main(null);
				break;
			case 6:
				Practical5.mergeSort.main(null);
				break;
			case 7:
				Practical6.QuickSort.main(null);
				break;
			case 8:
				Practical7.Searching.main(null);
				break;
			case 9:
				Practical8.Trie.main(null);
				break;
			case 10:
				Practical9.DataCompression.main(null);
				break;
			case 11:
				break;
			default:
				System.out.println("Enter value between 0 and 11!");
			}
			
			
			
		}
		
		catch(InputMismatchException e) {
			System.err.println("Input only integers");
		}
	}
	
	/*
	 * Needs to be displayed constantly so function is needed to output options
	 */
	private static void displayOptions() {
		System.out.println("Press the key associated to run this section of the portfolio\n"
				+ "0: -EXTRA- Implementation of hanoi game\n"
				+ "1: Analysis on 2 implentations of which values in an array sum to 0\n"
				+ "2: Analysis or use of russianPeasants algorithm for multiplication\n"
				+ "3: Analysis on run time for tower of hanoi\n"
				+ "4: Analysis on fibonacci sequence generation for 3 implentations\n"
				+ "5: Comparison of insertion selection and bozoSort\n"
				+ "6: Analysis and comparison of MergeSort and enhanced MergeSort\n"
				+ "7: Analysis and comparison of QuickSort and enhanced Quicksort\n"
				+ "8: Comparison of bruteForce and knuth-morris-pratt imlpentation of pattern matching in strings\n"
				+ "9: Generate a trie and search through it\n"
				+ "10:RunLength encoding on a given string\n"
				+ "11:EXIT PROGRAM");
	}


	/*
	 * Allows user to chose another practical
	 */

	public static boolean replay() {
		System.out.println("Do you want to view another practical or exit");
		System.out.println("Please enter (y-another/n-exit)");
		Scanner in = new Scanner(System.in);
		
		String s = in.next();
		switch(s) {
		case "y":
			return true;
		case "n":
			return false;
		default:
			System.out.println("Please try again");
			return replay();
		}	
	}
}

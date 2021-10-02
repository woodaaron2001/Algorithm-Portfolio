package Practical3;
import java.util.InputMismatchException;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;


public class HanoiAnalysis {

	public static String towersOfHanoi(int num,char source, char dest, char aux ) {
		//takes in 3 char or stacks 
		String returnString= "";
		if(num == 1) {		
			
			//if we reach base case we move the top disk from source to dest
			//however source and dest depends on the sequence taken as source and dest are relative to the current step
			return returnString + "Move disk "+ num + " from stack " + source + " to stack " + dest+"\n";	
		}
	
		//as we move down the desination and auxillary stacks switch 
		 returnString += towersOfHanoi(num-1,source,aux,dest);
		 returnString +="Move disk "+ num + " from stack " + source + " to stack " + dest+"\n";
		
		return returnString += towersOfHanoi(num-1,aux,dest,source);
	}
	
	
	public static void main(String args[]) {
		
		System.out.println("~Program analysing Towers of Hanoi~");
		chooseTowerSize();
	}
	

	
	public static void chooseTowerSize() {

		System.out.println("Enter how high the tower will be: ");
		Scanner in = new Scanner(System.in);
		try {
			String result = "";
			int towerSize = in.nextInt();
			Stopwatch hanoi = new Stopwatch();
			result = towersOfHanoi(towerSize,'A','B','C');
			
			System.out.format("%18s%18s\n","Number of disks", "Time elapsed(s)");
			System.out.format("%18d%18f\n",towerSize, hanoi.elapsedTime()/1000000000);
			viewSteps(result);
			//System.out.println(result);
			
		}
		catch(InputMismatchException e) {
			System.err.println("Input only integers");
		}
		replay();
		return;
	}
	
	/*
	 * Gives user option to view output of hanoi or not 
	 */
	public static boolean viewSteps(String hanoi) {
		System.out.println("Do you want to view hanoi steps(y/n)?");
		Scanner in = new Scanner(System.in);
		String s = in.next();
		switch(s) {
		case "y":
			System.out.println(hanoi);
		case "n":
			return false;
		default:
			System.out.println("Please try again");
		}
		return false;
	}
	public static void replay() {
		System.out.println("Do you want to run again?");
		System.out.println("Please enter (y/n)");
		Scanner in = new Scanner(System.in);
		
		String s = in.next();
		switch(s) {
		case "y":
			chooseTowerSize();
			break;
		
		case "n":
			System.out.println("Thanks for playing");
			break;

		default:
			System.out.println("Please try again");
		}	
	}
	
}

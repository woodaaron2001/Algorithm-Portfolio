package Practical9;

import java.util.Scanner;

public class DataCompression {

	/*
	 * Converts String into a runLengthEncoded version of said string
	 */
	public static String runLengthEncoding(String encode){
		
		char runningChar= encode.charAt(0);
		StringBuilder builder = new StringBuilder();
	
		int count = 0;
		
		for(char currentChar: encode.toCharArray()) {
			//if we notice a mismatch in the run
			if(currentChar != runningChar) {
				
				builder.append(runningChar);
				builder.append(count);
				
				//update the running char to the mismatch and reset count to 1
				runningChar = currentChar;
				count = 1;
			}
			else {
				count++;
			}
			
		}
		
		builder.append(runningChar);
		builder.append(count);
		return builder.toString();
	}
	
	public static void main(String args[]) {
		System.out.println("Program to compress a string of characters using runLength encoding");
		
		boolean replay = true;
		Scanner in  = new Scanner(System.in);
		
		while(replay) {
			System.out.print("Please enter a string of data:");
			System.out.println(runLengthEncoding(in.nextLine()));

			replay = replay();
		}
		
	}
	
	private static boolean replay() {
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
			return replay();
		}
	}
	
}

package Practical7;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Practical4.Sorting;
import Extras.In;

import javax.swing.JFileChooser;

public class Searching {

	/*
	 * Implentation of String search using bruteForce method
	 */
	public static String bruteForce(String txt ,String search) {
		int n = txt.length();
		int m = search.length();
		
		//n-m+1 as n-m means that  in text "today" "day" wouldnt be recognised as loop reaches end
		for(int i = 0 ; i < n-m+1;i++) {
			for(int j = 0; j<m;j++) {
				if(txt.charAt(i+j) != search.charAt(j)) {
					break;
				}
				if(j == m-1) {
					return "Text found at positon: "+i;
				}
			}
			
		}	
		return "Text could not be located";
	}
	
	
	/*
	 * Knuth-Morris-Pratt search where we jump back based on longest suffix/prefix at point 
	 */
	public static String kmp(String txt,String search) {
		int n = txt.length();
		int m = search.length();
	
		//lpsArray indicates how far back we should go in the pattern when we read a mismatch
		int[] lpsArray = new int[m];

		suffixPrefixTable(search,m,lpsArray);
		
		int i = 0,j=0;
		while(i<n) {
			
			if(txt.charAt(i) == search.charAt(j)) {
				i++;
				j++;
			}
			
			if(j == m) {
				return "Text found at positon: "+ (i-m) ;
			}
			
			else if(i < n && search.charAt(j) != txt.charAt(i)) {
				//if we have had multiple matches we need to recompute j and compare again
				if(j>0) {
					j = lpsArray[j-1];
				}
				//Alternatively we don't need to compare again if there were no matches
				else {
					i++;
				}
			
			}
		}

		return "Text could not be located";
	}
	
	/*
	 * LpsArray computes jump pattern for String
	 * longest proper suffix which is also a proper prefix at point in array
	 */
	protected static void suffixPrefixTable(String search, int m, int[] lpsArray) {
		
		lpsArray[0] = 0;
		
		int i = 0;
		int j = 1;
		
		//running till array is filled
		while(j<m) {
			if(search.charAt(i) == search.charAt(j)) {
				lpsArray[j] = ++i;
				j++;
			}
			else {
				//if we mismatch + i is 0 we know there no possible prefix/suffix
				if( i == 0 ) {
					lpsArray[j] = 0;
					j++;
				}
				//otherwise we need to loop back in our pattern as the suffix/prefix continues
				else {
				i = lpsArray[i-1];	
				}
				
				
			}
		
		}
       
	}

	/*
	 * prints info about timing for both search methods
	 */
	private static void analysis(String txt) {
	     	
	     double kmpTimeArray[] = new double[10];
	     double bruteForceTimeArray[] = new double[10];
	     
	     String[] keys = {"temp","this","random","values","finds","END","acacagt"};
	     
	     
	     for(int i =0; i<7;i++) {
	     Stopwatch timer1 = new Stopwatch();  
	     bruteForce(txt,keys[i]);
	     bruteForceTimeArray[i] = timer1.elapsedTime();
	        
	     Stopwatch timer2 = new Stopwatch();
	     kmp(txt,keys[i]);
	     kmpTimeArray[i] = timer2.elapsedTime();
	        
	     }
	     
	     System.out.format("%25s%25s%25s\n","Searched Word","BruteForceSearch","Knuth-Morris-Pratt");
	     for(int i =1; i<7; i++) {
	     System.out.format("%25s%25f%25f\n",keys[i],kmpTimeArray[i]/1000000000,bruteForceTimeArray[i]/1000000000);
	     }
		
	}
	
	public static void main(String args[]) throws FileNotFoundException {	 
		System.out.println("This program is designed to search for an occurence of a string in a given text:");
    	
		boolean replay = true;

    	while(replay) {
    		chooseOption();
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

	/*
	 * Parses user input
	 */
	public static void chooseOption() throws FileNotFoundException{
		String search = "";
		String text = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Press 1: Search text for pattern using bruteForce");
		System.out.println("Press 2: Search text for pattern using kmp");
		System.out.println("Press 3: Analyse speed of both algorithms on given text");
		
		try {
			int option = in.nextInt();
			switch(option) {
				case 1:
					
					System.out.println("Please first enter larger Text: ");
					search = in.nextLine();
					System.out.println("Please enter text to be found(Case Sensitive): ");
					text = in.nextLine();

					System.out.println(bruteForce(search,text));
					break;
				case 2: 
					
					System.out.println("Please first enter larger Text: ");
					System.out.println(in.nextLine());
					search = in.nextLine();
					System.out.println("Please enter text to be found(Case Sensitive): ");
					text = in.nextLine();

					System.out.println(kmp(search,text));
					break;
				case 3:
					
					Scanner searchableText = new Scanner(new File("src/Practical7/searchableText.txt"));
					
					while(searchableText.hasNextLine()) {
						search += searchableText.nextLine();
					}
					
					System.out.println("Analysis is run on text file found in Package practical 7");
					analysis(search);
					break;
					
				default:
					System.out.println("Must enter either 1 or 2 or 3");
			}
			
		}
		catch(InputMismatchException e) {
			System.err.println("Input only integers");
		}
	}



}

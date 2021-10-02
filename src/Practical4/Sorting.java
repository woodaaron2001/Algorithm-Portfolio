package Practical4;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;

import javax.swing.JOptionPane;

public class Sorting {
	
	
	public static int[] selectionSort(int array[]) {
		
		for(int x =0; x<array.length;x++) {
			
			//minval holds the value currently being pointed to in array
			//we need minIndex so we can swap the indices of the 2 smallest
			int minVal = array[x];
			int minIndex = x;
			
			//all previous have been sorted so we start from x
			for(int y =x; y<array.length; y++) {
				//checks for smallest
				if(array[y] < minVal  ) {
					minVal = array[y];
					minIndex = y;
				}
	
			}
			//obviously if x was the smallest then mindIndex wont change otherwise we swap
			if(minIndex != x) {
				swap(array,x,minIndex);
				
			}
			
		}
		
		return array;	
	}
	
	
	public static int[] insertionSort(int array[]) {
		
		for(int i = 1; i<array.length;i++) {
			//unlike selection sort where the inner loop moves over the sorted elements
			//insertion sort moves back through the sorted elements 
			int j = i-1;
			//findVal stores the value we want as it will be overwritten as move elements
			int findVal = array[i];
			
			//while the value currently pointed to is greater than 
			while( j >= 0  && array[j] > findVal){
				//moves each value into the next spot to make room for value
				array[j+1] = array[j];
				j--;
			}
			//finally insert
			array[j+1] = findVal;
					
			}	
		return array;
	}
	
	
	public static void swap(int array[],int pos,int pos2) {
		//temp is needed as we lose the value of array[pos] when we assign it a value
		int temp = array[pos];
		array[pos] = array[pos2];
		array[pos2]= temp;
		
		return;
	}
	
	public static void randomiseArray(int[] array) {

		Random rand = new Random();		
			for(int y= 0;y < array.length;y++) {
				int randNum = rand.nextInt(1000);
				array[y] = randNum;
			}
		}
	
	
	//print array 
	public static String printArray(int array[]) {
		StringBuilder builder = new StringBuilder();
		int x=0;
		for(;x <array.length-1;x++) {
			builder.append(array[x]+",");
		}
		
		builder.append(array[x]);
		return builder.toString();
	}
	
	public static void BozoSort(int array[]) {
	/*	BozoSort is a form of bogoSort where instead of
	 * shuffling the entire array you choose 2 random values
	 * and then shuffle them  */
		
		while(!arrayCheck(array)) {
				int randomIndex1 = (int) (Math.random() *array.length);
				int randomIndex2 = (int) (Math.random() *array.length);
				
				swap(array,randomIndex1,randomIndex2);
			}
	}
	public static Boolean arrayCheck(int array[]) {
		
		//checks is previous value is greater than the current value
		for(int x=1;x<array.length;x++) {
			if(array[x] < array[x-1]) {
				return false;
			}
		}
		return true;
	}
	
	

	/*
	 * compares all 3 arrays and outputs timings of each 
	 */
	public static void analysis(int[] insertionArr,int[] selectionArr,int[] bozoArr) {
		
		double time1,time2,time3;
		System.out.println("Running analysis please wait:");
		Stopwatch insertion = new Stopwatch();
		//insertionSort(insertionArr);
		time1 = insertion.elapsedTime();
        
    	Stopwatch selection = new Stopwatch();
		selectionSort(selectionArr);
		time2 = selection.elapsedTime();
    
		Stopwatch bozo = new Stopwatch();
		BozoSort(bozoArr);
		time3 = bozo.elapsedTime();
		
		System.out.format("%20s%20s%20s%20s%20s\n", "Insertion/Selection size", "Insertion time(S)","Selection time(S)","Bozo size","Bozo time(S)");
		System.out.format("%20d%20f%20f%20d%20f\n", insertionArr.length, time1/1000000000,time2/1000000000,bozoArr.length,time3/1000000000);

	}


		
		
	public static void main(String[] args) {
		
		System.out.println("Program to analyse bozoSort insertionSort and selectionSort");
		boolean replay = true;
		
		while(replay) {
		System.out.println("Enter the number of ints in selection and insertion sort array");
		Scanner in = new Scanner(System.in);
		
		int x= in.nextInt();
		int[] arrayInsertion = new int[x];
		int[] arraySelection = new int[x];
		
		randomiseArray(arrayInsertion);
		arraySelection = Arrays.copyOf(arrayInsertion,x);
		
		viewArray(arrayInsertion);
		
		System.out.println("Finally enter the size of the array to be bozo sorted (array size must be less than 15)");
		x= in.nextInt();
		while(x >= 15) {
			System.out.println("Too big");
			x= in.nextInt();
		}
		int[] arrayBozo = new int[x];

		
		randomiseArray(arrayBozo);
		viewArray(arrayBozo);
		
		analysis(arrayInsertion,arraySelection,arrayBozo);
		
		/*System.out.print("Insertion: "+ printArray(arrayInsertion));
		
		System.out.print("Merge: "+	printArray(arraySelection));
	
		System.out.print("Enhance: " +printArray(arrayBozo));*/
		
		replay = replay();
		}
		
	}

	/*
	 * Gives user option to view array or not 
	 */
	public static boolean viewArray(int array[]) {
		System.out.println("Do you want to view array(y/n)?");
		Scanner in = new Scanner(System.in);
		String s = in.next();
		switch(s) {
		case "y":
			System.out.println(printArray(array));
		case "n":
			return false;
		default:
			System.out.println("Please try again");
		}
		return false;
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

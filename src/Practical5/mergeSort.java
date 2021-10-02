package Practical5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class mergeSort {
	public static final int CUTOFF = 10;
	
	/*
	 * Used by both Enhanced and normal mergeSort to combine 2 auxillary arrays in sorted order
	 */
	public static void merge(int a[],int[] leftArray,int[] rightArray,int mid, int hi) {
		
		int i = 0,j = 0,k = 0;
		
		while(i < mid && j < hi) {
			//increments based on largest value in each subarray
			if(leftArray[i] <= rightArray[j]) {
				a[k++] = leftArray[i++];
			}
			else {
				a[k++] = rightArray[j++];
			}
		}

		//Leftovers on both sides 
		while(i<mid) {a[k++] = leftArray[i++];}
		while(j<hi) {a[k++] = rightArray[j++];}
		
	}
	/*
	 * Splits array into two smaller arrays bounded by mid and hi
	 */
	public static void split(int leftArray[],int rightArray[],int array[],int mid,int hi) {
		
		//copying first half to left array
		for (int k = 0; k< mid ;k++) {
			leftArray[k] = array[k];
		}
		
		//second half to right array 
		for (int k = mid; k<hi ;k++) {
			rightArray[k-mid] = array[k];
		}
		return;
	}
	
	
	public static void MergeSort(int[] array,int hi) {
		
		if(hi < 2) {return;}
		
		//find the midpoint
		int mid = hi/2; 
		
		//defining two arrays which will be filled by the main array
		int[] leftArray = new int[mid];
		int[] rightArray = new int[hi-mid];
		
		split(leftArray,rightArray,array,mid,hi);
		
		MergeSort(leftArray,mid);
		MergeSort(rightArray,hi-mid);
		
		//combine the two sorted arrays back into the main array 
		merge(array,leftArray,rightArray,mid,hi-mid);
		
	
	}
	
	
	public static void EnhancedMergeSort(int[] array,  int lo,int hi) {
		//cutoff For insertionSort
		if(array.length <= CUTOFF) {
			insertionSort(array,0,array.length);
		}
		else {
		if(hi < 2) {return;}
		
		int mid = hi/2; 
		
		int[] leftArray = new int[mid];
		int[] rightArray = new int[hi-mid];
		
		//splits array between two smaller arrays
		split(leftArray,rightArray,array,mid,hi);
		
		//calls for mergesort to split arrays once again
		MergeSort(leftArray,mid);
		MergeSort(rightArray,hi-mid);
		
		//if the rightMost value of leftArray is less than the leftMost of RightArray -> no need for merge
		if(leftArray[mid-1] > rightArray[0]) {
		merge(array,leftArray,rightArray,mid,hi-mid);
		}
		}
	}
	
public static int[] insertionSort(int array[],int lo,int hi) {
		
		for(int i = lo; i<hi;i++) {

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
	
	public static void analysis(int[] MergeSortArr,int[] EnhancedMergeArr,int[] insertionArr) {
		double time1,time2,time3;
		
		System.out.println("Running analysis please wait:");
		Stopwatch insertion = new Stopwatch();
		//insertionSort(insertionArr,0,insertionArr.length);
		time1 = insertion.elapsedTime();
		
    	Stopwatch QuickSort = new Stopwatch();
    	MergeSort(MergeSortArr,MergeSortArr.length);
		time2 = QuickSort.elapsedTime();
    
		Stopwatch Enhanced = new Stopwatch();
		EnhancedMergeSort(EnhancedMergeArr,0,MergeSortArr.length);
		time3 = Enhanced.elapsedTime();
		
		System.out.format("%20s%20s%20s%30s\n", "Array Size", "InsertionSort Time","MergeSort Time","Enhanced MergeSort Time");
		System.out.format("%20d%20f%20f%30f\n", insertionArr.length, time1/1000000000,time2/1000000000,time3/1000000000);
		
		/*System.out.print("Insertion: "+ printArray(insertionArr));
		
		System.out.print("Merge: "+	printArray(MergeSortArr));
	
		System.out.print("Enhance: " +printArray(EnhancedMergeArr));*/
		

	}
	
	public static String printArray(int array[]) {
		StringBuilder builder = new StringBuilder();
		int x=0;
		for(;x <array.length-1;x++) {
			builder.append(array[x]+",");
		}
		
		builder.append(array[x]);
		return builder.toString();
	}
	
	public static void randomiseArray(int[] array) {

		Random rand = new Random();		
			for(int y= 0;y < array.length;y++) {
				int randNum = rand.nextInt(1000);
				array[y] = randNum;
			}
		}
	
	
	public static void main(String args[]) {
		System.out.println("Program to analyse performance of MergeSort vs Enhanced MergeSort");
		
		boolean replay = true;
		while(replay) {
		System.out.print("Enter the number of ints to be sorted(Will be randomised):");
		Scanner in = new Scanner(System.in);
		
		int x= in.nextInt();
		int[] arrayMergeSort = new int[x];
		int[] arrayEnhancedMerge= new int[x];
		int[] arrayInsertion = new int[x];
		//make copy of this array so the test is fair
		randomiseArray(arrayInsertion);
		arrayMergeSort = Arrays.copyOf(arrayInsertion,x);
		arrayEnhancedMerge = Arrays.copyOf(arrayInsertion, x);

		
		viewArray(arrayInsertion);
		analysis(arrayMergeSort,arrayEnhancedMerge,arrayInsertion);
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
package Practical6;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;
public class QuickSort {

	public static final int CUTOFF = 10;
	
		/*
		 * QuickSort Function wihtout optimisations
		 */
		public static void QuickSort(int array[]) {
			helpSort(array,0,array.length-1);
		}

		
		/*
		 * HelpSort is implemented so user doesnt have to worry about passing in values apart from array
		 */
		public static void helpSort(int array[],int start,int end) {
			if(start < end) {	
				
				/*
				 * Partition returns value piv where values to the right are greater and values to the left are less
				 */
				int piv = partition(array,start,end);
				
				//sort left
				helpSort(array,start,piv-1);
				//sort right
				helpSort(array,piv+1,end);
			}
		}

		
		

		
		public static int partition(int array[],int start, int end) {
			
			//arbitary number picked from front or end 
			int pivot = array[end];
			
			//pi holds values on left which are smaller than pivot
			int pi = start-1;
			//i holds values greater than pivot
			for(int i = start ; i <= end-1;i++) {
				if(array[i] <= pivot) {
					pi++;
					//since i is less than pivot we need to swap it to the front at pi
					swap(array,pi,i);
				}
			}
			
			//put pivot in the middle of the array
			swap(array,pi+1,end);
			return pi+1;
		}
		
	/*
	 * Enhanced QuickSort implements a shuffle first 
	 * As well as choosing the median of the first middle and last elements as the partition	
	 * Also implements insertionSort for small arrays
	 */
	public static void EnhancedQuickSort(int array[]) {
		shuffleAlternate(array);
		EnhancedHelpSort(array,0,array.length-1);
	}

	public static void EnhancedHelpSort(int array[],int start,int end) {
		if(start < end) {
			if(end-start <= CUTOFF) {
				insertionSort(array,start,end+1);
			}
			else {
				
			int piv = EnhancedPartition(array,start,end);
				
			EnhancedHelpSort(array,start,piv-1);
		
			EnhancedHelpSort(array,piv+1,end);
			}
		}

	}	
	
	public static int EnhancedPartition(int array[],int start, int end) {
		int pivot = medianOf3(array[start],array[end-1],array[(start+end)/2]);
		int pi = start-1;
		for(int i = start ; i <= end-1;i++) {
			if(array[i] < pivot) {
				pi++;
				swap(array,pi,i);
			}
		}
		swap(array,pi+1,end);
		return pi+1;
	}
	
	/*
	 * Finds the median value of 3 integers
	 */
	public static int  medianOf3(int one,int two, int three) {
		//alternatively use arrays.sort() and find array[1] for middle element
		if(one < two) {
			if(one > three) {
				return one;
			}
			else if(two > three) {
				return three;
			}
			else {
				return two;
			}
			
		}
		
		else {
			if(two  > three) {
				return two;
			}
			else if(one > three) {
				return three;
			}
			else{
				return one;
			}
		}
		
	}
	
	/*
	 * Shuffles array for enhanced quick sort
	 */
	   public static void shuffleAlternate(int[] a) {
	        int n = a.length;
	        for (int i = 0; i < n; i++) {
	            // choose index uniformly in [i, n-1]
	            int r = i + (int) (Math.random() * (n - i));
	            int swap = a[r];
	            a[r] = a[i];
	            a[i] = swap;
	        }
	    }
	   
	   
	   
	
	

	public static int[] insertionSort(int array[],int lo,int hi) {
		
		for(int i = lo+1; i<hi;i++) {
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
		
		int temp = array[pos];
		array[pos] = array[pos2];
		array[pos2]= temp;
		
		return;
	} 
	
	/*
	 * printArray returns string rather than prints-helps with jUnit tests
	 */
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
	
	/*
	 * Analyses insertion Sort,quickSort and ehancedQuickSort timing
	 */
	public static void analysis(int[] QuickSortArr,int[] EnhancedQuickArr,int[] insertionArr) {
		double time1,time2,time3;
		
		System.out.println("Running analysis please wait:");
		Stopwatch insertion = new Stopwatch();
		//insertionSort(insertionArr,0,insertionArr.length);
		time1 = insertion.elapsedTime();
		
    	Stopwatch QuickSort = new Stopwatch();
    	QuickSort(QuickSortArr);
		time2 = QuickSort.elapsedTime();
    
		Stopwatch Enhanced = new Stopwatch();
		EnhancedQuickSort(EnhancedQuickArr);
		time3 = Enhanced.elapsedTime();
		
		System.out.format("%20s%30s%30s%30s\n", "Array Size", "InsertionSort Time","QuickSort Time","Enhanced QuickSort Time");
		System.out.format("%20d%30f%30f%30f\n", insertionArr.length, time1/1000000000,time2/1000000000,time3/1000000000);

	}
	
	public static void main(String[] args) {
		System.out.println("Program to analyse performance of Quicksort vs Enhanced Quicksort");
		
		boolean replay = true;
		while(replay) {
		System.out.print("Enter the number of ints to be sorted(Will be randomised):");
		Scanner in = new Scanner(System.in);
		
		int x= in.nextInt();
		int[] arrayQuickSort = new int[x];
		int[] arrayEnhancedQuick = new int[x];
		int[] arrayInsertion = new int[x];
		
		randomiseArray(arrayInsertion);
		
		//make copy of this array so the test is fair
		arrayQuickSort = Arrays.copyOf(arrayInsertion, x);
		arrayEnhancedQuick = Arrays.copyOf(arrayInsertion, x);

		viewArray(arrayInsertion);
		analysis(arrayQuickSort,arrayEnhancedQuick,arrayInsertion);
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

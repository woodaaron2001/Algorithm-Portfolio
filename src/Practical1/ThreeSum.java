package Practical1;
/******************************************************************************
 *  Compilation:  javac ThreeSumA.java
 *  Execution:    java ThreeSum input.txt
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 ******************************************************************************/
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;

public class ThreeSum {

    // Do not instantiate.
    private ThreeSum() { }
 


    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param  a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     *         such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int countA(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
        	//check first 
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                    	//System.out.println(a[i]+","+a[j]+","+a[k]);
                    	count++;
                    }
                }
            }
        }
        return count;
    } 

    
    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

  
    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int countB(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) {
                	count++;
                	//System.out.println(a[i]+","+a[j]+","+a[k]);
                }
            }
        }
        return count;
    } 
/**
     * Changed version so command line arguments arent used but user chooses a file from system
     * Done so we can compare the 2 versions and run the practical from main
     * 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)  { 
    	System.out.println("Program comparing 2 implementations of an algorith which finds triples adding to 0");
    	boolean replay = true;
    	while(replay) {
    		analysis();
    		replay = replay();
    	}

    } 
    
    /*
     * Analysis function which outputs times using systemFormat
     */
    public static void analysis() {
        double time1,time2;
        In in = null;
    	System.out.println("Please use the built in file explorer to find a file of integers");
    	
    	//file chooser opens up a pane so user can choose a file without command line
    	JFileChooser chooser = new JFileChooser();
    	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    	 in = new In(chooser.getSelectedFile());
    	}
    	
    	System.out.println("Running analysis Please wait:");
    	int[] a = in.readAllInts();
    	int[] b = Arrays.copyOf(a, a.length);
        Stopwatch timer1 = new Stopwatch();
        int count = countA(a);
        time1 = timer1.elapsedTime();
        
        Stopwatch timer2 = new Stopwatch();
        countB(b);
        time2 = timer2.elapsedTime();
        
        System.out.format("%10s%13s%10s%10s\n","Num Ints","Num triples","Time sumA","Time sumB");
        System.out.format("%10d%13d%10f%10f\n",a.length,count,time1/1000000000,time2/1000000000);
    }
    
    
	/*
	 *Allows user to re enter and try another file
	 */
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
			return replay();
		}

	}
}



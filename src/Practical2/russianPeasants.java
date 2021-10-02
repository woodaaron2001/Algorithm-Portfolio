package Practical2;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;
public class russianPeasants {
	static BigInteger val0 = new BigInteger("0");
	static BigInteger val1 = new BigInteger("1");
	static BigInteger val2 = new BigInteger("2");
	//i just defined these here so I wouldnt have to use BigInteger.valueOf(constant) everywhere
	
	//note for both algorithms if we want to be able to times a negative by a negative or have a negative b
	//we can work out the final sign based on our input,get the absolute value of our numbers 
	//then times that final number by -1 or 1 depending on what final sign was 
	//however this wasnt implemented so it wouldnt interfere with the time taken to run the algorithms
	
	public static BigInteger peasantIt(BigInteger  a, BigInteger  b) {
	
		BigInteger res = new BigInteger("0"); 
		//b.signum checks that we are greater than 0 by checking sign of number
		while(b.signum() == 1) {
			
			if(b.mod(val2).compareTo(val1) == 0) {
				//if b is odd add a to res
				res = res.add(a);
			}
			
			//else multiply a by 2 and divide b by 2
			a = a.multiply(val2);
			b = b.divide(val2);
			
		}
		return res;
	}
	
	
	public static long peasantUser(long  a, long  b) {
		if(a == 0) {return 0;}
		if(a % 2 == 1) {return b + peasantUser(a/2,b*2);}
		else {return peasantUser(a/2,b*2);}
	}
	
	public static BigInteger peasantrecur(BigInteger a, BigInteger b) {
		if(val1.compareTo(a) == 0) return b;// base case when a is 0  
		if(a.mod(val2).compareTo(val1) == 0) {
			//if a is odd add the value onto the running value of func calls
			return b.add( peasantrecur(a.divide(val2),b.multiply(val2)));
		}
		return  peasantrecur(a.divide(val2),b.multiply(val2));//else call peasantrecur normally
	}

	//this class is used to get results for analysis
	public static void analysis1() {
		
		//define two bigIntegers 
		BigInteger one = new BigInteger("100000000000000000000000000");
        BigInteger two = new BigInteger("1000000000000000000000000000000000000");
        double avg = 0; // holds average time
        
        //since there can be alot of room for error i decided to get an average of the time 
        //i also changed stopwatch to System.nanoTime as it is more accurate
        for(int x = 0;x<100;x++) {
        Stopwatch BigIntTimer1 = new Stopwatch();
        BigInteger res =  peasantrecur(one,two);
        //BigInteger res = peasntIt(one,two);
        if(x != 1)
        avg = avg + (BigIntTimer1.elapsedTime()/1000000000); //divide by this to get from nanosec to sec
        }
        System.out.printf("elapsed time: %f\n", avg/50);
	}
	
	
	//this class prints out values and times for successive multiplications
	public static void analysis() {
		BigInteger one = new BigInteger("5");
        BigInteger two = new BigInteger("5");
        System.out.format("%10s%10s%20s%20s%20s", "a", "b","Result","Recursive","Iterative\n");
        
        for(int x = 1; x< 10; x++) {
        	one = one.multiply(BigInteger.valueOf(x));
        	two = two.multiply(BigInteger.valueOf(x));
        	Stopwatch BigIntTimer1 = new Stopwatch();
            BigInteger res =  peasantrecur(one,two);
            double time1 = BigIntTimer1.elapsedTime();
            
            
            Stopwatch BigIntTimer2 = new Stopwatch();
            peasantIt(one,two);
            double time2 = BigIntTimer2.elapsedTime();
            
            System.out.format("%10d%10d%20d%20f%20f\n",one, two,res,time1,time2);
            }
        }
	
	
	public static void main(String args[]) {
		System.out.println("~Russian peasants algorithm used to multiply 2 values~");
		boolean replay = true;
		while(replay) {
		chooseOption();
		replay = replay();
		}
	}
	
	/*
	 * User chooses analysis or multiplication 
	 */
	public static void chooseOption() {
		System.out.println("Program has 2 abilities press 0 and 1 for either");
		System.out.println("0: Runs analysis on iterative and recursive algorithm using BigInteger");
		System.out.println("1: Allows user to enter values and multiply 2 numbers");
		
		boolean error = true;
		
		Scanner in = new Scanner(System.in);
		while(error)
		try {
			int option = in.nextInt();
			switch(option) {
				case 0:
					System.out.println("Running analysis");
					analysis();
					error = false;
					break;
				
				case 1: 
					long a,b;
					System.out.println("Entering user version");
					System.out.println("Enter input 1");
					a = in.nextInt();
					System.out.println("Enter input 2");
					b = in.nextInt();
					System.out.println(a+" * "+b+" = "+peasantUser(a,b));
					error = false;
					break;
				
				default:
					System.out.println("Must enter either 0 or 1");
			}
			
		}
		catch(InputMismatchException e) {
			System.err.println("Input only integers");
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
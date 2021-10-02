package Practical3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Practical3Test {

	@Test
	void fibIterativeTest() {
		assertEquals(6765,fibonacci.fibIt(20));
	}
	
	void fibRecurTest() {
		assertEquals(6765,fibonacci.fibRec(20));
	}
	
	void fibMemoTest() {
		assertEquals(6765,fibonacci.fibMemo(20));
	}
	
	void hanoiTest() {
		
		String result = ("Move disk 1 from stack A to stack C\n"
				+ "Move disk 2 from stack A to stack B\n"
				+ "Move disk 1 from stack C to stack B\n");
		
		assertEquals(result,HanoiAnalysis.towersOfHanoi(2,'A','B','C'));
		
		
	}

}

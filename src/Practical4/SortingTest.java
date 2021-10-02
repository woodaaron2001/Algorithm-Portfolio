package Practical4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Practical5.mergeSort;

public class SortingTest {

	@Test 
	void selectionSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		Sorting.selectionSort(array);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(Sorting.printArray(result),Sorting.printArray(array));
		
	}
	
	@Test 
	void EnhancedBozoSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		Sorting.BozoSort(array);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(Sorting.printArray(result),Sorting.printArray(array));
	}
	
	@Test 
	void insertionSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		Sorting.insertionSort(array);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(Sorting.printArray(result),Sorting.printArray(array));
	}

}

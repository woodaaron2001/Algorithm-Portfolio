package Practical6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuickSortTest {

	@Test
	void PartitionTest(){
		
		int[] output = {3,4,112,5,6,2345,1,57,43};
		
		assertEquals(5,QuickSort.partition(output, 0, 8));
		}
	
	@Test 
	void QuickSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		QuickSort.QuickSort(array);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(QuickSort.printArray(result),QuickSort.printArray(array));
		
	}
	
	@Test 
	void EnhancedQuickSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		QuickSort.EnhancedQuickSort(array);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(QuickSort.printArray(result),QuickSort.printArray(array));
	}
	
	@Test 
	void insertionSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		QuickSort.insertionSort(array,0,array.length);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(QuickSort.printArray(result),QuickSort.printArray(array));
	}

}
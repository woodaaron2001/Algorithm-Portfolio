package Practical5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MergeSortTest {

	@Test
	void mergeTest(){
		
		int[] arrayL = {3,6,32,40};
		int[] arrayR = {5,8,45,60};
		int outPutArray[] ={0,0,0,0,0,0,0,0};
		mergeSort.merge(outPutArray,arrayL,arrayR, 4, 4);
		
		int[] result = {3,5,6,8,32,40,45,60};
		
		assertEquals(mergeSort.printArray(result),mergeSort.printArray(outPutArray));
		
		}
	
	@Test 
	void mergeSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		mergeSort.MergeSort(array, array.length);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(mergeSort.printArray(result),mergeSort.printArray(array));
		
	}
	
	@Test 
	void EnhancedMergeSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		mergeSort.EnhancedMergeSort(array,0,array.length);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(mergeSort.printArray(result),mergeSort.printArray(array));
	}
	
	@Test 
	void insertionSort(){	
		int[] array = {3,4,112,5,6,2345,1,57,43};
		mergeSort.insertionSort(array,0,array.length);
		
		int[] result = {1,3,4,5,6,43,57,112,2345};
		assertEquals(mergeSort.printArray(result),mergeSort.printArray(array));
	}

}

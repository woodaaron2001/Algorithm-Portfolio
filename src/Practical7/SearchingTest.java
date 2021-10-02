package Practical7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Practical4.Sorting;

public class SearchingTest {

	@Test
	void bruteForceTest() {
		String inputText = "Text that can be searched";
		String searchText = "search";
		
		assertEquals("Text found at positon: 17",Searching.bruteForce(inputText,searchText));
		
		inputText = "aabbaaccababacccaaabbcaaaabbcaad";
		searchText = "bbcaad";
		assertEquals("Text found at positon: 26",Searching.bruteForce(inputText,searchText));
	}
	
	@Test
	void kmpTest() {
		String inputText = "Text that can be searched";
		String searchText = "search";
		
		assertEquals("Text found at positon: 17",Searching.bruteForce(inputText,searchText));
		
		inputText = "aabbaaccababacccaaabbcaaaabbcaad";
		searchText = "bbcaad";
		assertEquals("Text found at positon: 26",Searching.bruteForce(inputText,searchText));
	}
	
	@Test
	void LpsArrayTest() {
		
		String searchText = "acacagt";
		int output[] = new int[7];
		int expected[] = {0,0,1,2,3,0,0};
		 Searching.suffixPrefixTable(searchText, 7, output);
		 
		 assertEquals(Sorting.printArray(expected),Sorting.printArray(output));
		
		 
		
	}

}

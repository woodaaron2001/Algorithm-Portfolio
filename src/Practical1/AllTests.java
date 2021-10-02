package Practical1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Practical2.russianPeasantsTest;
import Practical3.Practical3Test;
import Practical4.SortingTest;
import Practical5.MergeSortTest;
import Practical6.QuickSortTest;
import Practical7.SearchingTest;
import Practical8.TrieTest;
import Practical9.CompressionTest;

@RunWith(Suite.class)
@SuiteClasses({ThreeSumTest.class,russianPeasantsTest.class,Practical3Test.class,SortingTest.class,MergeSortTest.class,QuickSortTest.class,SearchingTest.class,TrieTest.class,CompressionTest.class})

public class AllTests {
	

}

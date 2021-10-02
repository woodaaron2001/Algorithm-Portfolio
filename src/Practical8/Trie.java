package Practical8;

import java.util.InputMismatchException;
import java.util.Scanner;
import Extras.StdIn;
import Extras.StdOut;
import Extras.Stopwatch;
import Extras.In;

	public class Trie{
	
	// Alphabet size (# of symbols) we pick 26 for English alphabet
	static final int ALPHABET_SIZE = 26; 
	
	
	// class for Trie node 
	static class TrieNode { 
	TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
	// isEndOfWord is true if the node represents end of a word i.e. leaf node
	boolean isEndOfWord; 
	
	TrieNode(){ 
	isEndOfWord = false; 
	
		for (int i = 0; i < ALPHABET_SIZE; i++) 
			children[i] = null; 
			}	
	}
	
	static TrieNode root;
	
	
	
	// If not key present, inserts into trie 
	// If the key is prefix of Trie node,  
	//  marks leaf node
	static void insert(String key){ 
	
	//start from the root
	TrieNode traversal = root;
	
	for(char c: key.toCharArray()) {
		//charToindex converts ascii value to an indexable position between 0 and 25
		int CharToIndex = c-'a';
		
		if(traversal.children[CharToIndex] == null) {
			//if we find that there isnt a child with the current letter
			traversal.children[CharToIndex] = new TrieNode();
			
		}
		
		//like in a binary tree the cursor is updated by letting it equal to its child
		traversal = traversal.children[CharToIndex];
	}
	
	//used for searching to mark a valid key
	traversal.isEndOfWord = true;
	} 
	
	// Returns true if key presents in trie, else false 
	static boolean search(String key) { 
		TrieNode traversal = root;
	
		
		for(char c: key.toCharArray()) {
			int CharToIndex = c-'a';
			
			//if there is no child equal then it is not contained in the trie
			if(traversal.children[CharToIndex] == null) {
				return false;
			}
			
			traversal = traversal.children[CharToIndex];
		}
		
		/*
		 * IF "filtered" is in the array and we search for filter it may be contained in the trie but is not valid
		 */
		if(traversal.isEndOfWord) {
			return true;
		}
		return false;
	} 
	
	
	// Driver 
	public static void main(String args[]) { 
	
	System.out.println("This program is designed to create and search tries:");
		 
	String output[] = {"Not present in trie", "Present in trie"}; 
	
	//user can create their own trie or use a predefined one
	String keys[] = CreateOwnKeys();
	root = new TrieNode(); 
	 
	for (int i = 0; i < keys.length ; i++) {
	insert(keys[i]); 
	}
	
	Scanner in = new Scanner(System.in);
	boolean replay= true;
	while(replay) {
		System.out.println("Entry String:(ONLY letter characters)");
		System.out.println(search(in.nextLine().toLowerCase())? output[1]: output[0]);
		replay = replay();
	}
	} 
	
	/*
	 * Explains program and parses input
	 */
	public static String[] CreateOwnKeys(){
		System.out.println("Press 0 to use a predefined trie of:");
		System.out.println("bank, book, bar, bring, film, filter, simple, silt, silver");
		System.out.println("Press 1 to insert 10 keys to create your own trie:");
		
		Scanner in = new Scanner(System.in);
		try {
			int option = in.nextInt();
			switch(option) {
				case 0:
					String[] preDefKeys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
					return preDefKeys;
				case 1: 
					
					System.out.println("Note String MUST only consist of letters(Case will be converted) or error building trie");
					String[] userKeys = new String[10];
					
					Scanner keys = new Scanner(System.in);
					for(int i = 0; i <10;i++) {
						System.out.print("Insert"+(i+1)+": ");
						userKeys[i] = keys.nextLine().toLowerCase();
					}
					System.out.println(userKeys[9]);
					return userKeys;
					
				default:
					System.out.println("Must enter either 0 or 1");
			}	
		}
		catch(InputMismatchException e) {
			System.err.println("Input only integers");
		}
		return null;
	}
	private static boolean replay() {
		System.out.println("Do you want to search again?(y/n)");
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
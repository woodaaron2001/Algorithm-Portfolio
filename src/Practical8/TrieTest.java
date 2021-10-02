package Practical8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Practical8.Trie.TrieNode;

public class TrieTest {

	@Test
	void insertTest() {
		String[] preDefKeys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
		
		Trie test = new Trie();
		
		test.root = new TrieNode(); 
		
		int i; 
		for (i = 0; i < preDefKeys.length ; i++) {
		Trie.insert(preDefKeys[i]); 
		}
		
	}
	
	@Test
	void searchTest() {
		String[] preDefKeys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
		
		Trie test = new Trie();
		
		test.root = new TrieNode(); 
		
		int i; 
		for (i = 0; i < preDefKeys.length ; i++) {
		Trie.insert(preDefKeys[i]); 
		}
		
		for (i = 0; i < preDefKeys.length ; i++) {
		assertEquals(true,Trie.search(preDefKeys[i])); 
		}
		
		assertEquals(false,Trie.search("notInTrie"));
		
		
	}

}

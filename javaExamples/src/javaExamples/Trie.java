package javaExamples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Trie {
	
	class TrieNode {
		private Map<Character,TrieNode> children;
		private String data;
		private boolean isEndOfWord;
		
		public TrieNode(String input) {
			children = new TreeMap<Character,TrieNode>();
			data = input;
			isEndOfWord = false;
		}

		public boolean isEndOfWord() {
			return isEndOfWord;
		}

		public void setEndOfWord(boolean isEndOfWord) {
			this.isEndOfWord = isEndOfWord;
		}

		public Map<Character,TrieNode> getChildren() {
			return children;
		}

		public String getData() {
			return data;
		}
	}
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode("");
	}
	
	public boolean find(String element) {
		TrieNode current = root;
		for (int i=0; i < element.length(); i++) {
			Character ch = element.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) return false;
			current = node;
		}
		return current.isEndOfWord();
	}
	
	public boolean insert(String element) {
		TrieNode current = root;
		for (int i=0; i < element.length(); i++) {
			Character ch = element.charAt(i);
			if (!current.getChildren().containsKey(ch))
				current.getChildren().put(ch, new TrieNode(ch.toString()));
			current = current.getChildren().get(ch);
		}
		current.setEndOfWord(true);
		return true;
	}
	
	public boolean delete(String element) {
		Stack<TrieNode> stack = new Stack<TrieNode>();
		TrieNode current = root;
		
		for (int i=0; i < element.length(); i++) {
			Character ch = element.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) return false;
			stack.push(node);
			current = node;
		}
		
		if (!current.isEndOfWord()) return false;
		
		current.setEndOfWord(false);
		
		while (stack.size() > 1) {
			TrieNode node = stack.pop();
			TrieNode prev = stack.peek();
			if (prev.getChildren().size() > 1)
				prev.getChildren().remove(node.getData().charAt(0));
			else break;
		}
		
		return true;
	}
	

	public List<String> allWordsStartingWith(String prefix) {
	  	 
	  /* creating a new empty list */
		ArrayList<String> words = new ArrayList<String>();
		if(prefix == null){
		  return words;
		}
		TrieNode current = root;
		for (int i=0; i < prefix.length(); i++) {
			Character ch = prefix.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) return words;
			current = node;
		}
		/* if we haven't returned by now, then node is the last char of the prefix */

		if(current.getChildren().size() > 0){
			ArrayList<String> newList = new ArrayList<String>();
			newList = getAllChildren(prefix, current);
			words.addAll(newList);
		}
    
		return words;
	}
	
	private ArrayList<String> getAllChildren(String stringUntilNode, TrieNode node){
	  ArrayList<String> words = new ArrayList<String>();
	  if(node.isEndOfWord() == true){
	    words.add(stringUntilNode);
	  }
	  for(TrieNode child : node.getChildren().values()) {
	      String currentString = stringUntilNode + child.data;
	      ArrayList<String> newList = new ArrayList<String>();
	      newList = getAllChildren(currentString, child);
	      words.addAll(newList);
	  }
	  return words;
	}
	
	/* main class to demonstrate usage of the class */
	public static void main(String[] args) {
		
		/* create a new Trie */
		Trie dict = new Trie();
		/* add elements */
		dict.insert("cap");
		dict.insert("cape");
		dict.insert("caper");
		dict.insert("car");
		dict.insert("carry");
		dict.insert("care");
		dict.insert("carp");
		dict.insert("cart");
		dict.insert("cat");
		dict.insert("cater");
		dict.insert("dog");
		dict.insert("dearly");
		dict.insert("dealt");
		dict.insert("dean");
		
		List<String> list = dict.allWordsStartingWith("car");
		
		Collections.sort(list);
		
		/* the list should print [car, care, carp, carry, cart] */
		System.out.println(list);

	}
}
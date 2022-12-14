package com.leetcode.ykechan.word_search_ii;

import java.util.Collections;
import java.util.List;

public class Solution {
	
	public List<String> findWords(char[][] board, String[] words) {
        return Collections.emptyList();
    }
	
	protected char[] toArray(char[][] board) {
		int nRow = board.length;
		int nCol = board[0].length;
		
		int len = nRow * nCol;
		char[] array = new char[len];
		for(int i = 0; i < len; i += nCol) {
			char[] row = board[i / nCol];
			System.arraycopy(row, 0, array, i, nCol);
		}
		return array;
	}
	
	protected Node buildTrie(String[] words) {
		Node root = new Node('^');
		for(String w : words) {
			root = this.add(root, w, 0);
		}
		return root;
	}
	
	protected Node add(Node node, String word, int begin) {
		char ch = begin >= word.length() ? '\0' : word.charAt(begin);
		int i = ch == '\0' ? 27 : ch - 'a';
		
		Node next = node.children[i];
		if(next == null){
			next = new Node(ch);
			node.children[i] = next;
		}
		return node;
	}
	
	protected static class Node {
		
		public final char ch;
		
		public final Node[] children;

		public Node(char ch) {
			this.ch = ch;
			this.children = ch == '\0' ? null : new Node[27];
		}
		
	}
	
	protected static class State {
		
		public final char[] board;
		
		public final int pos;

		public State(char[] board, int pos) {
			this.board = board;
			this.pos = pos;
		}
		
	}

}

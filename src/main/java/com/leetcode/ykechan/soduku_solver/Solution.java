package com.leetcode.ykechan.soduku_solver;

import java.util.Arrays;

class Solution {
	
    public void solveSudoku(char[][] board) {
		int[] array = this.toArray(board);
		int[] ans = this.solveSudoku(array, 0);
				
		
		int len = 9;
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				int k = i * len + j;
				if(board[i][j] == '.') {
					char ch = (char) (ans[k] + '0');
					board[i][j] = ch;
				}
			}
		}
	}
	
	public int[] solveSudoku(int[] array, int at) {
		if(at >= array.length) {
			return array;
		}
		
		if(array[at] > 0) {
			return this.solveSudoku(array, at + 1);
		}
				
		for(int n = 1; n < 10; n++) {
			array[at] = n;
			if(!this.isValid(array)){
				continue;
			}
			
			int[] temp = Arrays.copyOf(array, array.length);
			int[] ans = this.solveSudoku(temp, at + 1);
			if(ans != null) {
				return ans;
			}
		}

		return null;
	}
	
	public int[] toArray(char[][] board) {
		int len = 9;
		int[] array = new int[len * len];
		for(int i = 0; i < board.length; i++) {
			char[] row = board[i];
			for(int j = 0; j < row.length; j++) {
				char ch = row[j];
				int k = i * len + j;
				array[k] = ch == '.' ? 0 : (ch - '0');
			}
		}
		return array;
	}
	
	public boolean isValid(int[] array) {
		int len = 9;
		for(int i = 0; i < len; i++) {
			int done = 0;
			for(int j = 0; j < len; j++) {
				int k = i * len + j;
				if(array[k] < 1) {
					continue;
				}
				
				int mask = 1 << (array[k] - 1);
				if((done & mask) > 0) {
					return false;
				}
				
				done |= mask;
			}
		}
		
		for(int j = 0; j < len; j++) {
			int done = 0;
			for(int i = 0; i < len; i++) {
				int k = i * len + j;
				if(array[k] < 1) {
					continue;
				}
				
				int mask = 1 << (array[k] - 1);
				if((done & mask) > 0) {
					return false;
				}
				
				done |= mask;
			}
		}
		
		for(int n = 0; n < len; n++) {
			int x = 3 * (n % 3);
			int y = 3 * (n / 3);
			
			int done = 0;
			for(int k = 0; k < len; k++) {
				int i = y + k / 3;
				int j = x + k % 3;
				
				int digit = array[i * len + j];
				if(digit < 1) {
					continue;
				}
				
				int mask = 1 << (digit - 1);
				if((done & mask) > 0) {
					return false;
				}
				
				done |= mask;
			}			
		}
		return true;
	}
}

package com.leetcode.ykechan.n_queen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	
    public List<List<String>> solveNQueens(int n) {		
		return this.solve(n).stream().map(this::toBoard).collect(Collectors.toList());
	}
	
	protected List<int[]> solve(int n){
		List<int[]> ans = new ArrayList<>();
		
		int[] queens = new int[n];
		Arrays.fill(queens, -1);
		
		int depth = 0;
		while(depth >= 0 || queens[0] < n) {
			int curr = queens[depth];
			if(++curr >= n) {
				queens[depth] = -1;
				if(--depth < 0) {
					break;
				}
				continue;
			}
			
			queens[depth] = curr;
			boolean valid = true;
			for(int i = 0; i < depth; i++) {
				int pos = queens[i];
				int dist = depth - i;
				
				if(curr - pos == dist || pos - curr == dist || pos == curr) {
					valid = false;					
					break;
				}
			}
			
			if(!valid) {
				//System.out.println(Arrays.toString(queens) + " is invalid.");
				for(int i = depth + 1; i < queens.length; i++) {
					queens[i] = -1;
				}
				continue;
			}
			
			if(depth == n - 1) {
				// found a solution
				ans.add(Arrays.copyOf(queens, n));
				if(depth-- < 1){
                    break;
                }
				continue;
			}
			
			//System.out.println("Checking " + Arrays.toString(queens) + " => " + depth);
			depth++;
		}
		return ans;
	}
	
	protected List<String> toBoard(int[] queens) {		
		int n = queens.length;
		String[] rows = new String[n];
		for(int i = 0; i < queens.length; i++) {
			int q = queens[i];
			
			StringBuilder buf = new StringBuilder();
			for(int j = 0; j < n; j++) {
				buf.append(j == q ? 'Q' : '.');
			}
			rows[i] = buf.toString();
		}
		return Arrays.asList(rows);
	}
}

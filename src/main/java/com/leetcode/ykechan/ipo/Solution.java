package com.leetcode.ykechan.ipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
	
	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		int len = profits.length;
		List<Project> projs = new ArrayList<>();
		for(int i = 0; i < len; i++) {
			Project p = new Project(profits[i], capital[i]);
			projs.add(p);
		}
		
		Collections.sort(projs, Comparator.comparingInt(a -> a.capital));
		
		PriorityQueue<Project> queue = new PriorityQueue<>(Comparator.comparingInt(a -> -a.profit));
		
		int curr = 0;
		while(k > 0 && curr < len){
			Project p = projs.get(curr);
			if(p.capital <= w) {
				queue.add(p);
				curr++;
				continue;
			}
			
			if(queue.isEmpty()) {
				break;
			}
			
			Project target = queue.poll();
			w += target.profit;
			k--;
		}
		
		for(int i = 0; i < k && !queue.isEmpty(); i++){
			Project p = queue.poll();
			if(p.capital <= w) {
				w += p.profit;				
			}
		}
		return w;
	}
	
	
	protected static class Project {
		
		public final int profit;
		
		public final int capital;

		public Project(int profit, int capital) {
			this.profit = profit;
			this.capital = capital;
		}
		
	}

}

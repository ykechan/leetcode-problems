package com.leetcode.ykechan.mindev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	
	public int minimumDeviation(int[] nums) {
		SortedMap<Integer, Integer> map = new TreeMap<>();
		for(int num : nums) {
			map.put(num, num);
		}
		
		while(map.lastKey() % 2 == 0) {
			int key = map.lastKey();
			int val = map.get(key);
			
			map.remove(key);
			map.put(key / 2, val);
		}
		
		//System.out.println("max=" + map.lastKey());
		int minDev = map.lastKey() - map.firstKey();
		while(true) {
			int min = map.firstKey();
			int val = map.get(min);
			
			//System.out.println("min=" + min);
			
			int max = map.lastKey();			
			
			if(max - min < minDev) {
				minDev = max - min;
			}
			
			//System.out.println(map);
			if(min % 2 > 0 || val > min) {
				map.remove(min);
				map.put(2 * min, val);
			}else {
				break;
			}
			
			max = map.lastKey();
			min = map.firstKey();
			
			//System.out.println("[" + min + "," + max + "] in " + map);
			
			if(max - min < minDev) {
				minDev = max - min;
			}
		}
		return minDev;
	}
	
}

package com.leetcode.tomhz.course_schedule_II;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Solution {
	Set<Integer> set = new HashSet<>();
	Boolean cycleLoop = false;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> dep = genDepends(prerequisites);
		List<Integer> sortList = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			if (dep.getOrDefault(i, Collections.emptyList()).isEmpty()) {
				if (!sortList.contains(i)) {
					sortList.add(i);
				}
				continue;
			}

			if (!sortList.contains(i)) {
				findSubDeps(numCourses, sortList, i, dep);
			}
		}
		if (cycleLoop) {
			sortList.clear();
		}
		return sortList.stream().mapToInt(i -> i).toArray();
	}

	void findSubDeps(int numCourses, List<Integer> sortList, int num, Map<Integer, List<Integer>> dep) {
		if (set.contains(num) || cycleLoop) {
			// detect cycle
			cycleLoop = true;
			return;
		}

		List<Integer> deps = dep.getOrDefault(num, Collections.emptyList());
		for (int reqNum : deps) {

			if (!sortList.contains(reqNum)) {

				set.add(num);
				findSubDeps(numCourses, sortList, reqNum, dep);
				set.remove(num);
			}
		}
		sortList.add(num);
	}

	Map<Integer, List<Integer>> genDepends(int[][] p) {
		Map<Integer, List<Integer>> rs = new HashMap<>();
		for (int i = 0; i < p.length; i++) {
			if (rs.get(p[i][0]) == null) {
				List<Integer> tmp = new ArrayList<Integer>();
				tmp.add(p[i][1]);
				rs.put(p[i][0], tmp);
			} else {
				rs.get(p[i][0]).add(p[i][1]);
			}
		}
		return rs;
	}
}

package com.leetcode.ykechan.shortest_path_obstacles_elimination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	
	private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void shouldBeAbleToPassExample1() {
		int[][] grid = new int[][] {
			new int[] {0, 0, 0},
			new int[] {1, 1, 0},
			new int[] {0, 0, 0},
			new int[] {0, 1, 1},
			new int[] {0, 0, 0}
		};
		
		int ans = this.sol.shortestPath(grid, 1);
		System.out.println(ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] grid = new int[][] {
			new int[] {0, 1, 1},
			new int[] {1, 1, 1},
			new int[] {1, 0, 0}
		};
		
		int ans = this.sol.shortestPath(grid, 1);
		System.out.println(ans);
	}

}

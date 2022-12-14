package com.leetcode.ykechan.making_a_large_island;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leetcode.ykechan.making_a_large_island.Solution.DisjointSet;

public class SolutionTest {
	
	private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void test() {
		System.out.println(BigDecimal.ZERO.equals(null));
	}
	
	@Test
	public void shouldBeAbleToFindRoot() {
		DisjointSet set = new DisjointSet(10);
		
		set.union(0, 1);
		set.union(2, 3);
		set.union(4, 5);
		set.union(6, 7);
		set.union(8, 9);
		
		set.union(3, 5);
		
		Assertions.assertEquals(set.find(0), set.find(1));
		Assertions.assertEquals(set.find(2), set.find(3));
		Assertions.assertEquals(set.find(4), set.find(5));
		Assertions.assertEquals(set.find(6), set.find(7));
		Assertions.assertEquals(set.find(8), set.find(9));
		
		Assertions.assertEquals(set.find(3), set.find(5));
		Assertions.assertEquals(set.find(2), set.find(5));
		Assertions.assertEquals(set.find(4), set.find(3));
		
		Assertions.assertNotEquals(set.find(1), set.find(2));
		Assertions.assertNotEquals(set.find(5), set.find(6));
	}
	
	@Test
	public void shouldBeAbleToPassExample1() {
		int[][] grid = new int[][] {
			new int[] {1, 0},
			new int[] {0, 1}
		};
		
		int ans = this.sol.largestIsland(grid);
		Assertions.assertEquals(3, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] grid = new int[][] {
			new int[] {1, 1},
			new int[] {1, 0}
		};
		
		int ans = this.sol.largestIsland(grid);
		Assertions.assertEquals(3, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		int[][] grid = new int[][] {
			new int[] {1, 1},
			new int[] {1, 1}
		};
		
		int ans = this.sol.largestIsland(grid);
		Assertions.assertEquals(4, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase55() {
		int[][] grid = new int[][] {
			new int[] {0, 0, 0, 0, 0, 0, 0},
			new int[] {0, 1, 1, 1, 1, 0, 0},
			new int[] {0, 1, 0, 0, 1, 0, 0},
			new int[] {1, 0, 1, 0, 1, 0, 0},
			new int[] {0, 1, 0, 0, 1, 0, 0},
			new int[] {0, 1, 0, 0, 1, 0, 0},
			new int[] {0, 1, 1, 1, 1, 0, 0},
		};
		
		int ans = this.sol.largestIsland(grid);
		Assertions.assertEquals(18, ans);
	}

}

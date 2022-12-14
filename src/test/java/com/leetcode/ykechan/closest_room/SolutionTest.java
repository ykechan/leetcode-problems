package com.leetcode.ykechan.closest_room;

import org.junit.jupiter.api.Assertions;
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
		int[][] rooms = new int[][] {new int[] {2, 2}, new int[] {1, 2}, new int[] {3, 2}};
		int[][] queries = new int[][] {new int[] {3, 1}, new int[] {3, 3}, new int[] {5, 2}};
		
		int[] ans = this.sol.closestRoom(rooms, queries);
		Assertions.assertArrayEquals(new int[] {3, -1, 3}, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] rooms = new int[][] {
			new int[] {1, 4}, 
			new int[] {2, 3}, 
			new int[] {3, 5},
			new int[] {4, 1},
			new int[] {5, 2}
		};
		int[][] queries = new int[][] {new int[] {2, 3}, new int[] {2, 4}, new int[] {2, 5}};
		
		int[] ans = this.sol.closestRoom(rooms, queries);
		Assertions.assertArrayEquals(new int[] {2, 1, 3}, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase2() {
		int[][] rooms = new int[][] {
			new int[] {23, 22},
			new int[] {6, 20},
			new int[] {15, 6},
			new int[] {22, 19},
			new int[] {2, 10},
			new int[] {21, 4},
			new int[] {10, 18},
			new int[] {16, 1},
			new int[] {12, 7},
			new int[] {5, 22}
		};
		
		int[][] queries = new int[][] {
			new int[] {12, 5},
			new int[] {15, 15},
			new int[] {21, 6},
			new int[] {15, 1},
			new int[] {23, 4},
			new int[] {15, 11},
			new int[] {1, 24},
			new int[] {3, 19},
			new int[] {25, 8},
			new int[] {18, 6}
		};
		
		int[] ans = this.sol.closestRoom(rooms, queries);
		Assertions.assertArrayEquals(new int[] {12, 10, 22, 15, 23, 10, -1, 5, 23, 15}, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase10() {
		int[][] rooms = new int[][] {
			new int[] {7, 14},
			new int[] {11, 6},
			new int[] {3, 1},
			new int[] {9, 4},
			new int[] {14, 14},
			new int[] {17, 11},
			new int[] {22, 13},
			new int[] {6, 25},
			new int[] {12, 22},
			new int[] {21, 9}
		};
		
		int[][] queries = new int[][] {
			new int[] {21, 17},
			new int[] {4, 6},
			new int[] {17, 25},
			new int[] {15, 18},
			new int[] {17, 16},
			new int[] {18, 16},
			new int[] {8, 17},
			new int[] {6, 7},
			new int[] {9, 22},
			new int[] {17, 18}
		};
		
		int[] ans = this.sol.closestRoom(rooms, queries);
		Assertions.assertArrayEquals(new int[] {12, 6, 6, 12, 12, 12, 6, 6, 6, 12}, ans);
	}

}

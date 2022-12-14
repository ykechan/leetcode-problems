package com.leetcode.tomhz.map_of_highest_peak;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

public class SolutionTest {

private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void shouldBeAbleToComputeExample1() {
		Assertions.assertArrayEquals(new int[][] {
			new int[] {1, 0},
			new int[] {2, 1}
		}
			, this.sol.highestPeak(new int[][] {
			new int[] {0, 1},
			new int[] {0, 0}
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample2() {
		Assertions.assertArrayEquals(new int[][] {
			new int[] {1,1,0},
			new int[] {0,1,1},
			new int[] {1,2,2}
		}
			, this.sol.highestPeak(new int[][] {
			new int[] {0,0,1},
			new int[] {1,0,0},
			new int[] {0,0,0}
		}));
	}

}

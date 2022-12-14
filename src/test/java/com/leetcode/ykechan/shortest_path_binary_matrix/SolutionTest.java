package com.leetcode.ykechan.shortest_path_binary_matrix;

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
		Assertions.assertEquals(2, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0, 1},
			new int[] {1, 0}
		}));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(4, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0, 0, 0},
			new int[] {1, 1, 0},
			new int[] {1, 1, 0}
		}));
	}

}

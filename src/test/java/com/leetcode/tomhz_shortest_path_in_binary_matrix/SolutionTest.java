package com.leetcode.tomhz_shortest_path_in_binary_matrix;

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
		Assertions.assertEquals(2, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0, 1},
			new int[] {1, 0}
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample2() {
		Assertions.assertEquals(4, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,0,0},
			new int[] {1,1,0},
			new int[] {1,1,0}
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample3() {
		Assertions.assertEquals(-1, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {1,0,0},
			new int[] {1,1,0},
			new int[] {1,1,0}
		}));
	}

	@Test
	public void shouldBeAbleToComputeExample4() {
		Assertions.assertEquals(-1, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,0,0},
			new int[] {1,1,0},
			new int[] {1,1,1}
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample5() {
		Assertions.assertEquals(1, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0}
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample6() {
		Assertions.assertEquals(8, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,0,1,1,0,0},
			new int[] {0,0,0,0,1,1},
			new int[] {1,0,1,1,0,0},
			new int[] {0,0,1,1,0,0},
			new int[] {0,0,0,0,0,0},
			new int[] {0,0,1,0,0,0},
		}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample7() {
		Assertions.assertEquals(14, this.sol.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,1,0,0,0,0},
			new int[] {0,1,0,1,1,0},
			new int[] {0,1,1,0,1,0},
			new int[] {0,0,0,0,1,0},
			new int[] {1,1,1,1,1,0},
			new int[] {1,1,1,1,1,0},
		}));
	}
}

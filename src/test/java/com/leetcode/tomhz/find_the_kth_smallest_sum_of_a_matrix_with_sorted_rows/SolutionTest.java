package com.leetcode.tomhz.find_the_kth_smallest_sum_of_a_matrix_with_sorted_rows;

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
		Assertions.assertEquals(7, this.sol.kthSmallest(new int[][] {{1,3,11},{2,4,6}},5));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(17, this.sol.kthSmallest(new int[][] {{1,3,11},{2,4,6}},9));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(9, this.sol.kthSmallest(new int[][] {{1,10,10},{1,4,5},{2,3,6}},7));
	}
}
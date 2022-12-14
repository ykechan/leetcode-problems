package com.leetcode.tomhz.course_schedule_II;

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
	public void shouldBeAbleToPassTestCase() {
    	Assertions.assertArrayEquals(new int[] {6,5,4,2,3,0,1}, this.sol.findOrder(7, new int[][] {{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}}));
	}

	@Test
	public void shouldBeAbleToPassExample1() {
		Assertions.assertArrayEquals(new int[] {0,1}, this.sol.findOrder(2, new int[][] {{1,0}}));
	}
	
	@Test //cycleLoop
	public void shouldBeAbleToPassExample2() {
		Assertions.assertArrayEquals(new int[] {}, this.sol.findOrder(1, new int[][] {{1,0},{0,1}}));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase3() {
		Assertions.assertArrayEquals(new int[] {0,1,2,3}, this.sol.findOrder(4, new int[][] {{1,0},{2,0},{3,1},{3,2}}));
	}
}

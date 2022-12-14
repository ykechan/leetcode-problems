package com.leetcode.ykechan.min_cost_to_connect_2_groups_of_points;

import java.util.Arrays;
import java.util.List;

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
		List<List<Integer>> cost = Arrays.asList(
			Arrays.asList(15, 96),
			Arrays.asList(36, 2)
		);
		
		Assertions.assertEquals(17, this.sol.connectTwoGroups(cost));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		List<List<Integer>> cost = Arrays.asList(
			Arrays.asList(1, 3, 5),
			Arrays.asList(4, 1, 1),
			Arrays.asList(1, 5, 3)
		);
		
		Assertions.assertEquals(4, this.sol.connectTwoGroups(cost));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		List<List<Integer>> cost = Arrays.asList(
			Arrays.asList(2, 5, 1),
			Arrays.asList(3, 4, 7),
			Arrays.asList(8, 1, 2),
			Arrays.asList(6, 2, 4),
			Arrays.asList(3, 8, 8)
		);
		
		Assertions.assertEquals(10, this.sol.connectTwoGroups(cost));
	}

}

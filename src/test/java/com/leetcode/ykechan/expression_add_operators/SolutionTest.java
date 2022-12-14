package com.leetcode.ykechan.expression_add_operators;

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
		List<String> ans = this.sol.addOperators("123", 6);
		Assertions.assertEquals(2, ans.size());
		Assertions.assertEquals("1*2*3", ans.get(0));
		Assertions.assertEquals("1+2+3", ans.get(1));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		List<String> ans = this.sol.addOperators("232", 8);
		Assertions.assertEquals(2, ans.size());
		Assertions.assertEquals("2*3+2", ans.get(0));
		Assertions.assertEquals("2+3*2", ans.get(1));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		List<String> ans = this.sol.addOperators("3456237490", 9191);
		Assertions.assertTrue(ans.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToPassTestCase1() {
		List<String> ans = this.sol.addOperators("105", 5);
		System.out.println(ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase2() {
		List<String> ans = this.sol.addOperators("00", 0);
		System.out.println(ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase3() {
		List<String> ans = this.sol.addOperators("010", 0);
		System.out.println(ans);
	}


}

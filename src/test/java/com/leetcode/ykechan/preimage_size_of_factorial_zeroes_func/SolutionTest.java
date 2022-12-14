package com.leetcode.ykechan.preimage_size_of_factorial_zeroes_func;

import java.math.BigInteger;

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
		Assertions.assertEquals(5, this.sol.preimageSizeFZF(0));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(0, this.sol.preimageSizeFZF(5));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(5, this.sol.preimageSizeFZF(3));
	}
	
	@Test
	public void shouldBeAbleToPassExample4() {
		Assertions.assertEquals(5, this.sol.preimageSizeFZF(10));
	}
	
	@Test
	public void shouldBeAbleToPassExample5() {
		Assertions.assertEquals(0, this.sol.preimageSizeFZF(36));
	}
	
	@Test
	public void shouldBeAbleToPassKEquals21To30() {
		int[] ans = new int[] {
			5, 5, 0, 5, 5, 5, 5, 5, 0, 0
		};
		for(int i = 0; i < ans.length; i++) {
			Assertions.assertEquals(ans[i], this.sol.preimageSizeFZF(21 + i));
		}
	}
	
	@Test
	public void shouldBeAbleToPassKEquals128To132() {
		int[] ans = new int[] {5, 0, 5, 5, 5};
		for(int i = 0; i < ans.length; i++) {
			Assertions.assertEquals(ans[i], this.sol.preimageSizeFZF(128 + i));
		}
	}
	
	@Test
	public void shouldBeAbleToPassKEquals1024To1028() {
		int[] ans = new int[] {5, 5, 5, 5, 0};
		for(int i = 0; i < ans.length; i++) {
			Assertions.assertEquals(ans[i], this.sol.preimageSizeFZF(1024 + i));
		}
	}

}

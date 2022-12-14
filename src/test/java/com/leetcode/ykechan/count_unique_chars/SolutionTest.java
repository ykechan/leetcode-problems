package com.leetcode.ykechan.count_unique_chars;

import java.util.Random;

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
	public void shouldBeAbleToComputeABC() {
		Assertions.assertEquals(10, this.sol.uniqueLetterString("ABC"));
	}
	
	@Test
	public void shouldBeAbleToComputeABA() {
		Assertions.assertEquals(8, this.sol.uniqueLetterString("ABA"));
	}
	
	@Test
	public void shouldBeAbleToComputeLeet() {
		Assertions.assertEquals(12, this.sol.bruteForce("LEET"));
		Assertions.assertEquals(12, this.sol.uniqueLetterString("LEET"));
	}
	
	@Test
	public void shouldBeAbleToComputeLeetCode() {
		Assertions.assertEquals(92, this.sol.uniqueLetterString("LEETCODE"));
	}
	
	@Test
	public void shouldBeAbleToComputeAllSameCase() {
		int ans = this.sol.bruteForce("AAAAA");
		Assertions.assertEquals(ans, this.sol.uniqueLetterString("AAAAA"));
	}
	
	@Test
	public void shouldBeAbleToPassRandom() {
		int limit = 1024;
		int len = 64;
		
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		for(int n = 0; n < limit; n++) {
			StringBuilder buf = new StringBuilder();
			rand.ints(len).map(Math::abs).map(i -> i % upper.length())
				.mapToObj(i -> upper.charAt(i))
				.forEach(buf::append);
			
			String str = buf.toString();
			int ans = this.sol.bruteForce(str);			
			Assertions.assertEquals(ans, this.sol.uniqueLetterString(str), str);
			System.out.println("Pass #" + n);
		}
	}

}

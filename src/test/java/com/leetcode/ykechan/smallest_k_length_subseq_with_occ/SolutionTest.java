package com.leetcode.ykechan.smallest_k_length_subseq_with_occ;

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
		String ans = this.sol.smallestSubsequence("leet", 3, 'e', 1);
		System.out.println(ans);
		Assertions.assertEquals("eet", ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		String ans = this.sol.smallestSubsequence("leetcode", 4, 'e', 2);
		System.out.println(ans);
		Assertions.assertEquals("ecde", ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		String ans = this.sol.smallestSubsequence("bb", 2, 'b', 2);
		System.out.println(ans);
		Assertions.assertEquals("bb", ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase106() {
		String ans = this.sol.smallestSubsequence("aaabbbcccddd", 3, 'b', 2);
		System.out.println(ans);
		Assertions.assertEquals("abb", ans);
	}

}



package com.leetcode.ykechan.string_compression_ii;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leetcode.ykechan.string_compression_ii.Solution.Tuple;

public class SolutionTest {
	
	private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void shouldBeAbleToPassExample1AndGetEncoding() {
		Map<Tuple, int[]> mem = new TreeMap<>();
		String s = "aaabcccd";
		int[] tokens = this.sol.optimalCompression(0, s, 2 * (1 + s.length()), mem);
		String ans = this.sol.encode(tokens);
		Assertions.assertEquals("a3c3", ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2AndGetEncoding() {
		Map<Tuple, int[]> mem = new TreeMap<>();
		String s = "aabbaa";
		int[] tokens = this.sol.optimalCompression(0, s, 2 * (1 + s.length()), mem);
		String ans = this.sol.encode(tokens);
		Assertions.assertEquals("a4", ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample3AndGetEncoding() {
		Map<Tuple, int[]> mem = new TreeMap<>();
		String s = "aaaaaaaaaaa";
		int[] tokens = this.sol.optimalCompression(0, s, 0 * (1 + s.length()), mem);
		String ans = this.sol.encode(tokens);
		Assertions.assertEquals("a11", ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample1AndGetAnswer() {
		Assertions.assertEquals(4, this.sol.getLengthOfOptimalCompression("aaabcccd", 2));
	}
	
	@Test
	public void shouldBeAbleToPassExample2AndGetAnswer() {
		Assertions.assertEquals(2, this.sol.getLengthOfOptimalCompression("aabbaa", 2));
	}
	
	@Test
	public void shouldBeAbleToPassExample3AndGetAnswer() {
		Assertions.assertEquals(3, this.sol.getLengthOfOptimalCompression("aaaaaaaaaaa", 0));
	}
}

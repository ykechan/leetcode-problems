package com.leetcode.ykechan.longest_substr_without_repeating_chars;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		Assertions.assertEquals(3, this.sol.lengthOfLongestSubstring("abcabcbb"));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(1, this.sol.lengthOfLongestSubstring("bbbbb"));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(3, this.sol.lengthOfLongestSubstring("pwwkew"));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int limit = 256;
		int len = 32;
		Random rand = new Random();
		for(int i = 0; i < limit; i++){
			String str = IntStream.range(0, len)
				.map(j -> rand.nextInt(26))
				.mapToObj(ch -> "" + (char) ('a' + ch))
				.collect(Collectors.joining());
			Assertions.assertEquals(
				this.sol.bruteForce(str), 
				this.sol.lengthOfLongestSubstring(str),
				str);
			System.out.println("Pass #" + i);
		}
	}

}

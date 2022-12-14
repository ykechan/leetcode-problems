package com.leetcode.ykechan.substring_with_largest_variance;

import java.util.Random;
import java.util.stream.Collectors;

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
		//System.out.println(this.sol.largestVariance("aababbb", 'b', 'a'));
		Assertions.assertEquals(3, this.sol.largestVariance("aababbb"));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		//System.out.println(this.sol.largestVariance("aababbb", 'b', 'a'));
		Assertions.assertEquals(0, this.sol.largestVariance("abcdef"));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase1() {
		String str = "qlpzglmzndisbtnc";
		Assertions.assertEquals(this.sol.bruteForce(str), this.sol.largestVariance(str));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase2() {
		Assertions.assertEquals(this.sol.bruteForce("futialacxoluiomz"), this.sol.largestVariance("futialacxoluiomz"));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase3() {
		Assertions.assertEquals(2, this.sol.largestVariance("oojxyoaxlsdmektp"));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase4() {
		String str = "xhehsyydhlkozogdcdalyjinneecpfbbenwgydzvnbfpuslkuynkoirnoxjwkupiamtndsnsyjsrzqqghkunqzmzjaxumtflhzaxgvmoyiubmzwvbnkydkwkxrzxdvom";
		Assertions.assertEquals(
			this.sol.bruteForce(str), 
			this.sol.largestVariance(str));
	}
	
	//kjtzqfbjexwydbwqxfflcyhowwagdwsevzsdssphivpuqrqupkbqadvsrvprmtoblxsfetrfdhgvjfioqyxfqoddeczemjlsojkzgsdpdgkqain
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int limit = 1024;
		int len = 256;
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		Random rand = new Random();
		for(int i = 0; i < limit; i++) {
			String str =rand.ints(len)
				.map(Math::abs).map(k -> k % alphabet.length())
				.mapToObj(k -> alphabet.subSequence(k, k + 1))
				.collect(Collectors.joining());
			
			Assertions.assertEquals(
				this.sol.bruteForce(str), 
				this.sol.largestVariance(str), str);
			System.out.println("Pass #" + i);
		}
	}


}

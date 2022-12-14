package com.leetcode.ykechan.partition_array_min_sum_diff;

import java.util.Arrays;
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
	public void shouldBeAbleToPassExample1() {
		Assertions.assertEquals(2, this.sol.minimumDifference(new int[] {3, 9, 7, 3}));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(72, this.sol.minimumDifference(new int[] {-36, 36}));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(0, this.sol.minimumDifference(new int[] {2, -1, 0, 4, -2, -9}));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase1() {
		int[] array = {99, -96, 55, 1, 52, 17, 7, -36};
		/*
		Assertions.assertEquals(
			this.sol.bruteForce(array), 
			this.sol.minimumDifference(array),
			Arrays.toString(array));
			*/
		System.out.println(this.sol.bruteForce(array));
		System.out.println(this.sol.minimumDifference(array));
	}	
	
	@Test
	public void shouldBeAbleToPassRandomTestCase2() {
		int[] array = {-5, 71, 22, -60, -59, -23, -7, -83, -36, 50, -29, 14, 29, 38, -46, -4, 53, -76, 54, 98};
		System.out.println(this.sol.bruteForce(array));
		System.out.println(this.sol.minimumDifference(array));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase3() {
		int[] array = {-79, -61, 14, -45, -50, 76, -33, -84, -15, -67, -50, 76, -27, 12, 33, 8, 63, -88, 15, 11};
		System.out.println(this.sol.bruteForce(array));
		System.out.println(this.sol.minimumDifference(array));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase1() {
		int[] array = {
			7772197,4460211,-7641449,-8856364,546755,
			-3673029,527497,-9392076,3130315,-5309187,
			-4781283,5919119,3093450,1132720,6380128,
			-3954678,-1651499,-7944388,-3056827,1610628,
			7711173,6595873,302974,7656726,-2572679,
			0,2121026,-5743797,-8897395,-9699694};
		System.out.println(this.sol.minimumDifference(array));
		//System.out.println(this.sol.bruteForce(array));
	}
		
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int limit = 1024;
		int len = 10;
		
		Random rand = new Random();
		for(int i = 0; i < limit; i++){
			int[] array = rand.ints(2 * len).map(k -> k % 100).toArray();
			Assertions.assertEquals(
				this.sol.bruteForce(array), 
				this.sol.minimumDifference(array),
				Arrays.toString(array));
			
			System.out.println("Pass #" + i);
		}
	}

}

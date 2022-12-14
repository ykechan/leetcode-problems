package com.leetcode.ykechan.subarray_with_elem_gt_varying_thres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
		int ans = this.sol.validSubarraySize(new int[] {1, 3, 4, 3, 1}, 6);
		System.out.println(ans);
		Assertions.assertEquals(3, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int ans = this.sol.validSubarraySize(new int[] {6, 5, 6, 5, 8}, 7);
		System.out.println(ans);
		Assertions.assertEquals(1, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		int ans = this.sol.validSubarraySize(new int[] {6, 5, 6, 5, 1}, 7);
		System.out.println(ans);
		Assertions.assertEquals(2, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase60() {
		
		int[] nums = {
			559,192,584,80,
			243,532,787,825,465,980,372,575,424,166,359,798,
			35,
			28,540,363,577,378,613,760,758,816,37,265,196,403,
			83,944,77,761,822,878,838,719,465,469,
			310,820,918,140,714,84,833,831,271,227,
			294,798,148,300,108,614,418,487,752,309,
			29,434,110,126,504,751,837,40,953,31,987,
			852,723,89,436,857,447,424,88,735,71
		};
		/*
		int[] nums = {
				559,192,584,80,
				243,532,787,825,465,980,372,575,424,166,359,798,
				35};
		*/
		int ans = this.sol.validSubarraySize(nums, 2117);
		System.out.println(ans);
		Assertions.assertEquals(3, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase39() {
		
		int[] nums = {818, 232, 595, 418, 608, 229, 37, 330, 876, 774, 931, 939, 479, 884, 354, 328};
		/*
		int[] nums = {
				559,192,584,80,
				243,532,787,825,465,980,372,575,424,166,359,798,
				35};
		*/
		int ans = this.sol.validSubarraySize(nums, 3790);
		System.out.println(ans);
		Assertions.assertEquals(-1, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase48() throws IOException {
		try(InputStream in = this.getClass()
				.getResourceAsStream("/com/leetcode/ykechan/subarray_with_elem_varying_thres/Test Case 48.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
			String line0 = reader.readLine();
			String line1 = reader.readLine();
			
			String[] tokens = line0.replace("[", "").replace("]", "").split(",");
			int[] nums = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
			int thres = Integer.parseInt(line1);
			
			int ans = this.sol.validSubarraySize(nums, thres);
			System.out.println(ans);
			
			int oracle = this.sol.bruteForce(nums, thres);
			System.out.println(oracle);
			
			Assertions.assertEquals(18, ans);
		}
	}
	@Test
	public void shouldBeAbleToPassTestCase68() throws IOException {
		try(InputStream in = this.getClass()
				.getResourceAsStream("/com/leetcode/ykechan/subarray_with_elem_varying_thres/Test Case 68.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
			String line0 = reader.readLine();
			String line1 = reader.readLine();
			
			String[] tokens = line0.replace("[", "").replace("]", "").split(",");
			int[] nums = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
			int thres = Integer.parseInt(line1);
			
			int ans = this.sol.validSubarraySize(nums, thres);
			System.out.println(ans);
			
		}
	}

}

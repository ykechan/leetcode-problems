package com.leetcode.ykechan.second_min_time_to_reach_dest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		int[][] edges = new int[][] {
			new int[] {1, 2},
			new int[] {1, 3},
			new int[] {1, 4},
			new int[] {3, 4},
			new int[] {4, 5}
		};
		
		int ans = this.sol.secondMinimum(5, edges, 3, 5);
		Assertions.assertEquals(13, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] edges = new int[][] {new int[] {1, 2}};
		int ans = this.sol.secondMinimum(2, edges, 3, 2);
		Assertions.assertEquals(11, ans);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase4() throws IOException {
		try(InputStream in = this.getClass().getResourceAsStream("/com/leetcode/ykechan/second_min_time_to_reach_dest/2045-test-case-4.txt")){
			int[][] edges = this.read(in);
			int ans = this.sol.secondMinimum(520, edges, 946, 183);
			Assertions.assertEquals(3142, ans);
		}
	}
	
	@Test
	public void shouldBeAbleToPassTestCase5() throws IOException {
		try(InputStream in = this.getClass().getResourceAsStream("/com/leetcode/ykechan/second_min_time_to_reach_dest/2045-test-case-5.txt")){
			int[][] edges = this.read(in);
			int ans = this.sol.secondMinimum(10000, edges, 1000, 1000);
			Assertions.assertEquals(15000, ans);
		}
	}
	
	protected int[][] read(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		List<int[]> edges = new ArrayList<>();
		
		String line = null;
		while((line = reader.readLine()) != null){
			if(line.trim().isEmpty()){
				continue;
			}
			
			String str = line.trim();
			int pos = str.indexOf(',');
			if(pos < 0){
				throw new UnsupportedOperationException();
			}
			
			int u = Integer.parseInt(str.substring(0, pos));
			int v = Integer.parseInt(str.substring(pos + 1));
			
			edges.add(new int[] {u, v});
		}
		return edges.toArray(new int[0][]);
	}

}

package com.leetcode.ykechan.build_a_matrix_with_conditions;

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
		int[][] matrix = this.sol.buildMatrix(3, 
			new int[][] {new int[] {1, 2}, new int[] {3, 2}}, 
			new int[][] {new int[] {2, 1}, new int[] {3, 2}});
		
		for(int[] row : matrix){
			System.out.println(Arrays.toString(row));
		}
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] matrix = this.sol.buildMatrix(3, 
			new int[][] {new int[] {1, 2}, new int[] {2, 3}, new int[] {3, 1}, new int[] {2, 3}}, 
			new int[][] {new int[] {2, 1}});
		
		Assertions.assertEquals(0, matrix.length);
	}
	
	@Test
	public void shouldBeAbleToPassTestCase3() {
		int[][] matrix = this.sol.buildMatrix(8, 
			new int[][] {
				new int[] {1, 2}, new int[] {7, 3}, new int[] {4, 3}, new int[] {5, 8},
				new int[] {7, 8}, new int[] {8, 2}, new int[] {5, 8}, new int[] {3, 2},
				new int[] {1, 3}, new int[] {7, 6}, new int[] {4, 3}, new int[] {7, 4},
				new int[] {4, 8}, new int[] {7, 3}, new int[] {7, 5}
			}, 
			new int[][] {
				new int[] {5, 7}, new int[] {2, 7}, new int[] {4, 3}, new int[] {6, 7},
				new int[] {4, 3}, new int[] {2, 3}, new int[] {6, 2},
			});
		
		for(int[] row : matrix){
			System.out.println(Arrays.toString(row));
		}
	}

}

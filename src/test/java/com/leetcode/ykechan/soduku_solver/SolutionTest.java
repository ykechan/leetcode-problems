package com.leetcode.ykechan.soduku_solver;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	
private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void shouldBeAbleToSolveExample() {
		char[][] board = new char[][] {
			"53..7....".toCharArray(),
			"6..195...".toCharArray(),
			".98....6.".toCharArray(),
			"8...6...3".toCharArray(),
			"4..8.3..1".toCharArray(),
			"7...2...6".toCharArray(),
			".6....28.".toCharArray(),
			"...419..5".toCharArray(),
			"....8..79".toCharArray()
		};
		this.sol.solveSudoku(board);
		Assert.assertEquals("534678912", new String(board[0]));
		Assert.assertEquals("672195348", new String(board[1]));
		Assert.assertEquals("198342567", new String(board[2]));
		Assert.assertEquals("859761423", new String(board[3]));
		Assert.assertEquals("426853791", new String(board[4]));
		Assert.assertEquals("713924856", new String(board[5]));
		Assert.assertEquals("961537284", new String(board[6]));
		Assert.assertEquals("287419635", new String(board[7]));
		Assert.assertEquals("345286179", new String(board[8]));
	}

}

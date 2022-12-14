package com.leetcode.tomhz.game_of_life;

public class Solution {
	public void gameOfLife(int[][] board) {
		int[][] dp = new int[board.length][board[0].length];
		for(int x = 0;x < dp.length;x++) {
			for(int y = 0;y < dp[x].length;y++) {
				dp[x][y] = board[x][y];
			}
		}
		for(int x = 0;x < dp.length;x++) {
			for(int y = 0;y < dp[x].length;y++) {
				switch(getLiveCell(dp,x,y)) {
					case 0:
					case 1:
						board[x][y] = 0;
						break;
					case 2:
						board[x][y] = dp[x][y];
						break;
					case 3:
						board[x][y] = 1;
						break;
					case 4: case 5: case 6: case 7: case 8:
						board[x][y] = 0;
						break;
				}	
			}
		}
    }
	
	public int getLiveCell(int[][] board,int x,int y) {
		int count = 0;
		for(int startX = x - 1;startX <= x + 1;startX++) {
			for(int startY = y - 1;startY <= y + 1;startY++) {
				if(startX < 0 || startY < 0) {
					continue;
				}
				if(startX > (board.length - 1) || startY > (board[startX].length - 1)) {
					continue;
				}
				count += board[startX][startY];
			}
		}
		count = count - board[x][y];
		return count;
	}
}

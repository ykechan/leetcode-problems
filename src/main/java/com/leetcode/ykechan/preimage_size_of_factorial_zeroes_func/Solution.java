package com.leetcode.ykechan.preimage_size_of_factorial_zeroes_func;

public class Solution {
	
	public int preimageSizeFZF(int k) {
		if(k < 0){
			return 0;
		}
		
		if(k < 3) {
			return 5;
		}
		
		long upper = this.argmaxFx(k);
		if(this.trailZero(upper) != k){
			return 0;
		}
		
		long lower = this.argmaxFx(k - 1);
		return (int) (upper - lower);
	}
	
	public long argmaxFx(int k) {
		long lower = 0;
		long upper = this.upperBound(k);
		
		while(upper - lower > 1){
			long mid = (lower + upper) / 2;
			long fx = this.trailZero(mid);
			
			if(fx > k) {
				upper = mid;
			}else{
				lower = mid;
			}
		}
		
		return lower;
	}
	
	public long upperBound(int k) {
		if(k < 4){
			return 20;
		}
		// 1, 1, 2, 3, 5, 8, 13, 21
		long step = 13;
		long num = 21;
		
		while(this.trailZero(num) <= k) {
			long temp = num;
			num += step;
			step = temp;
		}
		return num;
	}	
	
	public long trailZero(long x) {
		return Math.min(this.factorsOf(x, 2), this.factorsOf(x, 5));
	}
	
	public long factorsOf(long x, int n) {
		if(x < n){
			return 0;
		}
		
		long k = x / n;
		return k + this.factorsOf(k, n);
	}

}

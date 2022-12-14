package com.leetcode.ykechan.tree_of_coprimes;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	
	@Test
	public void listAllPrimes() {
		for(int i = 2; i < 50; i++) {
			if(i % 2 == 0) {
				continue;
			}
			
			boolean isPrime = true;
			for(int j = 2; j < i; j++) {
				if(j * j > i) {
					continue;
				}
				
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				System.out.println(i);
			}
		}
	}

}

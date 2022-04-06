package com.leetcode.ykechan.remove_k_digits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	
	private Solution solution;
	
	@BeforeEach
	public void init() {
		this.solution = new Solution();
	}
	
	@Test
	public void shouldBeAbleToPassExample1() {
		Assertions.assertEquals("1219", this.solution.removeKdigits("1432219", 3));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals("200", this.solution.removeKdigits("10200", 1));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals("0", this.solution.removeKdigits("10", 2));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase3() {
		Assertions.assertEquals("0", this.solution.removeKdigits("10", 1));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase26() {
		Assertions.assertEquals("11", this.solution.removeKdigits("112", 1));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase27() {
		Assertions.assertEquals("260469", this.solution.removeKdigits("52660469", 2));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase34() throws IOException {
		try(InputStream input = this.getClass().getResourceAsStream("/com/leetcode/ykechan/remove_k_digits/test-case-34.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
			
			String num = reader.readLine().replace("\"", "");
			int k = Integer.parseInt(reader.readLine());
			String ans = reader.readLine().replace("\"", "");
			
			Assertions.assertEquals(ans, this.solution.removeKdigits(num, k));
		}
	}		

}

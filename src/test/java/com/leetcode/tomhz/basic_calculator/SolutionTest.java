package com.leetcode.tomhz.basic_calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	
private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}

    @Test
	public void shouldBeAbleToPassTestCase() {
		Assertions.assertEquals(23, this.sol.calculate("(3+(+5+ -2) - -3)+(6+ - -8)"));
	}

	@Test
	public void shouldBeAbleToPassExample1() {
		Assertions.assertEquals(-3, this.sol.calculate("-2 - + 1"));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(-1, this.sol.calculate("-2+ 1"));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(3, this.sol.calculate("2+1"));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase4() {
		Assertions.assertEquals(2147483647, this.sol.calculate("2147483647"));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase5() {
		Assertions.assertEquals(2, this.sol.calculate("1 + 1"));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase6() {
		Assertions.assertEquals(9, this.sol.calculate("(1+(4+ +5+ -2) - -3)+(6+ -8)"));
	}

}

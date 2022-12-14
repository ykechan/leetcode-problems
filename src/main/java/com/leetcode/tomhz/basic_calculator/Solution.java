package com.leetcode.tomhz.basic_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//LeetCode 224 only + -
		public int calculate(String s) {
			Deque<String> stack = new ArrayDeque<>();
			Parser parser = Parser.START;
			
			for(int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				Parser next = parser.jump(stack, ch);
				System.out.println("ch=" + ch + ":" + parser + " => " + next);
				
				if(next == Parser.FAILED) {
					throw new IllegalArgumentException("Invalid expression " + s);
				}
				
				parser = next;
			}
			
			if(parser.jump(stack, '\0') != Parser.ACCEPT || stack.size() != 1) {
				throw new IllegalArgumentException("Invalid expression " + s);
			}
			
			return Integer.parseInt(stack.pop());
	    }
		
		public enum Parser {
			
			START {

				@Override
				public Parser jump(Deque<String> stack, char ch) {
					switch(ch) {
						case '\0':
							return stack.isEmpty() ? FAILED : ACCEPT;
					
						case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
							stack.push(String.valueOf(ch));
							return stack.size() > 0 || stack.peek() != "" ? PENDING : ACCEPT;

						case ' ':
							return START;
						case '(':
							stack.push(String.valueOf(ch));
							return START;
							
						case '+':
							stack.push(String.valueOf(ch));
							return START;
						case '-':
							stack.push(String.valueOf(ch));
							return START;
						default:
							break;
					}
					return FAILED;
				}
				
			},
			
			PENDING {

				@Override
				public Parser jump(Deque<String> stack, char ch) {
					if(ch == '+' || ch == '-') {
						stack.push(String.valueOf(ch));
						return START;
					}
					if(ch == ' ') {
						return PENDING;
					}
					if(ch == ')') {
						if(stack.isEmpty() || stack.peek() == "") {
							return FAILED;
						}
						
						this.eval(stack);
						return stack.size() > 1 || stack.peek() != "" ? PENDING : ACCEPT;
					}
					if(ch == '\0') {
						stack.addLast("(");
						this.eval(stack);
						return stack.isEmpty() ? FAILED : ACCEPT;
					}
					if(Integer.parseInt(String.valueOf(ch)) >= 0 && Integer.parseInt(String.valueOf(ch)) < 10) {
						stack.push(stack.pop() + ch);
						return PENDING;
					}
					return FAILED;
				}
				
			},		
			
			ACCEPT {

				@Override
				public Parser jump(Deque<String> stack, char ch) {
					if(ch == '\0') {
						return this;
					}
					
					throw new UnsupportedOperationException("Parser has ended");
				}
				
			},
			
			FAILED {

				@Override
				public Parser jump(Deque<String> stack, char ch) {
					throw new UnsupportedOperationException("Parser has failed");
				}
				
			};
			
			public abstract Parser jump(Deque<String> stack, char ch);
			
			public void eval(Deque<String> stack) {
				if(stack.isEmpty()) {
					throw new UnsupportedOperationException("Unsupported operator " + stack);
				}
				int rs = 0;
				int pre = 0;
				while(!stack.peek().equals("(")) {
					String ch = stack.pop();
					//moreThanOne+-
					if(ch.equals("+")||ch.equals("-")) {
						while(stack.peek().equals("+")||stack.peek().equals("-")) {
							String next = stack.pop();
							if(ch.equals("-")&&next.equals("-")) {
								ch = "+";
							}else if(!ch.equals(next)){
								ch = "-";
							}
						}
					}
					switch(ch) {
					 case "+":
						 rs = rs + pre;
						 pre = 0;
						 break;
					 case "-":
						 rs = rs - pre;
						 pre = 0;
						 break;
					 default:
						 pre = Integer.parseInt(ch);
						 
						 break;
					}
				}
				if(stack.pop().equals("(") && pre != 0) {
					rs = rs + pre;	
				}
				stack.push(String.valueOf(rs));
			}
			
		}
		
		public static class State {
			
			public final char op;
			
			public final int result;

			public State(char op, int result) {
				this.op = op;
				this.result = result;
			}
			
		}
}

package com.leetcode.tomhz.tag_validator;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	public static void main(String[] args) throws InterruptedException, IOException {
		Solution s = new Solution();
		System.out.println("1.======" + s.isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));//true
		System.out.println("2.======" + s.isValid("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"));//true
		System.out.println("3.======" + s.isValid("<A>  <B> </A>   </B>"));//false
		System.out.println("4.======" + s.isValid("<A><B> abc</A> acb</B>"));//false
		System.out.println("5.======" + s.isValid("<A></A><B></B>"));//false
		System.out.println("6.======" + s.isValid("<B><A> abc</A> def</B>"));//true
		System.out.println("7.======" + s.isValid("<A>qwe</A><B>qwe</B>"));//false
		System.out.println("8.======" + s.isValid("<B><A></A></B>"));//true
		System.out.println("9.======" + s.isValid("<A><A></A>< /A>"));//false
		System.out.println("10.=====" + s.isValid("<A/><A/></A></A>"));//false
		System.out.println("11.=====" + s.isValid("</A></A></A></A>"));//false
		System.out.println("12.=====" + s.isValid("<A><![CDATA[</A>]]123></A>"));//false
		System.out.println("13.=====" + s.isValid("<TRUE><![CDATA[wahaha]]]><![CDATA[]> wahaha]]></TRUE>"));//true
		System.out.println("14.=====" + s.isValid("<A><A>456</A>  <A> 123  !!  <![CDATA[]]>  123 </A>   <A>123</A></A>"));//true
		System.out.println("14.=====" + s.isValid("<A><![CDATA[</A>]]>"));//false
		System.out.println("15.=====" + s.isValid("<A><![CDATA[</A>]]></A>"));//true
		System.out.println("16.=====" + s.isValid(">A<"));//false
		System.out.println("17.=====" + s.isValid("<DIV><></></DIV>"));//false
		System.out.println("18.=====" + s.isValid("<A></A>>"));//false
		System.out.println("19.=====" + s.isValid("<AAAAAAAAAA></AAAAAAAAAA>"));//false
		System.out.println("20.=====" + s.isValid("<DIV>This is the first line <![CDATA[<div> <![cdata]> [[]]</div>   ]]>  <DIV> <A>  <![CDATA[<b>]]>  </A>  <A> <C></C></A></DIV>    </DIV>"));//true
		
		

	}
	
	public static boolean inCdata;
	public boolean isValid(String code) {
		inCdata = false;
		Deque<Object> stack = new ArrayDeque<>();
		AtomsParser parser = AtomsParser.TAG;
		
		for(int i = 0; i < code.length(); i++) {
			char ch = code.charAt(i);
			AtomsParser next = parser.jump(stack, ch);
//			System.out.println("ch=" + ch + ":" + parser + " => " + next);
			
			if(next == AtomsParser.FAILED) {
				return false;
			}
			
			parser = next;
		}
		
		if(parser.jump(stack, '\0') != AtomsParser.ACCEPT || stack.size() != 1) {
			throw new IllegalArgumentException("Invalid expression " + code);
		}
		return (boolean) stack.pop();
    }
	
	public enum AtomsParser {
		TAG {
			@Override
			public AtomsParser jump(Deque<Object> stack, char ch) {
				if(ch == '\0') {
					this.eval(stack);
					return stack.isEmpty() ? FAILED : ACCEPT;
				}
				if(stack.isEmpty() && ch != '<') {
					return FAILED;
				}
				if(ch >= 'A' && ch <= 'Z') {
					State s = (State) stack.pop();
					s.result += String.valueOf(ch);
					stack.push(s);
					return TAG;
				}
				if(ch == '<') {
					stack.push(new State(OP.start,""));
					return TAG;
				}
				if(ch == '/') {
					State s = (State) stack.pop();
					//catch "<ABC/>" case
					if(!s.result.equals("")) {
						return FAILED;
					}
					s.op = OP.end;
					stack.push(s);
					return TAG;
				}
				if(ch == '>') {
					State s = (State) stack.peek();
					//check empty name tag
					if(s.result.equals("") || s.result.length() > 9) {
						return FAILED;
					}
					return PENDING;
				}
				return FAILED;
			}

		},
		
		//Value
		PENDING {
			String tagContent = "";
			@Override
			public AtomsParser jump(Deque<Object> stack, char ch) {
				String str = String.valueOf(ch);
				if(ch == '\0') {
					if(stack.peek() instanceof String || tagContent != "") {
						stack.clear();
						stack.push(false);
						return ACCEPT;
					}
					this.eval(stack);
					return stack.isEmpty() ? FAILED : ACCEPT;
				}
				if(str.equals(" ")) {
					return stack.peek().toString().equals("<") ? FAILED : PENDING;
				}
				if(stack.peek().toString().equals("<![CDATA[")) {
					stack.pop();
					tagContent = "";
					return CDATA.jump(stack, ch);
				}
				
				
				if(str.equals("<")) {
					stack.push(str);
					return PENDING;
				}
				if(stack.peek().toString().equals("<") && !str.equals("!")) {
//					if(tagContent == "" && str.equals("/")) {
//						stack.clear();
//						stack.push(false);
//						return FAILED;
//					}
					stack.pop();
					stack.push(new State(OP.start,""));
					tagContent = "";
					return TAG.jump(stack, ch);
				}
				if(stack.peek().toString().contains("<")) {
					stack.push(stack.pop() + str);
					if(!"<![CDATA[".contains(stack.peek().toString())) {
						return FAILED;
					}
					return PENDING;
				}
				tagContent += str;
				return PENDING;
			}

		},	
		
		CDATA {

			@Override
			public AtomsParser jump(Deque<Object> stack, char ch) {
				if(ch == '\0') {
					stack.clear();
					stack.push(false);
					return stack.isEmpty() ? FAILED : ACCEPT;
				}
				if(ch == ']' && inCdata == false){
					stack.push("");
					inCdata = true;
				}
				if(inCdata) {
					stack.push(stack.pop() + String.valueOf(ch));
					if(((String) stack.peek()).endsWith("]]>")) {
						stack.pop();
						inCdata = false;
						return PENDING.jump(stack, ch);
					}
//					if(ch == '<') {
//						inCdata = false;
//						if(!((String) stack.peek()).contains("]]>")) {
//							return FAILED;
//						}
//						stack.pop();
//						return PENDING.jump(stack, ch);
//					}
				}
				return CDATA;				
			}

		},

		ACCEPT {

			@Override
			public AtomsParser jump(Deque<Object> stack, char ch) {
				if (ch == '\0') {
					return this;
				}

				throw new UnsupportedOperationException("Parser has ended");
			}

		},

		FAILED {

			@Override
			public AtomsParser jump(Deque<Object> stack, char ch) {
				stack.clear();
				stack.push(false);
				return this;
//				throw new UnsupportedOperationException("Parser has failed");
			}

		};

		public abstract AtomsParser jump(Deque<Object> stack, char ch);
		
		public void eval(Deque<Object> stack) {
			if (stack.isEmpty()) {
				throw new UnsupportedOperationException("Unsupported operator " + stack);
			}
			Boolean rs = true;
			Deque<Object> preStack = new ArrayDeque<>();
			
			//check same tag name between start and end tag
			State first = (State)stack.peekFirst();
			State last = (State)stack.peekLast();
			if(!first.result.equals(last.result)) {
				rs = false;
				stack.clear();
			}
			
			while(!stack.isEmpty()) {
				if(!(stack.peek() instanceof State)) {
					rs = false;
					stack.clear();
					continue;
				}
				State s = (State)stack.pop();
				if(preStack.isEmpty() && s.op.equals(OP.start)) {
					rs = false;
					stack.clear();
					continue;
				}
				switch(s.op) {
				case start:
					if(preStack.isEmpty()) {
						rs = false;
						stack.clear();
                        continue;
					}
					State p = (State)preStack.pop();
					if(!s.result.equals(p.result)) {
						rs = false;
						stack.clear();
						continue;
					}
					break;
				case end:
					preStack.push(s);
					break;
				}
			}
			if(preStack.size() > 0 || stack.size() > 0) {
				rs = false;
			}
			stack.push(rs);
		}
	}
	
	public static class State {
		
		public OP op;
		
		public String result;
		
		public State() {}
		public State(OP op, String result) {
			this.op = op;
			this.result = result;
		}
		
	}
	
	public enum OP{start,end}
}



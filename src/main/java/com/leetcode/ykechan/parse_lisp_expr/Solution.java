package com.leetcode.ykechan.parse_lisp_expr;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	
	public int evaluate(String expression) {
		Context ctx = new Context();
		Parser parser = Parser.START;
		for(int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			Parser next = parser.jump(ctx, ch);
			
			//System.out.println("ch = " + ch + ": " + parser + " => " + next);
			
			if(next == Parser.FAIL){
				throw new IllegalArgumentException("Invalid expression " + expression + " near " + i);
			}
			parser = next;
		}
		
		Parser end = parser.jump(ctx, '\0');
		if(end != Parser.ACCEPT) {
			throw new IllegalArgumentException("Invalid expression " + expression + ": " + end);
		}
		
		return ctx.args.pop();
	}
	
	public enum Parser {
		START {

			@Override
			public Parser jump(Context ctx, char ch) {
				ctx.env = new ArrayDeque<>();
				ctx.env.push(Collections.emptyMap());
				
				ctx.args = new ArrayDeque<>();
				ctx.stack = new ArrayDeque<>();
				return BEGIN.jump(ctx, ch);
			}
			
		},
		
		BEGIN {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(Character.isWhitespace(ch)){
					return this;
				}
				
				if(ch == '-'){
					ctx.buf = new StringBuilder().append(ch);
					return NUM;
				}
				
				if(ch >= '0' && ch <= '9'){
					ctx.buf = new StringBuilder();
					return NUM.jump(ctx, ch);
				}
				
				if(ch >= 'a' && ch <= 'z'){
					ctx.buf = new StringBuilder();
					return VAR.jump(ctx, ch);
				}
				
				if(ch == '('){
					ctx.buf = new StringBuilder();
					return FUNC;
				}
				return FAIL;
			}
			
		},
		
		NUM {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(ch >= '0' && ch <= '9'){
					ctx.buf.append(ch);
					return this;
				}
				
				if((ch >= 'a' && ch <= 'z')
				|| (ch >= 'A' && ch <= 'Z')) {
					// a number cannot be delimited by a letter
					return FAIL;
				}
				
				try {
					int value = Integer.valueOf(ctx.buf.toString());
					ctx.args.push(value);
					return END.jump(ctx, ch);
				} finally {
					ctx.buf = null;
				}
			}
			
		},
		
		VAR {

			@Override
			public Parser jump(Context ctx, char ch) {
				if((ch >= 'a' && ch <= 'z')
				|| (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9')) {
					ctx.buf.append(ch);
					return this;
				}
				
				Map<String, Integer> vars = ctx.env.peek();
				Integer val = vars.get(ctx.buf.toString());
				
				try {
					if(val == null){
						return FAIL;
					}
					
					ctx.args.push(val);
					return END.jump(ctx, ch);
				} finally {
					ctx.buf = null;
				}
			}
			
		},
		
		FUNC {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(ch >= 'a' && ch <= 'z') {
					ctx.buf.append(ch);
					return this;
				}
				
				if(!Character.isWhitespace(ch)){
					// function name must be delimited by a whitespace
					return FAIL;
				}
				
				try {
					String func = ctx.buf.toString();
					int returnIndex = ctx.args.size();
					if("add".equals(func)){
						ctx.stack.push(new Invoke(Func.ADD, returnIndex));
						return BEGIN;
					}
					
					if("mult".equals(func)){
						ctx.stack.push(new Invoke(Func.MULT, returnIndex));
						return BEGIN;
					}
					
					if("let".equals(func)){
						Map<String, Integer> vars = ctx.env.peek();
						ctx.stack.push(new Invoke(Func.LET, returnIndex));
						ctx.env.push(this.copy(vars));
						return LET;
					}
					return FAIL;
				} finally {
					ctx.buf = null;
				}
			}
			
			protected <T> Map<String, T> copy(Map<String, T> map) {
				Map<String, T> clone = new TreeMap<>();
				for(Map.Entry<String, T> entry : map.entrySet()) {
					clone.put(entry.getKey(), entry.getValue());
				}
				return clone;
			}
			
		},
		
		LET {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(Character.isWhitespace(ch)){
					return this;
				}
				
				if(ch >= 'a' && ch <= 'z'){
					ctx.buf = new StringBuilder();
					return VARNAME.jump(ctx, ch);
				}
				
				return BEGIN.jump(ctx, ch);
			}
			
		},
		
		VARNAME {

			@Override
			public Parser jump(Context ctx, char ch) {
				if((ch >= 'a' && ch <= 'z')
				|| (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9')) {
					ctx.buf.append(ch);
					return this;
				}
								
				try {
					String var = ctx.buf.toString();
					int returnIndex = ctx.args.size();
					
					if(ch == ')'){
						// single variable expression
						Map<String, Integer> vars = ctx.env.peek();
						Integer val = vars.get(var);
						if(val == null) {
							return FAIL;
						}
						
						ctx.args.push(val);
						return CLOSE.jump(ctx, ch);
					}
					
					if(!Character.isWhitespace(ch)){
						// must be delimited by a whitespace
						return FAIL;
					}
					
					ctx.stack.push(new Invoke(Func.SET, returnIndex, var));
					return ASGN.jump(ctx, ch);
				} finally {
					ctx.buf = null;
				}
			}
			
		},
		
		ASGN {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(Character.isWhitespace(ch)){
					return this;
				}
				
				if(ch == ')'){
					// the var is the expression itself
					Map<String, Integer> vars = ctx.env.peek();
					String var = ctx.stack.pop().var;
					Integer val = vars.get(var);
					if(val == null){
						return FAIL;
					}
					
					ctx.args.push(val);
					return END.jump(ctx, ch);					
				}
								
				return BEGIN.jump(ctx, ch);
			}
			
		},
		
		END {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(ctx.stack.isEmpty()) {
					return ctx.args.size() == 1 ? PENDING.jump(ctx, ch) : FAIL;
				}
				
				if(Character.isWhitespace(ch)) {
					return this;
				}
				
				Invoke invoke = ctx.stack.peek();
				int numArgs = ctx.args.size() - invoke.returnIndex;
				//System.out.println("End: " + invoke.func);
				switch(invoke.func){
					case LET:
						ctx.env.pop();
						ctx.stack.pop();
						return END;
						
					case SET: {
							String var = invoke.var;
							int val = ctx.args.pop();
							Map<String, Integer> vars = ctx.env.peek();
							vars.put(var, val);
							ctx.stack.pop();
							
							if(ch >= 'a' && ch <= 'z') {
								ctx.buf = new StringBuilder();
								return VARNAME.jump(ctx, ch);
							}
							
							return BEGIN.jump(ctx, ch);
						}
				
					case ADD:
					case MULT:
						if(numArgs == 1){
							// must be delimited by a whitespace
							return BEGIN.jump(ctx, ch);
						}else if(numArgs == 2){
							return CLOSE.jump(ctx, ch);
						}
						return FAIL;
						
					default:
						break;
				}

				throw new UnsupportedOperationException("Invalid function " + invoke.func);
			}
			
		},
		
		CLOSE {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(Character.isWhitespace(ch)){
					return this;
				}
				
				if(ch != ')'){
					return FAIL;
				}
				
				if(ctx.stack.isEmpty()){
					throw new IllegalStateException();
				}
				
				Invoke inv = ctx.stack.pop();
				int numArgs = ctx.args.size() - inv.returnIndex;
				switch(inv.func) {
					case LET:
						if(numArgs != 1){
							throw new IllegalStateException("Number of arguments is " +  numArgs + ", expected 1");
						}
												
						ctx.env.pop();							
						break;
				
					case ADD:
						if(numArgs == 2){
							int arg1 = ctx.args.pop();
							int arg0 = ctx.args.pop();
							ctx.args.push(arg0 + arg1);
						}else{
							throw new IllegalStateException("Number of arguments is " +  numArgs + ", expected 2");
						}
						break;	
					
					case MULT:
						if(numArgs == 2){
							int arg1 = ctx.args.pop();
							int arg0 = ctx.args.pop();
							ctx.args.push(arg0 * arg1);
						}else {
							throw new IllegalStateException("Number of arguments is " +  numArgs + ", expected 2");
						}
						break;
						
					default:
						throw new UnsupportedOperationException("Un-supported function " + inv.func);
				}
				return END;
			}
			
		},
		
		PENDING {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(ch == '\0') {
					return ACCEPT;
				}
				
				if(Character.isWhitespace(ch)) {
					return this;
				}
				
				return FAIL;
			}
			
		},
		
		ACCEPT {

			@Override
			public Parser jump(Context ctx, char ch) {
				if(ch == '\0'){
					return this;
				}
				throw new UnsupportedOperationException("Parser has ended");
			}
			
		},
		
		FAIL {

			@Override
			public Parser jump(Context ctx, char ch) {
				throw new UnsupportedOperationException("Parser has failed");
			}
			
		};
		
		public abstract Parser jump(Context ctx, char ch);
		
	}
	
	public static class Context {
		
		public StringBuilder buf;
		
		public Deque<Map<String, Integer>> env;
		
		public Deque<Integer> args;
		
		public Deque<Invoke> stack;
		
	}
	
	public static class Invoke {
		
		public final Func func;
		
		public final int returnIndex;
		
		public final String var;

		public Invoke(Func func, int returnIndex) {
			this.func = func;
			this.returnIndex = returnIndex;
			this.var = null;
		}
		
		public Invoke(Func func, int returnIndex, String var) {
			this.func = func;
			this.returnIndex = returnIndex;
			this.var = var;
		}
		
	}
	
	public enum Func {
		LET, ADD, MULT, SET
	}

}

package com.leetcode.ykechan.tag_validator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	
    public boolean isValid(String code) {
		Context ctx = new Context();
		State state = State.START;	
		
		for(int i = 0; i < code.length(); i++) {
			char ch = code.charAt(i);
			State next = state.jump(ctx, ch);
			//System.out.println("ch=" + ch + ", " + state + " => " + next);
			
			if(next == State.FAIL) {
				return false;
			}
			
			state = next;
		}
        
        State next = state.jump(ctx, '\0');
        //System.out.println("ch=" + '$' + ", " + state + " => " + next);
		return next == State.ACCEPT;
	}
	
	public enum State {
		
		START {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '\0') {
					return FAIL;
				}
				
				ctx.buffer = null;
				ctx.stack = new ArrayDeque<>();
				return TAG_CONTENT.jump(ctx, ch);
			}
			
		},
		
		TAG_CONTENT {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '<') {
					ctx.buffer = new StringBuilder();
					return OPEN_TAG;
				}
				
				if(ch == '\0') {
					return ctx.stack.isEmpty() ? ACCEPT : FAIL;
				}
                
				return ctx.stack.isEmpty() ? FAIL : TAG_CONTENT;
			}
			
		}, 
		
		OPEN_TAG {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '>') {
					return FAIL;
				}
				
				if(ch == '/') {
					// is a close tag
					ctx.buffer = new StringBuilder();
					return CLOSING_TAG;
				}
				
				if(ch == '!') {
                    if(ctx.stack.isEmpty()){
                        return FAIL;
                    }
                    
					// is cdata
					ctx.buffer = new StringBuilder();
					return OPEN_CDATA;
				}
				
				ctx.buffer = new StringBuilder();
				return OPENING_TAG.jump(ctx, ch);
			}
			
		},
		
		OPENING_TAG {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '>') {
					// finish open tag
					String tag = ctx.buffer.toString();
					ctx.buffer = null;
					ctx.stack.push(tag);
					return TAG_CONTENT;
				}
				
				if(ch >= 'A' && ch <= 'Z') {
					ctx.buffer.append(ch);
                    
                    if(ctx.buffer.length() <= 9){
                        return this;
                    }
				}
				
				return FAIL;
			}
			
		},
		
		CLOSING_TAG {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '>') {
					return FAIL;
				}
				
				ctx.buffer = new StringBuilder();
				return CLOSE_TAG.jump(ctx, ch);
			}
			
		},
		
		CLOSE_TAG {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '>') {
					String tag = ctx.buffer.toString();
					ctx.buffer = null;
					
					String open = ctx.stack.isEmpty() ? null : ctx.stack.peek();
					if(tag.equals(open)) {
						ctx.stack.pop();
						return ctx.stack.isEmpty() ? END : TAG_CONTENT;
					}
				}
				
				if(ch >= 'A' && ch <= 'Z') {
					ctx.buffer.append(ch);
					return this;
				}
				
				return FAIL;
			}
			
		},
		
		OPEN_CDATA {

			@Override
			public State jump(Context ctx, char ch) {
				int len = ctx.buffer.length();
				if(len >= CDATA.length()) {
					String tag = ctx.buffer.toString();
					ctx.buffer = null;
					return CDATA.equals(tag) ? CDATA_CONTENT.jump(ctx, ch) : FAIL;
				}
				
				char expects = CDATA.charAt(len);
				if(ch != expects) {
					return FAIL;
				}
				
				ctx.buffer.append(ch);
				return this;
			}
			
			private static final String CDATA = "[CDATA[";
		},
		
		CDATA_CONTENT {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == ']') {
					return CLOSE_CDATA_0;
				}
				return this;
			}
			
		},
		
		CLOSE_CDATA_0 {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == ']') {
					return CLOSE_CDATA_1;
				}
				return CDATA_CONTENT;
			}
			
		},
		
		CLOSE_CDATA_1 {

			@Override
			public State jump(Context ctx, char ch) {
				if(ch == '>') {
					return TAG_CONTENT;
				}
				
				return CDATA_CONTENT;
			}
			
		},
        
        END {
            
            @Override
			public State jump(Context ctx, char ch) {
				return ch == '\0' ? ACCEPT : FAIL;
			}
            
        },
		
		ACCEPT {

			@Override
			public State jump(Context ctx, char ch) {
				throw new UnsupportedOperationException("Parser has accepted");
			}
			
		},
		
		FAIL {

			@Override
			public State jump(Context ctx, char ch) {
				throw new UnsupportedOperationException("Parsing failed");
			}
			
		};
		
		public abstract State jump(Context ctx, char ch);
		
	}
	
	public static class Context {
		
		public StringBuilder buffer;
		
		public Deque<String> stack;
		
	}
}

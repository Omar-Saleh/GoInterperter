import java.lang.System;
import java.io.*;
import java.util.*;
class Lexer {
 Yylex tokenizer;
 public  Lexer(String fileName) 
 {
   try
   {
   tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
   }
   catch(Exception e)
   {
   }  
 }
 public Token nextToken()
 {
  Token next=null;
  try
  {
   next=  tokenizer.getToken();
  }
  catch(Exception e)
  {
  }
  return next;
 }
 }


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

 //initialize  variables to be used by class
 String eofMessage = "DONE";
 int import_open_paran;
 Stack<Character> checker;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

//Add code to be executed on initialization of the lexer
checker = new Stack<Character>();
	}

	private boolean yy_eof_done = false;
	private final int IMPORT = 2;
	private final int YYINITIAL = 0;
	private final int PACKAGE = 1;
	private final int yy_state_dtrans[] = {
		0,
		45,
		48
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NOT_ACCEPT,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"28:9,24,29,28:2,0,28:18,23,28,30,28:4,27,19,20,28:4,15,16,26,25:9,13,28:2,1" +
"4,28:3,31:26,28:4,32,28,7,31,8,31,11,12,10,31,1,31,9,31,2,18,4,3,31,5,31,6," +
"17,31:5,21,28,22,28:2,33:2")[0];

	private int yy_rmap[] = unpackFromString(1,64,
"0,1,2,3:9,4,3:3,5,6:4,7,3:4,8,3:6,9,5,10,5,3,11,9,12,11,13,3,12,14,15,11,16" +
",17,9,12,18,19,20,21,22,23,24,6,25,26,6,27")[0];

	private int yy_nxt[][] = unpackFromString(28,34,
"-1,1,62,63,62:8,52,2,3,4,5,62:2,6,7,8,9,10,11,12,37,36,43,13,47,62:2,14,-1," +
"62,58,62:10,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:15,15,-1:78,12:2,-1:8,34:26,1" +
"6,34:5,-1:2,62:12,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,21:12,-1:4,21:2,-1:6," +
"21:2,-1:4,21:2,-1:2,26:12,-1:4,26:2,-1:6,26:2,-1:4,26:2,-1:2,50:26,33,50:5," +
"-1:2,62:4,17,62:7,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,41:28,-1,38,41:2,-1:2" +
",51:28,-1,40,51:2,-1:2,62:7,18,62:4,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,21:" +
"12,22:4,21:2,22:4,23,24,22:4,25,22,21:2,14,-1,62:5,19,62:6,-1:4,62:2,-1:6,5" +
"9:2,-1:4,62,59,-1:2,26:12,27:4,26:2,28,29,27:2,30,31,27:2,39,27,32,44,26:2," +
"14,-1,62:10,20,62,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:3,35,62:8,-1:4,53," +
"62,-1:6,59:2,-1:4,62,59,-1:2,62:12,-1:4,62,42,-1:6,59:2,-1:4,62,59,-1:2,62:" +
"4,46,62:7,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:9,49,62:2,-1:4,62:2,-1:6,5" +
"9:2,-1:4,62,59,-1:2,62:3,54,62:8,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:6,5" +
"5,62:5,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:2,56,62:9,-1:4,62:2,-1:6,59:2" +
",-1:4,62,59,-1:2,62:7,61,62:4,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:8,57,6" +
"2:3,-1:4,62:2,-1:6,59:2,-1:4,62,59,-1:2,62:6,60,62:5,-1:4,62:2,-1:6,59:2,-1" +
":4,62,59,-1");

	public Token getToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

//Add code to be executed when the end of the file is reached
 return (new Token(Token.EOF, eofMessage));
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -2:
						break;
					case 2:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -3:
						break;
					case 3:
						{return (new Token(Token.EQUAL,yytext()));}
					case -4:
						break;
					case 4:
						{return (new Token(Token.DOT,yytext()));}
					case -5:
						break;
					case 5:
						{return (new Token(Token.SLASH,yytext()));}
					case -6:
						break;
					case 6:
						{checker.push('('); return (new Token(Token.OPEN_PARAN,yytext()));}
					case -7:
						break;
					case 7:
						{
	if(checker.peek() == '(') {
	 	checker.pop();
	 	return (new Token(Token.CLOSE_PARAN,yytext()));
	 }
}
					case -8:
						break;
					case 8:
						{checker.push('{'); return (new Token(Token.OPEN_CURLY,yytext()));}
					case -9:
						break;
					case 9:
						{
	if(checker.peek() == '}') {
	 	checker.pop();
	 	return (new Token(Token.CLOSE_CURLY,yytext()));
	 }
}
					case -10:
						break;
					case 10:
						{}
					case -11:
						break;
					case 11:
						{}
					case -12:
						break;
					case 12:
						{ return (new Token(Token.INT_LIT,yytext())); }
					case -13:
						break;
					case 13:
						{}
					case -14:
						break;
					case 14:
						
					case -15:
						break;
					case 15:
						{return (new Token(Token.COLON_EQUAL,yytext()));}
					case -16:
						break;
					case 16:
						{ return (new Token(Token.STRING_LIT,yytext())); }
					case -17:
						break;
					case 17:
						{return (new Token(Token.FOR,yytext()));}
					case -18:
						break;
					case 18:
						{return (new Token(Token.FUNC,yytext()));}
					case -19:
						break;
					case 19:
						{return (new Token(Token.IMPORT,yytext())); }
					case -20:
						break;
					case 20:
						{ return (new Token(Token.PACKAGE,yytext())); }
					case -21:
						break;
					case 21:
						{ yybegin(YYINITIAL); return (new Token(Token.IDENTIFIER,yytext())); }
					case -22:
						break;
					case 22:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -23:
						break;
					case 23:
						{}
					case -24:
						break;
					case 24:
						{}
					case -25:
						break;
					case 25:
						{}
					case -26:
						break;
					case 26:
						{ yybegin(YYINITIAL); return (new Token(Token.IDENTIFIER,yytext())); }
					case -27:
						break;
					case 27:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -28:
						break;
					case 28:
						{ import_open_paran = 1; return new Token(Token.OPEN_PARAN, yytext());}
					case -29:
						break;
					case 29:
						{ yybegin(YYINITIAL); import_open_paran = 0; return new Token(Token.CLOSE_PARAN, yytext());}
					case -30:
						break;
					case 30:
						{}
					case -31:
						break;
					case 31:
						{}
					case -32:
						break;
					case 32:
						{}
					case -33:
						break;
					case 33:
						{ if(import_open_paran == 0) yybegin(YYINITIAL); return (new Token(Token.STRING_LIT,yytext())); }
					case -34:
						break;
					case 35:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -35:
						break;
					case 36:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -36:
						break;
					case 37:
						{ return (new Token(Token.INT_LIT,yytext())); }
					case -37:
						break;
					case 38:
						{ return (new Token(Token.STRING_LIT,yytext())); }
					case -38:
						break;
					case 39:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -39:
						break;
					case 40:
						{ if(import_open_paran == 0) yybegin(YYINITIAL); return (new Token(Token.STRING_LIT,yytext())); }
					case -40:
						break;
					case 42:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -41:
						break;
					case 43:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -42:
						break;
					case 44:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -43:
						break;
					case 46:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -44:
						break;
					case 47:
						{return new Token(Token.ERROR, "Invalid input: " + yytext());}
					case -45:
						break;
					case 49:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -46:
						break;
					case 52:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -47:
						break;
					case 53:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -48:
						break;
					case 54:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -49:
						break;
					case 55:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -50:
						break;
					case 56:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -51:
						break;
					case 57:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -52:
						break;
					case 58:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -53:
						break;
					case 59:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -54:
						break;
					case 60:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -55:
						break;
					case 61:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -56:
						break;
					case 62:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -57:
						break;
					case 63:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -58:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}

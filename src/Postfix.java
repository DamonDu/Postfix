import java.io.*;

class Parser {
	static int lookahead;

	public Parser() throws IOException {
		lookahead = System.in.read();
	}

	void expr() throws IOException {
		term();
		while(rest() == 0){}
	}

	int rest() throws IOException {
		if (lookahead == '+') {
			match('+');
			term();
			System.out.write('+');
			return 0;
		} else if (lookahead == '-') {
			match('-');
			term();
			System.out.write('-');
			return 0;
		} else {
			if (Character.isDigit((char)lookahead)) {
				throw new Error("Syntax error: ");
			}
			else {
				throw new Error("error: The '" + (char)lookahead + "' is not allowed in input.");
			}
			lookahead = System.in.read();
			return 1;
		}
	}

	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
			match(lookahead);
		} else {
			if (lookahead == '+' || lookahead == '-') {
				throw new Error("Syntax error:");
			}
			else {
				throw new Error("error: The '" + (char)lookahead + "' is not allowed in input.");
			}
			
		}
	}

	void match(int t) throws IOException {
		if (lookahead == t) {
			lookahead = System.in.read();
		}
		else  throw new Error("syntax error");
	}
}

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		new Parser().expr();
		System.out.println("\nEnd of program.");
	}
}

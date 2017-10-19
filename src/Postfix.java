import java.io.*;

/**
* Parser类实现了解析一个中缀表达式并输出为后缀表达式。
*/
class Parser {
	/**
	* 当前位数往右一位的字符值。
	*/
	static int lookahead;
	/**
	* 类方法上一次读取的合法字符。
	*/
	static int last;

	/**
	* 该方法是Parser类的默认构造函数。
	* 将 lookahead 赋值为读入的第一个字符。
	* @throws IOException
	*/
	public Parser() throws IOException {
		lookahead = System.in.read();
	}

	/**
	* 该方法实现对表达式的处理。
	*/
	void expr() {
		while((char)lookahead != '\n') {
			try {
				if (!Character.isDigit((char)last)) {
					term();
				}
				while(rest() == 0){}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				lookahead = System.in.read();
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	* 该方法实现读入下个字符，判断是否合法并进行后续处理。
	* @return 一个表示输入是否合法的 int 值。
	* @throws Exception
	*/
	int rest() throws Exception {
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
			if (lookahead == '\n') {
				return 1;
			}
			if (Character.isDigit((char)lookahead)) {
				throw new Exception("\nSyntax error: Missing an operator!");
			} 
			if (lookahead < 127 && lookahead > 31) {
				throw new Exception("\nLexical error: The '" + lookahead + "' is not allowed in input.");
			}
		}
		return 1;
	}

	/**
	* 该方法读入第一个字符，判断其是否合法并进行后续处理。
	* @throws Exception
	*/
	void term() throws Exception {
		if (Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
			match(lookahead);
		} else {
			if (lookahead == '+' || lookahead == '-' || lookahead == '\n') {
				throw new Exception("\nSyntax error: Missing an operand!");
			}
			if (lookahead < 127 && lookahead > 31) {
				throw new Exception("\nLexical error: The '" + (char)lookahead + "' is not allowed in input.");
			}
		}
	}

	/**
	* 该方法检测当前字符是否与预计字符一致。
	* @param 待检测字符。
	* @throws Exception
	*/
	void match(int t) throws Exception {
		if (lookahead == t) {
			last = lookahead;
			lookahead = System.in.read();
		}
		else  throw new Exception("\nsyntax error");
	}
}

/**
* Postfix类实现了获取一个中缀表达式并输出为后缀表达式。
*/
public class Postfix {
	/**
	* 该方法为Postfix类的主函数。
	*/	
	public static void main(String[] args) throws IOException{
		System.out.println("Input an infix expression and output its postfix notation:");
		new Parser().expr();
		System.out.println("\nEnd of program.");
	}
}

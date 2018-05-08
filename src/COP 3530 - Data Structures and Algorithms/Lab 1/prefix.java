import java.util.Scanner;

public class prefix {
	// validates the character for an operand
	public static boolean isOperand(char symb) { 
		if(symb == '+' || symb == '-'|| symb == '*'
					   || symb == '/'|| symb == '$'
			           || symb == '('|| symb == ')') {
			return false;
		}
		else {
			return true;
		}
	}
	
	// converts the string from infix to prefix
	public static String infix_to_prefix(String infix) {													
		// output string position starts at rightmost end
		int outpos = infix.length() - 1;
		// create an output string of same length as infix string
		char [] outstr = new char[infix.length()];
		// create an empty stack for ops
		charStack opstk = new charStack();
		
		// read the infix string from right to left, using infix index
		for(int inpos = infix.length() - 1; inpos >= 0 ; inpos--) {			
			char symb = infix.charAt(inpos);	// read the next input character
			if (isOperand(symb)) {	// if the symbol is an operand
				outstr[outpos--] = symb;	// add it to the prefix string
			}
	        else {	// if the symbol is not an operand
	            while(!opstk.empty() && precedence(symb, opstk.peek())) {
	            	outstr[outpos--] = opstk.pop();	// add it to the prefix string
	            }    
	            if (opstk.empty() || symb!= '(') {
	            	opstk.push(symb);
	            }
	            else {
	            	opstk.pop();
	            }	// end else
	        }
		}	// end for loop
		// output any remaining operators
		while (!opstk.empty()) {
			outstr[outpos--] = opstk.pop();	// add it to the prefix string
		}
		String prefix = new String(outstr);
		return prefix;
	}
	
	// compares two ops for precedence
	// op2 is the operator on top of stack, op1 is the incoming operator
	public static boolean precedence(char op1, char op2) { 
		// opcode for + or - is 1
	    // opcode for * or / is 2
	    // opcode for $ is 3 
		int opcode1 = 0, opcode2 = 0;
		
		// opcodes corresponding to op1
		if (op1 == '(') {
			opcode1 = 0;
		}
		else if (op1 == '+' || op1 == '-') {
			opcode1 = 1;
		}
		else if (op1 == '*' || op1 == '/') {
			opcode1 = 2;
		}
		else if (op1 == '$') {
			opcode1 = 3;
		}
		else if (op1 == ')') {
			opcode1 = 4;
		}
		
		// opcodes corresponding to op2
		if (op2 == ')') {
			opcode2 = 0;
		}		
		else if (op2 == '+' || op2 == '-') {
			opcode2 = 1;
		}		
		else if (op2 == '*' || op2 == '/') {
			opcode2 = 2;
		}		
		else if (op2 == '$') {
			opcode2 = 3;
		}		
		else if (op2 == '(') {
			opcode2 = 4;
		}		
		
		// if opcode 1 < opcode2, than it has precedence
		if(opcode1 < opcode2) {
			return true;
		}
		// if opcode1 >= opcode2, than it does not have precedence
	    else {
	        return false;	
	    }
	}
	
	// prompts the user for an input string and converts it
	public static void main(String args[]) { 		
		String infix, preFix;
		System.out.print("Enter an infix string: "); 
		
		Scanner scanner = new Scanner(System.in);
		infix = scanner.next();
		
		preFix = infix_to_prefix(infix); // method to convert from infix to prefix
		System.out.println("The prefix string is: " + preFix); 
	}												   											  
}

/**
 * The Postfix.java class is responsible for parsing a string (making use of the string tokenizer)
 * and doing the conversion to post fix.
 * @author Okyanus Gumus, ID: 260900481
 * 
 */

import java.util.StringTokenizer;

public class postFix {
	
	/**
	 * parse method: Parses the inputed string to a queue.
	 * @param String arg: The string that is inputed and the one that is being parsed.
	 * @return newQueue: The resulting queue after parsing the string.
	 */
	
	public Queue parse(String arg) {
		
		Queue newQueue = new Queue();					// Creates a new object, newQueue, of type Queue.
		StringTokenizer sT = new StringTokenizer(arg,"+-*/()",true);	// Creates a new instance of the StringTokenizer.
		
		while(sT.hasMoreTokens() == true) {				// While the StringTokenizer has more tokens,
			
			newQueue.enqueue(sT.nextToken());			// Adds the token to the newQueue queue.
			
		}
		
		return newQueue;								// Returns newQueue when every token has been added.
		
	}
	
	/**
	 * In2Post method: Converts the inputed queue into a queue that follows the post fix format.
	 * Does the conversion according to the Shunting-yard algorithm.
	 * @param Queue Qin: The inputed queue and the queue that is being converted to post fix.
	 * @return outputQueue: 
	 */
	
	public Queue In2Post(Queue Qin) {
		
		Queue outputQueue = new Queue();				// Creates a new object, outputQueue, of type Queue.
		Stack operatorStack = new Stack();				// Creates a new object, operatorStack, of type Stack.
		int precedence = 0;								// Sets the precedence of the current node to 0.
		int topPrecedence = 0;							// Sets the precedence of the node on top of the operator stack to 0.
		
		while(true) {									// The main while loop of the method. Loops until the break condition is triggered.
			
			String current = Qin.dequeue();				// Sets the string "current" to the string at the front of the input queue (Qin).
			
			if(current == null) {						// If the input queue is empty,
				
				break;									// Breaks.
				
			}
			
			switch(current) {							// Determines the precedence of the string that
														// was on top of the input queue if it's an operator	
			case("*"): precedence = 3; break;			// using this switch statement. If the string is not
			case("/"): precedence = 3; break;			// an operator, no precedence value is assigned.
			case("+"): precedence = 2; break;
			case("-"): precedence = 2; break;
			case("("): precedence = 1; break;
							
			}
			
			if(operatorStack.top != null) {				// If the operator stack is not empty,
				
				switch(operatorStack.top.data) {		// Determines the precedence value of the
														// operator on top of the operator stack using this
				case("*"): topPrecedence = 3; break;	// switch statement.
				case("/"): topPrecedence = 3; break;
				case("+"): topPrecedence = 2; break;
				case("-"): topPrecedence = 2; break;
				case("("): topPrecedence = 1; break;
												
				}
				
			}
			
			if(current.equals("*") || current.equals("/") || current.equals("+") || current.equals("-") ) {		// If the current string is an operator,
				
				if(operatorStack.top == null) {			// If the operator stack is empty,
					
					operatorStack.push(current);		// Pushes the current string onto the operator stack.
					
				}
				
				else if(topPrecedence < precedence) {	// If the top of the stack has a lower precedence than the current string,
					
					operatorStack.push(current);		// Pushes the current string onto the operator stack.
					
				}
				
				else if(precedence <= topPrecedence) {	// If the top of the stack has a higher or equal precedence of that of the current string's,
					
					while(true) {						// Loops until the break condition is satisfied. 
						
						if(topPrecedence < precedence) {	// If the top of the stack has a lower precedence than the current string,
							
							break;							// Breaks;
							
						}
						
						if(operatorStack.top != null) {		// If the operator stack is not empty,
							
							outputQueue.enqueue(operatorStack.pop());	// Pops the operator on top of the operator stack and adds it to the output queue.
							
							if(operatorStack.top != null) {				// If the operator stack is not empty,
								
								switch(operatorStack.top.data) {		// Determines the precedence of the operator
																		// on top of the stack using the same switch 
								case("*"): topPrecedence = 3; break;	// statement as before.
								case("/"): topPrecedence = 3; break;
								case("+"): topPrecedence = 2; break;
								case("-"): topPrecedence = 2; break;
								case("("): topPrecedence = 1; break;
																
								}
								
							}
							
						}
						
						else {								// If the operator stack is empty,
							
							break;							// Breaks out of this while loop.
							
						}
						
					}
				
					operatorStack.push(current);			// Pushes the current onto the operator stack when
															// the top of the stack has lower precedence than that of the string.
				}
				
			}
			
			else if(current.equals("(")) {					// If the current string is a left parentheses,
				
				operatorStack.push(current);				// Pushes it onto the stack.
				
			}
			
			else if(current.equals(")")) {					// If the current string is a right parentheses,
				
				while(true) {								// Loops until the break condition is satisfied
					
					while((operatorStack.top.data) != null && !operatorStack.top.data.equals("(")) { 	// While the operator stack is not empty and the operator
																										// at the top of the operator stack isn't a left parentheses,
						outputQueue.enqueue(operatorStack.pop());			// Pops the operator from the operator stack and adds it to the output queue.							
						
					}
					
					if(operatorStack.top.data.equals("(")) {				// If the operator at the top of the operator stack is a left parentheses,
						
						operatorStack.pop();								// Pops the operator and disregards its.
						break;												// And breaks.
						
					}
					
					
				}
					
			}

			else {											// If the current string is not an operator,
				
				outputQueue.enqueue(current);				// Adds it to the output queue.
				
			}
				
		}
		
		if(operatorStack.top != null) {						// If the operator stack is not empty,
			
			while(true) {									// Loops until the break condition is satisfied.
				
				outputQueue.enqueue(operatorStack.pop());	// Pops the operator on top of the operator stack and adds it to the output queue.
				
				if(operatorStack.top == null) {				// If the operator stack is empty,
					
					break;									// Breaks.
					
				}
				
			}
			
		}
		
		return outputQueue;									// At the end of the method returns the output queue.
		
	}
	
	/**
	 * PostEval method: Evaluates a Queue in post fix notation.
	 * @param PostFix : The post fix queue that will be evaluated.
	 * @return endResult : The final evaluated result.
	 */
	
	public double PostEval(Queue PostFix) {
		
		Stack processingStack = new Stack();				// Creates a new stack called processingStack that is used to hold the operands.
				
		while (PostFix.front != null) {						// The main while loop of the program, repeats until the input queue is empty.
			
			double result = 0;										// Sets the initial result to 0.
			
			String current = PostFix.dequeue();						// Dequeues the input queue and sets it to the string "current"
			
			if(current.equals("*") || current.equals("/") || current.equals("+") || current.equals("-") ) {			// If "current" is an operator,
				
				String pop1 = processingStack.pop();				// Pops 2 operands from the processingStack and
				String pop2 = processingStack.pop();				// sets them to "pop1" and "pop2"
				
				double operand1 = Double.parseDouble(pop1);			// Converts the popped operands from string
				double operand2 = Double.parseDouble(pop2);			// to double.
				
				switch(current) {									// According to the operator that was dequeued, applies the appropriate calculation using this switch statement.
				
				case("+"): {										// If it is "+",
					
					result = operand2 + operand1;					// Sets result to the sum of the 2 popped strings,
					break;											// Then breaks.
					
				}
				
				case("-"): {										// If it is "-",
					
					result = operand2 - operand1;					// Subtracts the first popped string from the second one,
					break;											// Then breaks.
					
				}
				
				case("*"): {										// If it is "*",
					
					result = operand2 * operand1;					// Multiplies the two popped strings,
					break;											// Then breaks.
					
				}
				
				case("/"): {										// If it is "/",
					
					result = operand2 / operand1;					// Divides the second popped string by the first popped string,
					break;											// Then breaks.
					
				}
				
				}
				
				processingStack.push(Double.toString(result));		// Afterwards, pushes the resulting number onto the processingStack.
				
			}
			
			else {													// If "current" is an operand,
				
				processingStack.push(current);						// Pushes it to the processingStack.
				
			}
			
		}
		
		double endResult = Double.parseDouble(processingStack.pop());		// Pops the remaining number on the processingStack and parses it to a double and sets it to "endResult"
		
		return endResult;													// Returns endResult, the final evaluated result. 
		
	}

}

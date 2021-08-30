/**
 * JCalc class is the test class used for checking if the PostEval method in the postFix class works correctly as intended.
 * @author Okyanus Gumus, ID: 260900481
 *
 */

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class JCalc extends ConsoleProgram {
	
	postFix pf = new postFix();					// Creates a new object pF of type postFix. 
												// This will be used for the method calls.
	public void run() {
		
		println("Infix to Postfix conversion, enter expression of blank line to exit.");	// Informs the user about the program.
		
		while(true) {							// Main loop of the program
			
			String input = readLine("expr: ");	// Sets the string "input" to the user input.
			
			if(input.equals("")) break;			// If the user input is empty, breaks out of the while loop and prints the termination message.
			
			Queue Qin = pf.parse(input);		// Parses the user input onto the queue "Qin"
			Queue pfResult = pf.In2Post(Qin);				// Converts the user input to post fix notation.
			String postString = pfResult.toString();		// Converts the post fix queue to a String.
			println(input + " ==> " + postString);			// Prints the post fix conversion.
			double eval = pf.PostEval(pfResult);			// Evaluates the converted (to post fix) user input.
			println(postString + " evaluates to " + eval);	// Prints the evaluation of the user input.
			
		}
		
		println("Program terminated.");						// Informs the user that the program has terminated.
		
	}
	

}

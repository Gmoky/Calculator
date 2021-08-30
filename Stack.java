/**
 * The Stack.java class is tasked with adding nodes to a created stack (that is used in In2p method of the postFix class)
 * and popping nodes from the stack. 
 * @author Okyanus Gumus, ID: 260900481
 *
 */

public class Stack {
	
	listNode top = null; // Sets the top of the stack to null.
	
	/**
	 * push method: Pushes the inputed string onto the stack.
	 * @param String arg: The string that gets pushed onto the stack.
	 */
	
	public void push(String arg) {
		
		listNode node = new listNode(); 	// Creates a new node object of type listNode.
		node.data = arg;				  	// Sets the node's data as the input
		node.next = top;					// Sets the top of the stack as the node.
		top = node;
		
		
	}
	
	/**
	 * pop method: Pops the String that is on top of the stack.
	 * @return String data: The String that was on top of the stack.
	 */
	
	public String pop() {
		
		if (top == null) {					// If the top of the stack is empty
			
			return null;					// returns null
			
		}
		
		String data = top.data;				// Sets "data" to the data of the top node (a.k.a. top string).
		top = top.next;						// Sets the next node in line as top.
		
		return data;						// Returns the popped String.
		
	}
	
}

/**
 * The Queue.java class is responsible for the creation of a queue, adding nodes to a queue, removing nodes from a queue,
 * and dequeuing an entire queue onto a string. 
 * @author Okyanus Gumus, ID: 260900481
 *
 */

public class Queue {
	
	listNode front = null;		// Sets the front of the queue to null.
	listNode rear = null;		// Sets the end of the queue to null.
	
	/**
	 * enqueue method: Adds the inputed string to the queue.
	 * @param String arg: The string that gets added to the queue.
	 */
	
	public void enqueue(String arg) {
		
		listNode node = new listNode();			// Creates a new node object of type listNode.
		node.data = arg;						// Sets the data of the node to the inputed string.
		node.next = null;						// Makes the node point to null.
		
		if (rear != null) {						// If the queue is not empty,
			
			rear.next = node;					// Adds a new node to the end.
			
		}
		
		else {									// Else,
			
			front = node;						// Makes the front point to the new node. 
			
		}
		
		rear = node;							// Makes the rear point to the new node.
		
	}
	
	/**
	 * dequeue method: Dequeues the string that is at the front of the queue.
	 * @return String data: The string that was at the front of the queue.
	 */
	public String dequeue() {
		
		if (front != null) {					// If the queue is not empty,
													
			if (front == rear) {					// If there aren't any other nodes in the queue, 
				
				rear = null;						// Sets the rear to null.
				
			}
			
			String data = front.data;			// Sets "data" to the data of the node at the front.
			front = front.next;					// Sets front as the next node in the queue.
			
			return data;						// Returns data.
			
		}
		
		else {									// If the queue is empty,
			
			return null;						// Returns null.
			
		}
		
	}
	
	/**
	 * toString method: Appends the elements of a queue into a string.
	 * Taken from the Assignment 3 Solutions PDF.
	 * @author Frank P. Ferrie
	 * @return String output: The concatenation of every string in the queue.
	 */
	
	public String toString() {
		
		String out = "";
		listNode cf = front;
		listNode cr = rear;
		
		if(cf == cr) return out;					// Returns empty string if queue is empty
		
		while(true) {								// Walks the chain extracting each element.
			
			out = out + " " + cf.data.trim();		// Inserts a space in between
			
			if(cf == cr) break;						// Done when front equals rear
			
			cf = cf.next;
			
		}
		
		return out;
		
	}	
	
}

/**
 * JCalcGUI is the main class responsible for establishing the main calculator user interface. 
 * @author Okyanus Gumus, ID: 260900481
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import acm.gui.*;
import acm.program.Program;

@SuppressWarnings("serial")
public class JCalcGUI extends Program {
	
	postFix pF = new postFix();					// Creates a new object "pF" of type postFix. This object will be used
												// for the method calls from the postFix class.
	String buttonWidth = "60";					// The variable for the button width. Declared here so it can be easily changed.
	String buttonHeight = "40";					// The variable for the button height. Declared here so it can be changed by the user.
	
	String param = "width=" + buttonWidth + " height=" + buttonHeight; 		// "param" is a common setting used throughout the UI. That's why
																			// it's declared here.
	TextField inputField = new TextField("");		// Creates a text field for the input.		
	TextField outputField = new TextField("");		// Creates a text field for the output. 
	
	boolean isResultPresent = false;				// Creates a boolean expression that changes according to if there is anything present
													// on the output text field.
	public void init() {
		
		setSize(350,600);							// Sets the size of the application window.
		
		setBackground(Color.BLACK);					// Sets the background color of the window to black.
		
		setLayout(new TableLayout(10,4,10,10));		// Creates a new 10 by 4 table layout with 10 by 10 gaps.
		
		JLabel brand = new JLabel("TI-nspire CX III-T", SwingConstants.CENTER);		// creates the label "TI-nspire CX III-T" label and centers it.
																					// (My current calculator is CX II-T, III-T does not exit. I thought it would be a fun addition.
		brand.setFont(new Font("Arial", Font.BOLD, 30));		// Sets the font of the previously mentioned name label.
		brand.setForeground(Color.RED);							// Sets the color of the name label.
		
		add(brand,"gridwidth=4 height=" + buttonHeight);		// Adds the label.
		
		inputField.setBackground(Color.GRAY);							// Sets the background color of the input field to gray.
		inputField.setForeground(Color.WHITE);							// Sets the color of the text on the input field to white.
		inputField.setFont(new Font("Arial", Font.BOLD, 20));			// Sets the font of the text on the input field.
		inputField.addKeyListener(this);								// Adds a key listener to the input field.
		
		outputField.setBackground(Color.GRAY);							// Sets the background color of the output field to gray.
		outputField.setForeground(Color.WHITE);							// Sets the color of the text on the output field to white.
		outputField.setFont(new Font("Arial", Font.BOLD, 20));			// Sets the font of the text on the output field.
		outputField.setEditable(false);									// Makes it so the user can not interact with the output field.
		
		add(inputField, "gridwidth=4 height=" + buttonHeight);			// Adds the input field.
		add(outputField, "gridwidth=4 height=" + buttonHeight);			// Adds the output field.
		
		addAllButtons();												// Adds the buttons using the addAllButtons method.
		addActionListeners();											// Adds the action listeners to the buttons.
		
	}
	
	/**
	 * addAllButton method: Adds all the buttons making use of the addButton method.
	 * 
	 */
	
	private void addAllButtons() {
		
		addButton("C");
		addButton("");
		addButton("");
		addButton("/");
		addButton("7");
		addButton("8");
		addButton("9");
		addButton("*");
		addButton("4");
		addButton("5");
		addButton("6");
		addButton("-");
		addButton("1");
		addButton("2");
		addButton("3");
		addButton("+");
		addButton("0");
		addButton(".");
		addButton("");
		addButton("=");
		addButton("(");
		addButton(")");
		addButton("");
		addButton("");
		addButton("");
		addButton("");
		addButton("");
		addButton("Quit");
		
	}
	
	/**
	 * keyPressed method: Checks if certain keys have been pressed and executes the appropriate code
	 * if they have been pressed. 
	 */
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyChar() == '=') {		// If the enter key or equals is pressed ( the reason I used getKeyChar is because some laptops such as mine have
																				// "+=" combo key and the program would display the result when the + key was pressed if I used keyEvent.
			String expression = inputField.getText();							// Gets the text on the input field and sets it to the string "expression".
			Queue outputQ = pF.In2Post(pF.parse(expression));					// Parses the "expression" and turns it to post fix. 
			double result = pF.PostEval(outputQ);								// Evaluates the resulting queue from the post fix conversion.
			
			outputField.setText(Double.toString(result));						// Sets the output field to the result.
			
			isResultPresent = true;												// Since there's now a result displayed, sets isResultPresent to "true".
			
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_C) {								// If the C key is pressed when the user was 
			
			isResultPresent = false;											// Sets isResultPresent to false.
			inputField.setText("");												// Empties the input field.
			outputField.setText("");											// Empties the output field.
			
		}
		
		else {																	// In every other case,
			
			if(isResultPresent) {												// If there is a result on the output field,
				
				isResultPresent = false;										// Sets the isResultPresent to "false".
				inputField.setText("");											// Empties the input field.
				outputField.setText("");										// Empties the output field.
				
			}
				
		}
		
	}
	
	/**
	 * addButton method: The method that is responsible for setting the button properties and adding them.
	 * @param buttonText : Text to be displayed on the button.
	 */
	
	private void addButton(String buttonText) {
		
		JButton button = new JButton(buttonText);							// Creates a new JButton object "button" with the user inputed text.
		
		if(buttonText.equals("C")) {										// If the button is the "C" button,
			
			button.setBackground(Color.RED);								// Sets its color to red.
			
		}
		
		else {																// Otherwise,
					
			button.setBackground(Color.DARK_GRAY);							// Sets the color of the button to dark gray.
			
		}
		
		if(buttonText.equals("Quit")) {										// If the button is the quit button,
			
			button.setFont(new Font("Arial", Font.BOLD, 13));				// Sets the font a bit smaller so the text it properly displayed.
			
		}
		
		else {																// Otherwise,
			
			button.setFont(new Font("Arial", Font.BOLD, 20));				// Set the font.
			
		}
		
		button.setForeground(Color.WHITE);									// Sets the button text color to white.
		
		add(button, param);													// Adds the button with the "param" parameters.
		
	}
	
	/**
	 * actionPerformed sets the behavior of the buttons when they are pressed.
	 */
	
	public void actionPerformed(ActionEvent e) {
			
		if(e.getActionCommand() == "C") {							// If the "C" button is pressed,		
			
			inputField.setText("");									// Empties the input field.
			outputField.setText("");								// Empties the output field.
			
		}
		
		else if(e.getActionCommand() == "=") {						// If the "=" button is pressed,
			
			String expression = inputField.getText();				// Gets the expression on the input field and sets it to the string "expression".
			Queue outputQ = pF.In2Post(pF.parse(expression));		// First parses the expression then converts it to post fix.
			double result = pF.PostEval(outputQ);					// Evaluates the post fix expression.
			
			outputField.setText(Double.toString(result));			// Converts the resulting double to string and displays it on the output field.
			
			isResultPresent = true;									// Since there's a result on the output display, sets iSResultPresent to true;
			
		}
		
		else if(e.getActionCommand() == "Quit") {					// If the quit button is pressed,
			
			System.exit(0);											// Terminates the program.
			
		}
		
		else {														// For every other button,
			
			if(isResultPresent) {									// If there is a result on the output field,
				
				isResultPresent = false;							// Sets isResultPresent to false first.
				inputField.setText("");								// Empties the input field.
				outputField.setText("");							// Empties the output field.
				
			}
			
			String presentText = inputField.getText();				// Gets the string on the input field.
			presentText += e.getActionCommand();					// Adds the character of the button to the string on display.
			inputField.setText(presentText);						// Displays the concatenated string.
			
		}
		
	}
	
}

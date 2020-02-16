/*
 * RomanConverter for roman_to_arabic
 * Has two text boxes that can be written by the user
 * Numbers entered into the top appear in the bottom as Roman numerals
 * Roman numerals entered in the bottom appear in the top as Arabic numbers
 * Uses KeyListeners to update text boxes with each keystroke (no buttons)
 * Any invalid input is removed from the text boxes
 */

//import statements
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial") //serialization is not needed
public class RomanConverterFrame extends JFrame {
	//variables and array lists for Arabic numbers and Roman numerals
	private String numbers = "";
	private String letters = "";
	
	//components
	private JLabel arabicLabel = new JLabel("Arabic Numeral");
	private JLabel romanLabel = new JLabel("Roman Numeral");
	private JTextField arabicField = new JTextField(10);
	private JTextField romanField = new JTextField(10);
	
	//key listeners
	private arabicKeyListener arabicKeys = new arabicKeyListener();
	private romanKeyListener romanKeys = new romanKeyListener();
	
	/*
	 * Constructor
	 */
	public RomanConverterFrame(String windowTitle) {	
		super(windowTitle);
		setLayout(new FlowLayout());
		
		//adds key listeners to text fields
		this.arabicField.addKeyListener(arabicKeys);
		this.romanField.addKeyListener(romanKeys);
		
		//adds labels and text fields
		add(this.arabicLabel);
		add(this.arabicField);
		add(this.romanLabel);
		add(this.romanField);
	} //end of constructor
	
	/*
	 * Method to convert an Arabic number to Roman numerals
	 * Reads each individual character and adds to a counting string
	 * String is filled backward to allow more accurate writing
	 * Reversed again at the end of the method to get proper number
	 * If the entry is invalid, returns null
	 */
	private String arabicToRoman(String arabicNumber) {
		//tests if the first digit is zero, which it should not be
		if(arabicNumber.startsWith("0")) {
			return null;
		}
		
		/*
		 * Strings to hold backward Arabic number and Roman numeral
		 * Backward so it can be filled properly using input
		 */
		String newArabic = "";
		String upsideDown = "";
		
		//for loop to reverse Arabic number
		for(int i = (arabicNumber.length()-1); i >= 0; i--) {
			newArabic += arabicNumber.charAt(i);
		} //end of for loop
		
		//for loop to fill reverse Roman numeral
		for(int i = 0; i < newArabic.length(); i++) {
			if(i == 0) { //convert the first Arabic digit
				if(newArabic.charAt(i) == '0') {
					//there is no zero in Roman numerals
				}else if(newArabic.charAt(i) == '1') {
					upsideDown += "I"; //1 = I
				}else if(newArabic.charAt(i) == '2') {
					upsideDown += "II"; //2 = II
				}else if(newArabic.charAt(i) == '3') {
					upsideDown += "III"; //3 = III
				}else if(newArabic.charAt(i) == '4') {
					upsideDown += "VI"; //4 = IV
				}else if(newArabic.charAt(i) == '5') {
					upsideDown += "V"; //5 = V
				}else if(newArabic.charAt(i) == '6') {
					upsideDown += "IV"; //6 = VI
				}else if(newArabic.charAt(i) == '7') {
					upsideDown += "IIV"; //7 = VII
				}else if(newArabic.charAt(i) == '8') {
					upsideDown += "IIIV"; //8 = VIII
				}else if(newArabic.charAt(i) == '9') {
					upsideDown += "XI"; //9 = IX
				}else {
					return null; //invalid input
				}
			}else if(i == 1) { //convert the second Arabic digit
				if(newArabic.charAt(i) == '0') {
					//there is no zero in Roman numerals
				}else if(newArabic.charAt(i) == '1') {
					upsideDown += "X"; //10 = x
				}else if(newArabic.charAt(i) == '2') {
					upsideDown += "XX"; //20 = xx
				}else if(newArabic.charAt(i) == '3') {
					upsideDown += "XXX"; //30 = xxx
				}else if(newArabic.charAt(i) == '4') {
					upsideDown += "LX"; //40 = xl
				}else if(newArabic.charAt(i) == '5') {
					upsideDown += "L"; //50 = l
				}else if(newArabic.charAt(i) == '6') {
					upsideDown += "XL"; //60 = lx
				}else if(newArabic.charAt(i) == '7') {
					upsideDown += "XXL"; //70 = lxx
				}else if(newArabic.charAt(i) == '8') {
					upsideDown += "XXXL"; //80 = lxxx
				}else if(newArabic.charAt(i) == '9') {
					upsideDown += "CX"; //90 = XC
				}else {
					return null; //invalid input
				}
			}else if(i == 2) { //convert the third Arabic digit
				if(newArabic.charAt(i) == '0') {
					//there is no zero in Roman numerals
				}else if(newArabic.charAt(i) == '1') {
					upsideDown += "C"; //100 = c
				}else if(newArabic.charAt(i) == '2') {
					upsideDown += "CC"; //200 = cc
				}else if(newArabic.charAt(i) == '3') {
					upsideDown += "CCC"; //300 = ccc
				}else if(newArabic.charAt(i) == '4') {
					upsideDown += "DC"; //400 = cd
				}else if(newArabic.charAt(i) == '5') {
					upsideDown += "D"; //500 = d
				}else if(newArabic.charAt(i) == '6') {
					upsideDown += "CD"; //600 = dc
				}else if(newArabic.charAt(i) == '7') {
					upsideDown += "CCD"; //700 = dcc
				}else if(newArabic.charAt(i) == '8') {
					upsideDown += "CCCD"; //800 = dccc
				}else if(newArabic.charAt(i) == '9') {
					upsideDown += "MC"; //900 = cm
				}else {
					return null; //invalid input
				}
			}else if(i == 3) { //convert the fourth Arabic digit
				if(newArabic.charAt(i) == '0') {
					//there is no zero in Roman numerals
				}else if(newArabic.charAt(i) == '1') {
					upsideDown += "M"; //1000 = m
				}else if(newArabic.charAt(i) == '2') {
					upsideDown += "MM"; //2000 = mm
				}else if(newArabic.charAt(i) == '3') {
					upsideDown += "MMM"; //3000 = mmm
				}else { //can only go up to 3999, 4000+ is invalid
					return null; //invalid input
				}
			}else { //any input more than four digits is invalid
				return null;
			}
		} //end of for loop
		
		String romanNumerals = ""; //string to take Roman numeral
		//for loop to reverse backward Roman numeral
		for(int i = (upsideDown.length()-1); i >= 0; i--) {
			romanNumerals += upsideDown.charAt(i);
		} //end of for loop
		return romanNumerals;
	} //end of method arabicToRoman
	
	/*
	 * Method to convert a Roman numeral to Arabic numbers
	 * Reads each individual character and adds to a counting integer
	 * Accepts upper-case or lower-case characters
	 * If the entry is invalid, returns -1
	 */
	private int romanToArabic(String romanNumeral) {
		int arabicNumeral = 0; //integer to keep total
		
		//for loop to check every individual character of the Roman numeral
		for(int i = 0; i < romanNumeral.length(); i++) {
			if(romanNumeral.charAt(i) == 'M' || romanNumeral.charAt(i) == 'm') 
			{
				arabicNumeral += 1000; //M = 1000
			}else if(romanNumeral.charAt(i) == 'D' || romanNumeral.charAt(i) == 
					'd') {
				arabicNumeral += 500; //D = 500
			}else if(romanNumeral.charAt(i) == 'C' || romanNumeral.charAt(i) == 
					'c') {
				//checks if there is a next character
				if((i+1) < romanNumeral.length()) {
					if(romanNumeral.charAt(i+1) == 'D' || 
							romanNumeral.charAt(i+1) == 'd') {
						arabicNumeral += 400; //CD = 400
						i++; //skips next character
					}else if(romanNumeral.charAt(i+1) == 'M' || 
							romanNumeral.charAt(i+1) == 'm') {
						arabicNumeral += 900; //CM = 500
						i++; //skips next character
					}else {
						arabicNumeral += 100; //C = 100
					}
				}else { //if there is no next character
					arabicNumeral += 100; //C = 100
				}
			}else if(romanNumeral.charAt(i) == 'L' || romanNumeral.charAt(i) ==
					'l') {
				arabicNumeral += 50; //L = 50
			}else if(romanNumeral.charAt(i) == 'X' || romanNumeral.charAt(i) ==
					'x') {
				//checks if there is a next character
				if((i+1) < romanNumeral.length()) {
					if(romanNumeral.charAt(i+1) == 'L' || 
							romanNumeral.charAt(i+1) == 'l') {
						arabicNumeral += 40; //XL = 40
						i++; //skips next character
					}else if(romanNumeral.charAt(i+1) == 'C' || 
							romanNumeral.charAt(i+1) == 'c') {
						arabicNumeral += 90; //XC = 90
						i++; //skips next character
					}else {
						arabicNumeral += 10; //X = 10
					}
				}else { //if there is no next character
					arabicNumeral += 10; //X = 10
				}
			}else if(romanNumeral.charAt(i) == 'V' || 
					romanNumeral.charAt(i) == 'v') {
				arabicNumeral += 5;
			}else if(romanNumeral.charAt(i) == 'I' || 
					romanNumeral.charAt(i) == 'i') {
				if((i+1) < romanNumeral.length()) {
					if(romanNumeral.charAt(i+1) == 'V' || 
							romanNumeral.charAt(i+1) == 'v') {
						arabicNumeral += 4; //IV = 4
						i++; //skips next character
					}else if(romanNumeral.charAt(i+1) == 'X' || 
							romanNumeral.charAt(i+1) == 'x') {
						arabicNumeral += 9; //IX = 9
						i++; //skips next character
					}else {
						arabicNumeral += 1; //I = 1
					}
				}else {  //if there is no next character
					arabicNumeral += 1; //I = 1
				}
			}else { //invalid character input
				return -1; //returns an invalid input
			}
		} //end of for loop
		
		//makes sure Arabic number is a possible entry
		if(arabicNumeral > 3999 || arabicNumeral < 1) {
			return -1; //invalid
		}else {
			return arabicNumeral; //valid
		}
	} //end of method romanToArabic
	
	/*
	 * Handles keys being pressed in the Arabic text field
	 * Gets input from text field and converts to Roman numerals
	 * Reverts to last input if invalid input is entered
	 */
	public class arabicKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent k) {
				
		} //end of method keyPressed

		@Override
		public void keyReleased(KeyEvent k) {
			//gets the number from the text field
			String newNumber = RomanConverterFrame.this.arabicField.getText();
			
			//checks if the text field was emptied
			if(newNumber.equals("")) { //empties both text fields
				RomanConverterFrame.this.numbers = newNumber;
				RomanConverterFrame.this.arabicField.setText(newNumber);
				RomanConverterFrame.this.letters = newNumber;
				RomanConverterFrame.this.romanField.setText(newNumber);
			}else { //converts if not empty
				String newText = 
						RomanConverterFrame.this.arabicToRoman(newNumber);
				
				if(newText == null) { //checks for invalid input
					RomanConverterFrame.this.arabicField.setText
					(RomanConverterFrame.this.numbers);
				}else {
					RomanConverterFrame.this.numbers = newNumber;
					RomanConverterFrame.this.arabicField.setText(newNumber);
					RomanConverterFrame.this.letters = newText;
					RomanConverterFrame.this.romanField.setText(newText);
				}
			}
		} //end of method keyReleased

		@Override
		public void keyTyped(KeyEvent k) {
			
		} //end of method keyReleased
	} //end of arabicKeyListener
	
	/*
	 * Handles keys being pressed in the Roman text field
	 * Gets input from text field and converts to Arabic numbers
	 * Reverts to last input if invalid input is entered
	 */
	public class romanKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent k) {
			
		} //end of method keyPressed

		@Override
		public void keyReleased(KeyEvent k) {
			//gets text from the text field
			String newText = RomanConverterFrame.this.romanField.getText();
			
			//checks if text field was emptied
			if(newText.equals("")) { //empties both text fields
				RomanConverterFrame.this.letters = newText;
				RomanConverterFrame.this.romanField.setText(newText);
				RomanConverterFrame.this.numbers = newText;
				RomanConverterFrame.this.arabicField.setText(newText);
			}else { //converts if not empty
				int newNumber = 
						RomanConverterFrame.this.romanToArabic(newText);
				
				if(newNumber == -1) { //checks for invalid input
					RomanConverterFrame.this.romanField.setText
					(RomanConverterFrame.this.letters);
				}else { //sets text fields and strings to appropriate values
					RomanConverterFrame.this.letters = newText;
					RomanConverterFrame.this.romanField.setText(newText);
					RomanConverterFrame.this.numbers = (Integer.toString
							(newNumber));
					RomanConverterFrame.this.arabicField.setText(Integer.
							toString(newNumber));
				}
			}
		} //end of method keyReleased

		@Override
		public void keyTyped(KeyEvent k) {
			
		} //end of method keyTyped
	} //end of romanKeyListener
} //end of class RomanConverterFrame
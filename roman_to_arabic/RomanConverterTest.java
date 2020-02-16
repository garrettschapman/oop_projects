/*
 * Testing method for roman_to_arabic
 * Uses RomanConverter to create a window where users can input numbers
 */

//import statement
import javax.swing.JFrame;

public class RomanConverterTest {
	/*
	 * Main method
	 */
	public static void main(String args[]) {
		RomanConverterFrame converter = new RomanConverterFrame("Roman <--> "
				+ "Arabic");
		converter.setSize(300,100);
		converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		converter.setVisible(true);
	} //end of main method
} //end of class RomanConverterTest
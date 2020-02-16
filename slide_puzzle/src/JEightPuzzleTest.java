/*
 * Testing file for silde_puzzle
 * Uses ImageSelector to select an image and JEightPuzzleFrame to make a puzzle
 */

//import statement
import javax.swing.JFrame;

public class JEightPuzzleTest {
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		/*
		 * Uses ImageSelector to select an image
		 * Passes directory to open only folder containing this file
		 */
		ImageSelector imageSelector = new ImageSelector("./");
		imageSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String image = "./" + imageSelector.getImage();
		
		/*
		 * Uses JEightPuzzleFrame to create puzzle
		 */
		JEightPuzzleFrame puzzle = new JEightPuzzleFrame("slide_puzzle",
				image);
		puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		puzzle.setVisible(true);
	} //end of main method
} //end of class JEightPuzzleTest
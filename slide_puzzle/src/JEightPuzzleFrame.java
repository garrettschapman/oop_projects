/*
 * JEightPuzzleFrame for slide_puzzle
 * Creates a puzzle with 8 squares that must be swapped
 * When all puzzle pieces are in the right place, user is congratulated
 */

//Import statements
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
@SuppressWarnings("serial") //no need for serialization
public class JEightPuzzleFrame extends JFrame implements ActionListener {
	//buttons, icons, and panel
	private JButton[] buttons = new JButton[8];
	private Icon[] icon = new Icon[8];
	private JPanel panel = new JPanel();
	
	//board (layout of puzzle) and grid
	private int[][] board = {{0,1,2},{5,6,3},{4,7,8}};
	private GridLayout gridLayout = new GridLayout(3, 3);
	
	/*
	 * Constructor
	 * Takes the window name and path to image as parameters
	 */
	public JEightPuzzleFrame(String windowTitle, String path) {
		super(windowTitle);
		setLayout(this.gridLayout);
		
		//for loop to create icons for each button
		for(int i = 0; i < icon.length; i++) {
			icon[i] = extractIcon(path, i);
		} //end of for loop
		
		addButtons();
	} //end of constructor
	
	/*
	 * Method to extract the image
	 * Uses image path and starting location to create icon
	 * Start location also determines end location
	 * Returns an icon created by the method
	 */
	private Icon extractIcon(String path, int start) {
		ImageIcon extractedImage = new ImageIcon();
		
		BufferedImage image = null;
		try{ //attempts to get image file
			image = ImageIO.read(new File(path));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"Could not find image");
			System.exit(1);
		}
		
		/*
		 * Sets the size of the window
		 * Window takes additional pixels, which must be accounted for
		 * Through testing I determined I needed 23 for width and 46 for height
		 */
		setSize(image.getWidth()+23,image.getHeight()+46);
		
		//determines how far into the image each icon goes
		int width = (image.getWidth()/3);
		int height = (image.getHeight()/3);
		BufferedImage part = new BufferedImage(width, height, 
				BufferedImage.TYPE_4BYTE_ABGR);
		
		//determines how far along the right of the image the icon starts
		int xStart = 0, yStart = 0;
		if(start == 0 || start == 3 || start == 6) {
			xStart = 0;
		}else if(start == 1 || start == 4 || start == 7) {
			xStart = (image.getWidth()/3);
		}else if(start == 2 || start == 5 || start == 8) {
			xStart = (2 * (image.getWidth()/3));
		}
		//determines how far down the image the icon starts
		if(start == 0 || start == 1 || start == 2) {
			yStart = 0;
		}else if(start == 3 || start == 4 || start == 5) {
			yStart = (image.getHeight()/3);
		}else if(start == 6 || start == 7 || start == 8) {
			yStart = (2 * (image.getHeight()/3));
		}
		
		//for loop to create the icon to be returned
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				part.setRGB(x,y, image.getRGB((x + xStart), (y + yStart)));
				extractedImage.setImage(part); //sets the pixel to the icon
			} //end of internal for loop
		} //end of external for loop
		
		return extractedImage; //gives the icon back to the caller
	} //end of method extractIcon
	
	/*
	 * Method to add the buttons in the correct order
	 * Uses a for loop to read the board
	 * Adds buttons or blank space based on board
	 */
	private void addButtons() {
		int count = 0; //counting integer for buttons
		
		//for loop to add buttons based on their location on the board
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				if(this.board[i][j] == 0) { //zero is a placeholder for blank
					add(this.panel); //adds blank space
				}else {
					count = (this.board[i][j] - 1); //buttons start at zero
					this.buttons[count] = new JButton(icon[count]);
					this.buttons[count].addActionListener(this);
					add(this.buttons[count]);
				}
			} //end of internal for loop
		} //end of external for loop
	} //end of method addButtons
	
	/*
	 * Method to find the location in the grid
	 * Uses contents of board locations and compares them
	 * Returns an integer array that holds the array location on the board
	 */
	private int[] findLocation(int x) {
		int[] location = new int[2];
		
		//for loop to determine location
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				//checks if the board location holds the element
				if(x == this.board[i][j]) {
					location[0] = i;
					location[1] = j; 
					break; //ends loop prematurely
				}
			} //end of internal for loop
		} //end of external for loop
		
		return location; //sends the location back to the caller
	} //end of method findLocation
	
	/*
	 * Method to recreate the puzzle
	 * Removes and re-adds everything in new locations
	 * Congratulates user if the puzzle has been solved
	 * Randomizes the puzzle if it has been solved
	 */
	private void recreate() {
		//for loop to remove all buttons
		for(int i = 0; i <= this.buttons.length; i++) {
			if(i == this.buttons.length) { //removes blank panel
				remove(this.panel);
			}else { //removes a button
				remove(this.buttons[i]);
			}
		} //end of for loop
		//re-adds buttons and re-makes grid
		addButtons();
		revalidate();
		repaint();
		
		//checks if all buttons are in the correct place
		int count = 1; //counting variable
		boolean solved = true; //boolean to show if solved
		
		//for loop to determine if the puzzle has been solved
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				if(count == 9) { //checks blank space
					if(this.board[i][j] != 0) {
						//sets puzzle to not solved if incorrectly placed
						solved = false;
					}
				}else if(this.board[i][j] != count) { //checks blank space
					//sets puzzle to not solved if incorrectly placed
					solved = false;
				}
				count++; //increases counting variable
			} //end of internal for loop
		} //end of external for loop
		
		//Congratulates user and randomizes puzzle if it has been solved
		if(solved) {
			int input = JOptionPane.showConfirmDialog(null, "Congratulations!",
					"You Win!", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE); //congratulates user
			//randomizes puzzle once user clicks "okay"
			if(input == JOptionPane.OK_OPTION) {
			    randomize();
			}
		}
	} //end of method reset
	
	/*
	 * Method to randomize the puzzle
	 * Creates a new board using random numbers
	 */
	private void randomize() {
		boolean[] numbers = {true, true, true, true, true, true, true, true, 
				true}; //array to see what numbers are available for the board
		int[][] newBoard = new int[3][3]; //new board for randomized puzzle
		
		//for loop to create a new board
		for(int i = 0; i < newBoard.length; i++) {
			for(int j = 0; j < newBoard[i].length; j++) {
				//creates random number for new board
				int random = ThreadLocalRandom.current().nextInt(0, 8 + 1);
				if(numbers[random]) { //checks if number is still available
					newBoard[i][j] = random; //sets board location
					numbers[random] = false; //stops number from being reused
				}else {
					j--; //forces another random number for this square
				}
			} //end of internal for loop
		} //end of external for loop
		
		this.board = newBoard; //sets the board to the new layout
		recreate();
	} //end of method randomize
	
	/*
	 * Event handler
	 * Checks if button clicked is next to blank panel
	 * Swaps them if so
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		int click = 0; //variable for clicked button
		//for loop to find which button was clicked
		for(int i = 0; i < this.buttons.length; i++) {
			if(this.buttons[i] == event.getSource()) {
				click = (i + 1); //buttons start at zero
				break; //ends for loop prematurely
			}
		} //end of for loop
		
		//finds location of clicked button and blank panel
		int[] clickLocation = findLocation(click);
		int[] zeroLocation = findLocation(0);
		
		/*
		 * Checks if blank panel is adjacent to clicked button
		 * Button and blank panel are swapped if so
		 */
		//checks if same row
		if(clickLocation[0] == zeroLocation[0]) {
			//checks if left or right
			if(((clickLocation[1] + 1) == zeroLocation[1]) || 
					((clickLocation[1] - 1) == zeroLocation[1])) {
				int temp = this.board[clickLocation[0]][clickLocation[1]];
				this.board[clickLocation[0]][clickLocation[1]] = 0;
				this.board[zeroLocation[0]][zeroLocation[1]] = temp;
				recreate();
			}
		//checks if same column
		}else if(clickLocation[1] == zeroLocation[1]) {
			//checks if above or below
			if(((clickLocation[0] + 1) == zeroLocation[0]) || 
					((clickLocation[0] - 1) == zeroLocation[0])) {
				int temp = this.board[clickLocation[0]][clickLocation[1]];
				this.board[clickLocation[0]][clickLocation[1]] = 0;
				this.board[zeroLocation[0]][zeroLocation[1]] = temp;
				recreate();
			}
		}
	} //end of event handler
} //end of class JEightPuzzleFrame
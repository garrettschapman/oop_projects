/*
 * ImageSelector class for slide_puzzle
 * Uses a JFileChooser to open the directory and allow user to select an image
 */

//import statements
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial") //no need for serialization
public class ImageSelector extends JFrame {
	//variables and panel for ImageSelector
	private File directory;
	private String image = new String();
	private final JPanel panel = new JPanel();
	
	/*
	 * Constructor
	 */
	public ImageSelector(String directory) {
		//sets file directory
		this.directory = new File(directory);
		
		//opens selector and has user select a file
		JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(this.directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG"
				+ " Files", "jpg", "png"); //filters folder to only images
		selector.setFileFilter(filter);
		
		//gets file name and sets it to the image name
		int returnVal = selector.showOpenDialog(panel);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			image = selector.getSelectedFile().getName();
		}
	} //end of constructor
	
	/*
	 * Getter for the image
	 */
	public String getImage() {
		return this.image;
	} //end of getter
} //end of class ImageSelector
/*
 * paint_window class for painter project
 * Creates a window that the user can paint in
 */

//import statements
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial") //serialization is not needed
public class paint_window extends JFrame implements ActionListener, ItemListener {
	private JPanel header = new JPanel();
	
	//buttons
	private JButton undoButton = new JButton("Undo");
	private JButton clearButton = new JButton("Clear");
	
	//selection boxes and appropriate strings
	private String[] colors = {"Red","Green","Blue","Black"};
	private String[] shapes = {"Rectangle","Oval","Edge"};
	private static JComboBox<String> colorSelector;
	private static JComboBox<String> shapeSelector;
	
	//check box
	private JLabel filledLabel = new JLabel("Filled");
	private static JCheckBox filledBox;
	
	//static variables to be used by painter
	protected static int selectedColor;
	protected static int selectedShape;
	protected static Boolean isFilled;
	
	//label for the footer
	protected static JLabel mouseLocation = new JLabel("Mouse is out");
	
	//creates panels
	private Painter painter  = new Painter();
	
	/*
	 * Constructor
	 */
	public paint_window() {
		super("Paint");
		setSize(500,600);
		setLayout(new BorderLayout());
		
		setHeader(); //sets the header
		
		//adds panels and label to the frame
		add(this.header, BorderLayout.NORTH);
		add(this.painter, BorderLayout.CENTER);
		add(mouseLocation, BorderLayout.SOUTH);
	} //end of constructor
	
	/*
	 * Method to set up the header
	 */
	private void setHeader() {
		this.header.setLayout(new FlowLayout());
		
		//initializes static components
		colorSelector = new JComboBox<String>(this.colors);
		shapeSelector = new JComboBox<String>(this.shapes);
		filledBox = new JCheckBox();
		
		//adds listeners for all components
		this.undoButton.addActionListener(this);
		this.clearButton.addActionListener(this);
		colorSelector.addActionListener(this);
		shapeSelector.addActionListener(this);
		filledBox.addItemListener(this);
		
		//adds components to the panel
		this.header.add(this.undoButton);
		this.header.add(this.clearButton);
		this.header.add(colorSelector);
		this.header.add(shapeSelector);
		this.header.add(this.filledLabel);
		this.header.add(filledBox);
		
		//initializes static variables
		selectedColor = colorSelector.getSelectedIndex();
		selectedShape = shapeSelector.getSelectedIndex();
		isFilled = filledBox.isSelected();
	} //end of method setHeader
	
	/*
	 * Main method
	 */
	public static void main(String args[]) {
		paint_window window = new paint_window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		window.setSize(500,600); // set frame size
		window.setResizable(true);
		window.setVisible(true); // display frame
	} //end of main method
	
	/*
	 * Handler for buttons
	 * Checks which button was pressed and causes the proper effect
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Undo")) {
			this.painter.undo();
		}else if(e.getActionCommand().equals("Clear")) {
			this.painter.clear();
		}else if(e.getSource() == colorSelector) {
			selectedColor = colorSelector.getSelectedIndex();
		}else if(e.getSource() == shapeSelector) {
			selectedShape = shapeSelector.getSelectedIndex();
		}
	} //end of method actionPerformed
	
	/*
	 * Handler for the check box
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		isFilled = filledBox.isSelected();
	} //end of method itemStateChanged
} //end of class paint_window
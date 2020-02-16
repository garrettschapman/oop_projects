/*
 * Class Painter for painter project
 * Creates a panel for shapes to be drawn on by user
 */

//import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial") //serialization is not needed
public class Painter extends JPanel implements MouseListener, 
MouseMotionListener {
	//start and end point of new shape
	private int[] start = {0,0};
	private int[] end = {0,0};
	
	//list of shapes and shape that is selected
	private ArrayList<MyShape> shapeList = new ArrayList<MyShape>(0);
	private int selectedShape = -1;
	
	/*
	 * Constructor
	 */
	public Painter() {
		//adds mouse listeners
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	} //end of constructor
	
	/*
	 * Method to add a new shape
	 */
	private void addShape(int color, int shape, Boolean filled) {		
		MyShape newShape = null;
		
		//chooses the proper shape
		if(shape == 0) {
			newShape = new MyRectangle(this.start, this.end);
			newShape.setFilled(filled);
		}else if(shape == 1) {
			newShape = new MyEllipse(this.start, this.end);
			newShape.setFilled(filled);
		}else if(shape == 2) {
			newShape = new MyLine(this.start, this.end);
		}
		
		//chooses the proper color
		if(color == 0) {
			newShape.setColor(new Color(255,0,0));
		}else if(color == 1) {
			newShape.setColor(new Color(0,255,0));
		}else if(color == 2) {
			newShape.setColor(new Color(0,0,255));
		}else if(color == 3) {
			newShape.setColor(new Color(0,0,0));
		}
		
		this.shapeList.add(newShape);
		drawShapes();
	} //end of method addShape
	
	/*
	 * Method to draw all shapes
	 */
	private void drawShapes() {
		Graphics g = this.getGraphics();;
		g.clearRect(0,0,getWidth(),getHeight());
		
		//for loop to draw each shape
		for(int i = 0; i < this.shapeList.size(); i++) {
			this.shapeList.get(i).draw(g);
		} //end of for loop
	} //end of method drawShapes
	
	/*
	 * Method to find selected shape
	 * Searches list of shapes to find shape
	 * Sets selected shape number so it can be moved
	 * Uses -1 as a "bad value"
	 */
	private void findSelectedShape(int x, int y) {
		/*
		 * For loop to find selected shape
		 * Searches loop backward to find shape on top
		 */
		for(int i = (this.shapeList.size() - 1); i >= 0; i--) {
			Boolean selected = this.shapeList.get(i).isSelected(x, y);
			if(selected) {
				this.selectedShape = i;
				return;
			}
		} //end of for loop
		
		this.selectedShape = -1;
	} //end of method findSelectedShape
	
	/*
	 * Method to undo the most recent shape
	 * Removes the last shape entered into the panel
	 * Can be used multiple times to remove multiple shapes
	 */
	public void undo() {
		if(this.shapeList.size() > 0) {
			this.shapeList.remove(this.shapeList.size() - 1);
		}
		
		drawShapes();
	} //end of method undo
	
	/*
	 * Method to clear the painter
	 * Creates an empty list and redraws the panel
	 */
	public void clear() {
		ArrayList<MyShape> emptyList = new ArrayList<MyShape>(0);
		this.shapeList = emptyList;
		
		drawShapes();
	} //end of method clear
	
	/*
	 * Methods to handle mouse clicks and movement
	 * Create new shapes, move shapes, and set location label
	 * Left-clicking draws new shapes
	 * Right-clicking moves shapes
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//Not used
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//Not used
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//sets end point to bad input and sets footer text
		paint_window.mouseLocation.setText("Mouse is out");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			//sets start point to current location
			this.start[0] = e.getX();
			this.start[1] = e.getY();
			this.end[0] = e.getX();
			this.end[1] = e.getY();
			addShape(paint_window.selectedColor, paint_window.selectedShape, 
					paint_window.isFilled);
		}else if(SwingUtilities.isRightMouseButton(e)) {
			findSelectedShape(e.getX(), e.getY());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			this.end[0] = e.getX();
			this.end[1] = e.getY();
			addShape(paint_window.selectedColor, paint_window.selectedShape, 
					paint_window.isFilled);
		}else if(SwingUtilities.isRightMouseButton(e)) {
			if(this.selectedShape != -1) {
				this.shapeList.get(this.selectedShape).move(e.getX(), 
						e.getY());
				drawShapes();
				this.selectedShape = -1;
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			this.undo();
			this.end[0] = e.getX();
			this.end[1] = e.getY();
			addShape(paint_window.selectedColor, paint_window.selectedShape, 
					paint_window.isFilled);
		}else if(SwingUtilities.isRightMouseButton(e)) {
			if(this.selectedShape != -1) {
				this.shapeList.get(this.selectedShape).move(e.getX(), 
						e.getY());
				drawShapes();
			}
		}
		
		//sets end point to current location and sets footer text
		paint_window.mouseLocation.setText("(" + e.getX() + "," + e.getY() + ")");
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//sets end point to current location and sets footer text
		paint_window.mouseLocation.setText("(" + e.getX() + "," + e.getY() + ")");
	}
} //end of class Painter
/*
 * Class file MyLine for painter project
 * Creates a new line in the window
 */

//import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

@SuppressWarnings("serial") //no need for serialization
public class MyLine extends Line2D.Float implements MyShape {
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0; //variables for the line
	private Color color = new Color(255,255,255); //blank color (white)
	
	//difference between a selected point and the start and end points
	private int xDiff1 = 0, yDiff1 = 0, xDiff2 = 0, yDiff2 = 0;
	
	/*
	 * Constructor
	 * Sets start and end points of the line
	 */
	public MyLine(int[] start, int[] end) {
		this.x1 = start[0];
		this.y1 = start[1];
		this.x2 = end[0];
		this.y2 = end[1];
	} //end of constructor
	
	/*
	 * Method to draw the line
	 * Uses start and end point
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawLine(this.x1, this.y1, this.x2, this.y2);
	} //end of method draw
	
	/*
	 * Method to set the color of the line
	 * Can be red, green, blue, or black
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	} //end of method setColor
	
	/*
	 * Method to set whether or not the line is filled
	 * Lines cannot be filled, so nothing happens
	 */
	@Override
	public void setFilled(Boolean filled) {
		//do nothing
	} //end of method setFilled
	
	/*
	 * Method to determine if the line has been selected for movement
	 * Finds difference between start and end points and the selected point
	 * Checks if this distance is the same, meaning the point is on the line
	 */
	@Override
	public boolean isSelected(int x, int y) {
		//finds distance in line lengths
		double distance1 = Math.sqrt(((this.x1 - x) * (this.x1 - x)) + 
				((this.y1 - y) * (this.y1 - y)));
		double distance2 = Math.sqrt(((this.x2 - x) * (this.x2 - x)) + 
				((this.y2 - y) * (this.y2 - y)));
		double distance3 = Math.sqrt(((this.x1 - this.x2) * (this.x1 - 
				this.x2)) + ((this.y1 - this.y2) * (this.y1 - this.y2)));
		
		//sees if point is on the line based on line lengths
		if((int) (distance1 + distance2) == (int) distance3) {
			//sets difference between start and end points and selected point
			this.xDiff1 = this.x1 - x;
			this.yDiff1 = this.y1 - y;
			this.xDiff2 = this.x2 - x;
			this.yDiff2 = this.y2 - y;
			return true; //line is selected
		}
		
		return false; //line is not selected
	} //end of method isSelected
	
	/*
	 * Method to move the line
	 * Takes new location and changes start and end points of the line
	 */
	@Override
	public void move(int dx, int dy) {
		this.x1 = dx + this.xDiff1;
		this.y1 = dy + this.yDiff1;
		this.x2 = dx + this.xDiff2;
		this.y2 = dy + this.yDiff2;
	} //end of method move
} //end of class MyLine
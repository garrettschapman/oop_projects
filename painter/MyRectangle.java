/*
 * Class file MyRectangle for painter project
 * Creates a new rectangle in the window
 */

//import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial") //no need for serialization
public class MyRectangle extends Rectangle2D.Float implements MyShape {
	private int x = 0, y = 0, width = 0, height = 0; //variables for drawing
	private Color color = new Color(255,255,255); //blank color (white)
	private Boolean isFilled; //determines if the shape is solid
	
	private int xDiff = 0, yDiff = 0; //variables for movement
	
	/*
	 * Constructor
	 * Sets start point and width based on which point has greater coordinates
	 */
	public MyRectangle(int[] start, int[] end) {
		if(start[0] > end[0]) { //starts higher than end
			this.x = end[0];
			this.width = start[0] - this.x;
		}else { //ends higher than start
			this.x = start[0];
			this.width = end[0] - this.x;
		}
		
		if(start[1] > end[1]) { //starts higher than end
			this.y = end[1];
			this.height = start[1] - this.y;
		}else { //ends higher than start
			this.y = start[1];
			this.height = end[1] - this.y;
		}
	} //end of constructor
	
	/*
	 * Method to draw the rectangle
	 * Can draw a solid or outline rectangle
	 * Uses the height, width, and start point
	 * 
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color); //sets the rectangle's color
		if(this.isFilled) { //draws a solid rectangle
			g.fillRect(this.x, this.y, this.width, this.height);
		}else { //draws an outline of a rectangle
			g.drawRect(this.x, this.y, this.width, this.height);
		}
	} //end of method draw
	
	/*
	 * Method to set the rectangle's color
	 * Can be red, green, blue, or black
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	} //end of method setColor
	
	/*
	 * Method to determine if the rectangle is filled
	 */
	@Override
	public void setFilled(Boolean filled) {
		this.isFilled = filled;
	} //end of method setFilled
	
	/*
	 * Method to determine if the rectangle is selected
	 * Filled rectangles can be selected from their area
	 * Outlines can only be selected from their outline
	 */
	@Override
	public boolean isSelected(int x, int y) {
		if(this.isFilled) { //tests if filled rectangle is selected
			if(x >= this.x && x <= (this.x + this.width)) { //sees if in x
				if(y >= this.y && y <= (this.y + this.height)) { //sees if in y
					this.xDiff = this.x - x;
					this.yDiff = this.y - y;
					return true; //rectangle is selected
				}
			}
		}else { //sees if outline rectangle is selected
			if(x == this.x) {  //sees if on left leg
				if(y <= (this.y + this.height)) { //sees if within length
					this.xDiff = this.x - x;
					this.yDiff = this.y - y;
					return true; //rectangle is selected
				}
			}else if(y == this.y) { //sees if on top leg
				if(x <= (this.x + this.width)) { //sees if within length
					this.xDiff = this.x - x;
					this.yDiff = this.y - y;
					return true; //rectangle is selected
				}
			}else if(x == (this.x + this.width)) { //sees if on right leg
				if(y <= (this.y + this.height)) { //sees if within length
					this.xDiff = this.x - x;
					this.yDiff = this.y - y;
					return true; //rectangle is selected
				}
			}else if(y == (this.y + this.height)) { //sees if on bottom leg
				if(x <= (this.x + this.width)) { //sees if within length
					this.xDiff = this.x - x;
					this.yDiff = this.y - y;
					return true; //rectangle is selected
				}
			}
		}
		
		return false; //rectangle is not selected
	} //end of method isSelected
	
	/*
	 * Method to move the rectangle if selected
	 * Uses difference between start point and initially selected point
	 * Determines ratio and finds new start point based on ending point
	 */
	@Override
	public void move(int dx, int dy) {
		this.x = this.xDiff + dx;
		this.y = this.yDiff + dy;
	} //end of method move
} //end of class MyRectangle
/*
 * Class file MyEllipse for painter project
 * Creates a new ellipse in the window
 */

//import statement
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

@SuppressWarnings("serial") //no need for serialization
public class MyEllipse extends Ellipse2D.Float implements MyShape {
	private int x = 0, y = 0, width = 0, height = 0;
	private Color color = new Color(0,0,0);
	private Boolean isFilled;
	
	private int xDiff = 0, yDiff = 0;
	private int[] center = {0,0};
	
	/*
	 * Constructor
	 */
	public MyEllipse(int[] start, int[] end) {
		super(start[0], start[1], end[0] - start[0], end[1] - start[1]);
		
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
		
		//sets center
		this.center[0] = this.x + (this.width/2);
		this.center[1] = this.y + (this.height/2);
	} //end of constructor
	
	/*
	 * Method to draw the ellipse
	 * Can draw a solid or an outline ellipse
	 * Uses the height, width, and start point
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color); //sets the ellipse's color
		
		if(this.isFilled) { //draws a filled ellipse
			g.fillOval(this.x, this.y, this.width, this.height);
		}else { //draws an outline ellipse
			g.drawOval(this.x, this.y, this.width, this.height);
		}
	} //end of method draw
	
	/*
	 * Method to set the color of the ellipse
	 * Can be red, green, blue, or black
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	} //end of method setColor
	
	/*
	 * Method to determine if the ellipse is filled
	 */
	@Override
	public void setFilled(Boolean filled) {
		this.isFilled = filled;
	} //end of method setFilled
	
	/*
	 * Method to determine if the ellipse is selected
	 * Filled ellipses can be selected from anywhere in their area
	 * Outline ellipses must be selected from their outline
	 */
	@Override
	public boolean isSelected(int x, int y) {
		Point2D point = new Point(x,y);
		
		if(this.isFilled) { //filled ellipse
			if(this.contains(point)) {
				this.xDiff = this.x - x;
				this.yDiff = this.y - y;
				return true; //ellipse is selected
			}
		}else { //outline ellipse
			/*
			 * Uses the ellipse formula and determines if the point matches
			 * Has a margin of error of 0.1 to allow for easier selection
			 * Ellipse formula is based on if it is horizontal or vertical
			 */
			double check;
			if(this.width >= this.height) { //horizontal ellipse
				check = (((((double) x) - ((double) this.center[0])) * 
						(((double) x) - ((double) this.center[0])))/((((double) 
								this.height/2)) * (((double) this.height/2)))) 
						+ (((((double) y) - ((double) this.center[1])) * 
								(((double) y) - ((double) this.center[1])))/
								((((double) this.width/2)) * (((double) 
										this.width/2))));
			}else { //vertical ellipse
				check = (((((double) x) - ((double) this.center[0])) *
						(((double) x) - ((double) this.center[0])))/((((double)
								this.width/2)) * (((double) this.width/2)))) + 
						(((((double) y) - ((double) this.center[1])) * 
								(((double) y) - ((double) this.center[1])))/
								((((double) this.height/2)) * (((double) 
										this.height/2))));
			}
			
			//determines if the point is on the ellipse
			if(check >= 0.9 && check <= 1.1) {
				this.xDiff = this.x - x;
				this.yDiff = this.y - y;
				return true; //ellipse is selected
			}
		}
				
		return false; //ellipse is not selected
	} //end of method isSelected
	
	/*
	 * Method to move the ellipse
	 * Uses difference between start point and initially selected point
	 * Determines ratio and finds new start point based on ending point
	 * Resets center and ellipse frame
	 */
	@Override
	public void move(int dx, int dy) {
		this.x = this.xDiff + dx;
		this.y = this.yDiff + dy;
		
		this.center[0] = this.x + (this.width/2);
		this.center[1] = this.y + (this.height/2);
		
		this.setFrame(this.x, this.y, this.width, this.height);
	} //end of method move
} //end of class MyEllipse
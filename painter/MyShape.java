/*
 * Interface MyShape for painter project
 * Allows the creation of multiple shapes and storage in one ArrayList
 * Declares all methods for various shapes
 * Shapes: MyRectangle, MyEllipse, MyLine
 */

//import statements
import java.awt.Color;
import java.awt.Graphics;

public interface MyShape {
	void draw(Graphics g); //draws the shape
	void setColor(Color color); //sets the shape's color
	void setFilled(Boolean filled); //determines if the shape is filled
	boolean isSelected(int x, int y); //determines if the shape is selected
	void move(int dx, int dy); //moves the shape
} //end of interface MyShape
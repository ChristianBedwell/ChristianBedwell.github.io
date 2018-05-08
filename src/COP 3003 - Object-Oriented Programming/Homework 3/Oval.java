import java.awt.Color;
import java.awt.Graphics;

public class Oval extends BoundedShape
{
	// call default superclass constructor
	public Oval() {
		super();
	} // end MyOval no-argument constructor

	// call superclass constructor passing parameters
	public Oval(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
		super(x1, y1, x2, y2, color, isFilled);     	     	
   	} // end Oval constructor

	// draw oval
	public void draw(Graphics g) {
		g.setColor(getColor());
      
		if (isFilled()) {
			g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		}			
		else {
			g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		}
	}   
} 
 
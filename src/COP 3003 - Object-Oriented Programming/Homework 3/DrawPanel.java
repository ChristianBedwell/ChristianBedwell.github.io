import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawPanel extends JPanel {
   private Shape[] shapes; // array containing all the shapes
   private int shapeCounter; // total number of shapes drawn

   private int shapeType; // the type of shape to draw
   private Shape currentShape; // the current shape being drawn
   private Color currentColor; // the color of the shape
   private boolean filledShape; // whether this shape is filled
   
   private JLabel statusLabel; // label displaying mouse coordinates
   
   // DrawPanel constructor
   public DrawPanel(JLabel status) {
      shapes = new Shape[100]; // create the array
      shapeCounter = 0; // initially we have no shapes
      
      setShapeType(0); // initially draw lines
      setDrawingColor(Color.BLACK); // start drawing with black
      setFilledShape(false);// not filled by default
      currentShape = null; // not drawing anything initially
            
      setBackground(Color.WHITE); // set a white background
      
      // add the mouse listeners
      MouseHandler mouseHandler = new MouseHandler();
      addMouseListener(mouseHandler);
      addMouseMotionListener(mouseHandler);
      
      // set the status label for displaying mouse coordinates
      statusLabel = status;
   } 

   // draw shapes using polymorphism
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      for (int i = 0; i < shapeCounter; i++)
         shapes[i].draw(g);
      
      if (currentShape != null)
         currentShape.draw(g);
   } 

   // sets the type of shape to draw
   public void setShapeType(int shapeType) {
      if (shapeType < 0 || shapeType > 2)
         shapeType = 0;
      
      this.shapeType = shapeType;
   } 
   
   // sets the drawing color
   public void setDrawingColor(Color c) {
      currentColor = c;
   } 
   
   // clears the last shape drawn
   public void clearLastShape() {
      if (shapeCounter > 0) {
         shapeCounter--;
         repaint();
      }
   } 
   
   // clears all drawings on this panel
   public void clearDrawing() {
      shapeCounter = 0;
      repaint();
   } 

   // sets whether to draw a filled shape
   public void setFilledShape(boolean isFilled) {
      filledShape = isFilled;
   }
   
   // drags shapes that have been drawn
   public void dragShape(int x1, int x2, int y1, int y2, int releasedX, int releasedY, int pressedX, int pressedY) {	   
	   	switch(shapeType) {	   	
	   		case 0: // if shape is a rectangle
	   			
	   			if(filledShape == true) { // if rectangle is filled 
			   		if((pressedX >= x1 && pressedX <= x2) && (pressedY >= y1 && pressedY <= y2)) { // it may be dragged by the border or from inside
			   			int distanceX = releasedX - pressedX;
			   			int distanceY = releasedY - pressedY;
			   			currentShape.setX1(x1 + distanceX); 
			   			currentShape.setY1(y1 + distanceY);
			   			currentShape.setX2(x2 + distanceX);
			   			currentShape.setY2(y2 + distanceY); 
			   			repaint();
			   		}
			   		break;
			   	}
			   	else { // if the rectangle is not filled, then it can only be dragged by the border 
			   		if(((pressedX == x1) && (pressedY >= y1) && (pressedY <= y2)) 
			   			|| ((pressedX == x2) && (pressedY >= y1) && (pressedY <= y2)) 
			   			|| ((pressedY == y1) && (pressedX >= x1) && (pressedX <= x2)) 
			   			|| ((pressedY == y2) && (pressedX >= x1) && (pressedX <= x2))) {
			   			int distanceX = releasedX - pressedX;
			   			int distanceY = releasedY - pressedY;
			   			currentShape.setX1(x1 + distanceX); 
			   			currentShape.setY1(y1 + distanceY);
			   			currentShape.setX2(x2 + distanceX);
			   			currentShape.setY2(y2 + distanceY);
			   			repaint();
			   		}
			   		break;
			   	}
	   			
	   		case 1: // if shape is an oval
	   			
	   			int axisX = (x2 - x1)/2;
	    	    int centerX = x1 + axisX;
	    	    int axisY = (y2 - y1)/2;
	    	    int centerY = y1 + axisY;
	    	     	
	    	    // normalized equation for an oval
	    	    double ovalBounds = (((Math.pow((pressedX - centerX), 2))/(Math.pow(axisX, 2))) 
	    	    		+ ((Math.pow((pressedY - centerY), 2))/(Math.pow(axisY, 2))));
	 	   
	    		if(filledShape == true) { // if oval is filled 
	    			if(ovalBounds <= 1) { // it may be dragged by the border or from the inside
	    				int distanceX = releasedX - pressedX;
	    				int distanceY = releasedY - pressedY;
	    				currentShape.setX1(x1 + distanceX); 
			   			currentShape.setY1(y1 + distanceY);
			   			currentShape.setX2(x2 + distanceX);
			   			currentShape.setY2(y2 + distanceY); 
			   			repaint();
	    			}
	    			break;
	    		}
	    		else { // if the oval is not filled 
	    		 
	    			if(ovalBounds < 1) { // it can only be dragged by the border
	    			
	    				int distanceX = releasedX - pressedX;
	    				int distanceY = releasedY - pressedX;
	    				currentShape.setX1(x1 + distanceX); 
			   			currentShape.setY1(y1 + distanceY);
			   			currentShape.setX2(x2 + distanceX);
			   			currentShape.setY2(y2 + distanceY);
			   			repaint();
		
	    			}
	    			break;
	    		} 
	    		
	   		case 2: // if shape is a line
	   			
	   			int deviationX = pressedX - x1;
	   			int deviationY = pressedY - y1;
	   			int run = x2 - x1;
	   			int rise = y2 - y1;
	   			
	   			double lineBounds = ((deviationX * rise) - (deviationY * run));	   			
	   			
	   			if(lineBounds == 0) { // you can only click the line
	   				int distanceX = releasedX - pressedX;
    				int distanceY = releasedY - pressedY;
    				currentShape.setX1(x1 + distanceX); 
		   			currentShape.setY1(y1 + distanceY);
		   			currentShape.setX2(x2 + distanceX);
		   			currentShape.setY2(y2 + distanceY);	
		   			repaint();
	            }
	   			break;
	   	}
	   	currentShape = null;
   }
   // handles mouse events for this JPanel
   public class MouseHandler extends MouseAdapter
      implements MouseMotionListener {
	  int pressedX;
	  int pressedY;
	  int releasedX;
	  int releasedY;
      // creates and sets the initial position for the new shape
      public void mousePressed(MouseEvent e) {
    	 if(SwingUtilities.isLeftMouseButton(e)) {
    		 if (currentShape != null)
    	            return;

    	     // create the appropriate shape based on shapeType
    	     switch (shapeType) {
    	     	case 0:
    	     		currentShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, filledShape);      
    	               break;
    	        case 1:
    	            currentShape = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, filledShape);      
    	               break;
    	        case 2:
    	            currentShape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
    	               break;    	              
    	     } 
    	 }
    	 else if(SwingUtilities.isRightMouseButton(e)) {
    		 pressedX = e.getX();
    		 pressedY = e.getY();
    		 System.out.printf("(%d,%d)", pressedX, pressedY);
	   		
    	 }
      }

      // fixes the current shape onto the panel
      public void mouseReleased(MouseEvent e) {
    	  if(SwingUtilities.isLeftMouseButton(e)) {    	  
    		  if (currentShape == null)
    			  return;
         
    		  // set the second point on the shape
    		  currentShape.setX2(e.getX());
    		  currentShape.setY2(e.getY());
         
    		  // set the shape only if there is room in the array
    		  if (shapeCounter < shapes.length) {
    			  shapes[shapeCounter] = currentShape;
    			  shapeCounter++;
    		  }
         
    		  currentShape = null; // clear the temporary drawing shape     
    		  repaint();
    	  }
    	  else if(SwingUtilities.isRightMouseButton(e)) {
    		  currentShape = null;
    		  
    		  releasedX = e.getX();
    		  releasedY = e.getY();
    		  System.out.printf("(%d,%d)", releasedX, releasedY);
    		  for(int i = shapeCounter; i >= 0; i--) {
     			 currentShape = shapes[i];
     			 System.out.printf("Debugging: %s\tcurrentShape:\t%s\n",shapeType, currentShape);
     			 if(currentShape != null)    
     			 dragShape(shapes[i].getX1(), shapes[i].getX2(), shapes[i].getY1(), shapes[i].getY2(), releasedX, releasedY, pressedX, pressedY);
     		 }
    	  }
      }
      // update the shape to the current mouse position while dragging
      public void mouseDragged(MouseEvent e) {
        	 if (currentShape != null) {
        		 currentShape.setX2(e.getX());
        		 currentShape.setY2(e.getY());
        		 repaint();
        	 }        	 
         mouseMoved(e); // update status bar
      } 

      // updates the status bar to show the current mouse coordinates
      public void mouseMoved(MouseEvent e) {
    	  	statusLabel.setText(String.format("(%d,%d)", e.getX(), e.getY()));
      } 
      
      // updates the status bar if the mouse exits the panel
      public void mouseExited(MouseEvent e) {
    	  	statusLabel.setText("Mouse is out");
      }
   }
}

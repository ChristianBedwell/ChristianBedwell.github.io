import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenSaver extends JPanel implements MouseWheelListener, ActionListener {
	
	private int[] x = new int[60];
	private int[] y = new int[60];
	private int numOfPoints = 0; // number of points
	private int radius = 10;
	private int delay = 1000; // the delay for spiral growth (1000 milliseconds)
	private double centerX = -1;
	private double centerY = -1;
	private Timer timer = null;
	
	public ScreenSaver() { // constructor
		timer = new Timer(delay, this);
		timer.start();
		addMouseWheelListener(this);
	}
	
	public void actionPerformed(ActionEvent e) { // every time the timer starts, draw a new line
			addAPoint(); // add a new point
			numOfPoints = (numOfPoints + 1) % 60;
			repaint(); // call the paintComponent method
			
			if (numOfPoints == 59) {
				radius = 10;
			}			
	}
	
	public void paintComponent(Graphics g) { // draws the edges between points
		g.clearRect(0,0,(int)getSize().getWidth(),(int)getSize().getHeight());
		g.drawPolyline(x, y, numOfPoints);		
	}
	
	public void addAPoint() { // spiral geometry
		if(centerX < 0) {
			centerX = getSize().getWidth()/2;
		}
		if(centerY < 0) {
			centerY = getSize().getHeight()/2;
		}

		double x = centerX + Math.cos(numOfPoints * Math.PI/3) * radius;
		double y = centerY + Math.sin(numOfPoints * Math.PI/3) * radius;

		this.x[numOfPoints] = (int)x;
		this.y[numOfPoints] = (int)y;

		radius += 3;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) { // changes speed of the spiral
			e.getWheelRotation(); // returns the number of "clicks" the mouse wheel was rotated, as an integer
			if(e.getWheelRotation() > 0) {
				delay += 50; // if "positive", the rate at which the spiral grows, decreases
			}
			else if(e.getWheelRotation() < 0 && delay > 0) {
				delay -= 50; // if "negative", the rate at which the spiral grows, increases
			}
												
			timer.setDelay(delay); // update the delay with new value
			timer.restart();
		}		
	}
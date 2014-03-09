package drawer;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class MousePencil implements MouseMotionListener {
	private Canvas v;
	private boolean isDrawing;
	private int size = 20; //default
	public Color c;
	private final int erasersize = 50;
	private ArrayList<DrawPoint> updatePackage;
	private DrawingEditor boss;
	
	public MousePencil(Canvas v, DrawingEditor boss) {
		v.addMouseMotionListener(this);
		this.v = v;
		isDrawing = true;
		this.boss = boss;
		c = Color.BLACK;
	}
		
	public boolean isDrawing() {
		return isDrawing;
	}
	
	public void draw() {
		isDrawing = true;
	}
	
	public void erase() {
		isDrawing = false;
	}
	//Here's how updates will likely go down. When mouse clicked, it starts building a new array of points to be sent up to the editor.
	//The editor gets the list, puts everything into a string, and signals the tread that it is ready to hand over an update string.
	//Still need a color encoding scheme...
	
	public void mouseDragged(MouseEvent e) {
		DrawPoint point;
		if(isDrawing){
			point = new DrawPoint(e.getPoint(), size, c);
			v.addDrawPoint(point);
		}else{
			point = new DrawPoint(e.getPoint(), erasersize, Color.white);
			v.addDrawPoint(point);
		}
		v.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}
}

package drawer;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

public class MousePencil implements MouseListener, MouseMotionListener {
	private Canvas v;
	private boolean isDrawing;
	private int size = 20;
	public Color c;
	private final int erasersize = 50;
	public boolean updateReady;
	private Updater update;
	
	public MousePencil(Canvas v, ArrayList<String> host) {
		v.addMouseMotionListener(this);
		v.addMouseListener(this);
		this.v = v;
		c = Color.BLACK;
		isDrawing = true;

		update = new Updater(host);
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
	private DrawPoint prepPoint(Point p){
		DrawPoint point;
		if(isDrawing){
			point = new DrawPoint(p, size, c);
			v.addDrawPoint(point);
		}else{
			point = new DrawPoint(p, erasersize, Color.white);
			v.addDrawPoint(point);
		}
		return point;
	}

	@Override
	public void mouseReleased(MouseEvent e){
		System.out.println("released");
		updateReady = true;
		update.readyUp();
	}
	public void mouseDragged(MouseEvent e) {
		DrawPoint point = prepPoint(e.getPoint());
		update.addPoint(point);
		v.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");
		DrawPoint point = prepPoint(e.getPoint());
		update.addPoint(point);
		v.repaint();
	}
}

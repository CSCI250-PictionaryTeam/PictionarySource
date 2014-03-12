package drawer;

import java.awt.Color;
import java.awt.event.*;

public class MousePencil implements MouseListener, MouseMotionListener {
	private Canvas v;
	private boolean isDrawing;
	private int size = 20;
	public Color c;
	private final int erasersize = 50;
	public boolean updateReady;
	private Updater update;
	
	public MousePencil(Canvas v) {
		v.addMouseMotionListener(this);
		v.addMouseListener(this);
		this.v = v;
		c = Color.BLACK;
		isDrawing = true;

		update = new Updater();
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
//	public write(){
//		
//	}
	//Here's how updates will likely go down. When mouse clicked, it starts building a new array of points to be sent up to the editor.
	//The editor gets the list, puts everything into a string, and signals the tread that it is ready to hand over an update string.
	//Still need a color encoding scheme...
	@Override
	public void mouseReleased(MouseEvent e){
		updateReady = true;
		System.out.println(updateReady);
		update.readyUp();
	}
	public void mouseDragged(MouseEvent e) {
		DrawPoint point;
		if(isDrawing){
			point = new DrawPoint(e.getPoint(), size, c);
			v.addDrawPoint(point);
		}else{
			point = new DrawPoint(e.getPoint(), erasersize, Color.white);
			v.addDrawPoint(point);
		}
		update.addPoint(point);
		v.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		DrawPoint point;
		if(isDrawing){
			point = new DrawPoint(e.getPoint(), size, c);
			v.addDrawPoint(point);
		}else{
			point = new DrawPoint(e.getPoint(), erasersize, Color.white);
			v.addDrawPoint(point);
		}
		update.addPoint(point);
		v.repaint();
		update.readyUp();
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		DrawPoint point;
		if(isDrawing){
			point = new DrawPoint(e.getPoint(), size, c);
			v.addDrawPoint(point);
		}else{
			point = new DrawPoint(e.getPoint(), erasersize, Color.white);
			v.addDrawPoint(point);
		}
		update.addPoint(point);
		v.repaint();
	}

}

package drawer;

import java.awt.event.*;

public class MousePencil implements MouseMotionListener {
	private DrawingPanel v;
	private boolean isDrawing;
	
	public MousePencil(DrawingPanel v) {
		v.addMouseMotionListener(this);
		this.v = v;
		isDrawing = true;
	}
	
	private Drawing d() {return v.getDrawing();}
	
	public boolean isDrawing() {
		return isDrawing;
	}
	
	public void draw() {
		isDrawing = true;
	}
	
	public void erase() {
		isDrawing = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / v.xCell();
		int y = e.getY() / v.yCell();
		if(isDrawing){
			d().set(x, y, isDrawing());
		}else{
			chunkyErase(x,y,4);
		}
		v.repaint();
	}
	private void chunkyErase(int x, int y, int size){
		for(int i = x-size; i < x+size+1; i++){
			for(int j = y-3; j<y+4; j++){
				d().set(i, j, isDrawing());
			}
		}
	}
	private void chunkyDraw(int x, int y, int size){
		for(int i = x-size; i < x+size+1; i++){
			for(int j = y-3; j<y+4; j++){
				d().set(i, j, isDrawing());
			}
		}
	}

	public void mouseMoved(MouseEvent e) {}
}

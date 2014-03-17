package drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	ArrayList<DrawPoint> toDraw;
//	HashSet<DrawPoint> toDraw;
	
	public Canvas(){
		super();
		setBackground(Color.white);
		toDraw = new ArrayList<DrawPoint>();
//		toDraw = new HashSet<DrawPoint>();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(DrawPoint circle : toDraw){
			g.setColor(circle.color);
			g.fillOval(circle.x, circle.y, circle.rad, circle.rad);
		}
	}
	public void clear(){
		toDraw = new ArrayList<DrawPoint>();
//		toDraw = new HashSet<DrawPoint>();
		repaint();
	}
	public void addDrawPoint(DrawPoint d){
		toDraw.add(d);
		repaint();
	}
	public void addDrawString(String s){ //encode scheme =  & DrawPoint.toString() & DrawPoint.toString() & etc...
		String[] all = s.split("\\&");
		for(int i = 1; i < all.length; i++){
			toDraw.add(new DrawPoint(all[i]));
		}
		repaint();
	}
	public Color stringToColor(String s){
		return Color.BLACK;
	}
}

package drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ArrayList<DrawPoint> toDraw;
	
	
	public Canvas(){
		super();
		setBackground(Color.white);
		toDraw = new ArrayList<DrawPoint>();
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
		repaint();
	}
	public void addDrawPoint(DrawPoint d){
		toDraw.add(d);
		repaint();
	}
	public void addDrawString(String s){ //encode scheme =  int :  & DrawPoint.toString() & DrawPoint.toString() & etc...
		String[] first = s.split(":");
		
		Color color = stringToColor(first[0]);//needs an edit
		
		String[] all = first[1].split("&");
		for(String buildString : all){
			toDraw.add(new DrawPoint(buildString, color));
		}
	}
	public Color stringToColor(String s){
		return Color.BLACK;
	}
}

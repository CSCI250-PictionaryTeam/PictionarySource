package drawer;

import java.awt.Color;
import java.awt.Point;

public class DrawPoint {
	public final int x, y, rad;
	public final Color color;
	
	public DrawPoint(int x, int y, int radius, Color c){
		this.x = x;
		this.y = y;
		rad = radius;
		color = c;
		System.out.println(c.toString());
	}
	public DrawPoint(Point p, int size, Color c){
		x = p.x - 15;
		y = p.y - 15;
		rad = size;
		color = c;
	}
	public DrawPoint(String s){
		String[] all = s.split("-");
		System.out.println(all[0] + "wtf");
		this.rad = Integer.parseInt(all[0]);
		this.x = Integer.parseInt(all[1]);
		this.y = Integer.parseInt(all[2]);
		this.color = Color.getColor(all[2]);
		
	}
	@Override
	public String toString(){ //we can work on this
		String updateSafe = new String();
		updateSafe = "&" + rad + "-" + x + "-" + y + "-" + String.valueOf(color.getRGB());
		return updateSafe;
	}
	@Override
	public int hashCode(){
		return this.toString().hashCode();
	}
}

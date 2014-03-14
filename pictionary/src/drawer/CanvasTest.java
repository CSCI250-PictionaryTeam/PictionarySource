package drawer;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class CanvasTest {
	Canvas c;
	@Before
	public void setup(){
		c = new Canvas();
		
	}
	@Test
	public void addTestAndDrawPointConstructionTest() {
		assertEquals(0, c.toDraw.size());
		c.addDrawPoint(new DrawPoint(3,4,15,Color.red));
		c.addDrawPoint(new DrawPoint(new Point(85, 24), 15, Color.black));
		assertEquals(2, c.toDraw.size());
	}
	@Test
	public void updaterTestPackageAndDrawStringTest(){
		Updater up = new Updater();
		for(int x=0; x<10; x++){
				up.addPoint(new DrawPoint(x, x, 20, Color.black));

		}
		up.readyUp();
		String upcatch = up.getUpdate();
		c.addDrawString(upcatch);
		System.out.println("update: " + upcatch);
		System.out.println(c.toDraw.size());
		assertEquals(10, c.toDraw.size());
		assertEquals(0, up.toSend.toString().length());
	}
}

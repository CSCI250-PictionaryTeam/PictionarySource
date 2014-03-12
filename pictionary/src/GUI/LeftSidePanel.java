package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import drawer.DrawingEditor;

//the left side of the main GUI with scoreboard and the drawer

@SuppressWarnings("serial")
public class LeftSidePanel extends JPanel{
	private DrawingEditor drawer;
	private ScoreBoardPanel scores;
	
	public LeftSidePanel() throws IOException{
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		drawer = new DrawingEditor();
		drawer.setBorder(BorderFactory.createLineBorder(Color.black));
		scores = new ScoreBoardPanel();
		scores.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(drawer);
		add(scores);
	}
	
	public void update(){
		drawer.update();
	}
}

package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import drawer.Canvas;
import drawer.DrawingEditor;

//the left side of the main GUI with scoreboard and the drawer

@SuppressWarnings("serial")
public class LeftSidePanel extends JPanel{
	private DrawingEditor drawer;
	private LobbyPanel lobby;
	private ScoreBoardPanel scores;
	
	public LeftSidePanel(boolean isConnecting) throws IOException{
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		drawer = new DrawingEditor();
		drawer.setBorder(BorderFactory.createLineBorder(Color.black));
		scores = new ScoreBoardPanel();
		scores.setBorder(BorderFactory.createLineBorder(Color.black));
		lobby = new LobbyPanel();
		lobby.setBorder(BorderFactory.createLineBorder(Color.black));
		
		if (isConnecting){
			add(lobby);
		}else{
			add(drawer);
			add(scores);
		}
	}
	
	public void refresh(){
		drawer.refresh();
	}
	
	public void setDrawer(boolean isDrawer){
		drawer.setDrawer(isDrawer);
	}
	
	public Canvas getCanvas(){
		return drawer.getCanvas();
	}
}

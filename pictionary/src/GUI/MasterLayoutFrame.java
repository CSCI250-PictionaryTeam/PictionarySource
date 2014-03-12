package GUI;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

//puts all of the panels on and contains the main

@SuppressWarnings("serial")
public class MasterLayoutFrame extends JFrame{
	private LeftSidePanel leftSide;
	private ChatSidePanel rightSide;
	
	public MasterLayoutFrame() throws IOException{
		setTitle("P2P Pictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(new Resize());
		
		setLayout(new MigLayout());
		
		leftSide = new LeftSidePanel();
		rightSide = new ChatSidePanel();
		rightSide.setBorder(BorderFactory.createLineBorder(Color.black));
		add(leftSide, "pushx, growx, pushy, growy");
		add(rightSide, "growy");
		pack();
		refresh();
	}
	
	private class Resize implements ComponentListener{
		@Override
		public void componentHidden(ComponentEvent arg0) {
		}
		@Override
		public void componentMoved(ComponentEvent arg0) {
		}
		@Override
		public void componentResized(ComponentEvent arg0) {
			leftSide.refresh();
			rightSide.refresh();
		}
		@Override
		public void componentShown(ComponentEvent arg0) {
		}
	}
	
	public void setDrawer(boolean isDrawer){
		leftSide.setDrawer(isDrawer);
	}
	
	public void refresh(){
		leftSide.refresh();
		rightSide.refresh();
	}
	
	public static void main(String[] args) throws IOException {
		MasterLayoutFrame master = new MasterLayoutFrame();
		master.setVisible(true);
		master.setDrawer(true);
		master.refresh();
	}
}

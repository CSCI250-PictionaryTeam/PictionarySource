package GUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

//puts all of the panels on and contains the main

@SuppressWarnings("serial")
public class MasterLayoutFrame extends JFrame{
	
	public MasterLayoutFrame() throws IOException{
		setTitle("SwingDemo1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		LeftSidePanel leftSide = new LeftSidePanel();
		add(leftSide);
		RightSidePanel rightSide = new RightSidePanel();
		rightSide.setBorder(BorderFactory.createLineBorder(Color.black));
//		.createBevelBorder(BevelBorder.RAISED)
		add(rightSide);
		pack();
		leftSide.update();
	}
	
	public static void main(String[] args) throws IOException {
		new MasterLayoutFrame().setVisible(true);
	}
}

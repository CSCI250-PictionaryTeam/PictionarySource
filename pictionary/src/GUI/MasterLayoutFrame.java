package GUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class MasterLayoutFrame extends JFrame{
	
	public MasterLayoutFrame() throws IOException{
		setTitle("SwingDemo1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		add(new LeftSidePanel());
		RightSidePanel rightSide = new RightSidePanel();
		rightSide.setBorder(BorderFactory.createLineBorder(Color.black));
//		.createBevelBorder(BevelBorder.RAISED)
		add(rightSide);
		
		pack();
	}
	
	public static void main(String[] args) throws IOException {
		new MasterLayoutFrame().setVisible(true);
	}
}

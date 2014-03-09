import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class MasterLayoutFrame extends JFrame{
	
	public MasterLayoutFrame() throws IOException{
		setTitle("SwingDemo1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		add(new LeftSidePanel());
		add(new RightSidePanel());
		
		pack();
	}
	
	public static void main(String[] args) throws IOException {
		new MasterLayoutFrame().setVisible(true);
	}
}

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class RightSidePanel extends JPanel{
	private JTextPane chatArea;
	private JTextField chatEntryField;
	
	public RightSidePanel(){
		setPreferredSize(new Dimension(400, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		chatArea = new JTextPane();		
		chatArea.setPreferredSize(new Dimension(300, 650));
		chatEntryField = new JTextField(10);
		
		add(chatArea);
		add(chatEntryField);
	}

}

package GUI;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


@SuppressWarnings("serial")
public class RightSidePanel extends JPanel{
	private JTextPane chatArea;
	private JTextField chatEntryField;
	private ArrayList<String> chatLog;
	
	public RightSidePanel(){
		setPreferredSize(new Dimension(400, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		chatArea = new JTextPane();		
		chatArea.setPreferredSize(new Dimension(300, 650));
		chatArea.setEditable(false);
		chatEntryField = new JTextField(10);
		chatEntryField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chat"));
		chatEntryField.addKeyListener(new EnterPressed());
		
		chatArea.setText("Welcome to the game!" + "\n" + "player1: this is the chat log" + "\n" + "player2: This is sort of what it should look like" + "\n");
		
		add(chatArea);
		add(chatEntryField);
	}
	
	public String getTextInArea(){
		return chatEntryField.getText();
	}
	
	private class EnterPressed implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
				Document doc = chatArea.getDocument();
				try {
					doc.insertString(doc.getLength(), "player2: " + getTextInArea() + "\n", null);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				chatEntryField.setText("");
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

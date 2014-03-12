package GUI;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ChatStuff.ChatThread;
import ChatStuff.Receiver;

//the right side of the main GUI, it contains all the chat stuff.
//Right now some stuff gets displayed by default, but that will all have to get replaced with something that implements a chatLog

@SuppressWarnings("serial")
public class ChatSidePanel extends JPanel implements Runnable{
	private JTextPane chatViewArea;
	private JTextField chatEntryField;
	private JScrollPane chatScrollPane;
	private ArrayList<String> UserIPList;
	private Integer port;
	protected DataInputStream i;
	protected DataOutputStream o;
	private ArrayBlockingQueue<String> chatQueue;
	private ChatThread chatter;
//	private ArrayList<String> chatLog;
	
	public ChatSidePanel() throws IOException{
		port = 8888;
		chatQueue = new ArrayBlockingQueue<String>(20);
		Receiver receiver = new Receiver(port, chatViewArea, chatQueue);
		receiver.start();
		UserIPList = new ArrayList<String>();
		UserIPList.add(Inet4Address.getLocalHost().getHostAddress());
		
		setPreferredSize(new Dimension(400, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		chatViewArea = new JTextPane();		
		chatScrollPane = new JScrollPane(chatViewArea);
		chatScrollPane.setPreferredSize(new Dimension(300, 650));
		chatViewArea.setEditable(false);
		chatEntryField = new JTextField(10);
		chatEntryField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chat"));
		chatEntryField.addKeyListener(new EnterPressed());
		
		chatViewArea.setText("Welcome to the game!" + "\n" + "player1: this is the chat log" + "\n" + "player2: This is sort of what it should look like" + "\n");
		
		add(chatScrollPane);
		add(chatEntryField);
	}
	
	public String getTextInArea(){
		return chatEntryField.getText();
	}
	
	public void refresh(){
		chatScrollPane.setPreferredSize(new Dimension(300, this.getHeight()));
	}
	
	private void send(String msg, String host, int port) throws IOException {
		if (chatter != null && chatter.isGoing()) {
			chatter.halt();
		}
		chatter = new ChatThread(msg, host, port, chatQueue);
		new Receiver(port, chatViewArea, chatQueue).start();
		chatter.start();
	}
	
	private class EnterPressed implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
				Document doc = chatViewArea.getDocument();
				try {
					doc.insertString(doc.getLength(), "player2: " + getTextInArea() + "\n", null);
					send(getTextInArea(), UserIPList.get(0), port);
				} catch (BadLocationException e) {
					e.printStackTrace();
				} catch (IOException e1){
					e1.printStackTrace();
				}
				chatEntryField.setText("");
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {}
		@Override
		public void keyTyped(KeyEvent arg0) {}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

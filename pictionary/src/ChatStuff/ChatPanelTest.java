package ChatStuff;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

import GUI.ChatSidePanel;

public class ChatPanelTest {
	JFrame testFrame;
	ChatSidePanel testChat;
	
	public ChatPanelTest() throws IOException {
		
		testFrame = new JFrame("Testing");
		testFrame.setSize(600, 400);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setTitle("Testing");
		testFrame.setLayout(new BorderLayout());
		testChat = new ChatSidePanel();
		testFrame.add(testChat);
		testFrame.setVisible(true);
	}
	
	@Test
	public void testPanelLayout() {
		/*
		JFrame testFrame = new JFrame("Testing");
		testFrame.setSize(600, 400);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setTitle("Testing");
		testFrame.setLayout(new BorderLayout());
		ChatPanel testChat = new ChatPanel();
		testFrame.add(testChat);
		testFrame.setVisible(true);
		*/
	}
//	@Test
//	public void testAddChat() {
//		testChat.getChatLog().addChat("hello world");
//		System.out.println(testChat.getChatLog().getChatLogArray());
//	}

}

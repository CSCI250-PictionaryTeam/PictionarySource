package ChatStuff;


import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Receiver extends Thread {
	private ServerSocket accepter;
	private ReceiverThread receiverThread;
	private ArrayBlockingQueue<String> chatQueue;
	private JTextPane chatBox;

	//note from Sam
	//We may need to modify our receiver for 3 types of inputs
	//game state notices -- which will be related to the game master class we need 
	//chat messages -- which will need to be checked for an answer before being passed on
	//update messages -- which can just be immediately sent to the canvas
	//Suggestion chat messages get preceded with  :1:string
	// while drawing  messages get preceded with  :2:string
	// and game state messages get preceded with  :3:string
	
	public Receiver(int port, JTextPane chatBox, ArrayBlockingQueue<String> chatQueue) throws IOException {
		this.chatQueue = chatQueue;
		this.chatBox = chatBox;
		accepter = new ServerSocket(port);
		System.out.println("Server IP address: " + accepter.getInetAddress());
	}

	public void listen() throws IOException, InterruptedException {
		for (;;) {
			Socket s = accepter.accept();
			System.out.println("Connection accepted from " + s.getInetAddress());
			receiverThread = new ReceiverThread(chatQueue, chatBox, s);
		}
	}

	public void run() {
		try {
			listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

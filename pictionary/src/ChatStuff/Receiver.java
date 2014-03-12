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

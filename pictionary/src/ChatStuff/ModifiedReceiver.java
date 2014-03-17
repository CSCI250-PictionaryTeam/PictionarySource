package ChatStuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTextPane;

import GUI.MasterLayoutFrame;

public class ModifiedReceiver {
	private ServerSocket accepter;
	private ReceiverThread receiverThread;
	private ArrayBlockingQueue<String> chatQueue,updateQueue;
	private MasterLayoutFrame GUIBoss;
	private JTextPane chatBox;
	private final int port = 8888;

	//note from Sam
	//We may need to modify our receiver for 3 types of inputs
	//game state notices -- which will be related to the game master class we need 
	//chat messages -- which will need to be checked for an answer before being passed on
	//update messages -- which can just be immediately sent to the canvas
	//Suggestion chat messages get preceded with  :1:string
	// while drawing  messages get preceded with  :2:string
	// and game state messages get preceded with  :3:string
	
	public ModifiedReceiver(MasterLayoutFrame GUIBoss) throws IOException {
		this.chatQueue = chatQueue;
		this.chatBox = chatBox;
//		chatBox = GUIBoss.getchatbox
		accepter = new ServerSocket(port);
		System.out.println("Server IP address: " + accepter.getInetAddress());
	}

	public void listen() throws IOException, InterruptedException {
		for (;;) {
			Socket s = accepter.accept();
			System.out.println("Connection accepted from " + s.getInetAddress());
			receiverThread = new ModifiedReceiverThread(chatQueue, updateQueue, GUIBoss, s);
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

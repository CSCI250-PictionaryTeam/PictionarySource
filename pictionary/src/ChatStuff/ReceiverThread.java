package ChatStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ReceiverThread extends Thread {
	private ArrayBlockingQueue<String> chatQueue;
	private JTextPane chatBox;
	private Socket Connection;
	private boolean going;

	public ReceiverThread(ArrayBlockingQueue<String> chatQueue, JTextPane chatBox, Socket Connection) throws IOException, InterruptedException {
		this.chatQueue = chatQueue;
		this.chatBox = chatBox;
		this.Connection = Connection;
		this.going = true;
		BufferedReader responses = new BufferedReader(new InputStreamReader(Connection.getInputStream()));

		while (going) {
			while (going && !responses.ready()) {}
			String line = responses.readLine();
			//THIS IS OUR DECODER
			if (line != null) {chatQueue.put(line);}
		}
		going = false;
		Connection.close();
	}
}

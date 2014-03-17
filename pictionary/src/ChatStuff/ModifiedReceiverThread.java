package ChatStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTextPane;

import GUI.MasterLayoutFrame;

public class ModifiedReceiverThread {
	private ArrayBlockingQueue<String> chatQueue;
	private ArrayBlockingQueue<String> updateQueue;
	private Socket Connection;
	private boolean going;

	public ModifiedReceiverThread(ArrayBlockingQueue<String> chatQueue, ArrayBlockingQueue<String> updateQueue, MasterLayoutFrame gui, Socket Connection) throws IOException, InterruptedException {
		this.chatQueue = chatQueue;
		this.updateQueue = updateQueue;
		this.Connection = Connection;
		this.going = true;
		BufferedReader responses = new BufferedReader(new InputStreamReader(Connection.getInputStream()));

		while (going) {
			while (going && !responses.ready()) {}
			String line = responses.readLine();
			//THIS IS OUR DECODER
			if (line != null) {
				categorizeLine(line);
			}
		}
		going = false;
		Connection.close();
	}
	private void categorizeLine(String line) throws InterruptedException{
		if(isDrawUpdate(line)){
			updateQueue.put(line);
		}else if(isChatUpdate(line)){
			updateQueue.put(line);
		}else if(isResponseUpdate(line)){
			
		}
	}
	private boolean isDrawUpdate(String line){
		String[] stuff = line.split("**e2$4j**");
		if(stuff[0].equals("draw")){
			return true;
		}
		return false;
	}
	private boolean isChatUpdate(String line){
		String[] stuff = line.split("**e2$4j**");
		if(stuff[0].equals("chat")){
			return true;
		}
		return false;
	}
	private boolean isResponseUpdate(String line){
		String[] stuff = line.split("**e2$4j**");
		if(stuff[0].equals("response")){
			return true;
		}
		return false;
	}

}

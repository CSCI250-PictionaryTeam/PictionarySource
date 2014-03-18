package ChatStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTextPane;

import dataAndThreads.QueueHolder;

import GUI.MasterLayoutFrame;

public class ModifiedReceiverThread {
//	private ArrayBlockingQueue<String> chatQueue;
//	private ArrayBlockingQueue<String> updateQueue;
	private QueueHolder data;
	private Socket Connection;
	private boolean going;

	public ModifiedReceiverThread(QueueHolder data, MasterLayoutFrame gui, Socket Connection) throws IOException, InterruptedException {
		this.data = data;
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
			data.addDrawString(line);
		}else if(isChatUpdate(line)){
			data.addChatString(line);
		}else if(isResponseUpdate(line)){
			//EDIT THIS, get our nomenclature straight
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

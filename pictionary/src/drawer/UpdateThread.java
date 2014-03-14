package drawer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class UpdateThread extends Thread {
	private String msg, host;
	private int port;
	private boolean going;
	private Socket s;
	private ArrayBlockingQueue<String> chatQueue;

	public UpdateThread(String drawingUpdate, String host, Integer port, ArrayBlockingQueue<String> updateQueue) {
		this.msg = drawingUpdate;
		this.host = host;
		this.port = port;
//		this.chatQueue = chatQueue;
		going = true;
	}

	public synchronized boolean isGoing() {
		return going;
	}

	@Override
	public void run() {
		s = null;
		try {
			s = new Socket(host, port);
			PrintWriter writer = new PrintWriter(s.getOutputStream());
			writer.print(msg);
			writer.flush();
			going = false;

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (s != null) {s.close();}
			} catch (IOException ioe) {
				System.out.println("error closing socket");
			}
		}		
	}

	public synchronized void halt() {
		going = false;
	}
}



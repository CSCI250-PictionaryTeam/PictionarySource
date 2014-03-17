package drawer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class UpdateSendThread extends Thread {
	private String update, host;
	private boolean going;

	public UpdateSendThread(String update, String host) {
		this.host = host;
		going = true;
	}

	public synchronized boolean isGoing() {
		return going;
	}

	@Override
	public void run() {
		int port = 8888;
		Socket s = null;
		try{
			s = new Socket(host, port);
			PrintWriter writer = new PrintWriter(s.getOutputStream());
			writer.print(update);
			writer.flush();
			going = false;
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try{
				if(s != null){s.close();}
			}catch(IOException ioe){
				System.out.println("error closing socket");
			}
		}
		
	}

	public synchronized void halt() {
		going = false;
	}
}



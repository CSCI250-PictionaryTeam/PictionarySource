package dataAndThreads;

import java.util.concurrent.ArrayBlockingQueue;

public class QueueHolder {
	public ArrayBlockingQueue<String> drawUpdates, chatUpdates, gameUpdates, connectionUpdates;
	public QueueHolder(){
		drawUpdates = new ArrayBlockingQueue<String>(20);
		chatUpdates = new ArrayBlockingQueue<String>(20);
		gameUpdates = new ArrayBlockingQueue<String>(20);
		connectionUpdates = new ArrayBlockingQueue<String>(20);
	}
	public void addDrawString(String str) throws InterruptedException{
		drawUpdates.put(str);
	}
	public void addChatString(String str) throws InterruptedException{
		chatUpdates.put(str);
	}
	public void addGameString(String str) throws InterruptedException{
		gameUpdates.put(str);
	}
	public void addConnectionString(String str) throws InterruptedException{
		connectionUpdates.put(str);
	}

	public String getDrawString() throws InterruptedException{
		return drawUpdates.poll();
	}
	public String getChatString() throws InterruptedException{
		return chatUpdates.poll();
	}
	public String getGameString() throws InterruptedException{
		return gameUpdates.poll();
	}
	public String getConnectionString() throws InterruptedException{
		return connectionUpdates.poll();
	}
	
	public boolean allEmpty(){
		if(drawUpdates.isEmpty() & chatUpdates.isEmpty() & gameUpdates.isEmpty() & connectionUpdates.isEmpty()){
			return true;
		}
		return false;
	}
}

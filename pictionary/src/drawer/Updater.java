package drawer;

import java.util.ArrayList;

public class Updater {
	
	private ArrayList<DrawPoint> outgoingNotReady;
	StringBuilder toSend;
	private boolean ready;
	
	public Updater(){
		outgoingNotReady = new ArrayList<DrawPoint>();
		ready = false;
		toSend = new StringBuilder();
	}
	public void readyUp() {
		for(DrawPoint point: outgoingNotReady){
			toSend.append(point.toString());
		}
		outgoingNotReady = new ArrayList<DrawPoint>();
		System.out.println("Readied up");
		ready = true;
	}
	public boolean isReady(){
		return ready;
	}
	public void addPoint(DrawPoint dp){
		System.out.println("Receieved: " +dp.toString());
		outgoingNotReady.add(dp);
	}
	public String getUpdate(){
		String result = toSend.toString();
		clearSend();
		ready = false;
		return result;
	}
	private void clearSend(){
		toSend = new StringBuilder();
	}
	//NEEDS TO BE ABLE TO BLAST OUT AN UPDATE!
	private void dispatch(){
		
	}
}

package drawer;

import java.util.ArrayList;

public class Updater {
	
	private ArrayList<DrawPoint> outgoingNotReady;
	private String send;
	private boolean ready;
	
	public Updater(){
		outgoingNotReady = new ArrayList<DrawPoint>();
		ready = false;
		send = "";
	}
	public void readyUp() {
		for(DrawPoint point: outgoingNotReady){
			send.concat(point.toString());
		}
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
	//NEEDS TO BE ABLE TO BLAST OUT AN UPDATE!
}

package drawer;

import java.util.ArrayList;

public class Updater {
	
	private ArrayList<DrawPoint> outgoingNotReady;
	StringBuilder toSend;
	private ArrayList<String> hosts;
	
	public Updater(ArrayList<String> hostList){
		outgoingNotReady = new ArrayList<DrawPoint>();
		toSend = new StringBuilder("draw**e2$4j**");
		hosts = hostList;
	}
	public void readyUp() {
		for(DrawPoint point: outgoingNotReady){
			toSend.append(point.toString());
		}
		outgoingNotReady = new ArrayList<DrawPoint>();
		System.out.println("Readied up");
		System.out.println("Dispatching to all");
		dispatchToAll(toSend.toString(), hosts);
	}
	public void addPoint(DrawPoint dp){
		System.out.println("Receieved: " +dp.toString());
		outgoingNotReady.add(dp);
	}
	public String getUpdate(){
		String result = toSend.toString();
//		clearSend();
		return result;
	}
	private void clearSend(){
		toSend = new StringBuilder("draw**e2$4j**");
	}
	//NEEDS TO BE ABLE TO BLAST OUT AN UPDATE!
	private void dispatchToAll(String update, ArrayList<String> all){
		for(String host: all){
			UpdateSendThread out = new UpdateSendThread(update, host);
			out.start();
		}
	}
}

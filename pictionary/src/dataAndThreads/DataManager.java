package dataAndThreads;

import GUI.*;
import drawer.*;

public class DataManager extends Thread{
	
	private Canvas painting;
	private MasterLayoutFrame master;
	private QueueHolder q;
//	private chatField

	public DataManager(MasterLayoutFrame boss, QueueHolder holder){
		master = boss;
		painting = boss.getCanvas();
		q = holder;
	}
//	public synchronized boolean isGoing(){
//		return going;
//	}
	@Override
	public void run(){
		while(q.allEmpty()){}
		while(!q.allEmpty()){
			if(!q.drawUpdates.isEmpty()){
				painting.addDrawString(q.drawUpdates.poll());
			}
			if(!q.chatUpdates.isEmpty()){
				//send to chat
			}
			if(!q.gameUpdates.isEmpty()){
				//send where-ever
			}
			if(!q.connectionUpdates.isEmpty()){
				//send where-ever
			}
		}
	}
}

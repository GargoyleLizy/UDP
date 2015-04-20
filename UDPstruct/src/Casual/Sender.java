package Casual;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import UDPThread.SimpleClientThread;

public class Sender {
	private ArrayList<String> ip_group = new ArrayList();
	private LinkedBlockingQueue<String> cmd_que = new LinkedBlockingQueue();
	
	public Sender(){
		
	}
	
	public Sender(ArrayList<String> input_group){
		 this.ip_group = input_group;
		 
	}
	
	public void simple_init(){
		SimpleClientThread simple_client = new SimpleClientThread(ip_group,cmd_que); 
		simple_client.start();
	}
	
	public void  push_cmd(String cmd){
		cmd_que.add(cmd);
		
		//System.out.println("sender 1:"+cmd_que.toString()+ cmd_que.isEmpty());
	}
	
	
}

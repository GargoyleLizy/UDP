package Casual;

import java.util.concurrent.LinkedBlockingQueue;

import UDPThread.SimpleServerThread;

public class Receiver {
	private LinkedBlockingQueue<String> cmd_que = new LinkedBlockingQueue();

	public Receiver(){
		
	}
	public void simple_init(){
		SimpleServerThread simple_server = new SimpleServerThread(cmd_que);
		simple_server.start();
	}
	
	public String  pull_cmd(){
		return cmd_que.poll();
	}
	
	public boolean exist_cmd(){
		return !cmd_que.isEmpty();
	}
	
	
}

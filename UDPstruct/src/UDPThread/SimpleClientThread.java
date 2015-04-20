package UDPThread;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class SimpleClientThread extends Thread{
	private ArrayList<String> ip_group = new ArrayList();
	private  LinkedBlockingQueue<String> cmd_que = new LinkedBlockingQueue();
	
	
	
	public SimpleClientThread(ArrayList<String> ip_group,LinkedBlockingQueue<String> cmd_que){
		this.ip_group = ip_group;
		Iterator<String> ip_iter = ip_group.iterator();
		this.cmd_que = cmd_que;
	}
	
	public void run(){
		System.out.println("simple client start running");
		System.out.println(ip_group.toString());
		
		// important variables
		String cmd = null;
		byte[] buf = new byte[CONFIG.BUF_LEN];
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Saying hello
		String hello = "hello";
		buf = hello.getBytes();
		/*
		try {
			broadcast(buf,socket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		while(true){		
			
			if(cmd_que.isEmpty() ){
				
				try{
					// test!!!!
					//System.out.println("there is no cmd in queue");
					sleep(CONFIG.SIMPLE_INTV);
				}catch(InterruptedException e){
				}
			}
			else {
				
					//System.out.println(cmd_que.toString());
					cmd = cmd_que.poll();
					buf = cmd.getBytes();
					try {
						// test
						System.out.println("ready to broadcast "+ cmd);
						broadcast(buf,socket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
		
		
	}
	
	protected void broadcast(byte[] buf, DatagramSocket socket) throws  IOException{
		Iterator<String> ip_iter = ip_group.iterator();
		
		if(ip_iter.hasNext()){
			InetAddress address = InetAddress.getByName(ip_iter.next());
			DatagramPacket packet = new DatagramPacket(buf,buf.length,address,CONFIG.UDP_PORT);
			socket.send(packet);
			// For debug
			System.out.println("client send:" + buf.toString()); 
		}
	}
	
}

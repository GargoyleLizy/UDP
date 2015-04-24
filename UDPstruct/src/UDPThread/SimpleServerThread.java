package UDPThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleServerThread extends Thread {
	protected BufferedReader in= null;
	protected DatagramSocket socket = null;
	protected boolean moreQuotes = true;
	private LinkedBlockingQueue<String> cmd_que = new LinkedBlockingQueue();
	
	public SimpleServerThread(LinkedBlockingQueue<String> cmd_que){
		this.cmd_que = cmd_que;
		try {
			this.socket = new DatagramSocket(CONFIG.UDP_PORT);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		System.out.println("Server running, waiting fro client");
		String ip_and_cmd = null;
		while(true){
			try{
				byte[] buf = new byte[CONFIG.BUF_LEN];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				String cmd = new String(packet.getData(), 0, packet.getLength());
				InetAddress address = packet.getAddress();
				ip_and_cmd = address.toString() + " " +cmd; 
				
				// For Test
				System.out.println("serverthread get:"+ ip_and_cmd);
				cmd_que.add(ip_and_cmd);
			}catch(IOException e){
				System.out.println("server error");
			}
		}
		
	}
}

import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastClient {
	public static void main(String[] args) throws IOException{
		MulticastSocket socket = new MulticastSocket(CONFIG.UDP_PORT);
		InetAddress address = InetAddress.getByName(CONFIG.MULTI_GROUP);
		socket.joinGroup(address);
		
		DatagramPacket packet;
		
		for(int i =0; i<5; i++){
			byte[] buf = new byte[CONFIG.BUF_LEN];
			packet = new DatagramPacket(buf,buf.length);
			socket.receive(packet);
			
			String received = new String(packet.getData(),0,packet.getLength());
			System.out.println("Quote of the Moment: " + received);
		}
		
		socket.leaveGroup(address);
		socket.close();
	}
}

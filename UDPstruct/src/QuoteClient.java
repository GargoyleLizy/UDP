import java.io.IOException;
import java.net.*;


public class QuoteClient {
	public static void main(String[] args) throws IOException{
		if(args.length  != 1){
			System.out.println("Usage: java Qutote Clinet <hostname>");
			return;
		}
		
		// get a datagram socket
		DatagramSocket socket = new DatagramSocket();
		
		//send request
		byte[] buf = new byte[CONFIG.BUF_LEN];
		InetAddress address = InetAddress.getByName(args[0]);
		DatagramPacket packet = new DatagramPacket(buf, buf.length,address, CONFIG.UDP_PORT);
		socket.send(packet);
		
		// get response 
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		
		// display response
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println("Quote of the moment:" + received);
	}
	
}

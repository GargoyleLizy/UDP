import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteServerThread extends Thread {
	protected BufferedReader in= null;
	protected DatagramSocket socket = null;
	protected boolean moreQuotes = true;
	
	public QuoteServerThread() throws IOException{
		this("QuoteServer");
	}
	
	public QuoteServerThread(String name) throws IOException{
		super(name);
		socket= new DatagramSocket(CONFIG.UDP_PORT);
		
		try{
			in = new BufferedReader(new FileReader("one-liners.txt"));
		}
		catch(FileNotFoundException e){
			System.err.println("Couldnt open quote file. Sering time instead");
		}
	}
	
	public void run(){
		
		System.out.println("server running");
		
		while(moreQuotes){
			System.out.println("wating for client");
			try{
				byte[] buf = new byte[256];
				
				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				//figure out response
				String dString = null;
				if(in == null)
					dString = new Date().toString();
				else 
					dString = getNextQuote();
				
				System.out.println(dString);
				buf = dString.getBytes();
				
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
				
			} catch(IOException e){
				e.printStackTrace();
				moreQuotes = false;
			}
		} 
		socket.close();
	}
	
	protected String getNextQuote(){
		String returnValue = null;
		try{
			if((returnValue = in.readLine()) == null){
				in.close();
				moreQuotes = false;
				returnValue = "No more quotes. Goodbye";
			}
		}catch(IOException e){
			returnValue = "IOException occured in server";
		}
		return returnValue;
	}
}

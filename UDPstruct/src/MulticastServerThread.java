import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;


public class MulticastServerThread extends QuoteServerThread{
	
	public MulticastServerThread() throws IOException {
		super("MulticastServerThread");
	}
	
	public void run(){
		while(moreQuotes){
			try{
				byte[] buf = new byte[CONFIG.BUF_LEN];
				
				// construct quote
				String dString = null;
				if(in == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				buf = dString.getBytes();
				
				//send it
				InetAddress group = InetAddress.getByName(CONFIG.MULTI_GROUP);
				DatagramPacket packet = new DatagramPacket(buf,buf.length,group,CONFIG.UDP_PORT);
				socket.send(packet);
				
				// sleep for an interval
				try{
					sleep(CONFIG.MulTiServ_INTV);
				}catch(InterruptedException e){}
			}catch(IOException e){
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}
}

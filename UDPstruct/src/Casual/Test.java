package Casual;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	protected static Sender simp_sender = new Sender();
	protected static Receiver simp_receiver = new Receiver();
	
	protected static ArrayList<String> ip_group = new ArrayList();
	
	public static void main(String[] args){
		//
		Scanner scan = new Scanner(System.in);
		String cmd = null;
		// 
		String local = "localhost";
		ip_group.add(local);
		
		simp_sender = new Sender(ip_group);
		// initialize
		simp_sender.simple_init();
		simp_receiver.simple_init();
		
		// get and send
		do{
			cmd = scan.nextLine();
			simp_sender.push_cmd(cmd);
			System.out.println("we input: " + cmd);
			
			
			
		}while(cmd != "end\n");
	}
	
}

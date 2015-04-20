package Casual;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Cmd_mantainer {
	protected  LinkedBlockingQueue<String> out_cmd_q = new LinkedBlockingQueue();
	protected  LinkedBlockingQueue<String> in_cmd_q = new LinkedBlockingQueue();	
	protected ArrayList<String> ip_group = new ArrayList();
	
	public Cmd_mantainer(){

	}
}

package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import Objects.Item;

/**
 * 
 */

/**
 * @author Zaven
 *
 */
public class OrderServer extends Thread {
	private ArrayList<Item> inventory;
	BufferedReader input;
	BufferedWriter output;
	Socket socket;
	
	public OrderServer(Socket socket, ArrayList<Item> inventory){
		this.inventory = inventory;
		this.socket = socket;
		try{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e ) {}
	}
	
	public void run() {
		 while(true){
        	try {
	        	String[] command = input.readLine().split(" ");
	        	if(command[0].equalsIgnoreCase("LISTALL")){
	        		for(Item item : inventory)
	        			output.write(item.toString()+"\n");
	        		output.write("DONE\n");
	        		output.flush();
	        	} else if (command[0].equalsIgnoreCase("SELL")) {
	        		output.write( sellItem(command[1],Integer.parseInt(command[2])) ? 
	        				"SOLD\n" : "ERROR: Not enough in stock\n");
	        		output.flush();
	        	} else if (command[0].equalsIgnoreCase("QUIT")) {
	        		socket.close();
	        		return;
	        	}
        	} catch(IOException e) {};
        }
	}
	
	private synchronized boolean sellItem(String itemId,int quantity){
		for(Item item : inventory){
			if(item.getItemId().equals(itemId)){
				System.out.println(item.getQuantity() + " " + quantity);
				try {
					item.sell(quantity);
					return true;
				} catch (Exception e){
					return false;
				}
			}
		}
		return false;
	}
	
	
}

package Server;
import java.io.IOException;
import java.net.ServerSocket;
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
public class OrderListener {
	private static  ArrayList<Item> inventory;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		inventory = new ArrayList<Item>();
		//Initialize values
		inventory.add(new Item("Hammer",10,1.99));
		inventory.add(new Item("Nails",15,2.79));
		inventory.add(new Item("Foo Bar",12,6.59));
		
		try {
			ServerSocket ss = new ServerSocket(6100);
			while (true) {
				System.out.println("Waiting for a connection");
				Socket socket = ss.accept();
				System.out.println("Connection accepted");
				OrderServer th = new OrderServer(socket, inventory);
				th.start();
			}
		} catch (IOException e) { }
	}
}

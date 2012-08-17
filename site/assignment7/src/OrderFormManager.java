import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Objects.Item;
import Objects.Order;

/**
 * @author Zaven
 *
 */
public class OrderFormManager {
	private final String URL = "localhost";
	private final int PORT = 6100;
	
	public boolean processOrder(Order order){
		boolean orderProcessed = false;
		try{
			Socket socket = new Socket(URL,PORT);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.write("SELL "+Item.ID_PREFIX+order.getItemId()+ " " + order.getQuantity());
			out.flush();
			String response = in.readLine();
			if("SOLD".equals(response)){
				orderProcessed = true;
			}
			out.write("QUIT");
			out.flush();
			socket.close();
		}catch (IOException e){	
			e.printStackTrace();
		}
		return orderProcessed;
	}
}

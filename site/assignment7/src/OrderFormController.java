import Objects.Order;

/**
 * @author Zaven
 *
 */
public class OrderFormController {
	OrderFormManager manager;
	
	public OrderFormController(){
		manager = new OrderFormManager();
	}
	
	public String submit(Order order){
		String errMsg = validateOrder(order);
		if(errMsg!= null)
			return errMsg;
		if(manager.processOrder(order))
			return "The order could not be processed at this time.";
		return null;
	}
	
	//Validates order form
	private String validateOrder(Order order){
		if("".equals(order.getItemId()))
			return "You must select an item to place an order";
		if(order.getQuantity()==null)
			return "The quantity entered must be in numerical format";
		if(order.getQuantity().compareTo(0)<0)
			return "You must enter a positive quantity.";
		if(order.getCreditCard()==null)
			return "You must select a credit card.";
		return null;
	}
	
}

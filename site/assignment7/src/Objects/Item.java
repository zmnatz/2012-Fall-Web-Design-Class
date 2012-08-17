package Objects;
import java.text.NumberFormat;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;

/**
 * 
 */

/**
 * @author Zaven Mnatzakanian
 *
 */
public class Item {
	public static final String ID_PREFIX = "DS";
	private static int itemIdCounter = 100;
	
	private int itemId;
	private String desc;
	private int quantity;
	private double price;
	private double totalSales;
	
	public Item(){
		this.itemId = itemIdCounter++;
	}
	
	/**
	 * @param desc
	 * @param quantity
	 * @param price
	 */
	public Item(String desc, int quantity, double price) {
		this();
		this.desc = desc;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return ID_PREFIX + itemId;
	}
	
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the totalSales
	 */
	public double getTotalSales() {
		return totalSales;
	}
	
	/**
	 * @param quantity the quantity to add
	 * @return the quantity after items are added
	 */
	public synchronized int addQuantity(int quantity) {
		this.quantity += quantity;
		return this.quantity;
	}
	
	public synchronized void sell() throws Exception{
		sell(1);
	}
	/**
	 * 
	 * @param numSold the number of items sold
	 * @return quantity after items are sold
	 * @throws Exception - invalid sale
	 */
	public synchronized int sell(int numSold) throws Exception{
		if(numSold>quantity)
			throw new Exception("Item cannot be sold. Inventory not sufficient");
		totalSales += numSold * price;
		return addQuantity(numSold);
	}
	
	/**
	 * @param percent the percent to increase or decrease the price by.
	 * @throws Exception - invalid price decrease
	 */
	public synchronized void adjustPrice(double percent) throws Exception{
		if(percent<-1) 
			throw new Exception("Item cannot be reduced by more than 100%.");
		price *= 1+percent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (itemId != other.itemId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ID_PREFIX + itemId + "\t"
				+ (desc != null ? desc : "") + "\t"
				+ NumberFormat.getCurrencyInstance().format(price) + "\t"
				+ quantity;
	}
	
	
	
}

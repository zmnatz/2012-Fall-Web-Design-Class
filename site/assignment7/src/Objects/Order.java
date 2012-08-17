package Objects;
/**
 * 
 */

/**
 * @author Zaven
 *
 */
public class Order {
	private String itemId;
	private Integer quantity;
	private String specialInstructions;
	private String creditCard;
	
	public Order() { }
	
	public Order(String itemId, int quantity, String specialInstructions,
			String creditCard) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.specialInstructions = specialInstructions;
		this.creditCard = creditCard;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the specialInstructions
	 */
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	/**
	 * @param specialInstructions the specialInstructions to set
	 */
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
}

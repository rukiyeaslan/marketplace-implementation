package elements;

/**
 * 
 * @author rukiyeaslan
 *
 */
public class Order{
	
	private double amount;
	private double price;
	private int traderID;
	
	/**
	 * 
	 * @param traderID
	 * @param amount
	 * @param price
	 * creates order object with given parameters
	 */
	public Order(int traderID, double amount, double price) {		
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}

	/**
	 * 
	 * @return amount of the order
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
 
	/**
	 * 
	 * @return price of the order
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return the ID of the trader who made the order
	 */
	public int getTraderID() {
		return traderID;
	}

	/**
	 * 
	 * @param traderID
	 * sets trader ID to the parameter value
	 */
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}

	
	
	
	
	
	
}
package elements;
/**
 * 
 * @author rukiyeaslan
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {

	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	
	public int compareTo(BuyingOrder e) {
		
		if (this.getPrice() != e.getPrice()) {
			  //highest price
			if (this.getPrice() > e.getPrice()) {
				return -1;
			}
			else {
				return 1;
			}
				
		}
		
		else {
			if (this.getAmount() != e.getAmount()) {
				 //highest amount
				if (this.getAmount() > e.getAmount()) {
					return -1;
				}
				else {
					return 1;
				}
			}
			
			else {
				if (this.getTraderID() > e.getTraderID()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
	}
}

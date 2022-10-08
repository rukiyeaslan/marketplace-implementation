package elements;
import java.lang.Comparable;
/**
 * 
 * @author rukiyeaslan
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {

	 //not sure abt overriding parent constructor
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);   
	}

	

public int compareTo(SellingOrder e) {
		
		if (this.getPrice() != e.getPrice()) {
			//lowest price
			if (this.getPrice() > e.getPrice()) {
				return 1;
			}
			else {
				return -1;
			}
			
		}
		else {
			if (this.getAmount() != e.getAmount()) {
				 // higher amount
				if (this.getAmount()> e.getAmount()) {
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

package elements;

/**
 * 
 * @author rukiyeaslan
 *Transaction class is the class where successful transactions are hold
 */
public class Transaction {

	
	//gerçekleşen order ların tutulduğu class
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	
	//not sure abt constructor
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
}

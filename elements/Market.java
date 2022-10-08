package elements;
import java.util.*;
import executable.Main;

/**
 * 
 * @author rukiyeaslan
 * Market class is the class where transactions are made
 */
public class Market {
 
	
	int fee;
	/**
	 * 
	 * @param fee
	 * creates market object with given parameters
	 */
	public Market(int fee) {
		this.fee = fee;
	}
	
	
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();
	
	//transaction history
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	
	/**
	 * 
	 * @param order
	 * if there is a new selling order, adds this order to the sellingOrders priority queue
	 */
	public void giveSellOrder(SellingOrder order) {	
		
		sellingOrders.add(order);
		
		}
		//sellingOrders.comparator();
		
		
	
	/**
	 * 
	 * @param order
	 * if there is a new buying order, adds this order to the buyingOrders priority queue
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	
	//for input 666
	/**
	 * 
	 * @param price
	 * @param traders
	 * Trader#0 (The System) gives buying or selling orders for setting the current price of PQoin to the given price.
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		
		if (buyingOrders.peek() != null && buyingOrders.peek().getPrice() >= price ) {
		while( buyingOrders.peek().getPrice() >= price ) {
			SellingOrder newSell = new SellingOrder(0, buyingOrders.peek().getAmount(), buyingOrders.peek().getPrice());
			giveSellOrder(newSell);
			
			checkTransactions(traders);
			if (buyingOrders.size()==0 ) {
				break;
			}
		}
		
		
		}
	
		else if (sellingOrders.peek() != null && sellingOrders.peek().getPrice()<= price) {
			while (sellingOrders.peek().getPrice() <= price) {
				BuyingOrder newBuy = new BuyingOrder(0, sellingOrders.peek().getAmount(), sellingOrders.peek().getPrice());
				giveBuyOrder(newBuy);
				
				checkTransactions(traders);
				if (sellingOrders.size() ==0) {
					break;
				}
			}
			
		}
		
		
	}
	
	/**
	 * 
	 * @param traders
	 * if the prices at the PQs are overlapping, the market makes transactions with the top of PQs, until there is no overlapping.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		Transaction transaction;
		
		if (buyingOrders.size()>0 && sellingOrders.size() >0) {
		while (buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice() ) {
			SellingOrder sell = sellingOrders.poll();
			BuyingOrder buy = buyingOrders.poll();
			
			transaction = new Transaction(sell, buy);
			
			int sellerId = sell.getTraderID();
			int buyerId = buy.getTraderID();
			
			if (buy.getAmount() >= sell.getAmount()) { //amount = sell.getAmount()
				traders.get(buyerId).getWallet().withdrawBlocked(buy.getPrice()*sell.getAmount());  //fazla olanları wallet a geri yükleyeceğim
				traders.get(sellerId).getWallet().deposit(sell.getPrice()*sell.getAmount() * (1-(double)this.fee/1000));
				
				traders.get(buyerId).getWallet().depositCoin(sell.getAmount());
				traders.get(sellerId).getWallet().withdrawBlockedCoin(sell.getAmount());
				
				if (buy.getPrice() > sell.getPrice()) {
					traders.get(buyerId).getWallet().deposit((buy.getPrice()-sell.getPrice())*sell.getAmount());  ///****
				}
				
				if (buy.getAmount()-sell.getAmount() > 0) {
				BuyingOrder remBuy = new BuyingOrder(buyerId, buy.getAmount()-sell.getAmount(), buy.getPrice());
			   
				giveBuyOrder(remBuy);
				}
				
			}
			// amount = buy.getAmount()  there are remaining blocked pcoins of the seller
			else {   
				traders.get(buyerId).getWallet().withdrawBlocked(buy.getAmount()*sell.getPrice());
				traders.get(sellerId).getWallet().deposit(buy.getAmount()*sell.getPrice() * (1-(double)this.fee/1000));
				
				traders.get(buyerId).getWallet().depositCoin(buy.getAmount());
				traders.get(sellerId).getWallet().withdrawBlockedCoin(buy.getAmount());
				
				
				if(sell.getAmount()-buy.getAmount() >0 ) {
				SellingOrder remSell = new SellingOrder(sellerId, sell.getAmount()-buy.getAmount(), sell.getPrice());
			
				giveSellOrder(remSell);
				}
			}
			
			 
			
			transactions.add(transaction);
			if (buyingOrders.size()==0 || sellingOrders.size() ==0) {
				break;
			}
		}
		}}
	

	/**
	 * 
	 * @return sellingOrders priority queue
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	
    /**
     * 
     * @return buyingOrders priority queue
     */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}

	
    /**
     * 
     * @return transactions ArrayList
     */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	
	
	
	
	
	
}

package elements;
/**
 * 
 * @author rukiyeaslan
 * 
 */
public class Trader {

	private int id;
	private Wallet wallet;
	
	public static int numberOfUsers = 0;
	
	/**
	 * 
	 * @param dollars
	 * @param coins
	 * creates trader object with given parameters
	 */
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars,coins);
	}
	
	
	// if trader has enough dollars, return 1 and deposit that amount*price to the depositedDollars
	/**
	 * 
	 * @param amount
	 * @param price
	 * @param market
	 * @return 1 if trader is eligible for making a buying order, else 0
	 */
	public int buy(double amount, double price, Market market) {
		if (this.getWallet().getDollars() >= price*amount) {
			this.getWallet().withdraw(price*amount);
			this.getWallet().depositBlocked(price*amount);
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	// if trader has enough pcoins, return 1 and deposit that amount of coins
	/**
	 * 
	 * @param amount
	 * @param price
	 * @param market
	 * @return 1 if trader is eligible for making a selling order, else 0
	 */
	public int sell(double amount, double price, Market market) {
		if (this.getWallet().getCoins() >= amount) {
			this.getWallet().withdrawCoin(amount);
			this.getWallet().depositBlockedCoin(amount);
			return 1;
		}
		else {
			return 0;
		}
	}


	/**
	 * 
	 * @return ID of the trader
	 */
	public int getId() {
		return id;
	}


	/**
	 * 
	 * @param id
	 * sets ID of the trader to the parameter value
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * 
	 * @return wallet of the trader
	 */
	public Wallet getWallet() {
		return wallet;
	}


	
	
	
	
	
}

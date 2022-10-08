package elements;

/**
 * 
 * @author rukiyeaslan
 * Wallet class is the class which keeps amounts of dollars and PQoins of each trader
 */
public class Wallet {

	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	
	/**
	 * 
	 * @param dollars
	 * @param coins
	 * creates wallet object with given parameters
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}

	/**
	 * 
	 * @return the dollars that a trader has
	 */
	public double getDollars() {
		return dollars;
	}

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	/**
	 * 
	 * @return Pqoins that a trader has
	 */
	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	/**
	 * 
	 * @return blocked dollars that a trader has
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}

	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}

	/**
	 * 
	 * @return blocked coins that a trader has
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}

	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	
	//for input 3: deposit a certain amount of dollars to wallet
	/**
	 * 
	 * @param amount
	 * deposits a certain amount of dollars to wallet
	 */
	public void deposit(double amount) {
		this.dollars += amount;
	}
	
	//for input 4: withdraw a certain amount of dollars from wallet:
	/**
	 * 
	 * @param amount
	 * withdraws a certain amount of dollars from wallet
	 */
	public void withdraw(double amount) {
		
		this.dollars -= amount;
		
	}
	
	/**
	 * 
	 * @param amount
	 *  deposits a certain amount of coins to wallet
	 */
	public void depositCoin(double amount) {
		this.coins += amount;
	}
	
	/**
	 * 
	 * @param amount
	 * withdraws a certain amount of coins from wallet
	 */
	public void withdrawCoin(double amount) {
		this.coins -= amount;
	}
	
	/**
	 * 
	 * @param amount
	 * deposits a certain amount of blocked dollars to wallet
	 */
	public void depositBlocked(double amount) {
		this.blockedDollars += amount;
	}
	
	//for input 4: withdraw a certain amount of dollars from wallet
	/**
	 * 
	 * @param amount
	 * withdraws a certain amount of blocked dollars from wallet
	 */
	public void withdrawBlocked(double amount) {
		
		this.blockedDollars -= amount;
		
	}
	
	/**
	 * 
	 * @param amount
	 * deposits a certain amount of blocked coins to wallet
	 */
	public void depositBlockedCoin(double amount) {
		this.blockedCoins += amount;
	}
	
	/**
	 * 
	 * @param amount
	 * withdraws a certain amount of blocked coins from wallet
	 */
	public void withdrawBlockedCoin(double amount) {
		this.blockedCoins -= amount;
	}
	
	
	
	
	
}

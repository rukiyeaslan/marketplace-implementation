package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import elements.*;

/**
 * 
 * @author rukiyeaslan
 * Main class reads corresponding parameters and events from the input file, and logs the results to the output file.
 */
public class Main{
	
	public static Random myRandom;
	
	public static void main(String[] args) {
		
		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		
		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
			
		}
		
		PrintStream out;
		try {
		        out = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
		ArrayList<Trader> traderList = new ArrayList<Trader>();
		int seed = reader.nextInt();   //the random seed  (A)
		int B = reader.nextInt();   //transaction fee of marketplace
		int C = reader.nextInt();   //number of users
		int D = reader.nextInt();   // number of queries
		
		
		myRandom = new Random(seed);
		
		
		reader.nextLine();
		
		//creating market
		Market market = new Market(B);
		
		//invalid queries
		int invalid = 0;
		
		//creating users
		int id = 0;
		
	    //String[] userList = new String[C];
		for (int i = 0; i<C;i++) {
			String input = reader.nextLine();
			String[] lst = input.split(" ");
			Double dollar = Double.parseDouble(lst[0]);
			Double coins = Double.parseDouble(lst[1]);
	    	Trader trader = new Trader(dollar, coins);
	    	traderList.add(trader);
	    	trader.setId(id);
	    	Trader.numberOfUsers += 1;   //check again
	    	id+=1;	
	    	
		}
		
	   // taking queries
		for (int i=0; i<D;i++) {
			String input = reader.nextLine();
			String[] lst = input.split(" ");
			
			int query = Integer.parseInt(lst[0]);
			
			//10: give buying order of specific price
			if(query == 10) {
				
			int traderId = Integer.parseInt(lst[1]);
			double price = Double.parseDouble(lst[2]);	
			double amount = Double.parseDouble(lst[3]);	
			
			if (traderList.get(traderId).buy(amount, price, market) == 1) {
			BuyingOrder order = new BuyingOrder(traderId, amount, price);
			
		    market.giveBuyOrder(order);
			
			}
			else {
				invalid +=1;
			}
			market.checkTransactions(traderList);
			
			 
			}
			
			//11: give buying order of market price
			else if (query == 11) {
				
			int traderId = Integer.parseInt(lst[1]);
			double amount = Double.parseDouble(lst[2]);
			if (market.getSellingOrders().peek() != null) {
			double price = 	market.getSellingOrders().peek().getPrice();
			
			if (traderList.get(traderId).buy(amount, price, market) == 1) {
				BuyingOrder order = new BuyingOrder(traderId, amount, price);
				market.giveBuyOrder(order);
			}
			
			else {
				invalid +=1;
			}
			
			market.checkTransactions(traderList);
			}
			else {
				invalid += 1;
			}
			}
			
			//20: give selling order of specific price
			else if(query == 20) {
				
			int traderId = Integer.parseInt(lst[1]);	
			double price = Double.parseDouble(lst[2]);	
			double amount = Double.parseDouble(lst[3]);	
			
			if (traderList.get(traderId).sell(amount, price, market) == 1) {
				SellingOrder order = new SellingOrder(traderId, amount, price);
				market.giveSellOrder(order);
			}
			
			else {
				invalid +=1;
			}
			market.checkTransactions(traderList);
			//System.out.println(market.getTransactions().size());
			}
			
			//21: give selling order of market price
			else if(query == 21) {
			
			int traderId = Integer.parseInt(lst[1]);
			double amount = Double.parseDouble(lst[2]);	
			if (market.getBuyingOrders().peek() != null) {
			double price = market.getBuyingOrders().peek().getPrice();
			
			if (traderList.get(traderId).sell(amount, price, market) == 1) {
				SellingOrder order = new SellingOrder(traderId, amount, price);
				market.giveSellOrder(order);
			}
			
			else {
				invalid +=1;
			}
			market.checkTransactions(traderList);
			}
			else {
				invalid += 1;
			}
			}
			
			//3: deposit a certain amount of dollars to wallet:
			else if(query == 3) {
				
			int traderId = Integer.parseInt(lst[1]);
			double amount = Double.parseDouble(lst[2]);	
		
			traderList.get(traderId).getWallet().deposit(amount);
			}
			
			//4: withdraw a certain amount of dollars from wallet
			else if(query==4) {
			
			int traderId = Integer.parseInt(lst[1]);
			double amount = Double.parseDouble(lst[2]);	
			
			if (traderList.get(traderId).getWallet().getDollars() >= amount) {
			    traderList.get(traderId).getWallet().withdraw(amount);
			}
			
			else {
				invalid +=1;
			}
			}
			
			//5: print wallet status
			else if (query == 5) {
			int traderId = Integer.parseInt(lst[1]);
				
			double dollar = traderList.get(traderId).getWallet().getDollars() + traderList.get(traderId).getWallet().getBlockedDollars();
			double coins  = traderList.get(traderId).getWallet().getCoins() +traderList.get(traderId).getWallet().getBlockedCoins();
			
			String line = "Trader" +  traderId + " " +  String.format("%.5f", dollar) + "$ " +  String.format("%.5f", coins) + "PQ";
			out.println(line);
			}
			
			//System Queries
			
			//777: give rewards to all traders			
			else if(query == 777) {
			
						
			for (Trader j : traderList) {
				j.getWallet().depositCoin(myRandom.nextDouble()*10);
			}
			
			}
			
			//666: make open market operation
			else if(query == 666) {
				double price = Double.parseDouble(lst[1]);
				market.makeOpenMarketOperation(price, traderList);
			}
			
			//500: print the current market size
			else if(query == 500) {
				double totalDollar = 0.0;
				double totalPqoin = 0.0;
				
				SellingOrder[] sellingList = market.getSellingOrders().toArray(new SellingOrder[market.getSellingOrders().size()]);
				for (SellingOrder j : sellingList) {
					totalPqoin += j.getAmount();
				
				}
				
				BuyingOrder[] buyingList = market.getBuyingOrders().toArray(new BuyingOrder[market.getBuyingOrders().size()]);
				for (BuyingOrder k : buyingList) {
					totalDollar += k.getPrice()*k.getAmount();
				}
				
				String line = "Current market size: " + String.format("%.5f", totalDollar) + " " + String.format("%.5f", totalPqoin);
				out.println(line);
				
			}
			
			//501: print number of successful transactions
			else if (query == 501) {
				out.println("Number of successful transactions: " + market.getTransactions().size());
			}
			
			//502: print the number of invalid queries
			else if (query == 502) {
				out.println("Number of invalid queries: " + invalid);
			}
			
			//505: print the current prices
			
			else if(query == 505) {
				double buy = 0.0;
				double sell = 0.0;
				double current = 0.0;
				if (market.getBuyingOrders().peek() != null && market.getSellingOrders().peek() != null) {
				 buy = market.getBuyingOrders().peek().getPrice();
				sell = market.getSellingOrders().peek().getPrice();
				
				if(buy != 0.0 && sell !=0.0) {
					current = (buy + sell) / 2;
				}
				else if (buy == 0.0 && sell != 0.0) {
					 current = sell;
				}
				else if (sell == 0.0 && buy != 0.0) {
					 current = buy;
				}
				else{
			    	 current = 0.0;
			    }
				}
				
				else if(market.getBuyingOrders().peek() != null) {
				 buy = market.getBuyingOrders().peek().getPrice();
				 sell = 0.0;
					
					if(buy != 0.0 && sell !=0.0) {
						current = (buy + sell) / 2;
					}
					else if (buy == 0.0 && sell != 0.0) {
						 current = sell;
					}
					else if (sell == 0.0 && buy != 0.0) {
						 current = buy;
					}
					else{
				    	 current = 0.0;
				    }
					}
				
				else if (market.getSellingOrders().peek() != null) {
				buy = market.getBuyingOrders().peek().getPrice();
				sell = market.getSellingOrders().peek().getPrice();
				
					if(buy != 0.0 && sell !=0.0) {
						current = (buy + sell) / 2;
					}
					else if (buy == 0.0 && sell != 0.0) {
						 current = sell;
					}
					else if (sell == 0.0 && buy != 0.0) {
						 current = buy;
					}
					else{
				    	 current = 0.0;
				    
				}
				}
				else {
					current = 0.0;
				}
				String line = "Current prices: " + String.format("%.5f", buy) + " " + String.format("%.5f", sell) + " " + String.format("%.5f", current);
				out.println(line);
			
			}
			
			//555: print all traders’ wallet status
			else if(query == 555) {
				
				for (Trader j: traderList) {
				double dollar =	j.getWallet().getDollars() + j.getWallet().getBlockedDollars();
				double coin = j.getWallet().getCoins() + j.getWallet().getBlockedCoins();
				
				String line = "Trader " +  j.getId() + ": " +  String.format("%.5f", dollar) + "$ " +  String.format("%.5f", coin) + "PQ";
				out.println(line);
				}
			}
			
			
			
			
		}
	
		out.close();
		reader.close();		
		
	}
}


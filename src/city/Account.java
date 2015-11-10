package city;

import exception.NoSuchMoneyException;


/**
 * Class which represents a inhabitant's bank account
 * @author hembert & meyer
 */

public class Account
{
	private int balance;
	
	/**
	 * The constructor of a bank account
	 * @param amount The base amount in the account
	 */
	public Account(int amount) {
		if(amount < 0){
			this.balance = 0;
		} else {
			this.balance = amount;
		}
	}
	
	/**
	 * Take money from the account
	 * @param amount Amount to take
	 */
	public void debit(float amount) throws NoSuchMoneyException {
		if(this.balance - amount < 0){
			throw new NoSuchMoneyException();
		}
		this.balance -= amount;	
	}
	
	/**
	 * Add money to the account
	 * @param credit Amount to add
	 */
	public void credit(float amount) {
		this.balance += amount;	
	}

	public int getBalance() {
		return balance;
	}
}


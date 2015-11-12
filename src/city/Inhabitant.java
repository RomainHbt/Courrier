package city;

import exception.NoSuchMoneyException;
import letter.Letter;


/**
 * Class which represents a inhabitant of a city
 * @author hembert & meyer
 */

public class Inhabitant
{
	
	private String name;
	private City city;
	private Account bankAccount;
	
	/**
	 * Construction of an inhabitant
	 * @param name Name of the inhabitant
	 * @param city The city where lives the inhabitant
	 * @param amount The inhabitant bank account's amount
	 */
	public Inhabitant(String name, City city, int amount) {
		this.name = name;
		this.city = city;
		this.bankAccount = new Account(amount);
	}
	
	/**
	 * Receive a letter from the city
	 * @param letter Letter to be receive
	 */
	public void receiveLetter(Letter<?> letter) {
		// TODO implement me
	}
	
	/**
	 * Send a letter in the postbox
	 * @param letter Letter to be send
	 * @throws NoSuchMoneyException Exception if the inhabitant hasn't enough money
	 */
	public void sendLetter(Letter<?> letter) throws NoSuchMoneyException {
		this.withdraw(letter.getPrice());
		this.city.sendLetter(letter);
	}
	
	public String getName() {
		return name;
	}

	public City getCity() {
		return city;
	}
	
	public Account getBankAccount(){
		return bankAccount;
	}
	
	/**
	 * credit the account with the amount in parameter and print the action
	 * @param amount the money to increase the account
	 */
	public void credit(int amount) {
		this.bankAccount.credit(amount);
		String euro = "euros";
		if (amount == 1) euro = "euro";
		System.out.println("\t+ " + this.getName() + " account is credited with " + amount + " " + euro + "; its balance is now " + this.bankAccount.getBalance() + " euros");
	}
	
	/**
	 * withdraw the account of the inhabitant with the amount and print the action
	 * @param amount the money to lower the account
	 * @throws NoSuchMoneyException if you can't withdraw the account (ie there isn't enough money)
	 */
	public void withdraw(int amount) throws NoSuchMoneyException {
		this.bankAccount.debit(amount);
		String nb = "is"; 
		String euro = "euro";
		if (amount > 1) {
			nb = "are";
			euro = "euros";
		}
		System.out.println("\t- " + amount + " " + euro + " " + nb + " debited from " + this.getName()+ " account whose balance is now "+ this.bankAccount.getBalance() + " euros");
	}
	
}


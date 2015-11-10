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
		this.bankAccount.debit(letter.getPrice());
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
	
}


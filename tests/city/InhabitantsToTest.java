/**
 * 
 */
package city;

import exception.NoSuchMoneyException;
import letter.Letter;

/**
 * Classe for test
 * @author Meyer Hembert
 *
 */
public class InhabitantsToTest extends Inhabitant {
	public int numberOfLetterSent;
	public int numberOfLetterReceive;
	
	public InhabitantsToTest(String name, City city, int amount) {
		super(name, city, amount);
		numberOfLetterSent = 0;
	}
	
	public void sendLetter(Letter<?> letter) throws NoSuchMoneyException {
		super.sendLetter(letter);
		this.numberOfLetterSent++;
	}
	
	public void receiveLetter(Letter<?> letter) {
		super.receiveLetter(letter);
		this.numberOfLetterReceive++;
	}

}

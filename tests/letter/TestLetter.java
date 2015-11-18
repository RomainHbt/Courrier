/**
 * 
 */
package letter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import city.City;
import city.Inhabitant;
import city.InhabitantsToTest;
import exception.NoSuchMoneyException;


/**
 * @author Meyer Hembert
 *
 */
public abstract class TestLetter {
	protected Inhabitant anne;
	protected Inhabitant bernard;
	
	@Before
	public void init() {
		anne = new Inhabitant("Anne", new City("myCity"), 1);
		bernard = new Inhabitant("Bernard", new City("myCity"), 0);
	}
	
	/**
	 * Test method for {@link letter.Letter#getPrice()}.
	 */
	@Test
	public void testPositivePrice() {
		assertTrue(createLetter(anne, bernard).getPrice()>0);
	}
	
	@Test
	public void testSenderSendLetter() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 100);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		assertSame(0, sender.numberOfLetterSent);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(1, sender.numberOfLetterSent);
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void testWhenSenderHasBankAccountIsEmpty() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 0);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		sender.sendLetter(createLetter(sender,receiver));
	}
	
	@Test
	public void testPossibleToSend() {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 0);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		assertFalse((createLetter(sender, receiver)).possibleToSend());
		assertTrue((createLetter(receiver, sender)).possibleToSend());
	}
	
	protected abstract Letter<?> createLetter(Inhabitant anne, Inhabitant bernard);

}

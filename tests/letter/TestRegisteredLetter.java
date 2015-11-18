/**
 * 
 */
package letter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import content.ContentString;

import city.City;
import city.Inhabitant;
import city.InhabitantsToTest;
import exception.NoSuchMoneyException;

/**
 * @author Meyer Hembert
 *
 */
public class TestRegisteredLetter extends TestSpecialLetter {
	private InhabitantsToTest receiver;
	private InhabitantsToTest sender;
	
	@Before
	public void init() {
		sender = new InhabitantsToTest("sender", new City("myCity"), 200);
		receiver = new InhabitantsToTest("inhabitant", new City("myCity"), 150);
	}
	
	/**
	 * Test method for {@link letter.Letter#action()}.
	 * @throws NoSuchMoneyException 
	 */
	@Test
	public void testReceiverSendsAcknowledgemt() throws NoSuchMoneyException {
		assertSame(0, receiver.numberOfLetterSent);
		assertSame(0, sender.numberOfLetterSent);
		sender.sendLetter(createLetter());
		assertSame(1, sender.numberOfLetterSent);	
		assertEquals(184, sender.getBankAccount().getBalance());
		receiver.receiveLetter(createLetter());
		assertEquals(149, receiver.getBankAccount().getBalance());
		assertSame(1, receiver.numberOfLetterSent);
	}
	
	@Test
	public void testReceiveInReceive() throws NoSuchMoneyException{
		sender.sendLetter(new RegisteredLetter<>(createLetter()));
		assertEquals(169, sender.getBankAccount().getBalance());
	}
	
	@Test
	public void testAdditionalPrice() {
		assertEquals(16, createLetter().getPrice());
	}

	@Override
	protected Letter<?> createLetter(Inhabitant anne, Inhabitant bernard) {
		return new RegisteredLetter<>(new SimpleLetter(anne, bernard, new ContentString("hi")));
	}

	
	public RegisteredLetter<?> createLetter() {
		return new RegisteredLetter<>(new SimpleLetter(sender, receiver, new ContentString("hi")));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SpecialLetter<SimpleLetter> createLetterWithOtherLetter(
			Letter<?> simple) {
		return (SpecialLetter<SimpleLetter>) new RegisteredLetter<>(simple);
	}

}

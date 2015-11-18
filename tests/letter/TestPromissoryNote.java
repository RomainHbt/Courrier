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
import content.ContentAmount;
import exception.NoSuchMoneyException;

/**
 * @author Meyer Hembert
 *
 */
public class TestPromissoryNote extends TestLetter{

	PromissoryNote letter;
	PromissoryNote letter1;
	PromissoryNote letter2;
	PromissoryNote letter3;
	private Inhabitant jeanne;
	private Inhabitant richard;
	private Inhabitant robert;
	
	@Before
	public void init() {
		jeanne = new Inhabitant("Jeanne", new City("myCity"), 10);
		richard = new Inhabitant("Richard", new City("myCity"), 0);
		letter = new PromissoryNote(jeanne, richard, new ContentAmount(10));
		letter1 = new PromissoryNote(jeanne, richard, new ContentAmount(9));
		letter2 = new PromissoryNote(richard, jeanne, new ContentAmount(1));
		robert = new Inhabitant("Robert", new City("myCity"), 500);
		letter3 = new PromissoryNote(robert, jeanne, new ContentAmount(400));
	}
	
	/**
	 * Test method for {@link letter.SimpleLetter#action()}.
	 * @throws NoSuchMoneyException 
	 */
	@Test
	public void testAction() throws NoSuchMoneyException {
		jeanne.sendLetter(letter1);
		assertSame(9, jeanne.getBankAccount().getBalance());
		//take nine euros and give one euro to send a thank letter 
		richard.receiveLetter(letter1);
		assertSame(8, richard.getBankAccount().getBalance());
		assertSame(0, jeanne.getBankAccount().getBalance());
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void testActionWhenSenderHasntEnoughMoney() throws NoSuchMoneyException {
		jeanne.sendLetter(letter);
	}
	
	@Test
	public void testPossibleToSendPromissory() {
		assertFalse(letter.possibleToSend());
		assertTrue(letter1.possibleToSend());
	}

	@Override
	protected Letter<?> createLetter(Inhabitant an, Inhabitant bernard) {
		return new PromissoryNote(an, bernard, new ContentAmount(0));
	}
	
	@Test
	public void testActionWithLimitedAccount() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 1);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(0, sender.getBankAccount().getBalance());
		receiver.receiveLetter(createLetter(sender, receiver));
		assertEquals(1, receiver.numberOfLetterSent);
	}
	
	@Test
	public void testActionSendAThanksLetter() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 1);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(0, sender.getBankAccount().getBalance());
		receiver.receiveLetter(createLetter(sender, receiver));
		assertEquals(1, receiver.numberOfLetterSent);
	}
	
	@Test
	public void testActionCantSendAThanksLetter() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 1);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 0);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(0, sender.getBankAccount().getBalance());
		receiver.receiveLetter(createLetter(sender, receiver));
		assertEquals(0, receiver.numberOfLetterSent);
	}
	
	@Test
	public void testWithInterest() throws NoSuchMoneyException {
		robert.sendLetter(letter3);
		assertEquals(495, robert.getBankAccount().getBalance());
		jeanne.receiveLetter(letter3);
		assertEquals(95, robert.getBankAccount().getBalance());
		assertEquals(409, jeanne.getBankAccount().getBalance());
	}


}

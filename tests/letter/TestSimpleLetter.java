/**
 * 
 */
package letter;


import static org.junit.Assert.assertEquals;

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
public class TestSimpleLetter extends TestLetter{

	@Override
	protected Letter<?> createLetter(Inhabitant anne, Inhabitant bernard) {
		return new SimpleLetter(anne, bernard, new ContentString("hi"));
	}
	
	@Test
	public void testSendWithLimitedAccountAndAction() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 1);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 200);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(0, sender.getBankAccount().getBalance());
		
	}

}

package letter;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import city.City;
import city.InhabitantsToTest;

import content.ContentString;
import exception.NoSuchMoneyException;

public abstract class TestSpecialLetter extends TestLetter{
	protected InhabitantsToTest receiver;
	protected InhabitantsToTest sender;
	
	@Before
	public void init() {
		sender = new InhabitantsToTest("sender", new City("myCity"), 200);
		receiver = new InhabitantsToTest("inhabitant", new City("myCity"), 150);
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void testActionCantSendAThanksLetter() throws NoSuchMoneyException {
		InhabitantsToTest sender = new InhabitantsToTest("Sender", new City("myCity"), 1);
		InhabitantsToTest receiver = new InhabitantsToTest("receiver", new City("myCity"), 0);
		sender.sendLetter(createLetter(sender, receiver));
		assertEquals(0, sender.getBankAccount().getBalance());
		receiver.receiveLetter(createLetter(sender, receiver));
	}
	
	@Test
	public void whatAmITesting(){
		SimpleLetter simple = new SimpleLetter(sender, receiver, new ContentString("hey"));
		SpecialLetter<SimpleLetter> ul = createLetterWithOtherLetter(simple);
		
		@SuppressWarnings("unused")
		ContentString txt = ul.getContent().getContent();
	}

	protected abstract SpecialLetter<SimpleLetter> createLetterWithOtherLetter(Letter<?> simple) ;

}

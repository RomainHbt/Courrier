package letter;

import static org.junit.Assert.*;

import org.junit.Test;

import content.ContentString;

import city.Inhabitant;
import exception.NoSuchMoneyException;

public class TestUrgentLetter extends TestSpecialLetter {
	
	@Test
	public void testMulPrice() {
		assertEquals(2, createLetter().getPrice());
	}
	
	@Test
	public void testReceiveInReceive() throws NoSuchMoneyException{
		sender.sendLetter(new UrgentLetter<>(createLetter()));
		assertEquals(196, sender.getBankAccount().getBalance());
	}
	
	@Override
	protected Letter<?> createLetter(Inhabitant anne, Inhabitant bernard) {
		return new UrgentLetter<>(new SimpleLetter(anne, bernard, new ContentString("hi")));
	}
	
	public UrgentLetter<?> createLetter() {
		return new UrgentLetter<>(new SimpleLetter(sender, receiver, new ContentString("hi")));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SpecialLetter<SimpleLetter> createLetterWithOtherLetter(
			Letter<?> simple) {
		return (SpecialLetter<SimpleLetter>)new UrgentLetter<>(simple);
	}

}

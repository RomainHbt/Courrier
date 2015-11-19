package city;

import static org.junit.Assert.*;
import letter.Letter;
import letter.SimpleLetter;

import org.junit.Before;
import org.junit.Test;

import content.ContentString;

import exception.NoSuchMoneyException;

public class InhabitantTest {
	
	public Inhabitant h;
	public City c;

	@Before
	public void setUp() throws Exception {
		c = new City("Passe-tests");
		h = new Inhabitant("Test", c, 500);
	}

	@Test
	public void getCityName() {
		assertEquals(c, h.getCity());
	}
	
	@Test
	public void creditInhabitant() {
		h.credit(50);
		assertEquals(550, h.getBankAccount().getBalance());
	}
	
	@Test
	public void debitInhabitant() {
		try {
			h.withdraw(50);
			assertEquals(450, h.getBankAccount().getBalance());
		} catch (NoSuchMoneyException e) {}
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void uncoverInhabitant() throws NoSuchMoneyException {
		h.withdraw(550);
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void sendLetterWithoutMoney() throws NoSuchMoneyException {
		Letter l = new SimpleLetter(h, h, new ContentString("Test courrier"));
		h.withdraw(500);
		h.sendLetter(l);
	}

}

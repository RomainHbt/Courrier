package city;

import static org.junit.Assert.*;
import letter.SimpleLetter;

import org.junit.Test;

import content.ContentString;

public class TestCity {
	
	@Test
	public void testDistributeLetter() {
		City myCite = new City("myCity");
		InhabitantsToTest inhabitant1 = new InhabitantsToTest("Robert", myCite, 200);
		InhabitantsToTest inhabitant2 = new InhabitantsToTest("Gertrude", myCite, 200);
		SimpleLetter letter1 = new SimpleLetter(inhabitant1, inhabitant2, new ContentString("blabla"));
		myCite.addInhabitant(inhabitant1);
		myCite.addInhabitant(inhabitant2);
		myCite.sendLetter(letter1);
		assertSame(0, inhabitant2.numberOfLetterReceive);
		myCite.distributeLetter();
		assertSame(1, inhabitant2.numberOfLetterReceive);
	}
	
	@Test
	public void testDistributeLetterWithBadInhabitant() {
		City myCite = new City("myCity");
		InhabitantsToTest inhabitant1 = new InhabitantsToTest("Robert", myCite, 200);
		InhabitantsToTest inhabitant2 = new InhabitantsToTest("Gertrude", myCite, 200);
		SimpleLetter letter1 = new SimpleLetter(inhabitant1, inhabitant2, new ContentString("blabla"));
		myCite.addInhabitant(inhabitant1);
		myCite.sendLetter(letter1);
		assertSame(0, inhabitant2.numberOfLetterReceive);
		myCite.distributeLetter();
		assertSame(0, inhabitant2.numberOfLetterReceive);
	}

	@Test
	public void testAddInhabitant() {
		City myCite = new City("myCity");
		InhabitantsToTest inhabitant1 = new InhabitantsToTest("Robert", myCite, 200);
		assertSame(0, myCite.getListInhabitant().size());
		myCite.addInhabitant(inhabitant1);
		assertSame(1, myCite.getListInhabitant().size());
	}

}

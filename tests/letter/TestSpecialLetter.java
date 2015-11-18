package letter;


import org.junit.Before;
import org.junit.Test;

import city.City;
import city.InhabitantsToTest;

import content.ContentString;

public abstract class TestSpecialLetter extends TestLetter{
	protected InhabitantsToTest receiver;
	protected InhabitantsToTest sender;
	
	@Before
	public void init() {
		sender = new InhabitantsToTest("sender", new City("myCity"), 200);
		receiver = new InhabitantsToTest("inhabitant", new City("myCity"), 150);
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

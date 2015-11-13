package letter;

import city.Inhabitant;
import content.ContentString;

/**
 * ThankYouLetter is a Letter to thanks the sender
 *  
 * @author Meyer Hembert
 *
 */
public class ThankYouLetter extends SimpleLetter {

	/**
	 * Constructor ThankYouLetter
	 * @param sender the sender of the letter
	 * @param receiver the receiver of the letter
	 * @param descriptionLetter the description of this letter
	 */
	public ThankYouLetter(Inhabitant sender, Inhabitant receiver, String descriptionLetter) {
		super(sender, receiver, new ContentString("thanks for " + descriptionLetter));
	}

	/* 
	 * @see letter.SimpleLetter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "a thanks letter which is a simple letter";
	}
}

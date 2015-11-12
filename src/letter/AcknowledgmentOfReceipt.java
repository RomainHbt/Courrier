package letter;

import city.Inhabitant;
import content.ContentString;

/**
 * AcknowledgmentOfReceipt is a letter which is send at the sender to know if the receiver has received the letter
 * 
 * @author Meyer Hembert
 *
 */
public class AcknowledgmentOfReceipt extends SimpleLetter {

	/**
	 * Constructor AcknowledgmentOfReceipt
	 * @param sender the sender of the letter
	 * @param receiver the receiver of the letter
	 * @param descriptionLetter the description of the letter received
	 */
	public AcknowledgmentOfReceipt(Inhabitant sender, Inhabitant receiver, String descriptionLetter) {
		super(sender, receiver, new ContentString("acknoledgment of receipt for "+ descriptionLetter));
	}
	
	/* 
	 * @see letter.SimpleLetter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "acknowledgment of receipt";
	}

}

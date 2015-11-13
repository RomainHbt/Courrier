package letter;

import content.Content;

/**
 * UrgentLetter is a Letter which send quickly
 * 
 * @author Meyer Hembert
 *
 * @param <C> the content of the letter
 */

public class UrgentLetter<C extends Content> extends SpecialLetter<C> {

	/**
	 * Constructor Urgent Letter
	 * @param letter
	 */
	public UrgentLetter(Letter<C> letter) {
		super(letter);
		this.price *= 2;
	}

	/* 
	 * @see letter.Letter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "an urgent letter";
	}

}

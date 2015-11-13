/**
 * 
 */
package letter;

import content.Content;

/**
 * Special Letter for the UrgentLetter and the Registered Letter
 * 
 * @author Meyer Hembert
 *
 */
public abstract class SpecialLetter<C extends Content> extends Letter<C> {

	/**
	 * letter is the component
	 */
	protected Letter<C> letter;
	
	/**
	 * Constructor SpecialLetter
	 * @param letter the component of the special letter
	 */
	@SuppressWarnings("unchecked")
	public SpecialLetter(Letter<C> letter) {
		super(letter.getSender(), letter.getReceiver(), letter.getPrice(), ((C)letter));
		this.letter = letter;
	}

	/*
	 * @see letter.Letter#action()
	 */
	public void action() {
		this.letter.action();
	}
	
}

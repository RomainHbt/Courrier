package letter;

import content.Content;

/**
 * RegisteredLetter is a Letter which send an acknowledgment of receipt
 * 
 * @author Meyer Hembert
 *
 * @param <C> the content of the letter
 */

public class RegisteredLetter<C extends Content> extends SpecialLetter<C> {
	/**
	 * the additional cost
	 */
	protected static final int additionalCost = 15;
	
	/**
	 * Constructor for the registered letter
	 * @param letter the letter 
	 */
	public RegisteredLetter(Letter<C> letter) {
		super(letter);
		this.letter.price = this.price + additionalCost;
		this.price = this.letter.price;
	}

	/* 
	 * @see letter.Letter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "registered letter whose content is a " + this.letter.getDescriptionType();
	}
	
	/* 
	 * @see letter.Letter#lastAction()
	 */
	@Override
	protected void lastAction() {
		sendAcknoledmentOfReceipt();
	}
	
	/**
	 * send an acknowledgment of receipt
	 */
	private void sendAcknoledmentOfReceipt() {
		(new AcknowledgmentOfReceipt(this.receiver, this.sender, this.getOnlyDescriptionLetter())).action();
	}

}

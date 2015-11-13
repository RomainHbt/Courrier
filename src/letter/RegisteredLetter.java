package letter;

import content.Content;
import exception.NoSuchMoneyException;

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
		return "a registered letter";
	}
	
	
	/* 
	 * @see letter.Letter#action()
	 */
	@Override
	public void action() {
		super.action();
		sendAcknoledmentOfReceipt();
	}
	
	/**
	 * send an acknowledgment of receipt
	 */
	private void sendAcknoledmentOfReceipt() {
		try {
			this.receiver.sendLetter(new AcknowledgmentOfReceipt(this.receiver, this.sender, this.getDescription()));
		} catch (NoSuchMoneyException e) {}
	}

}

package letter;

import city.Inhabitant;
import content.ContentAmount;
import exception.NoSuchMoneyException;

/**
 * PromissoryNote is a Letter with Money content
 * 
 * @author Meyer Hembert
 *
 */

public class PromissoryNote extends Letter<ContentAmount>
{
	/**
	 * the price static for the letter
	 */
	protected static final int COST = 1;
	/**
	 * the factor add with the amount in the letter 
	 */
	protected static final int FACTOR = 1;
	/**
	 * the amount in the letter
	 */
	protected int amount;

	
	/**
	 * Constructor PromissoryNote
	 * @param sender the sender of the letter
	 * @param receiver the receiver of the letter
	 * @param content the content of the letter
	 */
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, ContentAmount content) {
		super(sender, receiver, COST + (FACTOR * content.getAmount())/100, content);
		amount =content.getAmount();	
	}


	/* 
	 * @see letter.Letter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "a promissory note letter";
	}


	/* 
	 * @see letter.Letter#possibleToSend()
	 */
	@Override
	public boolean possibleToSend() {
		return (this.sender.getBankAccount().getBalance() >= this.price + this.amount);
	}


	/* 
	 * @see letter.Letter#action()
	 */
	@Override
	public void action() {
		try {
			this.sender.withdraw(this.amount);
		} catch (NoSuchMoneyException e) {}
		this.receiver.credit(amount);
		try {
			receiver.sendLetter(new ThankYouLetter(receiver, sender, this.getDescription()));
		} catch (NoSuchMoneyException e) {}
	}
	
}


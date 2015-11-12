/**
 * 
 */
package content;

/**
 * ContentAmount is a content for a PromissoryLetter in particular.
 * We can take the amount and the description
 * 
 * @author Meyer Hembert
 *
 */
public class ContentAmount implements Content {
	/**
	 * the amount which the sender want give at the receiver
	 */
	private int amount;
	
	/**
	 * Constructor ContentAmount
	 * @param amount the money for the receiver
	 */
	public ContentAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the amount for the receiver
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/* 
	 * @see content.Content#getDescription()
	 */
	@Override
	public String getDescription() {
		return "money content (" + getAmount() + ")";
	}

}

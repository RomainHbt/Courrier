package letter;
import city.Inhabitant;
import content.Content;
import exception.NoSuchMoneyException;

/**
 * Letter which you can send, receive, send money or text
 * 
 * @author Meyer Hembert
 *
 * @param <C> the content of the letter
 */

public abstract class Letter<C extends Content> implements Content
{
	/**
	 * attribute keeps the sender of the letter
	 */
	protected Inhabitant sender;
	/**
	 * attribute keeps the receiver of the letter
	 */
	protected Inhabitant receiver;
	/**
	 * attribute keeps the price of the letter
	 */
	protected int price;
	/**
	 * attribute keeps the content of the letter
	 */
	protected C content;
	
	/**
	 * Constructor Letter 
	 * @param sender the inhabitant who send the letter
	 * @param receiver the inhabitant who receive the letter
	 * @param price the price of the letter
	 * @param content the content of the letter
	 */
	public Letter(Inhabitant sender, Inhabitant receiver, int price, C content) {
		this.sender = sender;
		this.receiver = receiver;
		this.price = price;
		this.content = content;
	}
	
	/**
	 * action to send the letter and print a description even if the send is impossible
	 */
	public void action() {
		try {
			if (possibleToSend()) {
				System.out.println(this.getDescription());
				this.sender.sendLetter(this);
				lastAction();
			} else {
				System.out.println("\t# " + this.sender.getName() + "'s account is empty !! Can't send a letter !");
			}
		} catch (NoSuchMoneyException e) {}
	}
	
	/**
	 * give a description about the letter
	 * @return a description about the particular of the letter
	 */
	protected abstract String getDescriptionType();
	/**
	 * It's true if it's possible to send else false
	 * @return true if it's possible to send else false
	 */
	protected boolean possibleToSend() {
		return (this.sender.getBankAccount().getBalance() >= this.price);
	}
	/**
	 * complete the action
	 */
	protected abstract void lastAction();

	/* 
	 * @see content.Content#getDescription()
	 */
	public String getDescription() {
		String euro = "euro";
		if (this.price > 1) {
			euro = "euros";
		}
		String description = " ->" + this.sender.getName() + " mails " + getOnlyDescriptionLetter();
		description += " to " + this.receiver.getName()  + " for a cost of " + this.price + " " + euro;
		return description;
	}
	
	/**
	 * @return only the description about the letter
	 */
	protected String getOnlyDescriptionLetter() {
		String description = "a " + this.getDescriptionType();
		description += " whose content is a " + this.content.getDescription() ;
		return description;
	}

	/**
	 * return the sender
	 * @return the sender
	 */
	public Inhabitant getSender() {
		return sender;
	}

	/**
	 * return the receiver
	 * @return the receiver
	 */
	public Inhabitant getReceiver() {
		return receiver;
	}

	/**
	 * return the price
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * return the content
	 * @return the content
	 */
	public C getContent() {
		return content;
	}
	
	
}


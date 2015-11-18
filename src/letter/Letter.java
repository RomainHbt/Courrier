package letter;
import java.util.Random;

import city.Inhabitant;
import content.Content;
import content.ContentAmount;
import content.ContentString;

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
	 * action when you receive the letter
	 */
	public abstract void action();
	
	/**
	 * give a description about the letter
	 * @return a description about the particular of the letter
	 */
	protected abstract String getDescriptionType();
	/**
	 * It's true if it's possible to send else false
	 * @return true if it's possible to send else false
	 */
	public boolean possibleToSend() {
		return (this.sender.getBankAccount().getBalance() >= this.price);
	}

	
	/**
	 * give a description for the sender
	 * @return a description for the sender
	 */
	public String getSendDescription() {
		String euro = "euro";
		if (this.price > 1) {
			euro = "euros";
		}
		String description = " -> " + this.sender.getName() + " mails " + getDescription();
		description += " to " + this.receiver.getName()  + " for a cost of " + this.price + " " + euro;
		return description;
	}
	
	/**
	 * give a description for the receiver
	 * @return a description for the receiver
	 */
	public String getReceiveDescription() {
		return (" <- " + this.receiver.getName() + " receives " + getDescription() + " from " + this.sender.getName());
	}
	
	/* 
	 * @see content.Content#getDescription()
	 */
	public String getDescription() {
		String description = this.getDescriptionType();
		description += " whose content is " + this.content.getDescription() ;
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
	
	/**
	 * Make a random type letter
	 * @return 
	 */
	public static Letter<?> makeLetter(Inhabitant sender){
		Random r = new Random();
		Letter<?> l;
		Letter<?> colis;
		Inhabitant receiver = sender.getCity().getRandomInhabitant();
		
		switch (r.nextInt(2)) {
			case 0:
				// SimpleLetter
				l = new SimpleLetter(sender, receiver, new ContentString("Coucou"));
				break;
			case 1:
				// PromissoryNote
				l = new PromissoryNote(sender, receiver, new ContentAmount(r.nextInt(50)+1));
				break;
			default:
				return null;
		}
		
		switch (r.nextInt(4)) {
			case 0:
				// Nothing
				return l;
			case 1:
				// RegisteredLetter
				colis = new RegisteredLetter<>(l);
				break;
			case 2:
				// UrgentLetter
				colis = new UrgentLetter<>(l);
				break;
			default:
				return null;
		}
		return colis;
	}
}


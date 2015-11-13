package letter;
import city.Inhabitant;
import content.ContentString;

/**
 * SimpleLetter is a letter with a Text Content
 * 
 * @author Meyer Hembert
 *
 */

public class SimpleLetter extends Letter<ContentString>
{
	/**
	 * the price static initial for this letter
	 */
	protected static final int COST = 1;
	
	/**
	 * Constructor for SimpleLetter
	 * @param sender the sender of the letter
	 * @param receiver the receiver of the letter
	 * @param content the content of the letter
	 */
	public SimpleLetter(Inhabitant sender, Inhabitant receiver, ContentString content) {
		super(sender, receiver, COST, content);
	}

	/* 
	 * @see letter.Letter#getDescriptionType()
	 */
	@Override
	protected String getDescriptionType() {
		return "a simple letter";
	}

	/* 
	 * @see letter.Letter#action()
	 */
	@Override
	public void action() {}

	
}


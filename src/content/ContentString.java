/**
 * 
 */
package content;

/**
 * ContentString is a content for a SimpleLetter in particular.
 * We can take the description
 * 
 * @author Meyer Hembert
 *
 */
public class ContentString implements Content {
	/**
	 * text keep the content
	 */
	private String text;
	
	/**
	 * Constructor ContentString
	 * @param text the content of the letter
	 */
	public ContentString(String text) {
		this.text= text;
	}

	/* 
	 * @see content.Content#getDescription()
	 */
	@Override
	public String getDescription() {
		return "text content (" + getText() + ")";
	}

	/**
	 * give the text of the content
	 * @return the text of the content
	 */
	private String getText() {
		return this.text;
	}

}

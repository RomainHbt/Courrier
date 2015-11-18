package content;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestContentString {
	
	@Test
	public void testContentString() {
		ContentString cs = new ContentString("hello");
		assertEquals("text content (hello)", cs.getDescription());
	}
}

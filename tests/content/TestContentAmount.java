package content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TestContentAmount {

	@Test
	public void testContentAmount() {
		ContentAmount ca = new ContentAmount(100);
		assertSame(100, ca.getAmount());
		assertEquals("money content (100)", ca.getDescription());
	}
}

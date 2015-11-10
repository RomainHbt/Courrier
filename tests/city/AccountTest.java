package city;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.NoSuchMoneyException;

public class AccountTest {

	private Account account;
	
	@Before
	public void setUp() throws Exception {
		account = new Account(10);
	}

	@Test
	public void creditTest() {
		assertSame(10, account.getBalance());
		account.credit(15);
		assertSame(25, account.getBalance());
	}
	
	@Test
	public void debitTest() {
		assertSame(10, account.getBalance());
		try {
			account.debit(8);
		} catch (NoSuchMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertSame(2, account.getBalance());
	}
	
	@Test(expected=NoSuchMoneyException.class)
	public void noMoneyTest() throws NoSuchMoneyException {
		account.debit(12);
	}

}

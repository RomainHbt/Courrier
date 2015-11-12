package exception;

@SuppressWarnings("serial")
public class NoSuchMoneyException extends Exception {

	public NoSuchMoneyException(){
		super("The inhabitant hasn't enough money !");
	}
	
}

package exception;

public class LackOfMoneyException extends RuntimeException {
	public LackOfMoneyException(String message) {
		super(message);
	}
}

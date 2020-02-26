package exception;

public class LackOfMoneyException extends RuntimeException {
	public LackOfMoneyException() {
		super("로또 비용이 부족합니다.");
	}
}

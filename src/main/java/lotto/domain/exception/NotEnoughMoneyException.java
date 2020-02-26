package lotto.domain.exception;

public class NotEnoughMoneyException extends RuntimeException {
	private static final String NOT_ENOUGH_MONEY_MESSAGE = "금액이 부족합니다.";

	public NotEnoughMoneyException() {
		super(NOT_ENOUGH_MONEY_MESSAGE);
	}
}

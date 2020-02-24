package lotto.exception;

public class InvalidMoneyException extends RuntimeException {
	private static final String ERROR_MESSAGE_MIN_MONEY = "천원 이상의 금액만 가능합니다.";

	public InvalidMoneyException() {
		super(ERROR_MESSAGE_MIN_MONEY);
	}
}

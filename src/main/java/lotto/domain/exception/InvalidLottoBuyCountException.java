package lotto.domain.exception;

public class InvalidLottoBuyCountException extends RuntimeException {
	private static final String INVALID_LOTTO_BUY_COUNT_MESSAGE = "구매 개수는 0 이상이어야 합니다.";

	public InvalidLottoBuyCountException() {
		super(INVALID_LOTTO_BUY_COUNT_MESSAGE);
	}
}

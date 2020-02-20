package lotto.exception;

public class InvalidRangeException extends RuntimeException {
	private static final String ERROR_MESSAGE_LOTTO_RANGE = "1이상 45이하의 숫자를 입력하세요.";

	public InvalidRangeException() {
		super(ERROR_MESSAGE_LOTTO_RANGE);
	}
}

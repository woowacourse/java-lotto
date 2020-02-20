package lotto.exception;

public class InvalidSizeException extends RuntimeException {
	private static final String ERROR_MESSAGE_LOTTO_SIZE = "6개의 숫자가 아닙니다.";

	public InvalidSizeException() {
		super(ERROR_MESSAGE_LOTTO_SIZE);
	}
}

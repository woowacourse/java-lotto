package lotto.exception;

public class DuplicateNumberException extends RuntimeException {
	private static final String ERROR_MESSAGE_DUPLICATE_NUMBER = "중복된 숫자가 있습니다";

	public DuplicateNumberException() {
		super(ERROR_MESSAGE_DUPLICATE_NUMBER);
	}
}

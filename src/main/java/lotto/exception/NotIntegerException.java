package lotto.exception;

public class NotIntegerException extends RuntimeException {
	private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

	public NotIntegerException() {
		super(ERROR_MESSAGE_NOT_INTEGER);
	}
}
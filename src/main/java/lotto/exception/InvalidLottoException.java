package lotto.exception;

public class InvalidLottoException extends RuntimeException {
	public InvalidLottoException() {
	}

	public InvalidLottoException(String message) {
		super(message);
	}
}

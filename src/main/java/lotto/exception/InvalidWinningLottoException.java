package lotto.exception;

public class InvalidWinningLottoException extends RuntimeException{
	public InvalidWinningLottoException() {
	}

	public InvalidWinningLottoException(String message) {
		super(message);
	}
}

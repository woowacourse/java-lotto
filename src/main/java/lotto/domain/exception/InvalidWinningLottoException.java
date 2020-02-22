package lotto.domain.exception;

public class InvalidWinningLottoException extends RuntimeException {
	private static final String INVALID_WINNING_LOTTO_MESSAGE = "로또 번호와 보너스 번호가 겹치면 안됩니다.";

	public InvalidWinningLottoException() {
		super(INVALID_WINNING_LOTTO_MESSAGE);
	}

	public InvalidWinningLottoException(String message) {
		super(message);
	}
}

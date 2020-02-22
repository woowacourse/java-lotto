package lotto.domain.exception;

public class InvalidLottoNumberException extends RuntimeException {
	private static final String INVALID_LOTTO_NUMBER_MESSAGE = "로또 번호는 1 ~ 45 사이의 수만 가능합니다";

	public InvalidLottoNumberException() {
		super(INVALID_LOTTO_NUMBER_MESSAGE);
	}

	public InvalidLottoNumberException(String message) {
		super(message);
	}
}

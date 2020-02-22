package lotto.domain.exception;

public class InvalidLottosException extends RuntimeException {
	private static final String INVALID_LOTTOS_MESSAGE = "하나 이상의 로또가 필요합니다.";

	public InvalidLottosException() {
		super(INVALID_LOTTOS_MESSAGE);
	}

	public InvalidLottosException(String message) {
		super(message);
	}
}

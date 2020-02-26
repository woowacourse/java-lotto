package lotto.exception;

public class InvalidLottoCountException extends RuntimeException {

	private static final String ERROR_MESSAGE_NOT_AVAILABLE = "구매할 수 없는 로또 수 입니다.";

	public InvalidLottoCountException() {
		super(ERROR_MESSAGE_NOT_AVAILABLE);
	}
}

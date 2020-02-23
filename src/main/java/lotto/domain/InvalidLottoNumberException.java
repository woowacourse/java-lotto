package lotto.domain;

/**
 * 로또 번호와 관련된 커스텀 예외
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class InvalidLottoNumberException extends RuntimeException {
	public InvalidLottoNumberException() {
		super();
	}

	public InvalidLottoNumberException(final String message) {
		super(message);
	}
}

package lotto.domain.lotto;

/**
 * 로또와 관련된 커스텀 예외
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/22
 */
public class InvalidLottoException extends RuntimeException {
	public InvalidLottoException() {
		super();
	}

	public InvalidLottoException(String message) {
		super(message);
	}
}

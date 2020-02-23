package lotto.domain;

/**
 * 클래스 이름 : WrongLottoNumberException.java
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class WrongLottoNumberException extends IllegalArgumentException {
	public WrongLottoNumberException() {
		super();
	}

	public WrongLottoNumberException(final String message) {
		super(message);
	}
}

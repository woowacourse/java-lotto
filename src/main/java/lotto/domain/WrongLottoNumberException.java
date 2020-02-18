package lotto.domain;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class WrongLottoNumberException extends Exception {
	public WrongLottoNumberException() {
		super();
	}

	public WrongLottoNumberException(String message) {
		super(message);
	}
}

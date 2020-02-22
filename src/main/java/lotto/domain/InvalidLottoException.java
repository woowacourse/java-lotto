package lotto.domain;

/**
 * 클래스 이름 : .java
 *
 * @author
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

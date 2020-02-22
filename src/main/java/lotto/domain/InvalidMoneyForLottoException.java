package lotto.domain;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/22
 */
public class InvalidMoneyForLottoException extends RuntimeException {
	public InvalidMoneyForLottoException() {
		super();
	}

	public InvalidMoneyForLottoException(String message) {
		super(message);
	}
}

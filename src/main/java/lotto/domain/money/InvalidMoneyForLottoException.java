package lotto.domain.money;

/**
 * 로또를 위한 돈과 관련된 커스텀 예외
 *
 * @author 토니, 히히
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

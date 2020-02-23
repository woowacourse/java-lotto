package lotto.domain;

/**
 * Lottos 일급 컬랙션과 관련된 커스텀 예외
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/22
 */
public class InvalidLottosException extends RuntimeException {
	public InvalidLottosException() {
		super();
	}

	public InvalidLottosException(String message) {
		super(message);
	}
}

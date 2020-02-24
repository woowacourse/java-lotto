package lotto.domain.result;

public class InvalidWinningLottoException extends IllegalArgumentException {
	public static final String DUPLICATION = "당첨 번호와 보너스 번호에 중복이 존재합니다.\n";

	public InvalidWinningLottoException(String message) {
		super(message);
	}
}

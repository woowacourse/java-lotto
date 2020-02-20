package lotto.domain;

public class InvalidWinningLottoException extends IllegalArgumentException {
	public static final String DUPLICATED_BONUS_NUMBER = "당첨번호와 보너스번호가 중복되었습니다.";

	public InvalidWinningLottoException(String s) {
		super(s);
	}
}

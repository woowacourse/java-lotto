package lotto.domain;

public class InvalidWinningLottoException extends IllegalArgumentException{
	public static final String DUPLICATION = "로또 번호와 보너스 번호는 중복이 될 수 없습니다.";

	InvalidWinningLottoException(String s) {
		super(s);
	}
}

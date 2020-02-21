package lotto.domain;

class WinningLottoException extends IllegalArgumentException {
	public static final String DUPLICATE_NUMBER = "보너스번호가 중복되었습니다.";

	WinningLottoException(String s) {
		super(s);
	}
}

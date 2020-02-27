package lotto.domain;

class InvalidWinningLottoException extends IllegalArgumentException {
	public static final String DUPLICATE_NUMBER = "보너스번호가 중복되었습니다.";
	public static final String NULL_AND_EMPTY_STRING = "null이나 빈 문자열은 올 수 없습니다.";
	public static final String NOT_INTEGER = "로또 숫자는 정수만 입력해주세요.";
	public static final String INVALID_COUNT = "6개의 숫자를 입력해주세요.";

	InvalidWinningLottoException(String s) {
		super(s);
	}
}

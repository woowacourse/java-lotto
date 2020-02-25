package lotto.domain;

class InvalidLottosException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "null 또는 빈 리스트는 입력할 수 없습니다.";

	InvalidLottosException(String s) {
		super(s);
	}
}

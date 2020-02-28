package lotto.domain.number;

class InvalidManualNumberLineException extends IllegalArgumentException {
	static final String NOT_INTEGER = "정수를 입력해주세요.";
	static final String NULL = "null은 입력할 수 없습니다.";
	static final String WRONG_SIZE = "로또 번호의 개수가 올바르지 않습니다.";

	InvalidManualNumberLineException(String s) {
		super(s);
	}
}

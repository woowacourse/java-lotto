package lotto.domain;

public class InvalidLottoMoneyException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "null 또는 빈 문자열은 입력할 수 없습니다.";
	public static final String NOT_INTEGER = "정수를 입력해주세요.";
	public static final String NOT_POSITIVE = "0보다 큰 정수를 입력해주세요.";
	public static final String INVALID_UNIT = "1,000원 단위의 정수를 입력해주세요.";
	public static final String OUT_OF_BOUND = "100,000원 이하의 정수를 입력해주세요.";

	public InvalidLottoMoneyException(String s) {
		super(s);
	}
}

package lotto.domain.exception;

public class InvalidLottoNumberException extends IllegalArgumentException {
	public static final String OUT_OF_BOUND = "1이상 45이하의 정수를 입력해주세요.";
	public static final String NOT_INTEGER = "정수를 입력해주세요.";

	public InvalidLottoNumberException(String s) {
		super(s);
	}
}

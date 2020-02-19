package lotto.domain;

public class InvalidNumberException extends IllegalArgumentException {
	public static final String OUT_OF_BOUND = "1이상 45이하의 정수를 입력해주세요.";

	public InvalidNumberException(String s) {
		super(s);
	}
}

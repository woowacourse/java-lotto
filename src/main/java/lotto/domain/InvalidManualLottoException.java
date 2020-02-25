package lotto.domain;

public class InvalidManualLottoException extends IllegalArgumentException {
	public static final String NOT_INTEGER = "정수를 입력해주세요.";
	public static final String NOT_POSITIVE = "0보다 큰 정수를 입력해주세요.";
	public static final String OUT_OF_BOUND = "구입한 로또 수보다 작은 수를 입력해주세요.";

	InvalidManualLottoException(String s) {
		super(s);
	}
}

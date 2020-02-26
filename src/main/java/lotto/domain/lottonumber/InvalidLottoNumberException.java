package lotto.domain.lottonumber;

public class InvalidLottoNumberException extends IllegalArgumentException {
	static final String OUT_OF_BOUND = "1이상 45이하의 정수를 입력해주세요.";
	static final String NOT_INTEGER = "정수를 입력해주세요.";

	InvalidLottoNumberException(String s) {
		super(s);
	}
}

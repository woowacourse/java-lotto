package lotto.exceptions;

public class LottoNumberIllegalException extends IllegalArgumentException {
	private static String MESSAGE = "는 적절하지 않은 로또 번호입니다.\n" +
			" - 1이상 45이하의 정수를 입력해주세요.";

	public LottoNumberIllegalException(int illegalLottoNumber) {
		super(illegalLottoNumber + MESSAGE);
	}
}

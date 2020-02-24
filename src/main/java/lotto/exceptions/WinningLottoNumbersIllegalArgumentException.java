package lotto.exceptions;

public class WinningLottoNumbersIllegalArgumentException extends IllegalArgumentException {
	private static final String MESSAGE = "보너스 번호가 당첨 번호에 포함되지 않아야 합니다.";

	public WinningLottoNumbersIllegalArgumentException() {
		super(MESSAGE);
	}
}

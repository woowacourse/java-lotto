package lotto.exceptions;

public class DuplicateWinningNumberException extends IllegalArgumentException {
	private static String MESSAGE = "당첨 번호와 보너스 번호가 중복됩니다. 다시 입력해주세요.";

	public DuplicateWinningNumberException() {
		super(MESSAGE);
	}
}

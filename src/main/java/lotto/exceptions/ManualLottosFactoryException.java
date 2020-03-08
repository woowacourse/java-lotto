package lotto.exceptions;

public class ManualLottosFactoryException extends IllegalArgumentException {
	public static final String MESSAGE
			= "수동 로또 입력 개수와 금액이 맞지 않습니다.";

	public ManualLottosFactoryException() {
		super(MESSAGE);
	}
}

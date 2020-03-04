package lotto.exceptions;

public class MoneyIllegalException extends IllegalArgumentException {
	private static String MESSAGE = "는 적절하지 않은 금액입니다.\n" +
			" - 0 이상의 1000의 배수로 입력해주세요.";

	public MoneyIllegalException(int illegalMoney) {
		super(illegalMoney + MESSAGE);
	}
}

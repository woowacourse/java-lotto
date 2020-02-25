package lotto.validator;

import lotto.domain.Money;

public class InputValidator {
	private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";
	private static final String ERROR_MESSAGE_OVER_COUNT = "구입 가능한 수보다 큰 수를 입력하였습니다.";

	public static void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
		}
	}

	public static void validateManualCount(int manualCount, Money money) {
		if (manualCount > money.divideThousand()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_OVER_COUNT);
		}
	}
}

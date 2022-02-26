package utils;

import rule.LottoRule;

public class InputValidateUtils {
	private static final String REGEX_NUMBER = "[0-9]+";

	public static void inputBlank(String number, String message) {
		if (number == null || number.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void inputNumber(String number, String message) {
		if (!number.matches(REGEX_NUMBER)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void inputOutOfRange(String number, String message) {
		if (Integer.parseInt(number) < LottoRule.LOTTO_MIN_NUMBER
			|| Integer.parseInt(number) > LottoRule.LOTTO_MAX_NUMBER) {
			throw new IllegalArgumentException(message);
		}
	}
}

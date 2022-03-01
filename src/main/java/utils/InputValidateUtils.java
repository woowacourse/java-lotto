package utils;

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
}

package utils;

public class InputValidateUtils {
	private static final String REGEX_NUMBER = "[0-9]+";
	public static final String INPUT_NEGATIVE_ERROR_MESSAGE = "[Error]: 양수를 입력하세요.";

	public static void checkInputIsBlankAndNumber(String number, String blankMessage, String numberMessage) {
		checkInputIsBlank(number, blankMessage);
		checkInputIsNumber(number, numberMessage);
	}

	public static void checkInputIsBlank(String number, String message) {
		if (number == null || number.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}

	private static void checkInputIsNumber(String number, String message) {
		if (!number.matches(REGEX_NUMBER)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void checkInputIsNegative(int num) {
		if (num < 0) {
			throw new IllegalArgumentException(INPUT_NEGATIVE_ERROR_MESSAGE);
		}
	}
}

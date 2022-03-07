package utils;

public class InputValidateUtils {
	private static final String BLANK_ERROR_MESSAGE = "[Error]: 빈칸은 입력할 수 없습니다.";
	public static final String INPUT_NEGATIVE_ERROR_MESSAGE = "[Error]: 양수를 입력하세요.";

	public static void checkInputIsBlank(String number) {
		if (number == null || number.isBlank()) {
			throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
		}
	}

	public static void checkInputIsNegative(int num) {
		if (num < 0) {
			throw new IllegalArgumentException(INPUT_NEGATIVE_ERROR_MESSAGE);
		}
	}
}

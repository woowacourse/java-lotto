package lotto.validator;

public class InputValidator {
	public static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

	public static void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
		}
	}
}
